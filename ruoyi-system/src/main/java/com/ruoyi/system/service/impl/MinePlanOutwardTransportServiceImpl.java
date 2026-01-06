package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.MinePlanOutwardTransport;
import com.ruoyi.system.mapper.MinePlanOutwardTransportMapper;
import com.ruoyi.system.service.IMinePlanOutwardTransportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 外运计划Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-03
 */
@Slf4j
@Service
public class MinePlanOutwardTransportServiceImpl implements IMinePlanOutwardTransportService {
    @Autowired
    private MinePlanOutwardTransportMapper minePlanOutwardTransportMapper;

    /**
     * 查询外运计划
     *
     * @param id 外运计划主键
     * @return 外运计划
     */
    @Override
    public MinePlanOutwardTransport getMinePlanOutwardTransportById(Long id) {
        return minePlanOutwardTransportMapper.selectMinePlanOutwardTransportById(id);
    }

    /**
     * 查询外运计划列表
     *
     * @param minePlanOutwardTransport 外运计划
     * @return 外运计划
     */
    @Override
    public List<MinePlanOutwardTransport> listMinePlanOutwardTransport(MinePlanOutwardTransport minePlanOutwardTransport) {
        return minePlanOutwardTransportMapper.selectMinePlanOutwardTransportList(minePlanOutwardTransport);
    }

    /**
     * 新增外运计划
     *
     * @param minePlanOutwardTransport 外运计划
     * @return 结果
     */
    @Override
    public int saveMinePlanOutwardTransport(MinePlanOutwardTransport minePlanOutwardTransport) {
        return minePlanOutwardTransportMapper.insertMinePlanOutwardTransport(minePlanOutwardTransport);
    }

    /**
     * 修改外运计划
     *
     * @param minePlanOutwardTransport 外运计划
     * @return 结果
     */
    @Override
    public int updateMinePlanOutwardTransport(MinePlanOutwardTransport minePlanOutwardTransport) {
        return minePlanOutwardTransportMapper.updateMinePlanOutwardTransport(minePlanOutwardTransport);
    }

    /**
     * 批量删除外运计划
     *
     * @param ids 需要删除的外运计划主键
     * @return 结果
     */
    @Override
    public int deleteMinePlanOutwardTransportByIds(Long[] ids) {
        return minePlanOutwardTransportMapper.deleteMinePlanOutwardTransportByIds(ids);
    }

    /**
     * 删除外运计划信息
     *
     * @param id 外运计划主键
     * @return 结果
     */
    @Override
    public int deleteMinePlanOutwardTransportById(Long id) {
        return minePlanOutwardTransportMapper.deleteMinePlanOutwardTransportById(id);
    }
}
