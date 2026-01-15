package za.co.sabs.planningtool.utils.messages.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ActivityRequest {
    private String testMethodName;
    private String testMethodDescription;
    private String project;
    private String testName;
    private String testType;
    private LocalDate testDate;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private String testLocation;
    private String purposeOfTest;
    private String conductedBy;
    private List<String> materials;
    private List<String> parameters;
}
