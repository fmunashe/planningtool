package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.WarrantyDto;
import za.co.sabs.planningtool.entity.Equipment;
import za.co.sabs.planningtool.entity.Warranty;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.EquipmentMapper;
import za.co.sabs.planningtool.mapper.WarrantyMapper;
import za.co.sabs.planningtool.processor.WarrantyProcessor;
import za.co.sabs.planningtool.service.EquipmentService;
import za.co.sabs.planningtool.service.WarrantyService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.WarrantyRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

@Service
public class WarrantyProcessorImpl implements WarrantyProcessor {
    private final WarrantyService service;
    private final WarrantyMapper mapper;
    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;

    public WarrantyProcessorImpl(WarrantyService service, WarrantyMapper mapper, EquipmentService equipmentService, EquipmentMapper equipmentMapper) {
        this.service = service;
        this.mapper = mapper;
        this.equipmentService = equipmentService;
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public ApiResponse<WarrantyDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Warranty> warranties = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(warranties, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<WarrantyDto> findById(Long id) {
        Warranty warranty = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Warranty not found"));
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(warranty));
    }

    @Override
    public ApiResponse<WarrantyDto> save(WarrantyRequest warrantyRequest) {
        Equipment equipment = equipmentService.findById(warrantyRequest.getEquipmentId()).orElseThrow(() -> new RecordNotFoundException("Equipment assigned to this warranty not found"));
        Warranty warranty = new Warranty();
        warranty.setName(warrantyRequest.getName());
        warranty.setDescription(warrantyRequest.getDescription());
        warranty.setWarrantNumber(warrantyRequest.getWarrantNumber());
        warranty.setWarrantyType(warrantyRequest.getWarrantyType());
        warranty.setCoverageStartDate(warrantyRequest.getCoverageStartDate());
        warranty.setCoverageEndDate(warrantyRequest.getCoverageEndDate());
        warranty.setCreatedBy(warrantyRequest.getCreatedBy());
        warranty.setActive(warrantyRequest.getActive());
        warranty.setEquipment(equipment);
        warranty = service.save(warranty);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(warranty));

    }

    @Override
    public ApiResponse<WarrantyDto> update(Long id, WarrantyDto warrantyDto) {
        Warranty warranty = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Warranty not found"));
        warranty.setName(warrantyDto.name());
        warranty.setDescription(warrantyDto.description());
        warranty.setWarrantNumber(warrantyDto.warrantNumber());
        warranty.setWarrantyType(warrantyDto.warrantyType());
        warranty.setCoverageStartDate(warrantyDto.coverageStartDate());
        warranty.setCoverageEndDate(warrantyDto.coverageEndDate());
        warranty.setCreatedBy(warrantyDto.createdBy());
        warranty.setActive(warrantyDto.active());
        warranty.setEquipment(equipmentMapper.toEntity(warrantyDto.equipment()));
        warranty = service.save(warranty);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(warranty));
    }

    @Override
    public ApiResponse<WarrantyDto> deleteById(Long id) {
        Warranty warranty = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Warranty not found"));
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(warranty));
    }
}
