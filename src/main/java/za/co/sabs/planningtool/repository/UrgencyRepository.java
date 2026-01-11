package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.sabs.planningtool.entity.Urgency;

import java.util.Optional;

@Repository
public interface UrgencyRepository extends JpaRepository<@NonNull Urgency,@NonNull Long> {

    Optional<Urgency> findByName(String name);
}
