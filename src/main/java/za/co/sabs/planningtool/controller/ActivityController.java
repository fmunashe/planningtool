package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.ActivityDto;
import za.co.sabs.planningtool.processor.ActivityProcessor;
import za.co.sabs.planningtool.utils.messages.request.ActivityRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/activity")
@CrossOrigin(origins = "*")
public class ActivityController implements BaseController<ActivityDto, ActivityRequest> {
    private final ActivityProcessor processor;

    public ActivityController(ActivityProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<ActivityDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<ActivityDto> activities = processor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ActivityDto>> create(ActivityRequest payload) {
        ApiResponse<ActivityDto> record = processor.save(payload);
        return ResponseEntity.ok(record);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ActivityDto>> update(Long id, ActivityDto payload) {
        ApiResponse<ActivityDto> record = processor.update(id, payload);
        return ResponseEntity.ok(record);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ActivityDto>> findById(Long id) {
        ApiResponse<ActivityDto> record = processor.findById(id);
        return ResponseEntity.ok(record);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ActivityDto>> deleteById(Long id) {
        ApiResponse<ActivityDto> record = processor.deleteById(id);
        return ResponseEntity.ok(record);
    }
}
