package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.ReviewCycleDto;
import za.co.sabs.planningtool.entity.ReviewCycle;

import java.util.function.Function;

@Service
public class ReviewCycleMapper implements Function<ReviewCycle, ReviewCycleDto> {

    @Override
    public ReviewCycleDto apply(ReviewCycle reviewCycle) {
        return new ReviewCycleDto(
                reviewCycle.getId(),
                reviewCycle.getName(),
                reviewCycle.getDescription(),
                reviewCycle.getWarrantNumber(),
                reviewCycle.getWarrantType(),
                reviewCycle.getCoverageStartDate(),
                reviewCycle.getCoverageEndDate(),
                reviewCycle.getCreatedBy(),
                reviewCycle.isActive(),
                reviewCycle.getCreatedAt(),
                reviewCycle.getUpdatedAt()
        );
    }
}
