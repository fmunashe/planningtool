package za.co.sabs.planningtool.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record EquipmentDto(
        Long id,
        String equipmentNumber,
        String name,
        String serialNumber,
        String location,
        String modelNumber,
        String modelName,
        Double cost,
        String description,
        String createdBy,
        Boolean active,
        String manufacturer,
        String supplier,
        LocalDate installationDate,
        LocalDate purchaseDate,
        LocalDate expirationDate,
        List<WarrantyDto> warranties,
        List<MaintenanceCycleDto> maintenanceCycles,
        List<ClaimDto> claims,
        List<CalibrationDto> calibrations,
        List<JobCardDto> jobCards,
        List<ReviewCycleDto> reviewCycles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
