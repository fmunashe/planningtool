package za.co.sabs.planningtool.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
