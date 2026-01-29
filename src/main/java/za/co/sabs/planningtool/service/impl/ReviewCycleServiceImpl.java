package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.ReviewCycle;
import za.co.sabs.planningtool.repository.ReviewCycleRepository;
import za.co.sabs.planningtool.service.ReviewCycleService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewCycleServiceImpl implements ReviewCycleService {
    private final ReviewCycleRepository reviewCycleRepository;

    public ReviewCycleServiceImpl(ReviewCycleRepository reviewCycleRepository) {
        this.reviewCycleRepository = reviewCycleRepository;
    }

    @Override
    public List<ReviewCycle> findAll() {
        return reviewCycleRepository.findAll();
    }

    @Override
    public Page<@NonNull ReviewCycle> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return reviewCycleRepository.findAll(pageable);
    }

    @Override
    public Optional<ReviewCycle> findById(Long id) {
        return reviewCycleRepository.findById(id);
    }

    @Override
    public ReviewCycle save(ReviewCycle reviewCycle) {
        return reviewCycleRepository.save(reviewCycle);
    }

    @Override
    public void deleteById(Long id) {
        reviewCycleRepository.deleteById(id);
    }
}
