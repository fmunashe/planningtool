package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.CalibrationDto;
import za.co.sabs.planningtool.dto.EquipmentDto;
import za.co.sabs.planningtool.entity.Calibration;
import za.co.sabs.planningtool.entity.Equipment;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.CalibrationMapper;
import za.co.sabs.planningtool.processor.CalibrationProcessor;
import za.co.sabs.planningtool.service.CalibrationService;
import za.co.sabs.planningtool.service.EquipmentService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.CalibrationRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class CalibrationProcessorImpl implements CalibrationProcessor {
    private final CalibrationService calibrationService;
    private final CalibrationMapper calibrationMapper;
    private final EquipmentService equipmentService;

    public CalibrationProcessorImpl(CalibrationService calibrationService, CalibrationMapper calibrationMapper, EquipmentService equipmentService) {
        this.calibrationService = calibrationService;
        this.calibrationMapper = calibrationMapper;
        this.equipmentService = equipmentService;
    }

    @Override
    public ApiResponse<CalibrationDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Calibration> calibrationPage = calibrationService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(calibrationPage, calibrationMapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CalibrationDto> findById(Long id) {
        Optional<Calibration> calibrationOptional = calibrationService.findById(id);
        if (calibrationOptional.isEmpty()) {
            throw new RecordNotFoundException("Calibration not found");
        }
        return HelperResponse.buildApiResponse(null, calibrationMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, calibrationMapper.apply(calibrationOptional.get()));
    }

    @Override
    public ApiResponse<CalibrationDto> save(CalibrationRequest calibrationRequest) {

        Optional<Equipment> equipmentOptional = equipmentService.findById(calibrationRequest.getEquipmentId());
        if (equipmentOptional.isEmpty()) {
            throw new RecordNotFoundException("Equipment not found");
        }
        Calibration calibration = new Calibration();
        calibration.setCalibrationType(calibration.getCalibrationType());
        calibration.setCalibrationNumber(calibrationRequest.getCalibrationNumber());
        calibration.setActive(calibrationRequest.isActive());
        calibration.setDescription(calibrationRequest.getDescription());
        calibration.setDocument(calibrationRequest.getDocument());
        calibration.setEquipment(equipmentOptional.get());
        calibration.setFrequency(calibrationRequest.getFrequency());
        calibration.setPlannedDate(calibrationRequest.getPlannedDate());
        calibration.setReturnDate(calibrationRequest.getReturnDate());
        calibration.setCreatedBy(calibrationRequest.getCreatedBy());
        calibration = calibrationService.save(calibration);
        return HelperResponse.buildApiResponse(null, calibrationMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, calibrationMapper.apply(calibration));
    }

    @Override
    public ApiResponse<CalibrationDto> update(Long id, CalibrationDto calibrationDto) {
        Optional<Calibration> calibrationOptional = calibrationService.findById(id);
        if (calibrationOptional.isEmpty()) {
            throw new RecordNotFoundException("Calibration not found");
        }
        Calibration calibration = calibrationOptional.get();
        calibration.setCalibrationType(calibration.getCalibrationType());
        calibration.setCalibrationNumber(calibrationDto.calibrationNumber());
        calibration.setActive(calibrationDto.active());
        calibration.setDescription(calibrationDto.description());
        calibration.setDocument(calibrationDto.document());
        calibration.setEquipment(getEquipment(calibrationDto));
        calibration.setFrequency(calibrationDto.frequency());
        calibration.setPlannedDate(calibrationDto.plannedDate());
        calibration.setReturnDate(calibrationDto.returnDate());
        calibration.setCreatedBy(calibrationDto.createdBy());
        calibration = calibrationService.save(calibration);
        return HelperResponse.buildApiResponse(null, calibrationMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, calibrationMapper.apply(calibration));
    }

    @Override
    public ApiResponse<CalibrationDto> deleteById(Long id) {
        Optional<Calibration> calibrationOptional = calibrationService.findById(id);
        if (calibrationOptional.isEmpty()) {
            throw new RecordNotFoundException("Calibration not found");
        }
        return HelperResponse.buildApiResponse(null, calibrationMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, calibrationMapper.apply(calibrationOptional.get()));
    }


    private Equipment getEquipment(CalibrationDto calibrationDto) {
        EquipmentDto equipment = calibrationDto.equipment();
        Equipment equipmentEntity = equipmentService.findById(equipment.id()).get();
        equipmentEntity.setEquipmentNumber(equipment.equipmentNumber());
        equipmentEntity.setName(equipment.name());
        equipmentEntity.setSerialNumber(equipment.serialNumber());
        equipmentEntity.setLocation(equipment.location());
        equipmentEntity.setModelNumber(equipment.modelNumber());
        equipmentEntity.setModelName(equipment.modelName());
        equipmentEntity.setCost(equipment.cost());
        equipmentEntity.setDescription(equipment.description());
        equipmentEntity.setCreatedBy(equipment.createdBy());
        equipmentEntity.setActive(equipment.active());
        equipmentEntity.setManufacturer(equipment.manufacturer());
        equipmentEntity.setSupplier(equipment.supplier());
        equipmentEntity.setInstallationDate(equipment.installationDate());
        equipmentEntity.setPurchaseDate(equipment.purchaseDate());
        equipmentEntity.setExpirationDate(equipment.expirationDate());
        return equipmentEntity;
    }
}
