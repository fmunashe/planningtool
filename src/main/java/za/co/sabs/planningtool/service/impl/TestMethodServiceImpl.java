package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.TestingMethod;
import za.co.sabs.planningtool.repository.TestMethodRepository;
import za.co.sabs.planningtool.service.TestMethodService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class TestMethodServiceImpl implements TestMethodService {
    private final TestMethodRepository testMethodRepository;

    public TestMethodServiceImpl(TestMethodRepository testMethodRepository) {
        this.testMethodRepository = testMethodRepository;
    }

    @Override
    public List<TestingMethod> findAll() {
        return testMethodRepository.findAll();
    }

    @Override
    public Page<@NonNull TestingMethod> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return testMethodRepository.findAll(pageable);
    }

    @Override
    public Optional<TestingMethod> findByName(String name) {
        return testMethodRepository.findByTestMethodName(name);
    }

    @Override
    public Optional<TestingMethod> findById(Long id) {
        return testMethodRepository.findById(id);
    }

    @Override
    public TestingMethod save(TestingMethod testingMethod) {
        return testMethodRepository.save(testingMethod);
    }

    @Override
    public void deleteById(Long id) {
        testMethodRepository.deleteById(id);
    }
}
