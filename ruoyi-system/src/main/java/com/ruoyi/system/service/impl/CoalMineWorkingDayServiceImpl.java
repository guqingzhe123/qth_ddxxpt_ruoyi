package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.CoalMineWorkingDay;
import com.ruoyi.system.mapper.CoalMineWorkingDayMapper;
import com.ruoyi.system.service.ICoalMineWorkingDayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作日Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-28
 */
@Slf4j
@Service
public class CoalMineWorkingDayServiceImpl implements ICoalMineWorkingDayService {
    @Autowired
    private CoalMineWorkingDayMapper coalMineWorkingDayMapper;

    /**
     * 查询工作日
     *
     * @param id 工作日主键
     * @return 工作日
     */
    @Override
    public CoalMineWorkingDay getCoalMineWorkingDayById(Long id) {
        return coalMineWorkingDayMapper.selectCoalMineWorkingDayById(id);
    }

    /**
     * 查询工作日列表
     *
     * @param coalMineWorkingDay 工作日
     * @return 工作日
     */
    @Override
    public List<CoalMineWorkingDay> listCoalMineWorkingDay(CoalMineWorkingDay coalMineWorkingDay) {
        return coalMineWorkingDayMapper.selectCoalMineWorkingDayList(coalMineWorkingDay);
    }

    /**
     * 新增工作日
     *
     * @param coalMineWorkingDay 工作日
     * @return 结果
     */
    @Override
    public int saveCoalMineWorkingDay(List<CoalMineWorkingDay> coalMineWorkingDay) {
        return coalMineWorkingDayMapper.insertCoalMineWorkingDay(coalMineWorkingDay);
    }

    /**
     * 修改工作日
     *
     * @param coalMineWorkingDay 工作日
     * @return 结果
     */
    @Override
    public int updateCoalMineWorkingDay(CoalMineWorkingDay coalMineWorkingDay) {
        return coalMineWorkingDayMapper.updateCoalMineWorkingDay(coalMineWorkingDay);
    }
    /**
     * 修改工作日
     *
     * @param coalMineWorkingDay 工作日
     * @return 结果
     */
    @Override
    public int batcupdateCoalMineWorkingDay(List<CoalMineWorkingDay> coalMineWorkingDay) {
        return coalMineWorkingDayMapper.batcupdateCoalMineWorkingDay(coalMineWorkingDay);
    }

    /**
     * 批量删除工作日
     *
     * @param ids 需要删除的工作日主键
     * @return 结果
     */
    @Override
    public int deleteCoalMineWorkingDayByIds(Long[] ids) {
        return coalMineWorkingDayMapper.deleteCoalMineWorkingDayByIds(ids);
    }

    /**
     * 删除工作日信息
     *
     * @param id 工作日主键
     * @return 结果
     */
    @Override
    public int deleteCoalMineWorkingDayById(Long id) {
        return coalMineWorkingDayMapper.deleteCoalMineWorkingDayById(id);
    }
}
