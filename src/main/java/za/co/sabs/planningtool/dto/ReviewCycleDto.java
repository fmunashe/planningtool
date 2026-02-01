package za.co.sabs.planningtool.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReviewCycleDto(
        Long id,
        String name,
        String description,
        String warrantNumber,
        String warrantType,
        LocalDate coverageStartDate,
        LocalDate coverageEndDate,
        String createdBy,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
