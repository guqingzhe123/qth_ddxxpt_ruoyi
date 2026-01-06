package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.UnitManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UnitManagementMapper {
    UnitManagement selectById(@Param("id") Long id);
    List<UnitManagement> selectList(UnitManagement query);
    int insert(UnitManagement entity);
    int update(UnitManagement entity);
    int deleteById(@Param("id") Long id);
    int deleteByIds(@Param("ids") List<Long> ids);
}
