package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.ReviewCycleDto;
import za.co.sabs.planningtool.entity.Equipment;
import za.co.sabs.planningtool.entity.ReviewCycle;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.EquipmentMapper;
import za.co.sabs.planningtool.mapper.ReviewCycleMapper;
import za.co.sabs.planningtool.processor.ReviewCycleProcessor;
import za.co.sabs.planningtool.service.EquipmentService;
import za.co.sabs.planningtool.service.ReviewCycleService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.HelperService;
import za.co.sabs.planningtool.utils.messages.request.ReviewCycleRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

@Service
public class ReviewCycleProcessorImpl implements ReviewCycleProcessor {
    private final ReviewCycleService service;
    private final ReviewCycleMapper mapper;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentService equipmentService;
    private final HelperService helperService;

    public ReviewCycleProcessorImpl(ReviewCycleService service, ReviewCycleMapper mapper, EquipmentMapper equipmentMapper, EquipmentService equipmentService, HelperService helperService) {
        this.service = service;
        this.mapper = mapper;
        this.equipmentMapper = equipmentMapper;
        this.equipmentService = equipmentService;
        this.helperService = helperService;
    }

    @Override
    public ApiResponse<ReviewCycleDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull ReviewCycle> cycles = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(cycles, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<ReviewCycleDto> findById(Long id) {
        ReviewCycle cycle = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Review cycle not found"));
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(cycle));
    }

    @Override
    public ApiResponse<ReviewCycleDto> save(ReviewCycleRequest reviewCycleRequest) {
        Equipment equipment = equipmentService.findById(reviewCycleRequest.getEquipmentId()).orElseThrow(() -> new RecordNotFoundException("Equipment assigned to this review cycle not found"));
        ReviewCycle reviewCycle = new ReviewCycle();
        reviewCycle.setName(reviewCycleRequest.getName());
        reviewCycle.setDescription(reviewCycleRequest.getDescription());
        reviewCycle.setWarrantNumber(reviewCycleRequest.getWarrantNumber());
        reviewCycle.setWarrantType(reviewCycleRequest.getWarrantType());
        reviewCycle.setCoverageStartDate(reviewCycleRequest.getCoverageStartDate());
        reviewCycle.setCoverageEndDate(reviewCycleRequest.getCoverageEndDate());
        reviewCycle.setCreatedBy(reviewCycleRequest.getCreatedBy());
        reviewCycle.setActive(reviewCycleRequest.isActive());
        reviewCycle.setEquipment(equipment);
        reviewCycle = service.save(reviewCycle);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(reviewCycle));

    }

    @Override
    public ApiResponse<ReviewCycleDto> update(Long id, ReviewCycleDto reviewCycleDto) {
        ReviewCycle reviewCycle = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Review cycle not found"));
        reviewCycle.setName(reviewCycleDto.name());
        reviewCycle.setDescription(reviewCycleDto.description());
        reviewCycle.setWarrantNumber(reviewCycleDto.warrantNumber());
        reviewCycle.setWarrantType(reviewCycleDto.warrantType());
        reviewCycle.setCoverageStartDate(reviewCycleDto.coverageStartDate());
        reviewCycle.setCoverageEndDate(reviewCycleDto.coverageEndDate());
        reviewCycle.setCreatedBy(reviewCycleDto.createdBy());
        reviewCycle.setActive(reviewCycleDto.active());
        reviewCycle.setEquipment(equipmentMapper.toEntity(reviewCycleDto.equipment()));
        reviewCycle = service.save(reviewCycle);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(reviewCycle));
    }

    @Override
    public ApiResponse<ReviewCycleDto> deleteById(Long id) {
        ReviewCycle cycle = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Review cycle not found"));
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(cycle));
    }
}
