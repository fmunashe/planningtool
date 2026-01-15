package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Component;
import za.co.sabs.planningtool.dto.ActivityDto;
import za.co.sabs.planningtool.entity.Activity;

import java.util.ArrayList;
import java.util.function.Function;

@Component
public class ActivityMapper implements Function<Activity, ActivityDto> {
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
                activity.getMaterials() != null ? activity.getMaterials() : new ArrayList<>(),
                activity.getTestParameters() != null ? activity.getTestParameters() : new ArrayList<>(),
                activity.getCreatedAt(),
                activity.getUpdatedAt()
        );
    }
}
