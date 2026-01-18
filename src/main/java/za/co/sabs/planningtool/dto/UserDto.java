package za.co.sabs.planningtool.dto;

import java.time.LocalDateTime;
import java.util.Set;

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
        Set<RoleDto> roles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
