package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.Laboratory;

import java.util.Optional;


public interface LaboratoryService extends BaseService<Laboratory> {
    Optional<Laboratory> findByName(String labName);
}
