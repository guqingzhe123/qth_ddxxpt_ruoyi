package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.po.CoalWashingProductionPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface CoalWashingProductionMapper {

    int insert(CoalWashingProductionPO po);

    int update(CoalWashingProductionPO po);

    int deleteById(Long id);

    CoalWashingProductionPO selectById(Long id);

    CoalWashingProductionPO selectByUnique(Date productionDate, String unitCode);

    /**
     * 分页查询
     * 参数 keys:
     *  productionDateFrom, productionDateTo, unitCode, unitName, mineCategory, userId, isDeleted
     */
    List<CoalWashingProductionPO> selectPageList(Map<String, Object> params);

    CoalWashingProductionPO selectByDay(@Param("statsDate") Date statsDate, @Param("unitCode") String unitCode);
    CoalWashingProductionPO selectByInDay(@Param("statsDate") Date statsDate);


    CoalWashingProductionPO selectByMonth(@Param("statsDate") Date statsDate, @Param("unitCode") String unitCode);

    CoalWashingProductionPO selectByYear(@Param("statsDate") Date statsDate, @Param("unitCode") String unitCode);


    CoalWashingProductionPO selectByDayUnitCode(@Param("statsDate") Date statsDate);

    CoalWashingProductionPO selectByMonthUnitCode(@Param("statsDate") Date statsDate);

    CoalWashingProductionPO selectByYearUnitCode(@Param("statsDate") Date statsDate);

    List<CoalWashingProductionPO> selecList(@Param("statsDate") Date statsDate);
    List<CoalWashingProductionPO> selecMonthList(@Param("statsDate") Date statsDate);
    List<CoalWashingProductionPO> selecYearList(@Param("statsDate") Date statsDate);

}
