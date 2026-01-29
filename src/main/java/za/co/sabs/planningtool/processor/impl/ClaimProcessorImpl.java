package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.ClaimDto;
import za.co.sabs.planningtool.dto.EquipmentDto;
import za.co.sabs.planningtool.entity.Claim;
import za.co.sabs.planningtool.entity.Equipment;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.ClaimMapper;
import za.co.sabs.planningtool.mapper.EquipmentMapper;
import za.co.sabs.planningtool.processor.ClaimProcessor;
import za.co.sabs.planningtool.service.ClaimService;
import za.co.sabs.planningtool.service.EquipmentService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.ClaimRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class ClaimProcessorImpl implements ClaimProcessor {
    private final ClaimService claimService;
    private final ClaimMapper claimMapper;
    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;

    public ClaimProcessorImpl(ClaimService claimService, ClaimMapper claimMapper, EquipmentService equipmentService, EquipmentMapper equipmentMapper) {
        this.claimService = claimService;
        this.claimMapper = claimMapper;
        this.equipmentService = equipmentService;
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public ApiResponse<ClaimDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Claim> claims = claimService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(claims, claimMapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<ClaimDto> findById(Long id) {
        Optional<Claim> optionalClaim = claimService.findById(id);
        if (optionalClaim.isEmpty()) {
            throw new RecordNotFoundException("Claim not found");
        }
        return HelperResponse.buildApiResponse(null, claimMapper, false, 200, true, AppConstants.LIST_MESSAGE, claimMapper.apply(optionalClaim.get()));
    }

    @Override
    public ApiResponse<ClaimDto> save(ClaimRequest claimRequest) {
        Optional<Equipment> optionalEquipment = equipmentService.findById(claimRequest.getEquipmentId());
        if (optionalEquipment.isEmpty()) {
            throw new RecordNotFoundException("Equipment not found");
        }
        Equipment equipment = optionalEquipment.get();
        Claim claim = new Claim();
        claim.setName(claim.getName());
        claim.setDescription(claim.getDescription());
        claim.setClaimNumber(claim.getClaimNumber());
        claim.setClaimType(claim.getClaimType());
        claim.setActive(claimRequest.getActive());
        claim.setClaimDate(claimRequest.getClaimDate());
        claim.setPlannedDate(claimRequest.getPlannedDate());
        claim.setReturnDate(claimRequest.getReturnDate());
        claim.setCreatedBy(claimRequest.getCreatedBy());
        claim.setDocument(claimRequest.getDocument());
        claim.setPerson(claimRequest.getPerson());
        claim.setEquipment(equipment);
        claim = claimService.save(claim);
        return HelperResponse.buildApiResponse(null, claimMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, claimMapper.apply(claim));
    }

    @Override
    public ApiResponse<ClaimDto> update(Long id, ClaimDto claimDto) {
        Optional<Claim> optionalClaim = claimService.findById(id);
        if (optionalClaim.isEmpty()) {
            throw new RecordNotFoundException("Claim not found");
        }
        Claim claim = optionalClaim.get();
        claim.setName(claimDto.name());
        claim.setDescription(claimDto.description());
        claim.setClaimNumber(claim.getClaimNumber());
        claim.setClaimType(claimDto.claimType());
        claim.setActive(claimDto.active());
        claim.setClaimDate(claimDto.claimDate());
        claim.setPlannedDate(claimDto.plannedDate());
        claim.setReturnDate(claimDto.returnDate());
        claim.setCreatedBy(claimDto.createdBy());
        claim.setDocument(claimDto.document());
        claim.setPerson(claimDto.person());
        claim.setEquipment(equipmentMapper.toEntity(claimDto.equipment()));
        claim = claimService.save(claim);
        return HelperResponse.buildApiResponse(null, claimMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, claimMapper.apply(claim));
    }

    @Override
    public ApiResponse<ClaimDto> deleteById(Long id) {
        Optional<Claim> optionalClaim = claimService.findById(id);
        if (optionalClaim.isEmpty()) {
            throw new RecordNotFoundException("Claim not found");
        }
        claimService.deleteById(id);
        return HelperResponse.buildApiResponse(null, claimMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, claimMapper.apply(optionalClaim.get()));
    }
}
