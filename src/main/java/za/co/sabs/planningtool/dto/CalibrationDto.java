package za.co.sabs.planningtool.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CalibrationDto(
        Long id,
        String calibrationNumber,
        String calibrationType,
        String frequency,
        LocalDate plannedDate,
        LocalDate returnDate,
        String createdBy,
        boolean active,
        String description,
        String document,
        EquipmentDto equipment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
