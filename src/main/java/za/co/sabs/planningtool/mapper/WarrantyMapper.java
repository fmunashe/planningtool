package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.WarrantyDto;
import za.co.sabs.planningtool.entity.Warranty;

import java.util.function.Function;

@Service
public class WarrantyMapper implements Function<Warranty, WarrantyDto> {
    private final EquipmentMapper equipmentMapper;

    public WarrantyMapper(EquipmentMapper equipmentMapper) {
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public WarrantyDto apply(Warranty warranty) {
        return new WarrantyDto(
                warranty.getId(),
                warranty.getName(),
                warranty.getWarrantNumber(),
                warranty.getWarrantyType(),
                warranty.getCoverageStartDate(),
                warranty.getCoverageEndDate(),
                warranty.getDescription(),
                warranty.getCreatedBy(),
                warranty.getActive(),
                equipmentMapper.apply(warranty.getEquipment()),
                warranty.getCreatedAt(),
                warranty.getUpdatedAt()
        );
    }
}
