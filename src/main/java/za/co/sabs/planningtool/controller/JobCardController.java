package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.JobCardDto;
import za.co.sabs.planningtool.processor.JobCardProcessor;
import za.co.sabs.planningtool.utils.messages.request.JobCardRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/job-cards")
@CrossOrigin(origins = "*")
public class JobCardController implements BaseController<JobCardDto, JobCardRequest> {
    private final JobCardProcessor processor;

    public JobCardController(JobCardProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<JobCardDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<JobCardDto> jobCards = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(jobCards);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<JobCardDto>> create(JobCardRequest payload) {
        ApiResponse<JobCardDto> jobCard = processor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCard);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<JobCardDto>> update(Long id, JobCardDto payload) {
        ApiResponse<JobCardDto> jobCard = processor.update(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(jobCard);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<JobCardDto>> findById(Long id) {
        ApiResponse<JobCardDto> jobCard = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(jobCard);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<JobCardDto>> deleteById(Long id) {
        ApiResponse<JobCardDto> jobCard = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(jobCard);
    }
}
