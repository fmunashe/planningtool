package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Urgency;
import za.co.sabs.planningtool.repository.UrgencyRepository;
import za.co.sabs.planningtool.service.UrgencyService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class UrgencyServiceImpl implements UrgencyService {
    private final UrgencyRepository urgencyRepository;

    public UrgencyServiceImpl(UrgencyRepository urgencyRepository) {
        this.urgencyRepository = urgencyRepository;
    }

    @Override
    public Optional<Urgency> findByName(String name) {
        return urgencyRepository.findByName(name);
    }

    @Override
    public List<Urgency> findAll() {
        return urgencyRepository.findAll();
    }

    @Override
    public Page<@NonNull Urgency> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return urgencyRepository.findAll(pageable);
    }

    @Override
    public Optional<Urgency> findById(Long id) {
        return urgencyRepository.findById(id);
    }

    @Override
    public Urgency save(Urgency urgency) {
        return urgencyRepository.save(urgency);
    }

    @Override
    public void deleteById(Long id) {
        urgencyRepository.deleteById(id);
    }
}
