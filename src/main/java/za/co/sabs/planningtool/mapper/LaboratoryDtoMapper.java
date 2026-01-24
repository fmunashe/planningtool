package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Component;
import za.co.sabs.planningtool.dto.JobCardDto;
import za.co.sabs.planningtool.dto.LaboratoryDto;
import za.co.sabs.planningtool.entity.JobCard;
import za.co.sabs.planningtool.entity.Laboratory;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LaboratoryDtoMapper implements Function<Laboratory, LaboratoryDto> {
    private final JobCardMapper jobCardMapper;

    public LaboratoryDtoMapper(JobCardMapper jobCardMapper) {
        this.jobCardMapper = jobCardMapper;
    }

    @Override
    public LaboratoryDto apply(Laboratory laboratory) {
        return new LaboratoryDto(laboratory.getId(),
                laboratory.getLabName(),
                laboratory.getLabNumber(),
                laboratory.getIsActive(),
                laboratory.getCreatedBy(),
                mapJobCards(laboratory.getJobCards()),
                laboratory.getCreatedAt(),
                laboratory.getUpdatedAt());
    }

    private List<JobCardDto> mapJobCards(List<JobCard> jobCards) {
        return jobCards.stream().map(jobCardMapper).collect(Collectors.toList());
    }
}
