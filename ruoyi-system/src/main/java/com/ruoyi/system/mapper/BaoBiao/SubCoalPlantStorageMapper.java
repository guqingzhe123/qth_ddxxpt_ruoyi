package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.po.SubCoalPlantStoragePO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SubCoalPlantStorageMapper {
    int batchInsert(List<SubCoalPlantStoragePO> list);
    int deleteByParentId(Long parentId);
    List<SubCoalPlantStoragePO> selectByParentId(Long parentId);
    List<SubCoalPlantStoragePO> selectByParentIds(List<Long> parentIds);

    int updateById(SubCoalPlantStoragePO subCoalPlantStorage);
    int deleteById(Long Id);
}
