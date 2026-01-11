package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.sabs.planningtool.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<@NonNull Permission, @NonNull Long> {
}
