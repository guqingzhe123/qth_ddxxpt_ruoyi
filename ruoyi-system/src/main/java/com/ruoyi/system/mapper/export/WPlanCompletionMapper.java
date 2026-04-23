package com.ruoyi.system.mapper.export;

import java.util.List;
import com.ruoyi.system.domain.export.WPlanCompletion;

/**
 * 煤炭销售汽运计划与完成Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface WPlanCompletionMapper {
    /**
     * 查询煤炭销售汽运计划与完成
     * 
     * @param id 煤炭销售汽运计划与完成主键
     * @return 煤炭销售汽运计划与完成
     */
    public WPlanCompletion selectWPlanCompletionById(String id);

    /**
     * 查询煤炭销售汽运计划与完成列表
     * 
     * @param wPlanCompletion 煤炭销售汽运计划与完成
     * @return 煤炭销售汽运计划与完成集合
     */
    public List<WPlanCompletion> selectWPlanCompletionList(WPlanCompletion wPlanCompletion);

    /**
     * 新增煤炭销售汽运计划与完成
     * 
     * @param wPlanCompletion 煤炭销售汽运计划与完成
     * @return 结果
     */
    public int insertWPlanCompletion(WPlanCompletion wPlanCompletion);

    /**
     * 修改煤炭销售汽运计划与完成
     * 
     * @param wPlanCompletion 煤炭销售汽运计划与完成
     * @return 结果
     */
    public int updateWPlanCompletion(WPlanCompletion wPlanCompletion);

    /**
     * 删除煤炭销售汽运计划与完成
     * 
     * @param id 煤炭销售汽运计划与完成主键
     * @return 结果
     */
    public int deleteWPlanCompletionById(String id);

    /**
     * 批量删除煤炭销售汽运计划与完成
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWPlanCompletionByIds(String[] ids);
}
