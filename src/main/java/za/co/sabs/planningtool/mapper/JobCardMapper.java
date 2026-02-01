package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.JobCardDto;
import za.co.sabs.planningtool.entity.JobCard;

import java.util.function.Function;

@Service
public class JobCardMapper implements Function<JobCard, JobCardDto> {
    private final ActivityMapper activityMapper;
    private final LaboratoryDtoMapper laboratoryMapper;
    private final UserMapper userMapper;
    private final ConsumableMapper consumableMapper;
    private final EquipmentMapper equipmentMapper;
    private final TestingMethodMapper testingMethodMapper;

    public JobCardMapper(ActivityMapper activityMapper, LaboratoryDtoMapper laboratoryMapper, UserMapper userMapper, ConsumableMapper consumableMapper, EquipmentMapper equipmentMapper, TestingMethodMapper testingMethodMapper) {
        this.activityMapper = activityMapper;
        this.laboratoryMapper = laboratoryMapper;
        this.userMapper = userMapper;
        this.consumableMapper = consumableMapper;
        this.equipmentMapper = equipmentMapper;
        this.testingMethodMapper = testingMethodMapper;
    }

    @Override
    public JobCardDto apply(JobCard jobCard) {

        return new JobCardDto(
                jobCard.getId(),
                jobCard.getJobNo(),
                jobCard.getCustomerName(),
                jobCard.getSoNumber(),
                jobCard.getSampleReceiptNo(),
                jobCard.getAccountStatus(),
                jobCard.getPoNumber(),
                jobCard.getCentralReceiptNo(),
                jobCard.getSampleLocation(),
                jobCard.getTurnAroundTime(),
                jobCard.getIsOutsource(),
                jobCard.getPhase(),
                jobCard.getSequence(),
                jobCard.getCreatedBy(),
                jobCard.isActive(),
                laboratoryMapper.apply(jobCard.getLaboratory()),
                jobCard.getManagers().stream().map(userMapper).toList(),
                jobCard.getPersonnel().stream().map(userMapper).toList(),
                jobCard.getActivities().stream().map(activityMapper).toList(),
                jobCard.getConsumables().stream().map(consumableMapper).toList(),
                jobCard.getTestingMethods().stream().map(testingMethodMapper).toList(),
                jobCard.getEquipments().stream().map(equipmentMapper).toList(),
                jobCard.getCreatedAt(),
                jobCard.getUpdatedAt()
        );
    }
}
