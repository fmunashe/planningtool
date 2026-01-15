package za.co.sabs.planningtool.service;

import za.co.sabs.planningtool.common.BaseService;
import za.co.sabs.planningtool.entity.Material;

import java.util.List;

public interface MaterialService extends BaseService<Material> {
    List<Material> saveAll(List<Material> materials);
}
