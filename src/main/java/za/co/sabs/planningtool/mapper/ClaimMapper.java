package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.ClaimDto;
import za.co.sabs.planningtool.entity.Claim;

import java.util.function.Function;

@Service
public class ClaimMapper implements Function<Claim, ClaimDto> {

    @Override
    public ClaimDto apply(Claim claim) {
        return new ClaimDto(
                claim.getId(),
                claim.getName(),
                claim.getClaimNumber(),
                claim.getClaimType(),
                claim.getClaimDate(),
                claim.getPlannedDate(),
                claim.getReturnDate(),
                claim.getCreatedBy(),
                claim.getActive(),
                claim.getDescription(),
                claim.getDocument(),
                claim.getPerson()
        );
    }

}
