package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Equipment;
import za.co.sabs.planningtool.repository.EquipmentRepository;
import za.co.sabs.planningtool.service.EquipmentService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Page<@NonNull Equipment> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return equipmentRepository.findAll(pageable);
    }

    @Override
    public Optional<Equipment> findById(Long id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }
}
