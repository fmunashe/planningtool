package za.co.sabs.planningtool.utils.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestMethodRequest {
    private String testMethodName;
    private String testMethodNo;
    private String phase;
    private String description;
    private String isoSansCode;
    private String turnAroundTime;
    private String createdBy;
    private Boolean Active;
}
