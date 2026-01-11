package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.CustomerDto;
import za.co.sabs.planningtool.processor.CustomerProcessor;
import za.co.sabs.planningtool.utils.messages.request.CustomerRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController implements BaseController<CustomerDto, CustomerRequest> {

    private final CustomerProcessor processor;

    public CustomerController(CustomerProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<CustomerDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<CustomerDto> response = processor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<CustomerDto>> create(CustomerRequest payload) {
        ApiResponse<CustomerDto> response = processor.save(payload);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<CustomerDto>> update(Long id, CustomerDto payload) {
        ApiResponse<CustomerDto> response = processor.update(id, payload);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<CustomerDto>> findById(Long id) {
        ApiResponse<CustomerDto> response = processor.findById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<CustomerDto>> deleteById(Long id) {
        ApiResponse<CustomerDto> response = processor.deleteById(id);
        return ResponseEntity.ok(response);
    }


}
