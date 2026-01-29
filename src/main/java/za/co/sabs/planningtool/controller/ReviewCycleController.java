package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.ReviewCycleDto;
import za.co.sabs.planningtool.processor.ReviewCycleProcessor;
import za.co.sabs.planningtool.utils.messages.request.ReviewCycleRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/review-cycle")
@CrossOrigin(origins = "*")
public class ReviewCycleController implements BaseController<ReviewCycleDto, ReviewCycleRequest> {
    private final ReviewCycleProcessor processor;

    public ReviewCycleController(ReviewCycleProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<ReviewCycleDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<ReviewCycleDto> cycles = processor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(cycles);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ReviewCycleDto>> create(ReviewCycleRequest payload) {
        ApiResponse<ReviewCycleDto> cycle = processor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(cycle);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ReviewCycleDto>> update(Long id, ReviewCycleDto payload) {
        ApiResponse<ReviewCycleDto> cycle = processor.update(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(cycle);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ReviewCycleDto>> findById(Long id) {
        ApiResponse<ReviewCycleDto> cycle = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cycle);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ReviewCycleDto>> deleteById(Long id) {
        ApiResponse<ReviewCycleDto> cycle = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cycle);
    }
}
