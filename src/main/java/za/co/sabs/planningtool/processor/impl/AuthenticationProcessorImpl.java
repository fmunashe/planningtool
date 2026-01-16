package za.co.sabs.planningtool.processor.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.processor.AuthenticationProcessor;
import za.co.sabs.planningtool.service.security.JwtTokenProvider;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.AuthRequest;
import za.co.sabs.planningtool.utils.messages.request.AuthResponse;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationProcessorImpl implements AuthenticationProcessor {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    public AuthenticationProcessorImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    public ApiResponse<AuthResponse> authenticate(AuthRequest authRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        var principal = auth.getPrincipal();
        String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
        var authorities = auth.getAuthorities();
        String token = jwtTokenProvider.generateToken(username, authorities);

        Optional<Long> remainingMinutes= extractExpiryInMinutesFromJwt(token);

        AuthResponse response =  AuthResponse.builder()
                .token(token)
                .minutesToExpire(remainingMinutes.get())
                .expiresAt(LocalDateTime.now().plusMinutes(remainingMinutes.get()))
                .build();

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, response);
    }

    private Optional<Long> extractExpiryInMinutesFromJwt(String jwt) {
        try {
            String[] parts = jwt.split("\\.");
            if (parts.length < 2) return Optional.empty();
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            Map<String, Object> claims = objectMapper.readValue(payloadJson, Map.class);
            Object expObj = claims.get("exp");
            if (expObj == null) return Optional.empty();

            long expSeconds;
            if (expObj instanceof Number) {
                expSeconds = ((Number) expObj).longValue();
            } else {
                expSeconds = Long.parseLong(expObj.toString());
            }

            long currentSeconds = Instant.now().getEpochSecond();
            long remainingSeconds = expSeconds - currentSeconds;
            long remainingMinutes = Math.max(0, remainingSeconds / 60); // Convert to minutes and ensure non-negative

            return Optional.of(remainingMinutes);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
