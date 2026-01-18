package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.UserDto;
import za.co.sabs.planningtool.processor.UserProcessor;
import za.co.sabs.planningtool.utils.messages.request.UserRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController implements BaseController<UserDto, UserRequest> {
    private final UserProcessor userProcessor;

    public UserController(UserProcessor userProcessor) {
        this.userProcessor = userProcessor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<UserDto>> getAll(int pageNo, int pageSize
    ) {
        ApiResponse<UserDto> users = userProcessor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(users);
    }


    @PutMapping("/assignRoles")
    public ResponseEntity<@NonNull ApiResponse<UserDto>> assignRoles(UserRequest userRequest) {
        ApiResponse<UserDto> user = userProcessor.assignRoles(userRequest);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<@NonNull ApiResponse<UserDto>> create(UserRequest payload) {
        return null;
    }

    @Override
    public ResponseEntity<@NonNull ApiResponse<UserDto>> update(Long id, UserDto payload) {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<UserDto>> findById(Long id) {
        ApiResponse<UserDto> user = userProcessor.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<UserDto>> deleteById(Long id) {
        ApiResponse<UserDto> user = userProcessor.deleteById(id);
        return ResponseEntity.ok(user);
    }
}
