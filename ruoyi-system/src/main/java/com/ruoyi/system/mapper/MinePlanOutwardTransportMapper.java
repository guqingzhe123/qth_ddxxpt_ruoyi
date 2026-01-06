package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.MinePlanOutwardTransport;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 外运计划Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-03
 */
public interface MinePlanOutwardTransportMapper {
    /**
     * 查询外运计划
     *
     * @param id 外运计划主键
     * @return 外运计划
     */
    public MinePlanOutwardTransport selectMinePlanOutwardTransportById(Long id);

    /**
     * 查询外运计划列表
     *
     * @param minePlanOutwardTransport 外运计划
     * @return 外运计划集合
     */
    public List<MinePlanOutwardTransport> selectMinePlanOutwardTransportList(MinePlanOutwardTransport minePlanOutwardTransport);

    /**
     * 新增外运计划
     *
     * @param minePlanOutwardTransport 外运计划
     * @return 结果
     */
    public int insertMinePlanOutwardTransport(MinePlanOutwardTransport minePlanOutwardTransport);

    /**
     * 修改外运计划
     *
     * @param minePlanOutwardTransport 外运计划
     * @return 结果
     */
    public int updateMinePlanOutwardTransport(MinePlanOutwardTransport minePlanOutwardTransport);

    /**
     * 删除外运计划
     *
     * @param id 外运计划主键
     * @return 结果
     */
    public int deleteMinePlanOutwardTransportById(Long id);

    /**
     * 批量删除外运计划
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMinePlanOutwardTransportByIds(Long[] ids);

    /**
     * 查询外运计划
     *
     * @param id 外运计划主键
     * @return 外运计划
     */
    public MinePlanOutwardTransport selectMinePlanOutwardTransportByMonth(@Param("statsDate") Date statsDate);
    /**
     * 查询外运计划
     *
     * @param id 外运计划主键
     * @return 外运计划
     */
    public MinePlanOutwardTransport selectMinePlanOutwardTransportByYear(@Param("statsDate") Date statsDate);
}
