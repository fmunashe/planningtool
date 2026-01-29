package za.co.sabs.planningtool.utils.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MaintenanceCycleRequest {
    private String maintenanceCycleNumber;
    private String maintenanceCycleType;
    private String frequency;
    private LocalDate plannedDate;
    private LocalDate returnDate;
    private String createdBy;
    private Boolean active;
    private String supplier;
    private String description;
    private Long equipmentId;
}
