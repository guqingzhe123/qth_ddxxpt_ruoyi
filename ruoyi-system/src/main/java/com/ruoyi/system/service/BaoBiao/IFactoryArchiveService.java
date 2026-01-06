package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import java.util.List;

public interface IFactoryArchiveService {
    FactoryArchive get(Long id);
    List<FactoryArchive> list(FactoryArchive query);
    int add(FactoryArchive entity);
    int edit(FactoryArchive entity);
    int remove(Long id);
    int removeBatch(List<Long> ids);
}
