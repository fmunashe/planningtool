package za.co.sabs.planningtool.utils.messages.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LabRequest {
    @NotBlank(message = "Lab name is required")
    private String labName;
    @NotBlank(message = "Lab number is required")
    private String labNumber;
    private Boolean isActive;
    private String createdBy;
}
