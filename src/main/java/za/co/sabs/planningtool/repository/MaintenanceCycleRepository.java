package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.MaintenanceCycle;

public interface MaintenanceCycleRepository extends JpaRepository<@NonNull MaintenanceCycle, @NonNull Long> {
}
