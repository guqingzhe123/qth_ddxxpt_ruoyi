package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.po.CoalPlantStoragePO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface CoalPlantStorageMapper {
    int insert(CoalPlantStoragePO po);
    int update(CoalPlantStoragePO po);
    int softDeleteById(Long id);
    CoalPlantStoragePO selectById(Long id);

    /**
     * 主表分页查询，支持子表筛选（EXISTS）1
     * params可包含：
     * unitName, userId, mineCategory, isDeleted,
     * recordTimeFrom, recordTimeTo,
     * recordDate, recordDateFrom, recordDateTo
     */
    List<CoalPlantStoragePO> selectList(Map<String, Object> params);
    List<CoalPlantStoragePO> selectListAll(Map<String, Object> params);


}
