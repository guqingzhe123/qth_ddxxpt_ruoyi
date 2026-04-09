package com.ruoyi.system.service;

import com.ruoyi.system.domain.TDailySales;

import java.util.List;

/**
 * 煤气厂销售日报（单版-含期初库存）Service接口
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
public interface ITDailySalesService {
    /**
     * 查询煤气厂销售日报（单版-含期初库存）
     * 
     * @param id 煤气厂销售日报（单版-含期初库存）主键
     * @return 煤气厂销售日报（单版-含期初库存）
     */
    public TDailySales getTDailySalesById(Long id);

    /**
     * 查询煤气厂销售日报（单版-含期初库存）列表
     * 
     * @param tDailySales 煤气厂销售日报（单版-含期初库存）
     * @return 煤气厂销售日报（单版-含期初库存）集合
     */
    public List<TDailySales> listTDailySales(TDailySales tDailySales);

    /**
     * 当月累计
     *
     * @param tDailySales 当月累计（单版-含期初库存）
     * @return 当月累计（单版-含期初库存）集合
     */
    public List<TDailySales> listTDailySalesMonth(TDailySales tDailySales);



    /**
     * 新增煤气厂销售日报（单版-含期初库存）
     * 
     * @param tDailySales 煤气厂销售日报（单版-含期初库存）
     * @return 结果
     */
    public int saveTDailySales(TDailySales tDailySales);

    /**
     * 修改煤气厂销售日报（单版-含期初库存）
     * 
     * @param tDailySales 煤气厂销售日报（单版-含期初库存）
     * @return 结果
     */
    public int updateTDailySales(TDailySales tDailySales);

    /**
     * 批量删除煤气厂销售日报（单版-含期初库存）
     * 
     * @param ids 需要删除的煤气厂销售日报（单版-含期初库存）主键集合
     * @return 结果
     */
    public int deleteTDailySalesByIds(Long[] ids);

    /**
     * 删除煤气厂销售日报（单版-含期初库存）信息
     * 
     * @param id 煤气厂销售日报（单版-含期初库存）主键
     * @return 结果
     */
    public int deleteTDailySalesById(Long id);


    /**
     * 删除煤气厂销售日报（单版-含期初库存）信息
     *
     * @param id 煤气厂销售日报（单版-含期初库存）主键
     * @return 结果
     */
    public int deleteTDailySalesByStatDate(TDailySales tDailySales);

}
