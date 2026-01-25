package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Consumable;
import za.co.sabs.planningtool.repository.ConsumableRepository;
import za.co.sabs.planningtool.service.ConsumableService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumableServiceImpl implements ConsumableService {
    private final ConsumableRepository consumableRepository;

    public ConsumableServiceImpl(ConsumableRepository consumableRepository) {
        this.consumableRepository = consumableRepository;
    }

    @Override
    public List<Consumable> findAll() {
        return consumableRepository.findAll();
    }

    @Override
    public Page<@NonNull Consumable> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return consumableRepository.findAll(pageable);
    }

    @Override
    public Optional<Consumable> findById(Long id) {
        return consumableRepository.findById(id);
    }

    @Override
    public Consumable save(Consumable consumable) {
        return consumableRepository.save(consumable);
    }

    @Override
    public void deleteById(Long id) {
        consumableRepository.deleteById(id);
    }
}
