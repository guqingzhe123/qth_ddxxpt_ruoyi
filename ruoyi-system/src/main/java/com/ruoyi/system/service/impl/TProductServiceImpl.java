package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.system.mapper.TProductMapper;
import com.ruoyi.system.domain.TProduct;
import com.ruoyi.system.service.ITProductService;

/**
 * 产品基础Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@Slf4j
@Service
public class TProductServiceImpl implements ITProductService {
    @Autowired
    private TProductMapper tProductMapper;

    /**
     * 查询产品基础
     * 
     * @param id 产品基础主键
     * @return 产品基础
     */
    @Override
    public TProduct getTProductById(Long id) {
        return tProductMapper.selectTProductById(id);
    }

    /**
     * 查询产品基础列表
     * 
     * @param tProduct 产品基础
     * @return 产品基础
     */
    @Override
    public List<TProduct> listTProduct(TProduct tProduct) {
        return tProductMapper.selectTProductList(tProduct);
    }

    /**
     * 新增产品基础
     * 
     * @param tProduct 产品基础
     * @return 结果
     */
    @Override
    public int saveTProduct(TProduct tProduct) {
        return tProductMapper.insertTProduct(tProduct);
    }

    /**
     * 修改产品基础
     * 
     * @param tProduct 产品基础
     * @return 结果
     */
    @Override
    public int updateTProduct(TProduct tProduct) {
        return tProductMapper.updateTProduct(tProduct);
    }

    /**
     * 批量删除产品基础
     * 
     * @param ids 需要删除的产品基础主键
     * @return 结果
     */
    @Override
    public int deleteTProductByIds(Long[] ids) {
        return tProductMapper.deleteTProductByIds(ids);
    }

    /**
     * 删除产品基础信息
     * 
     * @param id 产品基础主键
     * @return 结果
     */
    @Override
    public int deleteTProductById(Long id) {
        return tProductMapper.deleteTProductById(id);
    }
}
