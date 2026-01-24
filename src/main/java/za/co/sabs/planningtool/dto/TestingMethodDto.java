package za.co.sabs.planningtool.dto;

import java.time.LocalDateTime;

public record TestingMethodDto(
        Long id,
        String testMethodName,
        String testMethodNo,
        String phase,
        String description,
        String isoSansCode,
        String turnAroundTime,
        String createdBy,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
