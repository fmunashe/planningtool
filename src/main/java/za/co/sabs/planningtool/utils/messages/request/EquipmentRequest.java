package za.co.sabs.planningtool.utils.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EquipmentRequest {
    private String equipmentNumber;
    private String name;
    private String serialNumber;
    private String location;
    private String modelNumber;
    private String modelName;
    private Double cost;
    private String description;
    private String createdBy;
    private Boolean active;
    private String manufacturer;
    private String supplier;
    private LocalDate installationDate;
    private LocalDate purchaseDate;
    private LocalDate expirationDate;
}
