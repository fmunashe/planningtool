package za.co.sabs.planningtool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(mappedBy = "testingMethods")
    private Set<JobCard> JobCards = new HashSet<>();
}
