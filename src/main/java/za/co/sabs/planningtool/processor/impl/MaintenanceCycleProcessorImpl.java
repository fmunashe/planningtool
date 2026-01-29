package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.MaintenanceCycleDto;
import za.co.sabs.planningtool.entity.Equipment;
import za.co.sabs.planningtool.entity.MaintenanceCycle;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.EquipmentMapper;
import za.co.sabs.planningtool.mapper.MaintenanceCycleMapper;
import za.co.sabs.planningtool.processor.MaintenanceCycleProcessor;
import za.co.sabs.planningtool.service.EquipmentService;
import za.co.sabs.planningtool.service.MaintenanceCycleService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.MaintenanceCycleRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class MaintenanceCycleProcessorImpl implements MaintenanceCycleProcessor {
    private final MaintenanceCycleService service;
    private final MaintenanceCycleMapper mapper;
    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;

    public MaintenanceCycleProcessorImpl(MaintenanceCycleService service, MaintenanceCycleMapper mapper, EquipmentService equipmentService, EquipmentMapper equipmentMapper) {
        this.service = service;
        this.mapper = mapper;
        this.equipmentService = equipmentService;
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public ApiResponse<MaintenanceCycleDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull MaintenanceCycle> cycles = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(cycles, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<MaintenanceCycleDto> findById(Long id) {
        Optional<MaintenanceCycle> optionalMaintenanceCycle = service.findById(id);
        if (optionalMaintenanceCycle.isEmpty()) {
            throw new RecordNotFoundException("Maintenance cycle not found");
        }

        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(optionalMaintenanceCycle.get()));
    }

    @Override
    public ApiResponse<MaintenanceCycleDto> save(MaintenanceCycleRequest maintenanceCycleRequest) {
        Optional<Equipment> optionalEquipment = equipmentService.findById(maintenanceCycleRequest.getEquipmentId());
        if (optionalEquipment.isEmpty()) {
            throw new RecordNotFoundException("Equipment ID provided for this maintenance cycle does not exist");
        }
        MaintenanceCycle cycle = new MaintenanceCycle();
        cycle.setEquipment(optionalEquipment.get());
        cycle.setMaintenanceCycleNumber(maintenanceCycleRequest.getMaintenanceCycleNumber());
        cycle.setMaintenanceCycleType(maintenanceCycleRequest.getMaintenanceCycleType());
        cycle.setDescription(maintenanceCycleRequest.getDescription());
        cycle.setFrequency(maintenanceCycleRequest.getFrequency());
        cycle.setPlannedDate(maintenanceCycleRequest.getPlannedDate());
        cycle.setReturnDate(maintenanceCycleRequest.getReturnDate());
        cycle.setCreatedBy(maintenanceCycleRequest.getCreatedBy());
        cycle.setActive(maintenanceCycleRequest.getActive());
        cycle.setSupplier(maintenanceCycleRequest.getSupplier());
        cycle = service.save(cycle);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(cycle));
    }

    @Override
    public ApiResponse<MaintenanceCycleDto> update(Long id, MaintenanceCycleDto maintenanceCycleDto) {
        Optional<MaintenanceCycle> optionalMaintenanceCycle = service.findById(id);
        if (optionalMaintenanceCycle.isEmpty()) {
            throw new RecordNotFoundException("Maintenance cycle not found");
        }
        MaintenanceCycle cycle = optionalMaintenanceCycle.get();
        cycle.setEquipment(equipmentMapper.toEntity(maintenanceCycleDto.equipment()));
        cycle.setMaintenanceCycleNumber(maintenanceCycleDto.maintenanceCycleNumber());
        cycle.setMaintenanceCycleType(maintenanceCycleDto.maintenanceCycleType());
        cycle.setDescription(maintenanceCycleDto.description());
        cycle.setFrequency(maintenanceCycleDto.frequency());
        cycle.setPlannedDate(maintenanceCycleDto.plannedDate());
        cycle.setReturnDate(maintenanceCycleDto.returnDate());
        cycle.setCreatedBy(maintenanceCycleDto.createdBy());
        cycle.setActive(maintenanceCycleDto.active());
        cycle.setSupplier(maintenanceCycleDto.supplier());
        cycle = service.save(cycle);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(cycle));

    }

    @Override
    public ApiResponse<MaintenanceCycleDto> deleteById(Long id) {
        Optional<MaintenanceCycle> optionalMaintenanceCycle = service.findById(id);
        if (optionalMaintenanceCycle.isEmpty()) {
            throw new RecordNotFoundException("Maintenance cycle not found");
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(optionalMaintenanceCycle.get()));

    }
}
