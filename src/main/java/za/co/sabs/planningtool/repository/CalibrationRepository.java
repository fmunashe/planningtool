package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.Calibration;

public interface CalibrationRepository extends JpaRepository<@NonNull Calibration, @NonNull Long> {
}
