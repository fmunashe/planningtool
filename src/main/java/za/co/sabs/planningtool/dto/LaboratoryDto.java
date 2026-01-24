package za.co.sabs.planningtool.dto;

import java.time.LocalDateTime;
import java.util.List;

public record LaboratoryDto
        (
                Long id,
                String labName,
                String labNumber,
                Boolean isActive,
                String createdBy,
                List<JobCardDto> jobCards,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) {
}
