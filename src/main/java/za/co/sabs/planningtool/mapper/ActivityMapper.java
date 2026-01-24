package za.co.sabs.planningtool.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import za.co.sabs.planningtool.dto.ActivityDto;
import za.co.sabs.planningtool.entity.Activity;

import java.util.ArrayList;
import java.util.function.Function;

@Component
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
}
