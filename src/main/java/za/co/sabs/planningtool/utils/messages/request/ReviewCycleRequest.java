package za.co.sabs.planningtool.utils.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReviewCycleRequest {
    private String name;
    private String description;
    private String warrantNumber;
    private String warrantType;
    private LocalDate coverageStartDate;
    private LocalDate coverageEndDate;
    private String createdBy;
    private boolean active;
    private Long equipmentId;
}
