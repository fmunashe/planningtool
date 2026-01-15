package za.co.sabs.planningtool.processor.impl;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.ActivityDto;
import za.co.sabs.planningtool.entity.Activity;
import za.co.sabs.planningtool.entity.Material;
import za.co.sabs.planningtool.entity.Parameter;
import za.co.sabs.planningtool.entity.TestingMethod;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.ActivityMapper;
import za.co.sabs.planningtool.processor.ActivityProcessor;
import za.co.sabs.planningtool.service.ActivityService;
import za.co.sabs.planningtool.service.MaterialService;
import za.co.sabs.planningtool.service.ParameterService;
import za.co.sabs.planningtool.service.TestMethodService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.ActivityRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityProcessorImpl implements ActivityProcessor {
    private final ActivityService activityService;
    private final ActivityMapper mapper;
    private final TestMethodService methodService;
    private final MaterialService materialService;
    private final ParameterService parameterService;

    public ActivityProcessorImpl(ActivityService activityService, ActivityMapper activityMapper, TestMethodService methodService, MaterialService materialService, ParameterService parameterService) {
        this.activityService = activityService;
        this.mapper = activityMapper;
        this.methodService = methodService;
        this.materialService = materialService;
        this.parameterService = parameterService;
    }


    @Override
    public ApiResponse<ActivityDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Activity> activities = activityService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(activities, null, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<ActivityDto> findById(Long id) {
        Optional<Activity> optionalActivity = activityService.findById(id);
        return optionalActivity.map(activity -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(activity)))
                .orElseThrow(() -> new RecordNotFoundException("Failed to find activity record with Id of " + id));

    }

    @Override
    public ApiResponse<ActivityDto> save(ActivityRequest activityRequest) {
        TestingMethod method = methodService.findByName(activityRequest.getTestMethodName())
                .orElseGet(() -> {
                    TestingMethod newMethod = new TestingMethod();
                    newMethod.setName(activityRequest.getTestMethodName());
                    newMethod.setDescription(activityRequest.getTestMethodDescription());
                    return methodService.save(newMethod);
                });

        Activity activity = Activity.builder()
                .testingMethod(method)
                .project(activityRequest.getProject())
                .testName(activityRequest.getTestName())
                .testType(activityRequest.getTestType())
                .testDate(activityRequest.getTestDate())
                .testStartTime(activityRequest.getTestStartTime())
                .testEndTime(activityRequest.getTestEndTime())
                .testLocation(activityRequest.getTestLocation())
                .purposeOfTest(activityRequest.getPurposeOfTest())
                .conductedBy(activityRequest.getConductedBy())
                .build();

        activity = activityService.save(activity);
        List<Material> materials = buildMaterials(activityRequest, activity);
        List<Parameter> parameters = buildParameters(activityRequest, activity);
        materials = materialService.saveAll(materials);
        parameters = parameterService.saveAll(parameters);
        activity.setMaterials(materials);
        activity.setTestParameters(parameters);
        activity = activityService.save(activity);

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(activity));

    }

    @Override
    public ApiResponse<ActivityDto> update(Long id, ActivityDto activityDto) {
        Optional<Activity> optionalActivity = activityService.findById(id);
        if (optionalActivity.isEmpty()) {
            throw new RecordNotFoundException("Error, activity not found");
        }
        Activity activity = optionalActivity.get();
        activity.setProject(activityDto.project());
        activity.setTestName(activityDto.testName());
        activity.setTestType(activityDto.testType());
        activity.setTestDate(activityDto.testDate());
        activity.setTestStartTime(activityDto.testStartTime());
        activity.setTestEndTime(activityDto.testEndTime());
        activity.setTestLocation(activityDto.testLocation());
        activity.setPurposeOfTest(activityDto.purposeOfTest());
        activity.setConductedBy(activityDto.conductedBy());
        activity.setMaterials(activityDto.materials());
        activity.setTestParameters(activityDto.parameters());

        activity = activityService.save(activity);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(activity));

    }

    @Override
    public ApiResponse<ActivityDto> deleteById(Long id) {
        Optional<Activity> optionalActivity = activityService.findById(id);
        if (optionalActivity.isEmpty()) {
            throw new RecordNotFoundException("Activity not found.");
        }
        activityService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    private List<Parameter> buildParameters(ActivityRequest request, Activity activity) {
        return request.getParameters()
                .stream()
                .map(param -> Parameter.builder().name(param).activity(activity).build())
                .collect(Collectors.toList());
    }

    private List<Material> buildMaterials(ActivityRequest request, Activity activity) {
        return request.getMaterials()
                .stream()
                .map(material -> Material.builder().name(material).activity(activity).build())
                .collect(Collectors.toList());
    }
}
