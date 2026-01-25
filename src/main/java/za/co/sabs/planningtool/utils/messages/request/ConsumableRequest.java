package za.co.sabs.planningtool.utils.messages.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumableRequest {
    private String consumableNumber;
    private String name;
    private String type;
    private String quantityOnHand;
    private String replenLevel;
    private String location;
    private String description;
    private Double cost;
    private String createdBy;
    private Boolean active;
    private Long supplierId;
}
