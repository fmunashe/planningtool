package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.Priority;

import java.util.Optional;

public interface PriorityService extends BaseService<Priority> {
    Optional<Priority> findByLevel(String level);
}
