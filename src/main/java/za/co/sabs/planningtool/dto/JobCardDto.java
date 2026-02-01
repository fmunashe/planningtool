package za.co.sabs.planningtool.dto;

import java.time.LocalDateTime;
import java.util.List;

public record JobCardDto(
        Long id,
        String jobNo,
        String customerName,
        String soNumber,
        String sampleReceiptNo,
        String accountStatus,
        String poNumber,
        String centralReceiptNo,
        String sampleLocation,
        String turnAroundTime,
        Boolean isOutsource,
        String phase,
        String sequence,
        String createdBy,
        boolean active,
        LaboratoryDto laboratory,
        List<UserDto> managers,
        List<UserDto> personnel,
        List<ActivityDto> activities,
        List<ConsumableDto> consumables,
        List<TestingMethodDto> testingMethods,
        List<EquipmentDto> equipments,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
