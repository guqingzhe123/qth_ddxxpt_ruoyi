package com.ruoyi.system.service.export;

import java.util.List;
import com.ruoyi.system.domain.export.WAgriculturalStats;

/**
 * 外销商品煤销量情况Service接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface IWAgriculturalStatsService {
    /**
     * 查询外销商品煤销量情况
     * 
     * @param id 外销商品煤销量情况主键
     * @return 外销商品煤销量情况
     */
    public WAgriculturalStats getWAgriculturalStatsById(String id);

    /**
     * 查询外销商品煤销量情况列表
     * 
     * @param wAgriculturalStats 外销商品煤销量情况
     * @return 外销商品煤销量情况集合
     */
    public List<WAgriculturalStats> listWAgriculturalStats(WAgriculturalStats wAgriculturalStats);

    /**
     * 新增外销商品煤销量情况
     * 
     * @param wAgriculturalStats 外销商品煤销量情况
     * @return 结果
     */
    public int saveWAgriculturalStats(WAgriculturalStats wAgriculturalStats);

    /**
     * 修改外销商品煤销量情况
     * 
     * @param wAgriculturalStats 外销商品煤销量情况
     * @return 结果
     */
    public int updateWAgriculturalStats(WAgriculturalStats wAgriculturalStats);

    /**
     * 批量删除外销商品煤销量情况
     * 
     * @param ids 需要删除的外销商品煤销量情况主键集合
     * @return 结果
     */
    public int deleteWAgriculturalStatsByIds(String[] ids);

    /**
     * 删除外销商品煤销量情况信息
     * 
     * @param id 外销商品煤销量情况主键
     * @return 结果
     */
    public int deleteWAgriculturalStatsById(String id);
}
