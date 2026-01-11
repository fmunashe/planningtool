package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.TaskStatus;
import za.co.sabs.planningtool.repository.TaskStatusRepository;
import za.co.sabs.planningtool.service.TaskStatusService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class TaskStatusImpl implements TaskStatusService {
    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusImpl(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public Optional<TaskStatus> findByStatus(String status) {
        return taskStatusRepository.findByStatus(status);
    }

    @Override
    public List<TaskStatus> findAll() {
        return taskStatusRepository.findAll();
    }

    @Override
    public Page<@NonNull TaskStatus> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return taskStatusRepository.findAll(pageable);
    }

    @Override
    public Optional<TaskStatus> findById(Long id) {
        return taskStatusRepository.findById(id);
    }

    @Override
    public TaskStatus save(TaskStatus taskStatus) {
        return taskStatusRepository.save(taskStatus);
    }

    @Override
    public void deleteById(Long id) {
        taskStatusRepository.deleteById(id);
    }
}
