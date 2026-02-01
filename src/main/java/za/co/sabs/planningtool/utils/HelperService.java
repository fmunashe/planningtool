package za.co.sabs.planningtool.utils;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.*;
import za.co.sabs.planningtool.entity.*;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.*;
import za.co.sabs.planningtool.service.ConsumableService;
import za.co.sabs.planningtool.service.EquipmentService;
import za.co.sabs.planningtool.service.TestMethodService;
import za.co.sabs.planningtool.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HelperService {
    private final ConsumableService consumableService;
    private final TestMethodService testMethodService;
    private final EquipmentService equipmentService;
    private final UserService userService;
    private final ActivityMapper activityMapper;
    private final WarrantyMapper warrantyMapper;
    private final MaintenanceCycleMapper maintenanceCycleMapper;
    private final ClaimMapper claimMapper;
    private final CalibrationMapper calibrationMapper;
    private final ReviewCycleMapper reviewCycleMapper;
    private final JobCardMapper jobCardMapper;

    public HelperService(ConsumableService consumableService, TestMethodService testMethodService, EquipmentService equipmentService, UserService userService, ActivityMapper activityMapper, WarrantyMapper warrantyMapper, MaintenanceCycleMapper maintenanceCycleMapper, ClaimMapper claimMapper, CalibrationMapper calibrationMapper, ReviewCycleMapper reviewCycleMapper, JobCardMapper jobCardMapper) {
        this.consumableService = consumableService;
        this.testMethodService = testMethodService;
        this.equipmentService = equipmentService;
        this.userService = userService;
        this.activityMapper = activityMapper;
        this.warrantyMapper = warrantyMapper;
        this.maintenanceCycleMapper = maintenanceCycleMapper;
        this.claimMapper = claimMapper;
        this.calibrationMapper = calibrationMapper;
        this.reviewCycleMapper = reviewCycleMapper;
        this.jobCardMapper = jobCardMapper;
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

    public List<WarrantyDto> mapWarranties(List<Warranty> warranties) {
        if (warranties == null || warranties.isEmpty()) {
            return Collections.emptyList();
        }
        return warranties.stream().map(warrantyMapper).toList();
    }

    public List<MaintenanceCycleDto> mapMaintenanceCycles(List<MaintenanceCycle> maintenanceCycles) {
        if (maintenanceCycles == null || maintenanceCycles.isEmpty()) {
            return Collections.emptyList();
        }
        return maintenanceCycles.stream().map(maintenanceCycleMapper).toList();
    }

    public List<ClaimDto> mapClaims(List<Claim> claims) {
        if (claims == null || claims.isEmpty()) {
            return Collections.emptyList();
        }
        return claims.stream().map(claimMapper).toList();
    }

    public List<CalibrationDto> mapCalibrations(List<Calibration> calibrations) {
        if (calibrations == null || calibrations.isEmpty()) {
            return Collections.emptyList();
        }
        return calibrations.stream().map(calibrationMapper).toList();
    }

    public List<ReviewCycleDto> mapReviewCycles(List<ReviewCycle> reviewCycles) {
        if (reviewCycles == null || reviewCycles.isEmpty()) {
            return Collections.emptyList();
        }
        return reviewCycles.stream().map(reviewCycleMapper).toList();
    }

    public List<JobCardDto> mapJobCards(Set<JobCard> jobCards) {
        if (jobCards == null || jobCards.isEmpty()) {
            return Collections.emptyList();
        }
        return jobCards.stream().map(jobCardMapper).toList();
    }

    public EquipmentDto getEquipment(Equipment equipment) {
        return new EquipmentDto(
                equipment.getId(),
                equipment.getEquipmentNumber(),
                equipment.getName(),
                equipment.getSerialNumber(),
                equipment.getLocation(),
                equipment.getModelNumber(),
                equipment.getModelName(),
                equipment.getCost(),
                equipment.getDescription(),
                equipment.getCreatedBy(),
                equipment.getActive(),
                equipment.getManufacturer(),
                equipment.getSupplier(),
                equipment.getInstallationDate(),
                equipment.getPurchaseDate(),
                equipment.getExpirationDate(),
                this.mapWarranties(equipment.getWarranties()),
                this.mapMaintenanceCycles(equipment.getMaintenanceCycles()),
                this.mapClaims(equipment.getClaims()),
                this.mapCalibrations(equipment.getCalibrations()),
                this.mapJobCards(equipment.getJobCards()),
                this.mapReviewCycles(equipment.getReviewCycles()),
                equipment.getCreatedAt(),
                equipment.getUpdatedAt());
    }
}
