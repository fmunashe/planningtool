package za.co.sabs.planningtool.utils.messages.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimRequest {
    private String name;
    private String claimNumber;
    private String claimType;
    private LocalDate claimDate;
    private LocalDate plannedDate;
    private LocalDate returnDate;
    private String createdBy;
    private Boolean active;
    private String description;
    private String document;
    private String person;
    private Long equipmentId;
}
