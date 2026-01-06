package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.CokeSalesPlan;
import com.ruoyi.system.mapper.CokeSalesPlanMapper;
import com.ruoyi.system.service.ICokeSalesPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 焦炭销售计划录入Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class CokeSalesPlanServiceImpl implements ICokeSalesPlanService {
    @Autowired
    private CokeSalesPlanMapper cokeSalesPlanMapper;

    /**
     * 查询焦炭销售计划录入
     *
     * @param id 焦炭销售计划录入主键
     * @return 焦炭销售计划录入
     */
    @Override
    public CokeSalesPlan getCokeSalesPlanById(Long id) {
        return cokeSalesPlanMapper.selectCokeSalesPlanById(id);
    }

    /**
     * 查询焦炭销售计划录入列表
     *
     * @param cokeSalesPlan 焦炭销售计划录入
     * @return 焦炭销售计划录入
     */
    @Override
    public List<CokeSalesPlan> listCokeSalesPlan(CokeSalesPlan cokeSalesPlan) {
        return cokeSalesPlanMapper.selectCokeSalesPlanList(cokeSalesPlan);
    }

    /**
     * 新增焦炭销售计划录入
     *
     * @param cokeSalesPlan 焦炭销售计划录入
     * @return 结果
     */
    @Override
    public int saveCokeSalesPlan(CokeSalesPlan cokeSalesPlan) {
        CokeSalesPlan plan=new CokeSalesPlan();
        plan.setPlanMonth(cokeSalesPlan.getPlanMonth());
        List<CokeSalesPlan> cokeSalesPlans = listCokeSalesPlan(plan);
        if(cokeSalesPlans.size()>0){
            cokeSalesPlan.setId(cokeSalesPlans.get(0).getId());
            cokeSalesPlan.setUpdateTime(DateUtils.getNowDate());
            return cokeSalesPlanMapper.updateCokeSalesPlan(cokeSalesPlan);
        }

        cokeSalesPlan.setCreateTime(DateUtils.getNowDate());
        return cokeSalesPlanMapper.insertCokeSalesPlan(cokeSalesPlan);
    }

    /**
     * 修改焦炭销售计划录入
     *
     * @param cokeSalesPlan 焦炭销售计划录入
     * @return 结果
     */
    @Override
    public int updateCokeSalesPlan(CokeSalesPlan cokeSalesPlan) {
        cokeSalesPlan.setUpdateTime(DateUtils.getNowDate());
        return cokeSalesPlanMapper.updateCokeSalesPlan(cokeSalesPlan);
    }

    /**
     * 批量删除焦炭销售计划录入
     *
     * @param ids 需要删除的焦炭销售计划录入主键
     * @return 结果
     */
    @Override
    public int deleteCokeSalesPlanByIds(Long[] ids) {
        return cokeSalesPlanMapper.deleteCokeSalesPlanByIds(ids);
    }

    /**
     * 删除焦炭销售计划录入信息
     *
     * @param id 焦炭销售计划录入主键
     * @return 结果
     */
    @Override
    public int deleteCokeSalesPlanById(Long id) {
        return cokeSalesPlanMapper.deleteCokeSalesPlanById(id);
    }
}
