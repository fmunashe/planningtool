package za.co.sabs.planningtool.dto;

import java.time.LocalDateTime;

public record LaboratoryDto
        (
                Long id,
                String labName,
                String labNumber,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) {
}
