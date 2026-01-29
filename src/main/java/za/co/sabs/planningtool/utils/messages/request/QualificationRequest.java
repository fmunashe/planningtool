package za.co.sabs.planningtool.utils.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class QualificationRequest {
    private String qualificationNumber;
    private String program;
    private String school;
    private String location;
    private String description;
    private String grade;
    private LocalDate startDate;
    private LocalDate endDate;
    private String createdBy;
    private Boolean active;
    private Long userId;
}
