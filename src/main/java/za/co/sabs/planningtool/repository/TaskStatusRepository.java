package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.sabs.planningtool.entity.TaskStatus;

import java.util.Optional;

@Repository
public interface TaskStatusRepository extends JpaRepository<@NonNull TaskStatus, @NonNull Long> {
    Optional<TaskStatus> findByStatus(String status);
}
