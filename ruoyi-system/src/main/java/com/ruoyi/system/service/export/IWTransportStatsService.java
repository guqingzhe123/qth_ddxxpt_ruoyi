package com.ruoyi.system.service.export;

import com.ruoyi.system.domain.export.WTransportStats;

import java.util.List;

/**
 * 驻矿公司煤炭调运日报Service接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface IWTransportStatsService {
    /**
     * 查询驻矿公司煤炭调运日报
     * 
     * @param id 驻矿公司煤炭调运日报主键
     * @return 驻矿公司煤炭调运日报
     */
    public WTransportStats getWTransportStatsById(String id);

    /**
     * 查询驻矿公司煤炭调运日报列表
     * 
     * @param wTransportStats 驻矿公司煤炭调运日报
     * @return 驻矿公司煤炭调运日报集合
     */
    public List<WTransportStats> listWTransportStats(WTransportStats wTransportStats);

    /**
     * 新增驻矿公司煤炭调运日报
     * 
     * @param wTransportStats 驻矿公司煤炭调运日报
     * @return 结果
     */
    public int saveWTransportStats(WTransportStats wTransportStats);

    /**
     * 修改驻矿公司煤炭调运日报
     * 
     * @param wTransportStats 驻矿公司煤炭调运日报
     * @return 结果
     */
    public int updateWTransportStats(WTransportStats wTransportStats);

    /**
     * 批量删除驻矿公司煤炭调运日报
     * 
     * @param ids 需要删除的驻矿公司煤炭调运日报主键集合
     * @return 结果
     */
    public int deleteWTransportStatsByIds(String[] ids);

    /**
     * 删除驻矿公司煤炭调运日报信息
     * 
     * @param id 驻矿公司煤炭调运日报主键
     * @return 结果
     */
    public int deleteWTransportStatsById(String id);
}
