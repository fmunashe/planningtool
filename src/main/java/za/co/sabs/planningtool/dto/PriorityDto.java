package za.co.sabs.planningtool.dto;

import java.time.LocalDateTime;

public record PriorityDto(
        Long id,
        String level,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
