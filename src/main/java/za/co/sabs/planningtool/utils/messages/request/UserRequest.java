package za.co.sabs.planningtool.utils.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserRequest {
    private String username;
    private List<String> roles;
}
