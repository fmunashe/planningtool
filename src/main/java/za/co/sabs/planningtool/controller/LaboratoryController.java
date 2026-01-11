package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.LaboratoryDto;
import za.co.sabs.planningtool.processor.LaboratoryProcessor;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.LabRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/labs")
@CrossOrigin(origins = "*")
public class LaboratoryController implements BaseController<LaboratoryDto, LabRequest> {
    private final LaboratoryProcessor processor;

    public LaboratoryController(LaboratoryProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<LaboratoryDto>> getAll(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<LaboratoryDto> labDtos = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(labDtos);
    }


    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<LaboratoryDto>> create(LabRequest labRequest) {
        ApiResponse<LaboratoryDto> labDto = processor.save(labRequest);
        return ResponseEntity.status(HttpStatus.OK).body(labDto);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<LaboratoryDto>> update(Long id, LaboratoryDto laboratoryDto) {
        ApiResponse<LaboratoryDto> recordDto = processor.update(id, laboratoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<LaboratoryDto>> findById(Long id) {
        ApiResponse<LaboratoryDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<LaboratoryDto>> deleteById(Long id) {
        ApiResponse<LaboratoryDto> recordDto = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
