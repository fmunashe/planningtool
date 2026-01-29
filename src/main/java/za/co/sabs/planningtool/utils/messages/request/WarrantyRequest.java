package za.co.sabs.planningtool.utils.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WarrantyRequest {
    private String name;
    private String warrantNumber;
    private String warrantyType;
    private LocalDate coverageStartDate;
    private LocalDate coverageEndDate;
    private String description;
    private String createdBy;
    private Boolean active;
    private Long equipmentId;
}
