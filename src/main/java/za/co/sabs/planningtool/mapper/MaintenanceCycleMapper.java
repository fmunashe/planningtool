package za.co.sabs.planningtool.mapper;

import io.swagger.v3.oas.annotations.servers.Server;
import za.co.sabs.planningtool.dto.MaintenanceCycleDto;
import za.co.sabs.planningtool.entity.MaintenanceCycle;

import java.util.function.Function;

@Server
public class MaintenanceCycleMapper implements Function<MaintenanceCycle, MaintenanceCycleDto> {

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
                maintenanceCycle.getCreatedAt(),
                maintenanceCycle.getUpdatedAt()
        );
    }
}
