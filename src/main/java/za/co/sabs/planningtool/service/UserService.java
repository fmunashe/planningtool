package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.User;

import java.util.Optional;

public interface UserService extends BaseService<User> {
    Optional<User> findByUsername(String username);
}
