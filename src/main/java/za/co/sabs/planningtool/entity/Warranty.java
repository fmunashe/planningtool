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
public class Warranty extends BaseEntity {
    private String name;
    private String warrantNumber;
    private String warrantyType;
    private LocalDate coverageStartDate;
    private LocalDate coverageEndDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id", nullable = false)
    @JsonBackReference("equipment-warrants")
    private Equipment equipment;
    private String createdBy;
    private Boolean active;
    private String description;
}
