package za.co.sabs.planningtool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaintenanceCycle extends BaseEntity {
    private String maintenanceCycleNumber;
    private String maintenanceCycleType;
    private String frequency;
    private LocalDate plannedDate;
    private LocalDate returnDate;
    private String createdBy;
    private Boolean active;
    private String supplier;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id", nullable = false)
    @JsonBackReference("equipment-maintenance-cycles")
    private Equipment equipment;
}
