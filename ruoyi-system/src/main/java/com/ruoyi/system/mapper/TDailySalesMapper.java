package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TDailySales;

import java.util.List;

/**
 * 煤气厂销售日报（单版-含期初库存）Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-07
 */
public interface TDailySalesMapper {
    /**
     * 查询煤气厂销售日报（单版-含期初库存）
     * 
     * @param id 煤气厂销售日报（单版-含期初库存）主键
     * @return 煤气厂销售日报（单版-含期初库存）
     */
    public TDailySales selectTDailySalesById(Long id);

    /**
     * 查询煤气厂销售日报（单版-含期初库存）列表
     * 
     * @param tDailySales 煤气厂销售日报（单版-含期初库存）
     * @return 煤气厂销售日报（单版-含期初库存）集合
     */
    public List<TDailySales> selectTDailySalesList(TDailySales tDailySales);


    /**
     * 当月累计
     *
     * @param tDailySales 当月累计
     * @return 当月累计
     */
    public List<TDailySales> selectTDailySalesListMonth(TDailySales tDailySales);


    /**
     * 新增煤气厂销售日报（单版-含期初库存）
     * 
     * @param tDailySales 煤气厂销售日报（单版-含期初库存）
     * @return 结果
     */
    public int insertTDailySales(TDailySales tDailySales);

    /**
     * 修改煤气厂销售日报（单版-含期初库存）
     * 
     * @param tDailySales 煤气厂销售日报（单版-含期初库存）
     * @return 结果
     */
    public int updateTDailySales(TDailySales tDailySales);

    /**
     * 删除煤气厂销售日报（单版-含期初库存）
     * 
     * @param id 煤气厂销售日报（单版-含期初库存）主键
     * @return 结果
     */
    public int deleteTDailySalesById(Long id);

    /**
     * 批量删除煤气厂销售日报（单版-含期初库存）
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTDailySalesByIds(Long[] ids);


    /**
     * 批量删除煤气厂销售日报（单版-含期初库存）
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTDailySalesByStatDate(TDailySales tDailySales);

}
