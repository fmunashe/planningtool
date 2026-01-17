package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.dto.UserDto;
import za.co.sabs.planningtool.processor.UserProcessor;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserProcessor userProcessor;

    public UserController(UserProcessor userProcessor) {
        this.userProcessor = userProcessor;
    }

    @GetMapping
    public ResponseEntity<@NonNull ApiResponse<UserDto>> findAll(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<UserDto> users = userProcessor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(users);
    }
}
