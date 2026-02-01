package za.co.sabs.planningtool.mapper;

import org.springframework.context.annotation.Lazy;
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

    public LaboratoryDtoMapper(@Lazy JobCardMapper jobCardMapper) {
        this.jobCardMapper = jobCardMapper;
    }

    @Override
    public LaboratoryDto apply(Laboratory laboratory) {
        return new LaboratoryDto(
                laboratory.getId(),
                laboratory.getLabName(),
                laboratory.getLabNumber(),
                laboratory.getIsActive(),
                laboratory.getCreatedBy(),
                mapJobCards(laboratory.getJobCards()),
                laboratory.getCreatedAt(),
                laboratory.getUpdatedAt());
    }

    public Laboratory toEntity(LaboratoryDto dto){
        if (dto == null) return null;
        Laboratory lab =  new Laboratory();
        lab.setId(dto.id());
        lab.setLabNumber(dto.labName());
        lab.setLabNumber(dto.labNumber());
        lab.setIsActive(dto.isActive());
        lab.setCreatedBy(dto.createdBy());
        lab.setCreatedAt(dto.createdAt());
        lab.setUpdatedAt(dto.updatedAt());
        return lab;
    }

    private List<JobCardDto> mapJobCards(List<JobCard> jobCards) {
        return jobCards.stream().map(jobCardMapper).collect(Collectors.toList());
    }
}
