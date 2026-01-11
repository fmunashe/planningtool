package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.sabs.planningtool.entity.TestingMethod;

@Repository
public interface TestMethodRepository extends JpaRepository<@NonNull TestingMethod, @NonNull Long> {
}
