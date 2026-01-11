package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.UrgencyDto;
import za.co.sabs.planningtool.entity.Urgency;

import java.util.function.Function;

@Service
public class UrgencyMapper implements Function<Urgency, UrgencyDto> {
    @Override
    public UrgencyDto apply(Urgency urgency) {
        return new UrgencyDto(
                urgency.getId(),
                urgency.getName(),
                urgency.getCreatedAt(),
                urgency.getUpdatedAt()
        );
    }
}
