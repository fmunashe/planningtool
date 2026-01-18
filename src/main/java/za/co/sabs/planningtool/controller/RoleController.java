package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.RoleDto;
import za.co.sabs.planningtool.processor.RoleProcessor;
import za.co.sabs.planningtool.utils.messages.request.RoleRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RoleController implements BaseController<RoleDto, RoleRequest> {
    private final RoleProcessor roleProcessor;

    public RoleController(RoleProcessor roleProcessor) {
        this.roleProcessor = roleProcessor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<RoleDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<RoleDto> roles = roleProcessor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<RoleDto>> create(RoleRequest payload) {
        ApiResponse<RoleDto> role = roleProcessor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<RoleDto>> update(Long id, RoleDto payload) {
        ApiResponse<RoleDto> role = roleProcessor.update(id, payload);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<RoleDto>> findById(Long id) {
        ApiResponse<RoleDto> role = roleProcessor.findById(id);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<RoleDto>> deleteById(Long id) {
        ApiResponse<RoleDto> role = roleProcessor.deleteById(id);
        return ResponseEntity.ok(role);
    }
}
