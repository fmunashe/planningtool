package za.co.sabs.planningtool.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.sabs.planningtool.common.BaseController;
import za.co.sabs.planningtool.dto.EquipmentDto;
import za.co.sabs.planningtool.processor.EquipmentProcessor;
import za.co.sabs.planningtool.utils.messages.request.EquipmentRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/equipments")
@CrossOrigin(origins = "*")
public class EquipmentController implements BaseController<EquipmentDto, EquipmentRequest> {
    private final EquipmentProcessor processor;

    public EquipmentController(EquipmentProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    @Override
    public ResponseEntity<@NonNull ApiResponse<EquipmentDto>> getAll(int pageNo, int pageSize) {
        ApiResponse<EquipmentDto> equipments = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(equipments);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<@NonNull ApiResponse<EquipmentDto>> create(EquipmentRequest payload) {
        ApiResponse<EquipmentDto> equipment = processor.save(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipment);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<EquipmentDto>> update(Long id, EquipmentDto payload) {
        ApiResponse<EquipmentDto> equipment = processor.update(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(equipment);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<EquipmentDto>> findById(Long id) {
        ApiResponse<EquipmentDto> equipment = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipment);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<@NonNull ApiResponse<EquipmentDto>> deleteById(Long id) {
        ApiResponse<EquipmentDto> equipment = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipment);
    }
}
