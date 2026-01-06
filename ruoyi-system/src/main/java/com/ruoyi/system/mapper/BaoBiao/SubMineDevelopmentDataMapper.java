package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.po.SubMineDevelopmentDataPO;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MineData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SubMineDevelopmentDataMapper {
    int batchInsert(List<SubMineDevelopmentDataPO> list);
    int deleteByMddId(Long mddId);
    List<SubMineDevelopmentDataPO> selectByMddId(Long mddId);
    List<SubMineDevelopmentDataPO> selectByMddIds(List<Long> ids);


    List<SubMineDevelopmentDataPO> selectDay(@Param("unitCode")String unitCode,@Param("recordDate") Date recordDate);
    List<SubMineDevelopmentDataPO> selectMonth(@Param("unitCode")String unitCode,@Param("recordDate") Date recordDate);
    List<SubMineDevelopmentDataPO> selectYear( @Param("unitCode")String unitCode,@Param("recordDate") Date recordDate);

    SubMineDevelopmentDataPO selectAllDay(@Param("recordDate") Date recordDate);
    SubMineDevelopmentDataPO selectAllMonth(@Param("recordDate") Date recordDate);
    SubMineDevelopmentDataPO selectAllYear(@Param("recordDate") Date recordDate);
    List<MineData> selectDayDate(@Param("recordDate") String recordDate);
    List<MineData> selectMonthDate(@Param("recordDate") String recordDate);
    List<MineData> selectYearDate(@Param("recordDate") String recordDate);

    int deleteByMddIdAll(@Param("unitName")String unitName,@Param("recordDate") String recordDate);

}
