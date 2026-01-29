package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.MaintenanceCycle;
import za.co.sabs.planningtool.repository.MaintenanceCycleRepository;
import za.co.sabs.planningtool.service.MaintenanceCycleService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceCycleServiceImpl implements MaintenanceCycleService {
    private final MaintenanceCycleRepository maintenanceCycleRepository;

    public MaintenanceCycleServiceImpl(MaintenanceCycleRepository maintenanceCycleRepository) {
        this.maintenanceCycleRepository = maintenanceCycleRepository;
    }

    @Override
    public List<MaintenanceCycle> findAll() {
        return maintenanceCycleRepository.findAll();
    }

    @Override
    public Page<@NonNull MaintenanceCycle> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return maintenanceCycleRepository.findAll(pageable);
    }

    @Override
    public Optional<MaintenanceCycle> findById(Long id) {
        return maintenanceCycleRepository.findById(id);
    }

    @Override
    public MaintenanceCycle save(MaintenanceCycle maintenanceCycle) {
        return maintenanceCycleRepository.save(maintenanceCycle);
    }

    @Override
    public void deleteById(Long id) {
        maintenanceCycleRepository.deleteById(id);
    }
}
