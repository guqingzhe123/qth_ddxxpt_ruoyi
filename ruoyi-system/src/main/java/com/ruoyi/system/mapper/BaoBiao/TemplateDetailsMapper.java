package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.TemplateDetails;
import com.ruoyi.system.domain.BaoBiao.TemplateDetailsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TemplateDetailsMapper {
    TemplateDetails selectById(@Param("id") Long id);
    List<TemplateDetails> selectList(TemplateDetails query);
    int insert(TemplateDetails entity);
    int update(TemplateDetails entity);
    int deleteById(@Param("id") Long id);
    int deleteByIds(@Param("ids") List<Long> ids);

    // 新增：按矿模板ID查详情（只返回详情字段）
    List<TemplateDetails> selectByTemplateId(@Param("mineTemplateId") Long mineTemplateId);

    // 新增：按矿模板ID查详情 + 带出模板信息（JOIN）
    List<TemplateDetailsVO> selectVOByTemplateId(@Param("mineTemplateId") Long mineTemplateId);
}
