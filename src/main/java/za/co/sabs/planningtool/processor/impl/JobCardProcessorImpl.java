package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.*;
import za.co.sabs.planningtool.entity.*;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.ActivityMapper;
import za.co.sabs.planningtool.mapper.JobCardMapper;
import za.co.sabs.planningtool.mapper.LaboratoryDtoMapper;
import za.co.sabs.planningtool.processor.ActivityProcessor;
import za.co.sabs.planningtool.processor.JobCardProcessor;
import za.co.sabs.planningtool.service.*;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.JobCardRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobCardProcessorImpl implements JobCardProcessor {
    private final JobCardService jobCardService;
    private final JobCardMapper jobCardMapper;
    private final LaboratoryService laboratoryService;
    private final ActivityProcessor activityProcessor;
    private final ActivityMapper activityMapper;
    private final ConsumableService consumableService;
    private final TestMethodService testMethodService;
    private final EquipmentService equipmentService;
    private final UserService userService;
    private final LaboratoryDtoMapper laboratoryMapper;

    public JobCardProcessorImpl(JobCardService jobCardService, JobCardMapper jobCardMapper, LaboratoryService laboratoryService, ActivityProcessor activityProcessor, ActivityMapper activityMapper, ConsumableService consumableService, TestMethodService testMethodService, EquipmentService equipmentService, UserService userService, LaboratoryDtoMapper laboratoryMapper) {
        this.jobCardService = jobCardService;
        this.jobCardMapper = jobCardMapper;
        this.laboratoryService = laboratoryService;
        this.activityProcessor = activityProcessor;
        this.activityMapper = activityMapper;
        this.consumableService = consumableService;
        this.testMethodService = testMethodService;
        this.equipmentService = equipmentService;
        this.userService = userService;
        this.laboratoryMapper = laboratoryMapper;
    }

    @Override
    public ApiResponse<JobCardDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull JobCard> jobCards = jobCardService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(jobCards, jobCardMapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<JobCardDto> findById(Long id) {
        JobCard jobCard = jobCardService.findById(id).orElseThrow(() -> new RecordNotFoundException(AppConstants.NOT_FOUND_MESSAGE));
        return HelperResponse.buildApiResponse(null, jobCardMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, jobCardMapper.apply(jobCard));
    }

    @Override
    public ApiResponse<JobCardDto> save(JobCardRequest jobCardRequest) {
        Laboratory laboratory = laboratoryService.findById(jobCardRequest.getLaboratoryId()).orElseThrow(() -> new RecordNotFoundException("Laboratory provided for this jobcard could not be found"));
        JobCard jobCard = JobCard.builder()
                .jobNo(jobCardRequest.getJobNo())
                .customerName(jobCardRequest.getCustomerName())
                .soNumber(jobCardRequest.getSoNumber())
                .sampleReceiptNo(jobCardRequest.getSampleReceiptNo())
                .accountStatus(jobCardRequest.getAccountStatus())
                .poNumber(jobCardRequest.getPoNumber())
                .centralReceiptNo(jobCardRequest.getCentralReceiptNo())
                .sampleLocation(jobCardRequest.getSampleLocation())
                .turnAroundTime(jobCardRequest.getTurnAroundTime())
                .isOutsource(jobCardRequest.isOutsource())
                .phase(jobCardRequest.getPhase())
                .sequence(jobCardRequest.getSequence())
                .createdBy(jobCardRequest.getCreatedBy())
                .active(jobCardRequest.isActive())
                .laboratory(laboratory)
                .activities(createActivity(jobCardRequest))
                .consumables(getConsumables(jobCardRequest.getConsumableIds()))
                .testingMethods(getTestMethods(jobCardRequest.getTestingMethodIds()))
                .equipments(getEquipments(jobCardRequest.getEquipmentIds()))
                .managers(getManagers(jobCardRequest.getManagerIds()))
                .personnel(getPersonnel(jobCardRequest.getPersonnelIds()))
                .build();

        jobCard = jobCardService.save(jobCard);
        return HelperResponse.buildApiResponse(null, jobCardMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, jobCardMapper.apply(jobCard));
    }

    @Override
    public ApiResponse<JobCardDto> update(Long id, JobCardDto jobCardDto) {
        JobCard jobCard = jobCardService.findById(id).orElseThrow(() -> new RecordNotFoundException(AppConstants.NOT_FOUND_MESSAGE));
        jobCard.setJobNo(jobCardDto.jobNo());
        jobCard.setCustomerName(jobCardDto.customerName());
        jobCard.setSoNumber(jobCardDto.soNumber());
        jobCard.setSampleReceiptNo(jobCardDto.sampleReceiptNo());
        jobCard.setAccountStatus(jobCardDto.accountStatus());
        jobCard.setPoNumber(jobCardDto.poNumber());
        jobCard.setPoNumber(jobCardDto.poNumber());
        jobCard.setCentralReceiptNo(jobCardDto.centralReceiptNo());
        jobCard.setSampleLocation(jobCardDto.sampleLocation());
        jobCard.setTurnAroundTime(jobCardDto.turnAroundTime());
        jobCard.setIsOutsource(jobCardDto.isOutsource());
        jobCard.setPhase(jobCardDto.phase());
        jobCard.setSequence(jobCardDto.sequence());
        jobCard.setCreatedBy(jobCardDto.createdBy());
        jobCard.setActive(jobCardDto.active());
        jobCard.setLaboratory(laboratoryMapper.toEntity(jobCardDto.laboratory()));
        jobCard.setActivities(getActivities(jobCardDto.activities()));
        jobCard.setConsumables(getConsumables(jobCardDto.consumables().stream().map(ConsumableDto::id).collect(Collectors.toSet())));
        jobCard.setTestingMethods(getTestMethods(jobCardDto.testingMethods().stream().map(TestingMethodDto::id).collect(Collectors.toSet())));
        jobCard.setEquipments(getEquipments(jobCardDto.equipments().stream().map(EquipmentDto::id).collect(Collectors.toSet())));
        jobCard.setManagers(getManagers(jobCardDto.managers().stream().map(UserDto::id).collect(Collectors.toSet())));
        jobCard.setPersonnel(getPersonnel(jobCardDto.personnel().stream().map(UserDto::id).collect(Collectors.toSet())));
        jobCard = jobCardService.save(jobCard);
        return HelperResponse.buildApiResponse(null, jobCardMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, jobCardMapper.apply(jobCard));
    }

    @Override
    public ApiResponse<JobCardDto> deleteById(Long id) {
        JobCard jobCard = jobCardService.findById(id).orElseThrow(() -> new RecordNotFoundException(AppConstants.NOT_FOUND_MESSAGE));
        jobCardService.deleteById(id);
        return HelperResponse.buildApiResponse(null, jobCardMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, jobCardMapper.apply(jobCard));
    }

    private List<Activity> createActivity(JobCardRequest jobCardRequest) {
        List<Activity> activities = new ArrayList<>();
        jobCardRequest.getActivities().forEach(activityRequest -> {
            ApiResponse<ActivityDto> activityDto = activityProcessor.save(activityRequest);
            Activity activity = activityMapper.toEntity(activityDto.getResponseDTO());
            activities.add(activity);
        });
        return activities;
    }

    private Set<Consumable> getConsumables(Set<Long> consumables) {
        Set<Consumable> consumableList = new HashSet<>();
        consumables.forEach(consumable -> {
            Consumable consume = consumableService.findById(consumable).orElseThrow(() -> new RecordNotFoundException("Consumable ID provided not found"));
            consumableList.add(consume);
        });
        return consumableList;
    }

    private Set<TestingMethod> getTestMethods(Set<Long> testMethods) {
        Set<TestingMethod> testingMethodsList = new HashSet<>();
        testMethods.forEach(method -> {
            TestingMethod testingMethod = testMethodService.findById(method).orElseThrow(() -> new RecordNotFoundException("Testing method ID provided not found"));
            testingMethodsList.add(testingMethod);
        });
        return testingMethodsList;
    }

    private Set<Equipment> getEquipments(Set<Long> equipments) {
        Set<Equipment> equipmentList = new HashSet<>();
        equipments.forEach(equip -> {
            Equipment equipment = equipmentService.findById(equip).orElseThrow(() -> new RecordNotFoundException("Equipment ID provided not found"));
            equipmentList.add(equipment);
        });
        return equipmentList;
    }

    private Set<User> getManagers(Set<Long> managers) {
        Set<User> managersList = new HashSet<>();
        managers.forEach(manage -> {
            User user = userService.findById(manage).orElseThrow(() -> new RecordNotFoundException("Manager ID provided not found"));
            managersList.add(user);
        });
        return managersList;
    }

    private Set<User> getPersonnel(Set<Long> personnel) {
        Set<User> personnelList = new HashSet<>();
        personnel.forEach(person -> {
            User user = userService.findById(person).orElseThrow(() -> new RecordNotFoundException("Personnel ID provided not found"));
            personnelList.add(user);
        });
        return personnelList;
    }

    private List<Activity> getActivities(List<ActivityDto> activityDtos) {
        List<Activity> activities = new ArrayList<>();
        activityDtos.forEach(activityDto -> {
            activities.add(activityMapper.toEntity(activityDto));
        });
        return activities;
    }
}
