package za.co.sabs.planningtool.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TestingMethod extends BaseEntity {
    private String testMethodName;
    private String testMethodNo;
    private String phase;
    private String description;
    private String isoSansCode;
    private String turnAroundTime;
    private String createdBy;
    private Boolean Active;
    @OneToMany(
            mappedBy = "testingMethod",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Activity> activities;

    @OneToMany(
            mappedBy = "testingMethod",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<JobCard> JobCards;
}
