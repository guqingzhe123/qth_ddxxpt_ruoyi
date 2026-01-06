package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.UnitManagement;
import com.ruoyi.system.mapper.BaoBiao.UnitManagementMapper;
import com.ruoyi.system.service.BaoBiao.IUnitManagementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UnitManagementServiceImpl implements IUnitManagementService {

    @Resource
    private UnitManagementMapper unitManagementMapper;

    @Override
    public UnitManagement get(Long id) {
        return unitManagementMapper.selectById(id);
    }

    @Override
    public List<UnitManagement> list(UnitManagement query) {
        return unitManagementMapper.selectList(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(UnitManagement entity) {
        return unitManagementMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(UnitManagement entity) {
        return unitManagementMapper.update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Long id) {
        return unitManagementMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeBatch(List<Long> ids) {
        return unitManagementMapper.deleteByIds(ids);
    }
}
