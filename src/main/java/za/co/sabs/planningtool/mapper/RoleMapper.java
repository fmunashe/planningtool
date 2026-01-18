package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.RoleDto;
import za.co.sabs.planningtool.entity.Role;

import java.util.function.Function;

@Service
public class RoleMapper implements Function<Role, RoleDto> {
    @Override
    public RoleDto apply(Role role) {
        return new RoleDto(role.getId(), role.getName(), role.getCreatedAt(), role.getUpdatedAt());
    }
}
