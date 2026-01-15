package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Parameter;
import za.co.sabs.planningtool.repository.ParameterRepository;
import za.co.sabs.planningtool.service.ParameterService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class ParameterServiceImpl implements ParameterService {
    private final ParameterRepository parameterRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public List<Parameter> findAll() {
        return parameterRepository.findAll();
    }

    @Override
    public Page<@NonNull Parameter> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return parameterRepository.findAll(pageable);
    }

    @Override
    public Optional<Parameter> findById(Long id) {
        return parameterRepository.findById(id);
    }

    @Override
    public Parameter save(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    @Override
    public List<Parameter> saveAll(List<Parameter> parameters) {
        return parameterRepository.saveAll(parameters);
    }

    @Override
    public void deleteById(Long id) {
        parameterRepository.deleteById(id);
    }
}
