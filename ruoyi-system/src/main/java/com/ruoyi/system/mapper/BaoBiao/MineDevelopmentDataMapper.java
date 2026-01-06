package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.po.MineDevelopmentDataPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MineDevelopmentDataMapper {
    int insert(MineDevelopmentDataPO po);
    int update(MineDevelopmentDataPO po);
    int softDeleteById(Long id);
    int softUpdeById(@Param("id")  Long id, @Param("totalDownCount")   Integer totalDownCount,@Param("totalUpCount")   Integer totalUpCount);
    MineDevelopmentDataPO selectById(Long id);
    List<MineDevelopmentDataPO> selectList(MineDevelopmentDataPO criteria);
}
