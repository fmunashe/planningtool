package za.co.sabs.planningtool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class JobCard extends BaseEntity {
    private String jobNo;
    private String customerName;
    private String soNumber;
    private String sampleReceiptNo;
    private String accountStatus;
    private String poNumber;
    private String centralReceiptNo;
    private String sampleLocation;
    private String turnAroundTime;
    private Boolean isOutsource = false;
    private String phase;
    private String sequence;
    private String createdBy;
    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laboratory_id", nullable = false)
    @JsonBackReference("laboratory-job-cards")
    private Laboratory laboratory;

    @OneToMany(
            mappedBy = "jobCard",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Activity> activities = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "jobcard_manager",
            joinColumns = @JoinColumn(name = "job_card_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id")
    )
    private Set<User> managers = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "jobcard_personnel",
            joinColumns = @JoinColumn(name = "job_card_id"),
            inverseJoinColumns = @JoinColumn(name = "personnel_id")
    )
    private Set<User> personnel = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "jobcards_consumables",
            joinColumns = @JoinColumn(name = "job_card_id"),
            inverseJoinColumns = @JoinColumn(name = "consumable_id")
    )
    private Set<Consumable> consumables = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "jobcards_testmethods",
            joinColumns = @JoinColumn(name = "job_card_id"),
            inverseJoinColumns = @JoinColumn(name = "test_method_id")
    )
    private Set<TestingMethod> testingMethods = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "jobcards_equipments",
            joinColumns = @JoinColumn(name = "job_card_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();
}
