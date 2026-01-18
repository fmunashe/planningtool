package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.Role;

import java.util.Optional;

public interface RoleService extends BaseService<Role> {
    Optional<Role> findByName(String name);
}
