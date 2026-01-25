package za.co.sabs.planningtool.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consumable extends BaseEntity {
    private String consumableNumber;
    private String name;
    private String type;
    private String quantityOnHand;
    private String replenLevel;
    private String location;
    private String description;
    private Double cost;
    private String createdBy;
    private Boolean active;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_person_id")
    @JsonManagedReference("consumable-contact-person")
    private ContactPerson supplier;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumable_id")
    @JsonManagedReference("consumable-job-cards")
    private List<JobCard> jobCards;
}
