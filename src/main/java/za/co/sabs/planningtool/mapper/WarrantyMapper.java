package za.co.sabs.planningtool.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.WarrantyDto;
import za.co.sabs.planningtool.entity.Warranty;
import za.co.sabs.planningtool.utils.HelperService;

import java.util.function.Function;

@Service
public class WarrantyMapper implements Function<Warranty, WarrantyDto> {
    private final HelperService helperService;

    public WarrantyMapper(@Lazy HelperService helperService) {
        this.helperService = helperService;
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
                helperService.getEquipment(warranty.getEquipment()),
                warranty.getCreatedAt(),
                warranty.getUpdatedAt()
        );
    }
}
