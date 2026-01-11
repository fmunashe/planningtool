package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.PriorityDto;
import za.co.sabs.planningtool.processor.PriorityProcessor;
import za.co.sabs.planningtool.utils.messages.request.PriorityRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/priority")
@CrossOrigin(origins = "*")
public class PriorityController implements BaseController<PriorityDto, PriorityRequest> {

    private final PriorityProcessor processor;

    public PriorityController(PriorityProcessor processor) {
        this.processor = processor;
    }


    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<PriorityDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<PriorityDto> priorityDtos = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(priorityDtos);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<PriorityDto>> create(PriorityRequest payload) {
        ApiResponse<PriorityDto> priorityDto = processor.save(payload);
        return ResponseEntity.status(HttpStatus.OK).body(priorityDto);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<PriorityDto>> update(Long id, PriorityDto payload) {
        ApiResponse<PriorityDto> recordDto = processor.update(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<PriorityDto>> findById(Long id) {
        ApiResponse<PriorityDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<PriorityDto>> deleteById(Long id) {
        ApiResponse<PriorityDto> recordDto = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
