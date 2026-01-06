package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.TemplateDetails;
import com.ruoyi.system.domain.BaoBiao.TemplateDetailsVO;
import java.util.List;

public interface ITemplateDetailsService {
    TemplateDetails get(Long id);
    List<TemplateDetails> list(TemplateDetails query);
    int add(TemplateDetails entity);
    int edit(TemplateDetails entity);
    int remove(Long id);
    int removeBatch(List<Long> ids);

    // 新增：
    List<TemplateDetails> listByTemplateId(Long mineTemplateId);
    List<TemplateDetailsVO> listVOByTemplateId(Long mineTemplateId);
}
