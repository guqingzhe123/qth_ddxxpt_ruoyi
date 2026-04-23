package com.ruoyi.system.service.export.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.export.WPlanCompletion;
import com.ruoyi.system.mapper.export.WPlanCompletionMapper;
import com.ruoyi.system.service.export.IWPlanCompletionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 煤炭销售汽运计划与完成Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WPlanCompletionServiceImpl implements IWPlanCompletionService {
    @Autowired
    private WPlanCompletionMapper wPlanCompletionMapper;

    /**
     * 查询煤炭销售汽运计划与完成
     * 
     * @param id 煤炭销售汽运计划与完成主键
     * @return 煤炭销售汽运计划与完成
     */
    @Override
    public WPlanCompletion getWPlanCompletionById(String id) {
        return wPlanCompletionMapper.selectWPlanCompletionById(id);
    }

    /**
     * 查询煤炭销售汽运计划与完成列表
     * 
     * @param wPlanCompletion 煤炭销售汽运计划与完成
     * @return 煤炭销售汽运计划与完成
     */
    @Override
    public List<WPlanCompletion> listWPlanCompletion(WPlanCompletion wPlanCompletion) {
        return wPlanCompletionMapper.selectWPlanCompletionList(wPlanCompletion);
    }

    /**
     * 新增煤炭销售汽运计划与完成
     * 
     * @param wPlanCompletion 煤炭销售汽运计划与完成
     * @return 结果
     */
    @Override
    public int saveWPlanCompletion(WPlanCompletion wPlanCompletion) {
        wPlanCompletion.setCreateTime(DateUtils.getNowDate());
        return wPlanCompletionMapper.insertWPlanCompletion(wPlanCompletion);
    }

    /**
     * 修改煤炭销售汽运计划与完成
     * 
     * @param wPlanCompletion 煤炭销售汽运计划与完成
     * @return 结果
     */
    @Override
    public int updateWPlanCompletion(WPlanCompletion wPlanCompletion) {
        wPlanCompletion.setUpdateTime(DateUtils.getNowDate());
        return wPlanCompletionMapper.updateWPlanCompletion(wPlanCompletion);
    }

    /**
     * 批量删除煤炭销售汽运计划与完成
     * 
     * @param ids 需要删除的煤炭销售汽运计划与完成主键
     * @return 结果
     */
    @Override
    public int deleteWPlanCompletionByIds(String[] ids) {
        return wPlanCompletionMapper.deleteWPlanCompletionByIds(ids);
    }

    /**
     * 删除煤炭销售汽运计划与完成信息
     * 
     * @param id 煤炭销售汽运计划与完成主键
     * @return 结果
     */
    @Override
    public int deleteWPlanCompletionById(String id) {
        return wPlanCompletionMapper.deleteWPlanCompletionById(id);
    }
}
