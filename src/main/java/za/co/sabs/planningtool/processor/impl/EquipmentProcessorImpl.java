package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.EquipmentDto;
import za.co.sabs.planningtool.entity.Equipment;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.EquipmentMapper;
import za.co.sabs.planningtool.processor.EquipmentProcessor;
import za.co.sabs.planningtool.service.EquipmentService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.EquipmentRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

@Service
public class EquipmentProcessorImpl implements EquipmentProcessor {
    private final EquipmentService service;
    private final EquipmentMapper mapper;

    public EquipmentProcessorImpl(EquipmentService service, EquipmentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<EquipmentDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Equipment> equipments = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(equipments, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<EquipmentDto> findById(Long id) {
        Equipment equipment = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Equipment not found"));
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(equipment));
    }

    @Override
    public ApiResponse<EquipmentDto> save(EquipmentRequest equipmentRequest) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentNumber(equipmentRequest.getEquipmentNumber());
        equipment.setName(equipmentRequest.getName());
        equipment.setSerialNumber(equipmentRequest.getSerialNumber());
        equipment.setLocation(equipmentRequest.getLocation());
        equipment.setModelName(equipmentRequest.getModelName());
        equipment.setModelNumber(equipmentRequest.getModelNumber());
        equipment.setCost(equipmentRequest.getCost());
        equipment.setDescription(equipmentRequest.getDescription());
        equipment.setCreatedBy(equipmentRequest.getCreatedBy());
        equipment.setActive(equipmentRequest.getActive());
        equipment.setManufacturer(equipmentRequest.getManufacturer());
        equipment.setSupplier(equipmentRequest.getSupplier());
        equipment.setInstallationDate(equipmentRequest.getInstallationDate());
        equipment.setPurchaseDate(equipmentRequest.getPurchaseDate());
        equipment.setExpirationDate(equipmentRequest.getExpirationDate());
        equipment = service.save(equipment);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, false, AppConstants.SUCCESS_MESSAGE, mapper.apply(equipment));
    }

    @Override
    public ApiResponse<EquipmentDto> update(Long id, EquipmentDto equipmentDto) {
        Equipment equipment = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Equipment not found"));
        equipment.setEquipmentNumber(equipmentDto.equipmentNumber());
        equipment.setName(equipmentDto.name());
        equipment.setSerialNumber(equipment.getSerialNumber());
        equipment.setLocation(equipment.getLocation());
        equipment.setModelName(equipmentDto.modelName());
        equipment.setModelNumber(equipmentDto.modelNumber());
        equipment.setCost(equipmentDto.cost());
        equipment.setDescription(equipmentDto.description());
        equipment.setCreatedBy(equipmentDto.createdBy());
        equipment.setActive(equipmentDto.active());
        equipment.setManufacturer(equipmentDto.manufacturer());
        equipment.setSupplier(equipmentDto.supplier());
        equipment.setInstallationDate(equipmentDto.installationDate());
        equipment.setPurchaseDate(equipmentDto.purchaseDate());
        equipment.setExpirationDate(equipmentDto.expirationDate());
        equipment = service.save(equipment);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, false, AppConstants.SUCCESS_MESSAGE, mapper.apply(equipment));
    }

    @Override
    public ApiResponse<EquipmentDto> deleteById(Long id) {
        Equipment equipment = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Equipment not found"));
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(equipment));
    }
}
