package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.UrgencyDto;
import za.co.sabs.planningtool.entity.Urgency;
import za.co.sabs.planningtool.exceptions.RecordExistsException;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.UrgencyMapper;
import za.co.sabs.planningtool.processor.UrgencyProcessor;
import za.co.sabs.planningtool.service.UrgencyService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.UrgencyRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class UrgencyProcessorImpl implements UrgencyProcessor {
    private final UrgencyService service;
    private final UrgencyMapper mapper;

    public UrgencyProcessorImpl(UrgencyService service, UrgencyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<UrgencyDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Urgency> urgencyPage = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(urgencyPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<UrgencyDto> findById(Long id) {
        Optional<Urgency> optionalUrgency = service.findById(id);

        return optionalUrgency.map(urgency -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(urgency)))
                .orElseThrow(() -> new RecordNotFoundException("Failed to find a task urgency record requested "));

    }

    @Override
    public ApiResponse<UrgencyDto> save(UrgencyRequest urgencyRequest) {
        Optional<Urgency> optionalUrgency = service.findByName(urgencyRequest.getName());
        if (optionalUrgency.isPresent()) {
            throw new RecordExistsException("Error, there is another task urgency  of " + urgencyRequest.getName());
        }
        Urgency urgency = new Urgency();
        urgency.setName(urgencyRequest.getName());
        urgency = service.save(urgency);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(urgency));

    }

    @Override
    public ApiResponse<UrgencyDto> update(Long id, UrgencyDto urgencyDto) {
        Optional<Urgency> optionalUrgency = service.findById(id);

        if (optionalUrgency.isEmpty() || optionalUrgency.get().getId() != id) {
            throw new RecordNotFoundException("Failed to update, urgency id mismatch.");
        }
        Urgency urgency = optionalUrgency.get();
        urgency.setName(urgencyDto.name());

        Urgency updatedUrgency = service.save(urgency);
        UrgencyDto mappedDto = mapper.apply(updatedUrgency);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<UrgencyDto> deleteById(Long id) {
        Optional<Urgency> optionalUrgency = service.findById(id);
        if (optionalUrgency.isEmpty()) {
            throw new RecordNotFoundException("Activity urgency not found.");
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }
}
