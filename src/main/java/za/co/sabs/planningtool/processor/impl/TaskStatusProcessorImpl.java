package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.TaskStatusDto;
import za.co.sabs.planningtool.entity.TaskStatus;
import za.co.sabs.planningtool.exceptions.RecordExistsException;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.TaskStatusMapper;
import za.co.sabs.planningtool.processor.TaskStatusProcessor;
import za.co.sabs.planningtool.service.TaskStatusService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.TaskStatusRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class TaskStatusProcessorImpl implements TaskStatusProcessor {
    private final TaskStatusService service;
    private final TaskStatusMapper mapper;

    public TaskStatusProcessorImpl(TaskStatusService service, TaskStatusMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<TaskStatusDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull TaskStatus> taskStatusPage = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(taskStatusPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<TaskStatusDto> findById(Long id) {
        Optional<TaskStatus> optionalTaskStatus = service.findById(id);

        return optionalTaskStatus.map(status -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(status)))
                .orElseThrow(() -> new RecordNotFoundException("Failed to find a task status record requested "));

    }

    @Override
    public ApiResponse<TaskStatusDto> save(TaskStatusRequest taskStatusRequest) {
        Optional<TaskStatus> optionalTaskStatus = service.findByStatus(taskStatusRequest.getTaskStatus());
        if (optionalTaskStatus.isPresent()) {
            throw new RecordExistsException("Error, there is another task status  of " + taskStatusRequest.getTaskStatus());
        }
        TaskStatus status = new TaskStatus();
        status.setStatus(taskStatusRequest.getTaskStatus());
        status = service.save(status);
        return HelperResponse.buildApiResponse(null, mapper, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(status));

    }

    @Override
    public ApiResponse<TaskStatusDto> update(Long id, TaskStatusDto taskStatusDto) {
        Optional<TaskStatus> optionalTaskStatus = service.findById(id);

        if (optionalTaskStatus.isEmpty() || optionalTaskStatus.get().getId() != id) {
            throw new RecordNotFoundException("Failed to update, priority id mismatch.");
        }
        TaskStatus status = optionalTaskStatus.get();
        status.setStatus(taskStatusDto.taskStatus());

        TaskStatus updatedStatus = service.save(status);
        TaskStatusDto mappedDto = mapper.apply(updatedStatus);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<TaskStatusDto> deleteById(Long id) {
        Optional<TaskStatus> optionalTaskStatus = service.findById(id);
        if (optionalTaskStatus.isEmpty()) {
            throw new RecordNotFoundException("Task status not found.");
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }
}
