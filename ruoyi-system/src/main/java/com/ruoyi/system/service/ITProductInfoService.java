package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TProductInfo;

/**
 * 产品基础信息Service接口
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
public interface ITProductInfoService {
    /**
     * 查询产品基础信息
     * 
     * @param id 产品基础信息主键
     * @return 产品基础信息
     */
    public TProductInfo getTProductInfoById(Long id);

    /**
     * 查询产品基础信息列表
     * 
     * @param tProductInfo 产品基础信息
     * @return 产品基础信息集合
     */
    public List<TProductInfo> listTProductInfo(TProductInfo tProductInfo);

    /**
     * 新增产品基础信息
     * 
     * @param tProductInfo 产品基础信息
     * @return 结果
     */
    public int saveTProductInfo(TProductInfo tProductInfo);

    /**
     * 修改产品基础信息
     * 
     * @param tProductInfo 产品基础信息
     * @return 结果
     */
    public int updateTProductInfo(TProductInfo tProductInfo);

    /**
     * 批量删除产品基础信息
     * 
     * @param ids 需要删除的产品基础信息主键集合
     * @return 结果
     */
    public int deleteTProductInfoByIds(Long[] ids);

    /**
     * 删除产品基础信息信息
     * 
     * @param id 产品基础信息主键
     * @return 结果
     */
    public int deleteTProductInfoById(Long id);
}
