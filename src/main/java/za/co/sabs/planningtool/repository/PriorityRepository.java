package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.sabs.planningtool.entity.Priority;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<@NonNull Priority, @NonNull Long> {
    Optional<Priority> findByLevel(String level);
}
