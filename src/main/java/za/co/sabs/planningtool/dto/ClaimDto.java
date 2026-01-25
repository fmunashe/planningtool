package za.co.sabs.planningtool.dto;


import java.time.LocalDate;

public record ClaimDto(
        Long id,
        String name,
        String claimNumber,
        String claimType,
        LocalDate claimDate,
        LocalDate plannedDate,
        LocalDate returnDate,
        String createdBy,
        Boolean active,
        String description,
        String document,
        String person,
        EquipmentDto equipment
) {
}
