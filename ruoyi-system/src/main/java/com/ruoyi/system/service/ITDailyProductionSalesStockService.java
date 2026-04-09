package com.ruoyi.system.service;

import com.ruoyi.system.domain.TDailyProductionSalesStock;

import java.util.List;

/**
 * 焦化产品产销存日报Service接口
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
public interface ITDailyProductionSalesStockService {
    /**
     * 查询焦化产品产销存日报
     * 
     * @param id 焦化产品产销存日报主键
     * @return 焦化产品产销存日报
     */
    public TDailyProductionSalesStock getTDailyProductionSalesStockById(Long id);

    /**
     * 查询焦化产品产销存日报列表
     * 
     * @param tDailyProductionSalesStock 焦化产品产销存日报
     * @return 焦化产品产销存日报集合
     */
    public List<TDailyProductionSalesStock> listTDailyProductionSalesStock(TDailyProductionSalesStock tDailyProductionSalesStock);

    /**
     * 焦化产品产销存当月累计
     *
     * @param tDailyProductionSalesStock 焦化产品产销存当月累计
     * @return 焦化产品产销存日报集合
     */
    public List<TDailyProductionSalesStock> listTDailyProductionSalesStockMonth(TDailyProductionSalesStock tDailyProductionSalesStock);


    /**
     * 新增焦化产品产销存日报
     * 
     * @param tDailyProductionSalesStock 焦化产品产销存日报
     * @return 结果
     */
    public int saveTDailyProductionSalesStock(TDailyProductionSalesStock tDailyProductionSalesStock);

    /**
     * 修改焦化产品产销存日报
     * 
     * @param tDailyProductionSalesStock 焦化产品产销存日报
     * @return 结果
     */
    public int updateTDailyProductionSalesStock(TDailyProductionSalesStock tDailyProductionSalesStock);

    /**
     * 批量删除焦化产品产销存日报
     * 
     * @param ids 需要删除的焦化产品产销存日报主键集合
     * @return 结果
     */
    public int deleteTDailyProductionSalesStockByIds(Long[] ids);

    /**
     * 删除焦化产品产销存日报信息
     * 
     * @param id 焦化产品产销存日报主键
     * @return 结果
     */
    public int deleteTDailyProductionSalesStockById(Long id);
    /**
     * 删除焦化产品产销存日报信息
     *
     * @param id 焦化产品产销存日报主键
     * @return 结果
     */
    public int deleteTDailyProductionSalesStockByStatDate(TDailyProductionSalesStock tDailyProductionSalesStock);
}
