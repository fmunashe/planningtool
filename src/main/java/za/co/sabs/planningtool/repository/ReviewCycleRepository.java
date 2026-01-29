package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.ReviewCycle;

public interface ReviewCycleRepository extends JpaRepository<@NonNull ReviewCycle, @NonNull Long> {
}
