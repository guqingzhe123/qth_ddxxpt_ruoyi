package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TDailyProductionSalesStock;
import com.ruoyi.system.mapper.TDailyProductionSalesStockMapper;
import com.ruoyi.system.service.ITDailyProductionSalesStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 焦化产品产销存日报Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
@Slf4j
@Service
public class TDailyProductionSalesStockServiceImpl implements ITDailyProductionSalesStockService {
    @Autowired
    private TDailyProductionSalesStockMapper tDailyProductionSalesStockMapper;

    /**
     * 查询焦化产品产销存日报
     * 
     * @param id 焦化产品产销存日报主键
     * @return 焦化产品产销存日报
     */
    @Override
    public TDailyProductionSalesStock getTDailyProductionSalesStockById(Long id) {
        return tDailyProductionSalesStockMapper.selectTDailyProductionSalesStockById(id);
    }

    /**
     * 查询焦化产品产销存日报列表
     * 
     * @param tDailyProductionSalesStock 焦化产品产销存日报
     * @return 焦化产品产销存日报
     */
    @Override
    public List<TDailyProductionSalesStock> listTDailyProductionSalesStock(TDailyProductionSalesStock tDailyProductionSalesStock) {
        return tDailyProductionSalesStockMapper.selectTDailyProductionSalesStockList(tDailyProductionSalesStock);
    }

    /**
     * 焦化产品产销存当月累计
     *
     * @param tDailyProductionSalesStock 焦化产品产销存当月累计
     * @return 焦化产品产销存当月累计
     */
    @Override
    public List<TDailyProductionSalesStock> listTDailyProductionSalesStockMonth(TDailyProductionSalesStock tDailyProductionSalesStock) {
        return tDailyProductionSalesStockMapper.selectTDailyProductionSalesStockMonthList(tDailyProductionSalesStock);
    }

    /**
     * 新增焦化产品产销存日报
     * 
     * @param tDailyProductionSalesStock 焦化产品产销存日报
     * @return 结果
     */
    @Override
    public int saveTDailyProductionSalesStock(TDailyProductionSalesStock tDailyProductionSalesStock) {
        tDailyProductionSalesStock.setCreateTime(DateUtils.getNowDate());
        return tDailyProductionSalesStockMapper.insertTDailyProductionSalesStock(tDailyProductionSalesStock);
    }

    /**
     * 修改焦化产品产销存日报
     * 
     * @param tDailyProductionSalesStock 焦化产品产销存日报
     * @return 结果
     */
    @Override
    public int updateTDailyProductionSalesStock(TDailyProductionSalesStock tDailyProductionSalesStock) {
        tDailyProductionSalesStock.setUpdateTime(DateUtils.getNowDate());
        return tDailyProductionSalesStockMapper.updateTDailyProductionSalesStock(tDailyProductionSalesStock);
    }

    /**
     * 批量删除焦化产品产销存日报
     * 
     * @param ids 需要删除的焦化产品产销存日报主键
     * @return 结果
     */
    @Override
    public int deleteTDailyProductionSalesStockByIds(Long[] ids) {
        return tDailyProductionSalesStockMapper.deleteTDailyProductionSalesStockByIds(ids);
    }

    /**
     * 删除焦化产品产销存日报信息
     * 
     * @param id 焦化产品产销存日报主键
     * @return 结果
     */
    @Override
    public int deleteTDailyProductionSalesStockById(Long id) {
        return tDailyProductionSalesStockMapper.deleteTDailyProductionSalesStockById(id);
    }

    @Override
    public int deleteTDailyProductionSalesStockByStatDate(TDailyProductionSalesStock tDailyProductionSalesStock) {
        return tDailyProductionSalesStockMapper.deleteTDailyProductionSalesStockByStatDate(tDailyProductionSalesStock);
    }
}
