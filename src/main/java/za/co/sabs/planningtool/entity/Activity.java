package za.co.sabs.planningtool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Activity extends BaseEntity {
    private String project;
    private String testName;
    private String testType;
    private String conductedBy;
    private LocalDate testDate;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private String testLocation;
    private String purposeOfTest;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "testing_method_id", nullable = false)
    private TestingMethod testingMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobCard_id", nullable = false)
    @JsonBackReference("jobcard-activities")
    private JobCard jobCard;

    @BatchSize(size = 20)
    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference("activity-parameters")
    private List<Parameter> testParameters = new ArrayList<>();

    @BatchSize(size = 20)
    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference("activity-materials")
    private List<Material> materials = new ArrayList<>();
}
