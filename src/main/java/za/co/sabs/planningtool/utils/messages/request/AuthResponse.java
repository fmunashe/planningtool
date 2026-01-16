package za.co.sabs.planningtool.utils.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Duration expiresAt;
}
