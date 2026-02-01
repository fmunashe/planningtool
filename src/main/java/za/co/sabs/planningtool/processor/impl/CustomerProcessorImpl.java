package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.CustomerDto;
import za.co.sabs.planningtool.entity.Customer;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
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
        return optionalCustomer.map(customer -> HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(customer)))
                .orElseThrow(() -> new RecordNotFoundException("Failed to find a customer record with Id of " + id));

    }

    @Override
    public ApiResponse<CustomerDto> save(CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = service.findByEmail(customerRequest.getEmail());
        if (optionalCustomer.isPresent()) {
            throw new RecordNotFoundException("Error, there is another customer with email " + customerRequest.getEmail());
        }
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setAccountNumber(customerRequest.getAccountNumber());
        customer.setCustomerId(customerRequest.getCustomerId());
        customer = service.save(customer);
        return HelperResponse.buildApiResponse(null, mapper, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(customer));
    }

    @Override
    public ApiResponse<CustomerDto> update(Long id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = service.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new RecordNotFoundException("Error, no customer record found with Id " + id);
        }
        Customer customer = optionalCustomer.get();
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setPhoneNumber(customerDto.phoneNumber());
        customer.setAccountNumber(customerDto.accountNumber());
        customer.setCustomerId(customerDto.customerId());
        customer = service.save(customer);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(customer));
    }

    @Override
    public ApiResponse<CustomerDto> deleteById(Long id) {
        Optional<Customer> optionalCustomer = service.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new RecordNotFoundException("Customer not found.");
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }
}
