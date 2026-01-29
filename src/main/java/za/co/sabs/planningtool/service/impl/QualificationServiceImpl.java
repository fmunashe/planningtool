package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Qualification;
import za.co.sabs.planningtool.repository.QualificationRepository;
import za.co.sabs.planningtool.service.QualificationService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class QualificationServiceImpl implements QualificationService {
    private final QualificationRepository qualificationRepository;

    public QualificationServiceImpl(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    @Override
    public List<Qualification> findAll() {
        return qualificationRepository.findAll();
    }

    @Override
    public Page<@NonNull Qualification> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return qualificationRepository.findAll(pageable);
    }

    @Override
    public Optional<Qualification> findById(Long id) {
        return qualificationRepository.findById(id);
    }

    @Override
    public Qualification save(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    @Override
    public void deleteById(Long id) {
        qualificationRepository.deleteById(id);
    }
}
