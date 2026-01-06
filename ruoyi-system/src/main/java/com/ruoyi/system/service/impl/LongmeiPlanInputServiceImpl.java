package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.LongmeiPlanInput;
import com.ruoyi.system.mapper.LongmeiPlanInputMapper;
import com.ruoyi.system.service.ILongmeiPlanInputService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 龙煤计划录入Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class LongmeiPlanInputServiceImpl implements ILongmeiPlanInputService {
    @Autowired
    private LongmeiPlanInputMapper longmeiPlanInputMapper;

    /**
     * 查询龙煤计划录入
     *
     * @param id 龙煤计划录入主键
     * @return 龙煤计划录入
     */
    @Override
    public LongmeiPlanInput getLongmeiPlanInputById(Long id) {
        return longmeiPlanInputMapper.selectLongmeiPlanInputById(id);
    }

    /**
     * 查询龙煤计划录入列表
     *
     * @param longmeiPlanInput 龙煤计划录入
     * @return 龙煤计划录入
     */
    @Override
    public List<LongmeiPlanInput> listLongmeiPlanInput(LongmeiPlanInput longmeiPlanInput) {
        return longmeiPlanInputMapper.selectLongmeiPlanInputList(longmeiPlanInput);
    }

    /**
     * 新增龙煤计划录入
     *
     * @param longmeiPlanInput 龙煤计划录入
     * @return 结果
     */
    @Override
    public int saveLongmeiPlanInput(LongmeiPlanInput longmeiPlanInput) {
        longmeiPlanInput.setCreateTime(DateUtils.getNowDate());
        return longmeiPlanInputMapper.insertLongmeiPlanInput(longmeiPlanInput);
    }

    /**
     * 修改龙煤计划录入
     *
     * @param longmeiPlanInput 龙煤计划录入
     * @return 结果
     */
    @Override
    public int updateLongmeiPlanInput(LongmeiPlanInput longmeiPlanInput) {
        longmeiPlanInput.setUpdateTime(DateUtils.getNowDate());
        return longmeiPlanInputMapper.updateLongmeiPlanInput(longmeiPlanInput);
    }

    /**
     * 批量删除龙煤计划录入
     *
     * @param ids 需要删除的龙煤计划录入主键
     * @return 结果
     */
    @Override
    public int deleteLongmeiPlanInputByIds(Long[] ids) {
        return longmeiPlanInputMapper.deleteLongmeiPlanInputByIds(ids);
    }

    /**
     * 删除龙煤计划录入信息
     *
     * @param id 龙煤计划录入主键
     * @return 结果
     */
    @Override
    public int deleteLongmeiPlanInputById(Long id) {
        return longmeiPlanInputMapper.deleteLongmeiPlanInputById(id);
    }
}
