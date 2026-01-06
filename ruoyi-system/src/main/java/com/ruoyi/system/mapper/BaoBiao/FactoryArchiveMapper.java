package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FactoryArchiveMapper {
    FactoryArchive selectById(@Param("id") Long id);
    List<FactoryArchive> selectList(FactoryArchive query);
    int insert(FactoryArchive entity);
    int update(FactoryArchive entity);
    int deleteById(@Param("id") Long id);
    int deleteByIds(@Param("ids") List<Long> ids);

    FactoryArchive getName(@Param("name") String name);

}
