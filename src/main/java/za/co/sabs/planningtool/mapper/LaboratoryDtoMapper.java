package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Component;
import za.co.sabs.planningtool.dto.LaboratoryDto;
import za.co.sabs.planningtool.entity.Laboratory;

import java.util.function.Function;

@Component
public class LaboratoryDtoMapper implements Function<Laboratory, LaboratoryDto> {
    @Override
    public LaboratoryDto apply(Laboratory laboratory) {
        return new LaboratoryDto(laboratory.getId(),
                laboratory.getLabName(),
                laboratory.getLabNumber(),
                laboratory.getCreatedAt(),
                laboratory.getUpdatedAt());
    }
}
