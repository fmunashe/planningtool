package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.TaskStatusDto;
import za.co.sabs.planningtool.processor.TaskStatusProcessor;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.TaskStatusRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/taskStatus")
@CrossOrigin(origins = "*")
public class TaskStatusController implements BaseController<TaskStatusDto, TaskStatusRequest> {
    private final TaskStatusProcessor processor;

    public TaskStatusController(TaskStatusProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<TaskStatusDto>> getAll(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<TaskStatusDto> taskStatusDtos = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(taskStatusDtos);
    }


    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<TaskStatusDto>> create(TaskStatusRequest taskStatusRequest) {
        ApiResponse<TaskStatusDto> taskStatusDto = processor.save(taskStatusRequest);
        return ResponseEntity.status(HttpStatus.OK).body(taskStatusDto);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<TaskStatusDto>> update(Long id, TaskStatusDto taskStatusDto) {
        ApiResponse<TaskStatusDto> recordDto = processor.update(id, taskStatusDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<TaskStatusDto>> findById(Long id) {
        ApiResponse<TaskStatusDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<TaskStatusDto>> deleteById(Long id) {
        ApiResponse<TaskStatusDto> recordDto = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
