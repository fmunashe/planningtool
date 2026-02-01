package za.co.sabs.planningtool.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.CalibrationDto;
import za.co.sabs.planningtool.entity.Calibration;
import za.co.sabs.planningtool.utils.HelperService;

import java.util.function.Function;

@Service
public class CalibrationMapper implements Function<Calibration, CalibrationDto> {
    private final HelperService helperService;

    public CalibrationMapper(@Lazy HelperService helperService) {
        this.helperService = helperService;
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
                helperService.getEquipment(calibration.getEquipment()),
                calibration.getCreatedAt(),
                calibration.getUpdatedAt()
        );
    }
}
