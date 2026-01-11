package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.sabs.planningtool.entity.JobCard;

@Repository
public interface JobCardRepository extends JpaRepository<@NonNull JobCard, @NonNull Long> {
}
