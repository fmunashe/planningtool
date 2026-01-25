package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.ClaimDto;
import za.co.sabs.planningtool.processor.ClaimProcessor;
import za.co.sabs.planningtool.utils.messages.request.ClaimRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/claims")
@CrossOrigin(origins = "*")
public class ClaimController implements BaseController<ClaimDto, ClaimRequest> {
    private final ClaimProcessor claimProcessor;

    public ClaimController(ClaimProcessor claimProcessor) {
        this.claimProcessor = claimProcessor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<ClaimDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<ClaimDto> claims = claimProcessor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(claims);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ClaimDto>> create(ClaimRequest payload) {
        ApiResponse<ClaimDto> claim = claimProcessor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(claim);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ClaimDto>> update(Long id, ClaimDto payload) {
        ApiResponse<ClaimDto> claim = claimProcessor.update(id, payload);
        return ResponseEntity.ok(claim);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ClaimDto>> findById(Long id) {
        ApiResponse<ClaimDto> claim = claimProcessor.findById(id);
        return ResponseEntity.ok(claim);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ClaimDto>> deleteById(Long id) {
        ApiResponse<ClaimDto> claim = claimProcessor.deleteById(id);
        return ResponseEntity.ok(claim);
    }
}
