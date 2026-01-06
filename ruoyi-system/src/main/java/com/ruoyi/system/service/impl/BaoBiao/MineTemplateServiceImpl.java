package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.MineTemplate;
import com.ruoyi.system.mapper.BaoBiao.MineTemplateMapper;
import com.ruoyi.system.service.BaoBiao.IMineTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MineTemplateServiceImpl implements IMineTemplateService {

    @Resource
    private MineTemplateMapper mineTemplateMapper;

    @Override
    public MineTemplate get(Long id) {
        return mineTemplateMapper.selectById(id);
    }

    @Override
    public List<MineTemplate> list(MineTemplate query) {
        return mineTemplateMapper.selectList(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(MineTemplate entity) {
        return mineTemplateMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(MineTemplate entity) {
        return mineTemplateMapper.update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Long id) {
        return mineTemplateMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeBatch(List<Long> ids) {
        return mineTemplateMapper.deleteByIds(ids);
    }
}
