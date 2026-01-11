package za.co.sabs.planningtool.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Laboratory extends BaseEntity {
    private String labName;
    private String labNumber;
}
