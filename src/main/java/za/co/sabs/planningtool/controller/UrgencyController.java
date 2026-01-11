package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.UrgencyDto;
import za.co.sabs.planningtool.processor.UrgencyProcessor;
import za.co.sabs.planningtool.utils.messages.request.UrgencyRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/urgency")
@CrossOrigin(origins = "*")
public class UrgencyController implements BaseController<UrgencyDto, UrgencyRequest> {
    private final UrgencyProcessor processor;

    public UrgencyController(UrgencyProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<UrgencyDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<UrgencyDto> urgencyDtos = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(urgencyDtos);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<UrgencyDto>> create(UrgencyRequest payload) {
        ApiResponse<UrgencyDto> urgencyDto = processor.save(payload);
        return ResponseEntity.status(HttpStatus.OK).body(urgencyDto);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<UrgencyDto>> update(Long id, UrgencyDto payload) {
        ApiResponse<UrgencyDto> recordDto = processor.update(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<UrgencyDto>> findById(Long id) {
        ApiResponse<UrgencyDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<UrgencyDto>> deleteById(Long id) {
        ApiResponse<UrgencyDto> recordDto = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
