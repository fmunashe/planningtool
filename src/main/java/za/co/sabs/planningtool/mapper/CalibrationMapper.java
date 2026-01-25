package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.CalibrationDto;
import za.co.sabs.planningtool.dto.EquipmentDto;
import za.co.sabs.planningtool.entity.Calibration;
import za.co.sabs.planningtool.entity.Equipment;

import java.util.function.Function;

@Service
public class CalibrationMapper implements Function<Calibration, CalibrationDto> {
    private final EquipmentMapper equipmentMapper;

    public CalibrationMapper(EquipmentMapper equipmentMapper) {
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public CalibrationDto apply(Calibration calibration) {
        return new CalibrationDto(
                calibration.getId(),
                calibration.getCalibrationNumber(),
                calibration.getCalibrationType(),
                calibration.getFrequency(),
                calibration.getPlannedDate(),
                calibration.getReturnDate(),
                calibration.getCreatedBy(),
                calibration.isActive(),
                calibration.getDescription(),
                calibration.getDocument(),
                getEquipment(calibration.getEquipment()),
                calibration.getCreatedAt(),
                calibration.getUpdatedAt()
        );
    }

    private EquipmentDto getEquipment(Equipment equipment) {
        return equipmentMapper.apply(equipment);
    }
}
