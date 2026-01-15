package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Material;
import za.co.sabs.planningtool.repository.MaterialRepository;
import za.co.sabs.planningtool.service.MaterialService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public List<Material> saveAll(List<Material> materials) {
        return materialRepository.saveAll(materials);
    }

    @Override
    public Page<@NonNull Material> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return materialRepository.findAll(pageable);
    }

    @Override
    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public void deleteById(Long id) {
        materialRepository.deleteById(id);
    }
}
