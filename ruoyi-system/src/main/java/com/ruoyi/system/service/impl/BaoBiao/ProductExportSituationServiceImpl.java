package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.ProductExportSituation;
import com.ruoyi.system.mapper.BaoBiao.ProductExportSituationMapper;
import com.ruoyi.system.service.BaoBiao.IProductExportSituationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductExportSituationServiceImpl implements IProductExportSituationService {

    @Resource
    private ProductExportSituationMapper productExportSituationMapper;

    @Override
    public ProductExportSituation get(Long id) {
        return productExportSituationMapper.selectById(id);
    }

    @Override
    public List<ProductExportSituation> list(ProductExportSituation query) {
        return productExportSituationMapper.selectList(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(ProductExportSituation entity) {
        return productExportSituationMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(ProductExportSituation entity) {
        return productExportSituationMapper.update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Long id) {
        return productExportSituationMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeBatch(List<Long> ids) {
        return productExportSituationMapper.deleteByIds(ids);
    }
}
