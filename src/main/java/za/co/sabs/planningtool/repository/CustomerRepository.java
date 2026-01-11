package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.sabs.planningtool.entity.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<@NonNull Customer, @NonNull Long> {
    Optional<Customer> findByEmail(String username);
}
