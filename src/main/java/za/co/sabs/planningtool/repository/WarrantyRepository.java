package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.Warranty;

public interface WarrantyRepository extends JpaRepository<@NonNull Warranty, @NonNull Long> {
}
