package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<@NonNull Equipment,@NonNull Long> {
}
