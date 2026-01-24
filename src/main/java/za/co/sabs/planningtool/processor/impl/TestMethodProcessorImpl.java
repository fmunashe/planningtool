package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.TestingMethodDto;
import za.co.sabs.planningtool.entity.TestingMethod;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.TestingMethodMapper;
import za.co.sabs.planningtool.processor.TestMethodProcessor;
import za.co.sabs.planningtool.service.TestMethodService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.TestMethodRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class TestMethodProcessorImpl implements TestMethodProcessor {
    private final TestMethodService testMethodService;
    private final TestingMethodMapper testMethodMapper;

    public TestMethodProcessorImpl(TestMethodService testMethodService, TestingMethodMapper testMethodMapper) {
        this.testMethodService = testMethodService;
        this.testMethodMapper = testMethodMapper;
    }

    @Override
    public ApiResponse<TestingMethodDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull TestingMethod> methodPage = testMethodService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(methodPage, testMethodMapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<TestingMethodDto> findById(Long id) {
        Optional<TestingMethod> optionalTestingMethod = testMethodService.findById(id);
        if (optionalTestingMethod.isEmpty()) {
            throw new RecordNotFoundException("Failed to find testing method");
        }

        return HelperResponse.buildApiResponse(null, testMethodMapper, false, 200, true, AppConstants.FOUND_MESSAGE, testMethodMapper.apply(optionalTestingMethod.get()));
    }

    @Override
    public ApiResponse<TestingMethodDto> save(TestMethodRequest testMethodRequest) {
        TestingMethod testingMethod = new TestingMethod();
        testingMethod.setTestMethodName(testMethodRequest.getTestMethodName());
        testingMethod.setTestMethodNo(testMethodRequest.getTestMethodNo());
        testingMethod.setPhase(testMethodRequest.getPhase());
        testingMethod.setDescription(testMethodRequest.getDescription());
        testingMethod.setIsoSansCode(testMethodRequest.getIsoSansCode());
        testingMethod.setTurnAroundTime(testMethodRequest.getTurnAroundTime());
        testingMethod.setActive(testMethodRequest.getActive());
        testingMethod.setCreatedBy(testMethodRequest.getCreatedBy());
        testingMethod = testMethodService.save(testingMethod);
        return HelperResponse.buildApiResponse(null, testMethodMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, testMethodMapper.apply(testingMethod));
    }

    @Override
    public ApiResponse<TestingMethodDto> update(Long id, TestingMethodDto testingMethodDto) {
        Optional<TestingMethod> optionalTestingMethod = testMethodService.findById(id);
        if (optionalTestingMethod.isEmpty() || optionalTestingMethod.get().getId() != id) {
            throw new RecordNotFoundException("Failed to find testing method");
        }
        TestingMethod method = getTestingMethod(testingMethodDto, optionalTestingMethod);
        method = testMethodService.save(method);
        return HelperResponse.buildApiResponse(null, testMethodMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, testMethodMapper.apply(method));
    }

    private static TestingMethod getTestingMethod(TestingMethodDto testingMethodDto, Optional<TestingMethod> optionalTestingMethod) {
        TestingMethod method = optionalTestingMethod.get();
        method.setTestMethodName(testingMethodDto.testMethodName());
        method.setTestMethodNo(testingMethodDto.testMethodNo());
        method.setIsoSansCode(testingMethodDto.isoSansCode());
        method.setTurnAroundTime(testingMethodDto.turnAroundTime());
        method.setActive(testingMethodDto.active());
        method.setPhase(testingMethodDto.phase());
        method.setCreatedBy(testingMethodDto.createdBy());
        method.setDescription(testingMethodDto.description());
        return method;
    }

    @Override
    public ApiResponse<TestingMethodDto> deleteById(Long id) {
        Optional<TestingMethod> optionalTestingMethod = testMethodService.findById(id);
        if (optionalTestingMethod.isEmpty()) {
            throw new RecordNotFoundException("Failed to find testing method");
        }
        testMethodService.deleteById(id);
        return HelperResponse.buildApiResponse(null, testMethodMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }
}
