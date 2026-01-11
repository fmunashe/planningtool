package za.co.sabs.planningtool.dto;

import java.time.LocalDateTime;

public record TaskStatusDto(
        Long id,
        String taskStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
