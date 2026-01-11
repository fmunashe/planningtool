package za.co.sabs.planningtool.dto;

import java.time.LocalDateTime;

public record UrgencyDto(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
