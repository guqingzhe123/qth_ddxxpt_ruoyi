package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.ProductExportSituation;
import java.util.List;

public interface IProductExportSituationService {
    ProductExportSituation get(Long id);
    List<ProductExportSituation> list(ProductExportSituation query);
    int add(ProductExportSituation entity);
    int edit(ProductExportSituation entity);
    int remove(Long id);
    int removeBatch(List<Long> ids);
}
