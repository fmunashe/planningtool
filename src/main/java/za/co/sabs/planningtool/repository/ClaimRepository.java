package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.Claim;

public interface ClaimRepository extends JpaRepository<@NonNull Claim, @NonNull Long> {
}
