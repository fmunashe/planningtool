package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.JobCard;
import za.co.sabs.planningtool.repository.JobCardRepository;
import za.co.sabs.planningtool.service.JobCardService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class JobCardServiceImpl implements JobCardService {
    private final JobCardRepository jobCardRepository;

    public JobCardServiceImpl(JobCardRepository jobCardRepository) {
        this.jobCardRepository = jobCardRepository;
    }

    @Override
    public List<JobCard> findAll() {
        return jobCardRepository.findAll();
    }

    @Override
    public Page<@NonNull JobCard> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return jobCardRepository.findAll(pageable);
    }

    @Override
    public Optional<JobCard> findById(Long id) {
        return jobCardRepository.findById(id);
    }

    @Override
    public JobCard save(JobCard jobCard) {
        return jobCardRepository.save(jobCard);
    }

    @Override
    public void deleteById(Long id) {
        jobCardRepository.deleteById(id);
    }
}
