package za.co.sabs.planningtool.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record WarrantyDto(
        Long id,
        String name,
        String warrantNumber,
        String warrantyType,
        LocalDate coverageStartDate,
        LocalDate coverageEndDate,
        String description,
        String createdBy,
        Boolean active,
        EquipmentDto equipment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
