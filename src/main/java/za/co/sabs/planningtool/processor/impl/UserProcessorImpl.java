package za.co.sabs.planningtool.processor.impl;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.UserDto;
import za.co.sabs.planningtool.entity.Role;
import za.co.sabs.planningtool.entity.User;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.UserMapper;
import za.co.sabs.planningtool.processor.UserProcessor;
import za.co.sabs.planningtool.service.RoleService;
import za.co.sabs.planningtool.service.UserService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.UserRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserProcessorImpl implements UserProcessor {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    public UserProcessorImpl(UserService userService, UserMapper userMapper, RoleService roleService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public ApiResponse<UserDto> assignRoles(UserRequest userRequest) {
        Optional<User> optionalUser = userService.findByUsername(userRequest.getUsername());
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found with username: " + userRequest.getUsername());
        }
        User user = optionalUser.get();
        Set<Role> rolesToAdd = userRequest.getRoles().stream()
                .map(roleName -> roleService.findByName(roleName)
                        .orElseGet(() -> roleService.save(new Role(roleName)))
                )
                .collect(Collectors.toSet());
        user.getRoles().addAll(rolesToAdd);
        user = userService.save(user);
        return HelperResponse.buildApiResponse(null, userMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, userMapper.apply(user));
    }

    @Override
    public ApiResponse<UserDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull User> users = userService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(users, userMapper, true, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<UserDto> findById(Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found with id: " + id);
        }

        return HelperResponse.buildApiResponse(null, userMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, userMapper.apply(optionalUser.get()));
    }

    @Override
    public ApiResponse<UserDto> save(UserRequest userRequest) {
        return null;
    }

    @Override
    public ApiResponse<UserDto> update(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public ApiResponse<UserDto> deleteById(Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found with id: " + id);
        }
        userService.deleteById(id);
        return HelperResponse.buildApiResponse(null, userMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, userMapper.apply(optionalUser.get()));
    }
}
