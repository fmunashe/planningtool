package za.co.sabs.planningtool.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MaintenanceCycleDto(
        Long id,
        String maintenanceCycleNumber,
        String maintenanceCycleType,
        String frequency,
        LocalDate plannedDate,
        LocalDate returnDate,
        String createdBy,
        Boolean active,
        String supplier,
        String description,
        EquipmentDto equipment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
