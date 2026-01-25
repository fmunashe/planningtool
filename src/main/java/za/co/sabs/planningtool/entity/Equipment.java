package za.co.sabs.planningtool.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            fetch = FetchType.LAZY
    )
    private List<Warranty> warranties = new ArrayList<>();

    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<MaintenanceCycle> maintenanceCycles = new ArrayList<>();

    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Claim> claims = new ArrayList<>();
    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Calibration> calibrations = new ArrayList<>();

    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<JobCard> jobCards = new ArrayList<>();

    @OneToMany(
            mappedBy = "equipment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ReviewCycle> reviewCycles = new ArrayList<>();
}
