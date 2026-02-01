package za.co.sabs.planningtool.utils.messages.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobCardRequest {
    private String jobNo;
    private String customerName;
    private String soNumber;
    private String sampleReceiptNo;
    private String accountStatus;
    private String poNumber;
    private String centralReceiptNo;
    private String sampleLocation;
    private String turnAroundTime;
    private boolean isOutsource;
    private String phase;
    private String sequence;
    private String createdBy;
    private boolean active;
    private Long laboratoryId;
    private Set<Long> managerIds;
    private Set<Long> personnelIds;
    private List<ActivityRequest> activities;
    private Set<Long> consumableIds;
    private Set<Long> testingMethodIds;
    private Set<Long> equipmentIds;
}
