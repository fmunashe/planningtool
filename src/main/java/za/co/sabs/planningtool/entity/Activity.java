package za.co.sabs.planningtool.entity;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "testing_method_id", nullable = false)
    private TestingMethod testingMethod;

    @BatchSize(size = 20)
    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("activity-parameters")
    private List<Parameter> testParameters = new ArrayList<>();

    @BatchSize(size = 20)
    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("activity-materials")
    private List<Material> materials = new ArrayList<>();

    public void addMaterial(Material material) {
        materials.add(material);
        material.setActivity(this);
    }

    public void removeMaterial(Material material) {
        materials.remove(material);
        material.setActivity(null);
    }

    public void addParameter(Parameter parameter) {
        testParameters.add(parameter);
        parameter.setActivity(this);
    }

    public void removeParameter(Parameter parameter) {
        testParameters.remove(parameter);
        parameter.setActivity(null);
    }
}
