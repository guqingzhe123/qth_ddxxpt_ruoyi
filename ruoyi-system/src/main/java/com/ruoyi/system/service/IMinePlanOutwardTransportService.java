package com.ruoyi.system.service;

import com.ruoyi.system.domain.MinePlanOutwardTransport;

import java.util.List;

/**
 * 外运计划Service接口
 *
 * @author ruoyi
 * @date 2025-12-03
 */
public interface IMinePlanOutwardTransportService {
    /**
     * 查询外运计划
     *
     * @param id 外运计划主键
     * @return 外运计划
     */
    public MinePlanOutwardTransport getMinePlanOutwardTransportById(Long id);

    /**
     * 查询外运计划列表
     *
     * @param minePlanOutwardTransport 外运计划
     * @return 外运计划集合
     */
    public List<MinePlanOutwardTransport> listMinePlanOutwardTransport(MinePlanOutwardTransport minePlanOutwardTransport);

    /**
     * 新增外运计划
     *
     * @param minePlanOutwardTransport 外运计划
     * @return 结果
     */
    public int saveMinePlanOutwardTransport(MinePlanOutwardTransport minePlanOutwardTransport);

    /**
     * 修改外运计划
     *
     * @param minePlanOutwardTransport 外运计划
     * @return 结果
     */
    public int updateMinePlanOutwardTransport(MinePlanOutwardTransport minePlanOutwardTransport);

    /**
     * 批量删除外运计划
     *
     * @param ids 需要删除的外运计划主键集合
     * @return 结果
     */
    public int deleteMinePlanOutwardTransportByIds(Long[] ids);

    /**
     * 删除外运计划信息
     *
     * @param id 外运计划主键
     * @return 结果
     */
    public int deleteMinePlanOutwardTransportById(Long id);
}
