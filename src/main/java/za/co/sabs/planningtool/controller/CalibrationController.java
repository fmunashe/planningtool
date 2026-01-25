package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.CalibrationDto;
import za.co.sabs.planningtool.processor.CalibrationProcessor;
import za.co.sabs.planningtool.utils.messages.request.CalibrationRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/calibration")
@CrossOrigin(origins = "*")
public class CalibrationController implements BaseController<CalibrationDto, CalibrationRequest> {
    private final CalibrationProcessor calibrationProcessor;

    public CalibrationController(CalibrationProcessor calibrationProcessor) {
        this.calibrationProcessor = calibrationProcessor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<CalibrationDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<CalibrationDto> calibrations = calibrationProcessor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(calibrations);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<CalibrationDto>> create(CalibrationRequest payload) {
        ApiResponse<CalibrationDto> calibration = calibrationProcessor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(calibration);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<CalibrationDto>> update(Long id, CalibrationDto payload) {
        ApiResponse<CalibrationDto> calibration = calibrationProcessor.update(id, payload);
        return ResponseEntity.ok(calibration);
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<CalibrationDto>> findById(Long id) {
        ApiResponse<CalibrationDto> calibration = calibrationProcessor.findById(id);
        return ResponseEntity.ok(calibration);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<CalibrationDto>> deleteById(Long id) {
        ApiResponse<CalibrationDto> calibration = calibrationProcessor.deleteById(id);
        return ResponseEntity.ok(calibration);
    }
}
