package za.co.sabs.planningtool.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactPerson extends BaseEntity {
    private String name;
    private String email;
    private String telephone;
    private String address;
    private Boolean active;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private List<Consumable> consumables;
}
