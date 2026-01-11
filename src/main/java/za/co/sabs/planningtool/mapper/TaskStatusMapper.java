package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.TaskStatusDto;
import za.co.sabs.planningtool.entity.TaskStatus;

import java.util.function.Function;

@Service
public class TaskStatusMapper implements Function<TaskStatus, TaskStatusDto> {
    @Override
    public TaskStatusDto apply(TaskStatus taskStatus) {
        return new TaskStatusDto(
                taskStatus.getId(),
                taskStatus.getStatus(),
                taskStatus.getCreatedAt(),
                taskStatus.getUpdatedAt()
        );
    }
}
