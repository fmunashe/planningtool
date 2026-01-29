package za.co.sabs.planningtool.mapper;

import lombok.Data;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.EquipmentDto;
import za.co.sabs.planningtool.entity.Equipment;

import java.util.function.Function;

@Service
@Data
public class EquipmentMapper implements Function<Equipment, EquipmentDto> {
    private final WarrantyMapper warrantyMapper;
    private final MaintenanceCycleMapper maintenanceCycleMapper;
    private final ClaimMapper claimMapper;
    private final CalibrationMapper calibrationMapper;
    private final JobCardMapper jobCardMapper;
    private final ReviewCycleMapper reviewCycleMapper;

    public EquipmentMapper(WarrantyMapper warrantyMapper, MaintenanceCycleMapper maintenanceCycleMapper, ClaimMapper claimMapper, CalibrationMapper calibrationMapper, JobCardMapper jobCardMapper, ReviewCycleMapper reviewCycleMapper) {
        this.warrantyMapper = warrantyMapper;
        this.maintenanceCycleMapper = maintenanceCycleMapper;
        this.claimMapper = claimMapper;
        this.calibrationMapper = calibrationMapper;
        this.jobCardMapper = jobCardMapper;
        this.reviewCycleMapper = reviewCycleMapper;
    }

    @Override
    public EquipmentDto apply(Equipment equipment) {
        return new EquipmentDto(
                equipment.getId(),
                equipment.getEquipmentNumber(),
                equipment.getName(),
                equipment.getSerialNumber(),
                equipment.getLocation(),
                equipment.getModelNumber(),
                equipment.getModelName(),
                equipment.getCost(),
                equipment.getDescription(),
                equipment.getCreatedBy(),
                equipment.getActive(),
                equipment.getManufacturer(),
                equipment.getSupplier(),
                equipment.getInstallationDate(),
                equipment.getPurchaseDate(),
                equipment.getExpirationDate(),
                equipment.getWarranties().stream().map(warrantyMapper).toList(),
                equipment.getMaintenanceCycles().stream().map(maintenanceCycleMapper).toList(),
                equipment.getClaims().stream().map(claimMapper).toList(),
                equipment.getCalibrations().stream().map(calibrationMapper).toList(),
                equipment.getJobCards().stream().map(jobCardMapper).toList(),
                equipment.getReviewCycles().stream().map(reviewCycleMapper).toList(),
                equipment.getCreatedAt(),
                equipment.getUpdatedAt()
        );
    }

    public Equipment toEntity(EquipmentDto dto) {
        if (dto == null) return null;
        Equipment e = new Equipment();
        e.setId(dto.id());
        e.setEquipmentNumber(dto.equipmentNumber());
        e.setName(dto.name());
        e.setSerialNumber(dto.serialNumber());
        e.setLocation(dto.location());
        e.setModelNumber(dto.modelNumber());
        e.setModelName(dto.modelName());
        e.setCost(dto.cost());
        e.setDescription(dto.description());
        e.setCreatedBy(dto.createdBy());
        e.setActive(dto.active());
        e.setManufacturer(dto.manufacturer());
        e.setSupplier(dto.supplier());
        e.setInstallationDate(dto.installationDate());
        e.setPurchaseDate(dto.purchaseDate());
        e.setExpirationDate(dto.expirationDate());
        return e;
    }
}
