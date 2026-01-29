package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.MaintenanceCycleDto;
import za.co.sabs.planningtool.processor.MaintenanceCycleProcessor;
import za.co.sabs.planningtool.utils.messages.request.MaintenanceCycleRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/maintenance-cycle")
@CrossOrigin(origins = "*")
public class MaintenanceCycleController implements BaseController<MaintenanceCycleDto, MaintenanceCycleRequest> {
    private final MaintenanceCycleProcessor processor;

    public MaintenanceCycleController(MaintenanceCycleProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<MaintenanceCycleDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<MaintenanceCycleDto> cycles = processor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(cycles);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<MaintenanceCycleDto>> create(MaintenanceCycleRequest payload) {
        ApiResponse<MaintenanceCycleDto> cycle = processor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(cycle);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<MaintenanceCycleDto>> update(Long id, MaintenanceCycleDto payload) {
        ApiResponse<MaintenanceCycleDto> cycle = processor.update(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(cycle);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<MaintenanceCycleDto>> findById(Long id) {
        ApiResponse<MaintenanceCycleDto> cycle = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cycle);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<MaintenanceCycleDto>> deleteById(Long id) {
        ApiResponse<MaintenanceCycleDto> cycle = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cycle);
    }
}
