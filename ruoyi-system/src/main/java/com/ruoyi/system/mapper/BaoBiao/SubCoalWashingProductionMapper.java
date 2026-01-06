package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.po.SubCoalWashingProductionPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubCoalWashingProductionMapper {
    int batchInsert(List<SubCoalWashingProductionPO> list);
    int deleteByParentId(Long parentId);
    List<SubCoalWashingProductionPO> selectByParentId(Long parentId);
    List<SubCoalWashingProductionPO> selectByParentIds(List<Long> parentIds);
}
