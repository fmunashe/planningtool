package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.LaboratoryDto;
import za.co.sabs.planningtool.entity.Laboratory;
import za.co.sabs.planningtool.exceptions.RecordExistsException;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.LaboratoryDtoMapper;
import za.co.sabs.planningtool.processor.LaboratoryProcessor;
import za.co.sabs.planningtool.service.LaboratoryService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.LabRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class LaboratoryProcessorImpl implements LaboratoryProcessor {
    private final LaboratoryService labService;
    private final LaboratoryDtoMapper mapper;

    public LaboratoryProcessorImpl(LaboratoryService service, LaboratoryDtoMapper mapper) {
        this.labService = service;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<LaboratoryDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Laboratory> labs = labService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(labs, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<LaboratoryDto> findById(Long id) {
        Optional<Laboratory> optionalLaboratory = labService.findById(id);

        return optionalLaboratory.map(laboratory -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(laboratory)))
                .orElseThrow(() -> new RecordNotFoundException("Failed to find a laboratory record with Id of " + id));

    }

    @Override
    public ApiResponse<LaboratoryDto> save(LabRequest labRequest) {
        Optional<Laboratory> optionalLaboratory = labService.findByName(labRequest.getLabName());
        if (optionalLaboratory.isPresent()) {
            throw new RecordExistsException("Error, there is another laboratory with name " + labRequest.getLabName());
        }
        Laboratory lab = new Laboratory();
        lab.setLabName(labRequest.getLabName());
        lab.setLabNumber(labRequest.getLabNumber());
        lab.setIsActive(labRequest.getIsActive());
        lab.setCreatedBy(labRequest.getCreatedBy());
        lab = labService.save(lab);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(lab));
    }

    @Override
    public ApiResponse<LaboratoryDto> update(Long id, LaboratoryDto laboratoryDto) {
        Optional<Laboratory> laboratory = labService.findById(id);

        if (laboratory.isEmpty() || laboratory.get().getId() != id) {
            throw new RecordNotFoundException("Failed to update, laboratory id mismatch.");
        }
        Laboratory lab = laboratory.get();
        lab.setLabName(laboratoryDto.labName());
        lab.setLabNumber(laboratoryDto.labNumber());
        Laboratory updatedLab = labService.save(lab);
        LaboratoryDto mappedDto = mapper.apply(updatedLab);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<LaboratoryDto> deleteById(Long id) {
        Optional<Laboratory> optionalLaboratory = labService.findById(id);
        if (optionalLaboratory.isEmpty()) {
            throw new RecordNotFoundException("Laboratory not found.");
        }
        labService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }
}
