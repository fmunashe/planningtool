package za.co.sabs.planningtool.entity;

import jakarta.persistence.Entity;
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
    private List<JobCard> JobCards;
}
