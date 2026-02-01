package za.co.sabs.planningtool.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.ActivityDto;
import za.co.sabs.planningtool.entity.Activity;

import java.util.ArrayList;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ActivityMapper implements Function<Activity, ActivityDto> {
    private final TestingMethodMapper testingMethodMapper;

    @Override
    public ActivityDto apply(Activity activity) {
        return new ActivityDto(
                activity.getId(),
                activity.getProject(),
                activity.getTestName(),
                activity.getTestType(),
                activity.getTestDate(),
                activity.getTestStartTime(),
                activity.getTestEndTime(),
                activity.getTestLocation(),
                activity.getPurposeOfTest(),
                activity.getConductedBy(),
                activity.getTestingMethod() != null ? testingMethodMapper.apply(activity.getTestingMethod()) : null,
                activity.getMaterials() != null ? activity.getMaterials() : new ArrayList<>(),
                activity.getTestParameters() != null ? activity.getTestParameters() : new ArrayList<>(),
                activity.getCreatedAt(),
                activity.getUpdatedAt()
        );
    }


    public Activity toEntity(ActivityDto dto) {
        if (dto == null) return null;
        Activity activity = new Activity();

        activity.setId(dto.id());
        activity.setProject(dto.project());
        activity.setTestName(dto.testName());
        activity.setTestType(dto.testType());
        activity.setTestDate(dto.testDate());
        activity.setTestStartTime(dto.testStartTime());
        activity.setTestEndTime(dto.testEndTime());
        activity.setTestLocation(dto.testLocation());
        activity.setPurposeOfTest(dto.purposeOfTest());
        activity.setConductedBy(dto.conductedBy());
        activity.setMaterials(dto.materials());
        activity.setTestParameters(dto.parameters());
        activity.setCreatedAt(dto.createdAt());
        activity.setUpdatedAt(activity.getUpdatedAt());

        return activity;
    }
}
