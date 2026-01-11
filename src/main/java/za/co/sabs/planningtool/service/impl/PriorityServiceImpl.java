package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Priority;
import za.co.sabs.planningtool.repository.PriorityRepository;
import za.co.sabs.planningtool.service.PriorityService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityServiceImpl implements PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public Optional<Priority> findByLevel(String level) {
        return priorityRepository.findByLevel(level);
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    @Override
    public Page<@NonNull Priority> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return priorityRepository.findAll(pageable);
    }

    @Override
    public Optional<Priority> findById(Long id) {
        return priorityRepository.findById(id);
    }

    @Override
    public Priority save(Priority priority) {
        return priorityRepository.save(priority);
    }

    @Override
    public void deleteById(Long id) {
        priorityRepository.deleteById(id);
    }
}
