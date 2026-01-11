package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.PriorityDto;
import za.co.sabs.planningtool.entity.Priority;

import java.util.function.Function;

@Service
public class PriorityMapper implements Function<Priority, PriorityDto> {
    @Override
    public PriorityDto apply(Priority priority) {
        return new PriorityDto(
                priority.getId(),
                priority.getLevel(),
                priority.getCreatedAt(),
                priority.getUpdatedAt()
        );
    }
}
