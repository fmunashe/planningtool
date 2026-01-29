package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Warranty;
import za.co.sabs.planningtool.repository.WarrantyRepository;
import za.co.sabs.planningtool.service.WarrantyService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class WarrantyServiceImpl implements WarrantyService {
    private final WarrantyRepository warrantyRepository;

    public WarrantyServiceImpl(WarrantyRepository warrantyRepository) {
        this.warrantyRepository = warrantyRepository;
    }

    @Override
    public List<Warranty> findAll() {
        return warrantyRepository.findAll();
    }

    @Override
    public Page<@NonNull Warranty> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return warrantyRepository.findAll(pageable);
    }

    @Override
    public Optional<Warranty> findById(Long id) {
        return warrantyRepository.findById(id);
    }

    @Override
    public Warranty save(Warranty warranty) {
        return warrantyRepository.save(warranty);
    }

    @Override
    public void deleteById(Long id) {
        warrantyRepository.deleteById(id);
    }
}
