package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TProduct;

/**
 * 产品基础Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
public interface TProductMapper {
    /**
     * 查询产品基础
     * 
     * @param id 产品基础主键
     * @return 产品基础
     */
    public TProduct selectTProductById(Long id);

    /**
     * 查询产品基础列表
     * 
     * @param tProduct 产品基础
     * @return 产品基础集合
     */
    public List<TProduct> selectTProductList(TProduct tProduct);

    /**
     * 新增产品基础
     * 
     * @param tProduct 产品基础
     * @return 结果
     */
    public int insertTProduct(TProduct tProduct);

    /**
     * 修改产品基础
     * 
     * @param tProduct 产品基础
     * @return 结果
     */
    public int updateTProduct(TProduct tProduct);

    /**
     * 删除产品基础
     * 
     * @param id 产品基础主键
     * @return 结果
     */
    public int deleteTProductById(Long id);

    /**
     * 批量删除产品基础
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTProductByIds(Long[] ids);
}
