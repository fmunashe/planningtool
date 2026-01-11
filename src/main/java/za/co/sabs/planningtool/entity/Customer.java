package za.co.sabs.planningtool.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String accountNumber;
}
