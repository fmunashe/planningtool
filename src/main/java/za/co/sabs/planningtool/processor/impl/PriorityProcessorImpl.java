package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.PriorityDto;
import za.co.sabs.planningtool.entity.Priority;
import za.co.sabs.planningtool.exceptions.RecordExistsException;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.PriorityMapper;
import za.co.sabs.planningtool.processor.PriorityProcessor;
import za.co.sabs.planningtool.service.PriorityService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.PriorityRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class PriorityProcessorImpl implements PriorityProcessor {
    private final PriorityService service;
    private final PriorityMapper mapper;

    public PriorityProcessorImpl(PriorityService service, PriorityMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<PriorityDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Priority> priorityPage = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(priorityPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<PriorityDto> findById(Long id) {
        Optional<Priority> optionalPriority = service.findById(id);

        return optionalPriority.map(priority -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(priority)))
                .orElseThrow(() -> new RecordNotFoundException("Failed to find a priority record requested "));

    }

    @Override
    public ApiResponse<PriorityDto> save(PriorityRequest priorityRequest) {
        Optional<Priority> optionalPriority = service.findByLevel(priorityRequest.getLevel());
        if (optionalPriority.isPresent()) {
            throw new RecordExistsException("Error, there is another priority with level " + priorityRequest.getLevel());
        }
        Priority priority = new Priority();
        priority.setLevel(priorityRequest.getLevel());
        priority = service.save(priority);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(priority));

    }

    @Override
    public ApiResponse<PriorityDto> update(Long id, PriorityDto priorityDto) {
        Optional<Priority> optionalPriority = service.findById(id);

        if (optionalPriority.isEmpty() || optionalPriority.get().getId() != id) {
            throw new RecordNotFoundException("Failed to update, priority id mismatch.");
        }
        Priority priority = optionalPriority.get();
        priority.setLevel(priorityDto.level());

        Priority updatedPriority = service.save(priority);
        PriorityDto mappedDto = mapper.apply(updatedPriority);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<PriorityDto> deleteById(Long id) {
        Optional<Priority> optionalPriority = service.findById(id);
        if (optionalPriority.isEmpty()) {
            throw new RecordNotFoundException("Priority not found.");
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }


}
