package za.co.sabs.planningtool.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.ReviewCycleDto;
import za.co.sabs.planningtool.entity.ReviewCycle;
import za.co.sabs.planningtool.utils.HelperService;

import java.util.function.Function;

@Service
public class ReviewCycleMapper implements Function<ReviewCycle, ReviewCycleDto> {
    private final HelperService helperService;

    public ReviewCycleMapper(@Lazy HelperService helperService) {
        this.helperService = helperService;
    }

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
                helperService.getEquipment(reviewCycle.getEquipment()),
                reviewCycle.getCreatedAt(),
                reviewCycle.getUpdatedAt()
        );
    }
}
