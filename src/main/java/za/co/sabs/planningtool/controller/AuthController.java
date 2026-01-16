package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.processor.AuthenticationProcessor;
import za.co.sabs.planningtool.utils.messages.request.AuthRequest;
import za.co.sabs.planningtool.utils.messages.request.AuthResponse;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthenticationProcessor authenticationProcessor;

    public AuthController(AuthenticationProcessor authenticationProcessor) {
        this.authenticationProcessor = authenticationProcessor;
    }

    @PostMapping("/login")
    public ResponseEntity<@NonNull ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
        ApiResponse<AuthResponse> response = authenticationProcessor.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
