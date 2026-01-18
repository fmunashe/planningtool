package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.RoleDto;
import za.co.sabs.planningtool.entity.Role;
import za.co.sabs.planningtool.exceptions.RecordExistsException;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.RoleMapper;
import za.co.sabs.planningtool.processor.RoleProcessor;
import za.co.sabs.planningtool.service.RoleService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.RoleRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class RoleProcessorImpl implements RoleProcessor {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public RoleProcessorImpl(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @Override
    public ApiResponse<RoleDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Role> roles = roleService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(roles, roleMapper, true, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<RoleDto> findById(Long id) {
        Optional<Role> optionalRole = roleService.findById(id);
        if (optionalRole.isEmpty()) {
            throw new RecordNotFoundException("Role not found");
        }
        return HelperResponse.buildApiResponse(null, roleMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, roleMapper.apply(optionalRole.get()));
    }

    @Override
    public ApiResponse<RoleDto> save(RoleRequest roleRequest) {
        Optional<Role> optionalRole = roleService.findByName(roleRequest.getRoleName());
        if (optionalRole.isPresent()) {
            throw new RecordExistsException("Role with name " + roleRequest.getRoleName() + " already exists");
        }
        Role role = roleService.save(new Role(roleRequest.getRoleName()));
        return HelperResponse.buildApiResponse(null, roleMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, roleMapper.apply(role));
    }

    @Override
    public ApiResponse<RoleDto> update(Long id, RoleDto roleDto) {
        Optional<Role> optionalRole = roleService.findById(id);
        if (optionalRole.isEmpty() || optionalRole.get().getId() != id) {
            throw new RecordNotFoundException("Role id mismatch");
        }
        Role role = optionalRole.get();
        role.setName(roleDto.name());
        role = roleService.save(role);
        return HelperResponse.buildApiResponse(null, roleMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, roleMapper.apply(role));
    }

    @Override
    public ApiResponse<RoleDto> deleteById(Long id) {
        Optional<Role> optionalRole = roleService.findById(id);
        if (optionalRole.isEmpty()) {
            throw new RecordNotFoundException("Role not found");
        }
        roleService.deleteById(id);
        return HelperResponse.buildApiResponse(null, roleMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }
}
