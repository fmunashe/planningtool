package za.co.sabs.planningtool.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record QualificationDto(
        Long id,
        String qualificationNumber,
        String program,
        String school,
        String location,
        String description,
        String grade,
        LocalDate startDate,
        LocalDate endDate,
        String createdBy,
        Boolean active,
        UserDto user,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
}
