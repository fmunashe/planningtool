package za.co.sabs.planningtool.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipment extends BaseEntity {
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
    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference("equipment-warrants")
    private List<Warranty> warranties = new ArrayList<>();

    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference("equipment-maintenance-cycles")
    private List<MaintenanceCycle> maintenanceCycles = new ArrayList<>();

    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference("equipment-claims")
    private List<Claim> claims = new ArrayList<>();
    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Calibration> calibrations = new ArrayList<>();

    @ManyToMany(mappedBy = "equipments",fetch = FetchType.EAGER)
    private Set<JobCard> jobCards = new HashSet<>();

    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference("equipment-review-cycles")
    private List<ReviewCycle> reviewCycles = new ArrayList<>();
}
