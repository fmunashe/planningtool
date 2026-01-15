package za.co.sabs.planningtool.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import za.co.sabs.planningtool.entity.Material;
import za.co.sabs.planningtool.entity.Parameter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ActivityDto(
        Long id,
        String project,
        String testName,
        String testType,
        LocalDate testDate,
        LocalDateTime testStartTime,
        LocalDateTime testEndTime,
        String testLocation,
        String purposeOfTest,
        String conductedBy,
        @JsonIgnoreProperties({"materials"})
        List<Material> materials,
        @JsonIgnoreProperties({"parameters"})
        List<Parameter> parameters,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
