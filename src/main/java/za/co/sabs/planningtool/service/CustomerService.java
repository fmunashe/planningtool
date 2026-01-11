package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.Customer;

import java.util.Optional;

public interface CustomerService extends BaseService<Customer> {
    Optional<Customer> findByEmail(String email);
}
