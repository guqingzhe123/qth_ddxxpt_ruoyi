package com.ruoyi.system.service.export;

import com.ruoyi.system.domain.export.WPlanCompletion;

import java.util.List;

/**
 * 煤炭销售汽运计划与完成Service接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface IWPlanCompletionService {
    /**
     * 查询煤炭销售汽运计划与完成
     * 
     * @param id 煤炭销售汽运计划与完成主键
     * @return 煤炭销售汽运计划与完成
     */
    public WPlanCompletion getWPlanCompletionById(String id);

    /**
     * 查询煤炭销售汽运计划与完成列表
     * 
     * @param wPlanCompletion 煤炭销售汽运计划与完成
     * @return 煤炭销售汽运计划与完成集合
     */
    public List<WPlanCompletion> listWPlanCompletion(WPlanCompletion wPlanCompletion);

    /**
     * 新增煤炭销售汽运计划与完成
     * 
     * @param wPlanCompletion 煤炭销售汽运计划与完成
     * @return 结果
     */
    public int saveWPlanCompletion(WPlanCompletion wPlanCompletion);

    /**
     * 修改煤炭销售汽运计划与完成
     * 
     * @param wPlanCompletion 煤炭销售汽运计划与完成
     * @return 结果
     */
    public int updateWPlanCompletion(WPlanCompletion wPlanCompletion);

    /**
     * 批量删除煤炭销售汽运计划与完成
     * 
     * @param ids 需要删除的煤炭销售汽运计划与完成主键集合
     * @return 结果
     */
    public int deleteWPlanCompletionByIds(String[] ids);

    /**
     * 删除煤炭销售汽运计划与完成信息
     * 
     * @param id 煤炭销售汽运计划与完成主键
     * @return 结果
     */
    public int deleteWPlanCompletionById(String id);
}
