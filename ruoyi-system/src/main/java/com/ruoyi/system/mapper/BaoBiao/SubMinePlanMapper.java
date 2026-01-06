package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.po.MinePlanPO;
import com.ruoyi.system.domain.BaoBiao.po.SubMinePlanP1;
import com.ruoyi.system.domain.BaoBiao.po.SubMinePlanPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SubMinePlanMapper {
    int batchInsert(List<SubMinePlanPO> list);
    int deleteByPlanId(Long planId);
    int deleteById(Long id);
    int update(@Param("id") Long id, @Param("isDeleted") Integer isDeleted);
    int updateDell(@Param("id") Long id, @Param("isDeleted") Integer isDeleted);
    int updateDay(SubMinePlanPO po);

    SubMinePlanPO selectById(Long id);
    List<SubMinePlanPO> selectByPlanId(Long planId);
    List<SubMinePlanPO> selectByPlanIds(List<Long> planIds);
    List<SubMinePlanPO> selectByPlanYear(@Param("statsDate") Date statsDate, @Param("planType") String planType);
    List<SubMinePlanPO> selectByPlanMonth(@Param("statsDate") Date statsDate, @Param("planType") String planType);

    List<SubMinePlanPO> selectByPlanOneYear(@Param("statsDate") Date statsDate, @Param("planType") String planType, @Param("unitName") String unitName);

    SubMinePlanP1 selectAllJiHua(MinePlanPO planPO);
    SubMinePlanP1 selectAllJiHuaYear(MinePlanPO planPO);
}
