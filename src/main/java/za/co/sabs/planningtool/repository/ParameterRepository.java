package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.Parameter;

public interface ParameterRepository extends JpaRepository<@NonNull Parameter, @NonNull Long> {
}
