package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.Parameter;

import java.util.List;

public interface ParameterService extends BaseService<Parameter> {
    List<Parameter> saveAll(List<Parameter> parameters);
}
