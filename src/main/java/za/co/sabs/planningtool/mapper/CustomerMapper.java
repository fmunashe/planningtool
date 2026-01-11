package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Component;
import za.co.sabs.planningtool.dto.CustomerDto;
import za.co.sabs.planningtool.entity.Customer;

import java.util.function.Function;

@Component
public class CustomerMapper implements Function<Customer, CustomerDto> {
    @Override
    public CustomerDto apply(Customer customer) {
        return new CustomerDto(customer.getCustomerId(), customer.getName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAccountNumber());
    }
}
