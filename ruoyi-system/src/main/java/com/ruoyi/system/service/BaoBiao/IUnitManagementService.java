package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.UnitManagement;
import java.util.List;

public interface IUnitManagementService {
    UnitManagement get(Long id);
    List<UnitManagement> list(UnitManagement query);
    int add(UnitManagement entity);
    int edit(UnitManagement entity);
    int remove(Long id);
    int removeBatch(List<Long> ids);
}
