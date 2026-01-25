package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Calibration;
import za.co.sabs.planningtool.repository.CalibrationRepository;
import za.co.sabs.planningtool.service.CalibrationService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class CalibrationServiceImpl implements CalibrationService {
    private final CalibrationRepository calibrationRepository;

    public CalibrationServiceImpl(CalibrationRepository calibrationRepository) {
        this.calibrationRepository = calibrationRepository;
    }

    @Override
    public List<Calibration> findAll() {
        return calibrationRepository.findAll();
    }

    @Override
    public Page<@NonNull Calibration> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return calibrationRepository.findAll(pageable);
    }

    @Override
    public Optional<Calibration> findById(Long id) {
        return calibrationRepository.findById(id);
    }

    @Override
    public Calibration save(Calibration calibration) {
        return calibrationRepository.save(calibration);
    }

    @Override
    public void deleteById(Long id) {
        calibrationRepository.deleteById(id);
    }
}
