package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.sabs.planningtool.entity.Laboratory;

import java.util.Optional;

@Repository
public interface LaboratoryRepository extends JpaRepository<@NonNull Laboratory, @NonNull Long> {
    Optional<Laboratory> findByLabName(String name);
}
