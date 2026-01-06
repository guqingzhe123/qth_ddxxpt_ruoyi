package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.po.SubWashCoalPlanPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SubWashCoalPlanMapper {
    int batchInsert(List<SubWashCoalPlanPO> list);
    int uopDate(SubWashCoalPlanPO list);
    int deleteByPlanId(Long planId);
    List<SubWashCoalPlanPO> selectByPlanId(Long planId);
    List<SubWashCoalPlanPO> selectByPlanIds(List<Long> planIds);

    SubWashCoalPlanPO selectByPlanIdUnitName(@Param("unitName") String unitName, @Param("planId") Long planId);
    SubWashCoalPlanPO selectByPlanMonth(@Param("statsDate") Date statsDate, @Param("unitCode") String unitCode);
    SubWashCoalPlanPO selectByPlanYear(@Param("statsDate") Date statsDate, @Param("unitCode") String unitCode);

    List<SubWashCoalPlanPO> selectByPlanDayUnitCode(@Param("statsDate") Date statsDate);
    List<SubWashCoalPlanPO> selectByPlanYearUnitCode(@Param("statsDate") Date statsDate);



    int deleteByMddIdAll(@Param("unitName") String unitName,@Param("washCoalPlanId") Long statsDate);
}
