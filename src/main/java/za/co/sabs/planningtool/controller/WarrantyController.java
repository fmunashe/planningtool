package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.WarrantyDto;
import za.co.sabs.planningtool.processor.WarrantyProcessor;
import za.co.sabs.planningtool.utils.messages.request.WarrantyRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/warrants")
@CrossOrigin(origins = "*")
public class WarrantyController implements BaseController<WarrantyDto, WarrantyRequest> {
    private final WarrantyProcessor processor;

    public WarrantyController(WarrantyProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<WarrantyDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<WarrantyDto> warrants = processor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(warrants);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<WarrantyDto>> create(WarrantyRequest payload) {
        ApiResponse<WarrantyDto> warranty = processor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(warranty);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<WarrantyDto>> update(Long id, WarrantyDto payload) {
        ApiResponse<WarrantyDto> warranty = processor.update(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(warranty);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<WarrantyDto>> findById(Long id) {
        ApiResponse<WarrantyDto> warranty = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(warranty);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<WarrantyDto>> deleteById(Long id) {
        ApiResponse<WarrantyDto> warranty = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(warranty);
    }
}
