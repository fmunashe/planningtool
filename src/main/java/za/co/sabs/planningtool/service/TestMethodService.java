package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.TestingMethod;

import java.util.Optional;

public interface TestMethodService extends BaseService<TestingMethod> {
    Optional<TestingMethod> findByName(String name);
}
