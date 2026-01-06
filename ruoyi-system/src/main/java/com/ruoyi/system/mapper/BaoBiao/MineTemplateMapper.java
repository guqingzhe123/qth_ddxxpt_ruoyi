package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.MineTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MineTemplateMapper {
    MineTemplate selectById(@Param("id") Long id);
    List<MineTemplate> selectList(MineTemplate query);
    int insert(MineTemplate entity);
    int update(MineTemplate entity);
    int deleteById(@Param("id") Long id);
    int deleteByIds(@Param("ids") List<Long> ids);
}
