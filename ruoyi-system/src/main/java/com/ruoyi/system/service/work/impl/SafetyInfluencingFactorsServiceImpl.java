package com.ruoyi.system.service.work.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.work.SafetyInfluencingFactors;
import com.ruoyi.system.mapper.work.SafetyInfluencingFactorsMapper;
import com.ruoyi.system.service.work.ISafetyInfluencingFactorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司各单位影响安全生产因素（原因）Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-30
 */
@Slf4j
@Service
public class SafetyInfluencingFactorsServiceImpl implements ISafetyInfluencingFactorsService {
    @Autowired
    private SafetyInfluencingFactorsMapper safetyInfluencingFactorsMapper;

    /**
     * 查询公司各单位影响安全生产因素（原因）
     *
     * @param id 公司各单位影响安全生产因素（原因）主键
     * @return 公司各单位影响安全生产因素（原因）
     */
    @Override
    public SafetyInfluencingFactors getSafetyInfluencingFactorsById(Long id) {
        return safetyInfluencingFactorsMapper.selectSafetyInfluencingFactorsById(id);
    }

    /**
     * 查询公司各单位影响安全生产因素（原因）列表
     *
     * @param safetyInfluencingFactors 公司各单位影响安全生产因素（原因）
     * @return 公司各单位影响安全生产因素（原因）
     */
    @Override
    public List<SafetyInfluencingFactors> listSafetyInfluencingFactors(SafetyInfluencingFactors safetyInfluencingFactors) {
        return safetyInfluencingFactorsMapper.selectSafetyInfluencingFactorsList(safetyInfluencingFactors);
    }

    /**
     * 新增公司各单位影响安全生产因素（原因）
     *
     * @param safetyInfluencingFactors 公司各单位影响安全生产因素（原因）
     * @return 结果
     */
    @Override
    public int saveSafetyInfluencingFactors(SafetyInfluencingFactors safetyInfluencingFactors) {
        safetyInfluencingFactors.setCreateTime(DateUtils.getNowDate());
        return safetyInfluencingFactorsMapper.insertSafetyInfluencingFactors(safetyInfluencingFactors);
    }

    /**
     * 修改公司各单位影响安全生产因素（原因）
     *
     * @param safetyInfluencingFactors 公司各单位影响安全生产因素（原因）
     * @return 结果
     */
    @Override
    public int updateSafetyInfluencingFactors(SafetyInfluencingFactors safetyInfluencingFactors) {
        return safetyInfluencingFactorsMapper.updateSafetyInfluencingFactors(safetyInfluencingFactors);
    }

    /**
     * 批量删除公司各单位影响安全生产因素（原因）
     *
     * @param ids 需要删除的公司各单位影响安全生产因素（原因）主键
     * @return 结果
     */
    @Override
    public int deleteSafetyInfluencingFactorsByIds(Long[] ids) {
        return safetyInfluencingFactorsMapper.deleteSafetyInfluencingFactorsByIds(ids);
    }

    /**
     * 删除公司各单位影响安全生产因素（原因）信息
     *
     * @param id 公司各单位影响安全生产因素（原因）主键
     * @return 结果
     */
    @Override
    public int deleteSafetyInfluencingFactorsById(Long id) {
        return safetyInfluencingFactorsMapper.deleteSafetyInfluencingFactorsById(id);
    }
}
