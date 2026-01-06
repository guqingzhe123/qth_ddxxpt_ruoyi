package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.ProductExportSituation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductExportSituationMapper {
    ProductExportSituation selectById(@Param("id") Long id);
    List<ProductExportSituation> selectList(ProductExportSituation query);
    int insert(ProductExportSituation entity);
    int update(ProductExportSituation entity);
    int deleteById(@Param("id") Long id);
    int deleteByIds(@Param("ids") List<Long> ids);
    List<ProductExportSituation> selectMonthList(ProductExportSituation query);
}
