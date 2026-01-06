package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.MineTemplate;
import java.util.List;

public interface IMineTemplateService {
    MineTemplate get(Long id);
    List<MineTemplate> list(MineTemplate query);
    int add(MineTemplate entity);
    int edit(MineTemplate entity);
    int remove(Long id);
    int removeBatch(List<Long> ids);
}
