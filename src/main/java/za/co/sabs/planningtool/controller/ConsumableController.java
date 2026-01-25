package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.ConsumableDto;
import za.co.sabs.planningtool.processor.ConsumableProcessor;
import za.co.sabs.planningtool.utils.messages.request.ConsumableRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/consumables")
@CrossOrigin(origins = "*")
public class ConsumableController implements BaseController<ConsumableDto, ConsumableRequest> {
    private final ConsumableProcessor consumableProcessor;

    public ConsumableController(ConsumableProcessor consumableProcessor) {
        this.consumableProcessor = consumableProcessor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<ConsumableDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<ConsumableDto> consumables = consumableProcessor.findAll(pageNo, pageSize);
        return ResponseEntity.ok(consumables);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ConsumableDto>> create(ConsumableRequest payload) {
        ApiResponse<ConsumableDto> consumable = consumableProcessor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(consumable);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ConsumableDto>> update(Long id, ConsumableDto payload) {
        ApiResponse<ConsumableDto> consumable = consumableProcessor.update(id, payload);
        return ResponseEntity.ok(consumable);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ConsumableDto>> findById(Long id) {
        ApiResponse<ConsumableDto> consumable = consumableProcessor.findById(id);
        return ResponseEntity.ok(consumable);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<ConsumableDto>> deleteById(Long id) {
        ApiResponse<ConsumableDto> consumable = consumableProcessor.deleteById(id);
        return ResponseEntity.ok(consumable);
    }
}
