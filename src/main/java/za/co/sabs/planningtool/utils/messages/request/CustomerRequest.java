package za.co.sabs.planningtool.utils.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerRequest {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String accountNumber;
}
