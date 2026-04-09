package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.system.mapper.TProductInfoMapper;
import com.ruoyi.system.domain.TProductInfo;
import com.ruoyi.system.service.ITProductInfoService;

/**
 * 产品基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@Slf4j
@Service
public class TProductInfoServiceImpl implements ITProductInfoService {
    @Autowired
    private TProductInfoMapper tProductInfoMapper;

    /**
     * 查询产品基础信息
     * 
     * @param id 产品基础信息主键
     * @return 产品基础信息
     */
    @Override
    public TProductInfo getTProductInfoById(Long id) {
        return tProductInfoMapper.selectTProductInfoById(id);
    }

    /**
     * 查询产品基础信息列表
     * 
     * @param tProductInfo 产品基础信息
     * @return 产品基础信息
     */
    @Override
    public List<TProductInfo> listTProductInfo(TProductInfo tProductInfo) {
        return tProductInfoMapper.selectTProductInfoList(tProductInfo);
    }

    /**
     * 新增产品基础信息
     * 
     * @param tProductInfo 产品基础信息
     * @return 结果
     */
    @Override
    public int saveTProductInfo(TProductInfo tProductInfo) {
        tProductInfo.setCreateTime(DateUtils.getNowDate());
        return tProductInfoMapper.insertTProductInfo(tProductInfo);
    }

    /**
     * 修改产品基础信息
     * 
     * @param tProductInfo 产品基础信息
     * @return 结果
     */
    @Override
    public int updateTProductInfo(TProductInfo tProductInfo) {
        tProductInfo.setUpdateTime(DateUtils.getNowDate());
        return tProductInfoMapper.updateTProductInfo(tProductInfo);
    }

    /**
     * 批量删除产品基础信息
     * 
     * @param ids 需要删除的产品基础信息主键
     * @return 结果
     */
    @Override
    public int deleteTProductInfoByIds(Long[] ids) {
        return tProductInfoMapper.deleteTProductInfoByIds(ids);
    }

    /**
     * 删除产品基础信息信息
     * 
     * @param id 产品基础信息主键
     * @return 结果
     */
    @Override
    public int deleteTProductInfoById(Long id) {
        return tProductInfoMapper.deleteTProductInfoById(id);
    }
}
