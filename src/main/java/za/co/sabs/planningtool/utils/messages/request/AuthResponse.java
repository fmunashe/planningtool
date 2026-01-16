package za.co.sabs.planningtool.utils.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private LocalDateTime expiresAt;
    private Long minutesToExpire;
}
