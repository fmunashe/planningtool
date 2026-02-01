package za.co.sabs.planningtool.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Laboratory extends BaseEntity {
    private String labName;
    private String labNumber;
    private Boolean isActive;
    private String CreatedBy;
    @OneToMany(
            mappedBy = "laboratory",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<JobCard> JobCards;
}
