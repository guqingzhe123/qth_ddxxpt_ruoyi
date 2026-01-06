package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.mapper.BaoBiao.FactoryArchiveMapper;
import com.ruoyi.system.service.BaoBiao.IFactoryArchiveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FactoryArchiveServiceImpl implements IFactoryArchiveService {

    @Resource
    private FactoryArchiveMapper factoryArchiveMapper;

    @Override
    public FactoryArchive get(Long id) {
        return factoryArchiveMapper.selectById(id);
    }

    @Override
    public List<FactoryArchive> list(FactoryArchive query) {
        query.setIsSealed(0);
        return factoryArchiveMapper.selectList(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(FactoryArchive entity) {
        return factoryArchiveMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(FactoryArchive entity) {
        return factoryArchiveMapper.update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Long id) {
        return factoryArchiveMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeBatch(List<Long> ids) {
        return factoryArchiveMapper.deleteByIds(ids);
    }
}
