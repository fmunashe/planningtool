package za.co.sabs.planningtool.dto;

import za.co.sabs.planningtool.entity.ContactPerson;

import java.time.LocalDateTime;

public record ConsumableDto(
        Long id,
        String consumableNumber,
        String name,
        String type,
        String quantityOnHand,
        String replenLevel,
        String location,
        String description,
        Double cost,
        String createdBy,
        Boolean active,
        ContactPerson supplier,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
