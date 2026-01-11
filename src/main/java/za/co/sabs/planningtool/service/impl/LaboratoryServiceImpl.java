package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Laboratory;
import za.co.sabs.planningtool.repository.LaboratoryRepository;
import za.co.sabs.planningtool.service.LaboratoryService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {
    private final LaboratoryRepository repository;

    public LaboratoryServiceImpl(LaboratoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Laboratory> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<@NonNull Laboratory> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Laboratory> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Laboratory> findByName(String labName) {
        return repository.findByLabName(labName);
    }

    @Override
    public Laboratory save(Laboratory laboratory) {
        return repository.save(laboratory);
    }


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
