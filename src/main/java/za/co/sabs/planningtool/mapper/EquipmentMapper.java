package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.EquipmentDto;
import za.co.sabs.planningtool.entity.Equipment;

import java.util.function.Function;

@Service
public class EquipmentMapper implements Function<Equipment, EquipmentDto> {
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
                equipment.getCreatedAt(),
                equipment.getUpdatedAt()
        );
    }
}
