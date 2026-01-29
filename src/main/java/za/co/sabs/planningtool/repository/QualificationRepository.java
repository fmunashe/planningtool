package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.Qualification;

public interface QualificationRepository extends JpaRepository<@NonNull Qualification, @NonNull Long> {
}
