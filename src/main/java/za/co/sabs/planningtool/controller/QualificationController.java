package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.QualificationDto;
import za.co.sabs.planningtool.processor.QualificationsProcessor;
import za.co.sabs.planningtool.utils.messages.request.QualificationRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/qualifications")
@CrossOrigin(origins = "*")
public class QualificationController implements BaseController<QualificationDto, QualificationRequest> {
    private final QualificationsProcessor processor;

    public QualificationController(QualificationsProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<QualificationDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<QualificationDto> qualifications = processor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(qualifications);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<QualificationDto>> create(QualificationRequest payload) {
        ApiResponse<QualificationDto> qualification = processor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(qualification);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<QualificationDto>> update(Long id, QualificationDto payload) {
        ApiResponse<QualificationDto> qualification = processor.update(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(qualification);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<QualificationDto>> findById(Long id) {
        ApiResponse<QualificationDto> qualification = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(qualification);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<QualificationDto>> deleteById(Long id) {
        ApiResponse<QualificationDto> qualification = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(qualification);
    }
}
