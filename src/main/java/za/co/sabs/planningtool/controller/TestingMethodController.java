package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.TestingMethodDto;
import za.co.sabs.planningtool.processor.TestMethodProcessor;
import za.co.sabs.planningtool.utils.messages.request.TestMethodRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/testing-methods")
@CrossOrigin(origins = "*")
public class TestingMethodController implements BaseController<TestingMethodDto, TestMethodRequest> {
    private final TestMethodProcessor processor;

    public TestingMethodController(TestMethodProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<TestingMethodDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<TestingMethodDto> methods = processor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(methods);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<TestingMethodDto>> create(TestMethodRequest payload) {
        ApiResponse<TestingMethodDto> method = processor.save(payload);
        return ResponseEntity.ok(method);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<TestingMethodDto>> update(Long id, TestingMethodDto payload) {
        ApiResponse<TestingMethodDto> method = processor.update(id, payload);
        return ResponseEntity.ok(method);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<TestingMethodDto>> findById(Long id) {
        ApiResponse<TestingMethodDto> method = processor.findById(id);
        return ResponseEntity.ok(method);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<TestingMethodDto>> deleteById(Long id) {
        ApiResponse<TestingMethodDto> method = processor.deleteById(id);
        return ResponseEntity.ok(method);
    }
}
