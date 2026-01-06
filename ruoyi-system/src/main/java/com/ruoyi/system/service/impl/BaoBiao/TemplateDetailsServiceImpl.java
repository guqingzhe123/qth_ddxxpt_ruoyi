package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.TemplateDetails;
import com.ruoyi.system.domain.BaoBiao.TemplateDetailsVO;
import com.ruoyi.system.mapper.BaoBiao.TemplateDetailsMapper;
import com.ruoyi.system.service.BaoBiao.ITemplateDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TemplateDetailsServiceImpl implements ITemplateDetailsService {

    @Resource
    private TemplateDetailsMapper templateDetailsMapper;

    @Override
    public TemplateDetails get(Long id) {
        return templateDetailsMapper.selectById(id);
    }

    @Override
    public List<TemplateDetails> list(TemplateDetails query) {
        return templateDetailsMapper.selectList(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(TemplateDetails entity) {
        return templateDetailsMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(TemplateDetails entity) {
        return templateDetailsMapper.update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Long id) {
        return templateDetailsMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeBatch(List<Long> ids) {
        return templateDetailsMapper.deleteByIds(ids);
    }

    @Override
    public List<TemplateDetails> listByTemplateId(Long mineTemplateId) {
        return templateDetailsMapper.selectByTemplateId(mineTemplateId);
    }

    @Override
    public List<TemplateDetailsVO> listVOByTemplateId(Long mineTemplateId) {
        return templateDetailsMapper.selectVOByTemplateId(mineTemplateId);
    }
}
