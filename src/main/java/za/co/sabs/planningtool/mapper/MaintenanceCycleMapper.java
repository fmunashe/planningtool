package za.co.sabs.planningtool.mapper;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.MaintenanceCycleDto;
import za.co.sabs.planningtool.entity.MaintenanceCycle;
import za.co.sabs.planningtool.utils.HelperService;

import java.util.function.Function;

@Service
public class MaintenanceCycleMapper implements Function<MaintenanceCycle, MaintenanceCycleDto> {
    private final HelperService helperService;

    public MaintenanceCycleMapper(@Lazy HelperService helperService) {
        this.helperService = helperService;
    }

    @Override
    public MaintenanceCycleDto apply(MaintenanceCycle maintenanceCycle) {
        return new MaintenanceCycleDto(
                maintenanceCycle.getId(),
                maintenanceCycle.getMaintenanceCycleNumber(),
                maintenanceCycle.getMaintenanceCycleType(),
                maintenanceCycle.getFrequency(),
                maintenanceCycle.getPlannedDate(),
                maintenanceCycle.getReturnDate(),
                maintenanceCycle.getCreatedBy(),
                maintenanceCycle.getActive(),
                maintenanceCycle.getSupplier(),
                maintenanceCycle.getDescription(),
                helperService.getEquipment(maintenanceCycle.getEquipment()),
                maintenanceCycle.getCreatedAt(),
                maintenanceCycle.getUpdatedAt()
        );
    }
}
