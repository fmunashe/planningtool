package za.co.sabs.planningtool.utils.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CalibrationRequest {
    private String calibrationNumber;
    private String calibrationType;
    private String frequency;
    private LocalDate plannedDate;
    private LocalDate returnDate;
    private String createdBy;
    private boolean active;
    private String description;
    private String document;
    private Long equipmentId;
}
