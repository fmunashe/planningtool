package za.co.sabs.planningtool.dto;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        boolean enabled,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
