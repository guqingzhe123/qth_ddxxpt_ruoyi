package com.ruoyi.system.service;

import com.ruoyi.system.domain.DestinationOfRawCoal;

import java.util.List;

/**
 * 原煤去向/各矿日实际产量录入Service接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface IDestinationOfRawCoalService {
    /**
     * 查询原煤去向/各矿日实际产量录入
     * 
     * @param id 原煤去向/各矿日实际产量录入主键
     * @return 原煤去向/各矿日实际产量录入
     */
    public DestinationOfRawCoal getDestinationOfRawCoalById(Long id);

    /**
     * 查询原煤去向/各矿日实际产量录入列表
     * 
     * @param destinationOfRawCoal 原煤去向/各矿日实际产量录入
     * @return 原煤去向/各矿日实际产量录入集合
     */
    public List<DestinationOfRawCoal> listDestinationOfRawCoal(DestinationOfRawCoal destinationOfRawCoal);

    /**
     * 新增原煤去向/各矿日实际产量录入
     * 
     * @param destinationOfRawCoal 原煤去向/各矿日实际产量录入
     * @return 结果
     */
    public int saveDestinationOfRawCoal(List<DestinationOfRawCoal> destinationOfRawCoal);

    /**
     * 修改原煤去向/各矿日实际产量录入
     * 
     * @param destinationOfRawCoal 原煤去向/各矿日实际产量录入
     * @return 结果
     */
    public int updateDestinationOfRawCoal(DestinationOfRawCoal destinationOfRawCoal);

    /**
     * 批量删除原煤去向/各矿日实际产量录入
     * 
     * @param ids 需要删除的原煤去向/各矿日实际产量录入主键集合
     * @return 结果
     */
    public int deleteDestinationOfRawCoalByIds(Long[] ids);

    /**
     * 删除原煤去向/各矿日实际产量录入信息
     * 
     * @param id 原煤去向/各矿日实际产量录入主键
     * @return 结果
     */
    public int deleteDestinationOfRawCoalById(Long id);
}
