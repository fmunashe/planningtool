package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.RoleDto;
import za.co.sabs.planningtool.dto.UserDto;
import za.co.sabs.planningtool.entity.Role;
import za.co.sabs.planningtool.entity.User;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserMapper implements Function<User, UserDto> {
    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                mapRoles(user.getRoles()),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    private Set<RoleDto> mapRoles(Set<Role> roles) {
        return roles.stream().map(roleMapper).collect(Collectors.toSet());
    }
}
