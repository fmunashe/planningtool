package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.CustomerDto;
import za.co.sabs.planningtool.entity.Customer;
import za.co.sabs.planningtool.mapper.CustomerMapper;
import za.co.sabs.planningtool.processor.CustomerProcessor;
import za.co.sabs.planningtool.service.CustomerService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.CustomerRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class CustomerProcessorImpl implements CustomerProcessor {

    private final CustomerService service;
    private final CustomerMapper mapper;

    public CustomerProcessorImpl(CustomerService service, CustomerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<CustomerDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Customer> customerPage = service.findAll(pageNo, pageSize);

        return HelperResponse.buildApiResponse(customerPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CustomerDto> findById(Long id) {
        Optional<Customer> optionalCustomer = service.findById(id);

        return null;
    }

    @Override
    public ApiResponse<CustomerDto> save(CustomerRequest customerRequest) {
        return null;
    }

    @Override
    public ApiResponse<CustomerDto> update(Long id, CustomerDto customerDto) {
        return null;
    }

    @Override
    public ApiResponse<CustomerDto> deleteById(Long id) {
        return null;
    }
}
