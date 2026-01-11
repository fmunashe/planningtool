package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.Urgency;

import java.util.Optional;

public interface UrgencyService extends BaseService<Urgency> {
    Optional<Urgency> findByName(String name);
}
