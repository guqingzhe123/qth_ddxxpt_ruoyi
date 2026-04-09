package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TDailySales;
import com.ruoyi.system.mapper.TDailySalesMapper;
import com.ruoyi.system.service.ITDailySalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 煤气厂销售日报（单版-含期初库存）Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@Slf4j
@Service
public class TDailySalesServiceImpl implements ITDailySalesService {
    @Autowired
    private TDailySalesMapper tDailySalesMapper;

    /**
     * 查询煤气厂销售日报（单版-含期初库存）
     * 
     * @param id 煤气厂销售日报（单版-含期初库存）主键
     * @return 煤气厂销售日报（单版-含期初库存）
     */
    @Override
    public TDailySales getTDailySalesById(Long id) {
        return tDailySalesMapper.selectTDailySalesById(id);
    }

    /**
     * 查询煤气厂销售日报（单版-含期初库存）列表
     * 
     * @param tDailySales 煤气厂销售日报（单版-含期初库存）
     * @return 煤气厂销售日报（单版-含期初库存）
     */
    @Override
    public List<TDailySales> listTDailySales(TDailySales tDailySales) {
        return tDailySalesMapper.selectTDailySalesList(tDailySales);
    }
    /**
     * 当月累计
     *
     * @param tDailySales 当月累计
     * @return 当月累计
     */
    @Override
    public List<TDailySales> listTDailySalesMonth(TDailySales tDailySales) {
        return tDailySalesMapper.selectTDailySalesListMonth(tDailySales);
    }

    /**
     * 新增煤气厂销售日报（单版-含期初库存）
     * 
     * @param tDailySales 煤气厂销售日报（单版-含期初库存）
     * @return 结果
     */
    @Override
    public int saveTDailySales(TDailySales tDailySales) {
        tDailySales.setCreateTime(DateUtils.getNowDate());
        return tDailySalesMapper.insertTDailySales(tDailySales);
    }

    /**
     * 修改煤气厂销售日报（单版-含期初库存）
     * 
     * @param tDailySales 煤气厂销售日报（单版-含期初库存）
     * @return 结果
     */
    @Override
    public int updateTDailySales(TDailySales tDailySales) {
        tDailySales.setUpdateTime(DateUtils.getNowDate());
        return tDailySalesMapper.updateTDailySales(tDailySales);
    }

    /**
     * 批量删除煤气厂销售日报（单版-含期初库存）
     * 
     * @param ids 需要删除的煤气厂销售日报（单版-含期初库存）主键
     * @return 结果
     */
    @Override
    public int deleteTDailySalesByIds(Long[] ids) {
        return tDailySalesMapper.deleteTDailySalesByIds(ids);
    }

    /**
     * 删除煤气厂销售日报（单版-含期初库存）信息
     * 
     * @param id 煤气厂销售日报（单版-含期初库存）主键
     * @return 结果
     */
    @Override
    public int deleteTDailySalesById(Long id) {
        return tDailySalesMapper.deleteTDailySalesById(id);
    }

    @Override
    public int deleteTDailySalesByStatDate(TDailySales tDailySales) {
        return tDailySalesMapper.deleteTDailySalesByStatDate(tDailySales);
    }
}
