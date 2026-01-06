package com.ruoyi.system.mapper.work;

import com.ruoyi.system.domain.work.SafetyInfluencingFactors;

import java.util.List;

/**
 * 公司各单位影响安全生产因素（原因）Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-30
 */
public interface SafetyInfluencingFactorsMapper {
    /**
     * 查询公司各单位影响安全生产因素（原因）
     *
     * @param id 公司各单位影响安全生产因素（原因）主键
     * @return 公司各单位影响安全生产因素（原因）
     */
    public SafetyInfluencingFactors selectSafetyInfluencingFactorsById(Long id);

    /**
     * 查询公司各单位影响安全生产因素（原因）列表
     *
     * @param safetyInfluencingFactors 公司各单位影响安全生产因素（原因）
     * @return 公司各单位影响安全生产因素（原因）集合
     */
    public List<SafetyInfluencingFactors> selectSafetyInfluencingFactorsList(SafetyInfluencingFactors safetyInfluencingFactors);

    /**
     * 新增公司各单位影响安全生产因素（原因）
     *
     * @param safetyInfluencingFactors 公司各单位影响安全生产因素（原因）
     * @return 结果
     */
    public int insertSafetyInfluencingFactors(SafetyInfluencingFactors safetyInfluencingFactors);

    /**
     * 修改公司各单位影响安全生产因素（原因）
     *
     * @param safetyInfluencingFactors 公司各单位影响安全生产因素（原因）
     * @return 结果
     */
    public int updateSafetyInfluencingFactors(SafetyInfluencingFactors safetyInfluencingFactors);

    /**
     * 删除公司各单位影响安全生产因素（原因）
     *
     * @param id 公司各单位影响安全生产因素（原因）主键
     * @return 结果
     */
    public int deleteSafetyInfluencingFactorsById(Long id);

    /**
     * 批量删除公司各单位影响安全生产因素（原因）
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSafetyInfluencingFactorsByIds(Long[] ids);
}
