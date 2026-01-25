package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.Consumable;

public interface ConsumableRepository extends JpaRepository<@NonNull Consumable, @NonNull Long> {
}
