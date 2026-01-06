package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MiningAreaCategoryMapper {
    MiningAreaCategory selectById(@Param("id") Long id);
    List<MiningAreaCategory> selectList(MiningAreaCategory query);
    MiningAreaCategory select(MiningAreaCategory query);
    int insert(MiningAreaCategory entity);
    int update(MiningAreaCategory entity);
    int deleteById(@Param("id") Long id);
    int deleteByIds(@Param("id") List<Long> id);
    MiningAreaCategory getPartentId(@Param("ids") Integer ids);
    List<MiningAreaCategory> selectByLevel(@Param("level") Integer level);
    MiningAreaCategory getAreaName(@Param("areaName") String areaName);
    MiningAreaCategory getAreaCode(@Param("areaCode") String areaCode);


    List<MiningAreaCategory> QueryTeamName(@Param("areaCode") String areaCode);

}
