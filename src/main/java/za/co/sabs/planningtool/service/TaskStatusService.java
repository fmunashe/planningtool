package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.TaskStatus;

import java.util.Optional;

public interface TaskStatusService extends BaseService<TaskStatus> {
    Optional<TaskStatus> findByStatus(String status);
}
