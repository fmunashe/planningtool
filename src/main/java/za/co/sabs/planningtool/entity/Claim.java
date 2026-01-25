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
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Claim extends BaseEntity {
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id", nullable = false)
    @JsonBackReference("equipment-claims")
    private Equipment equipment;
}

