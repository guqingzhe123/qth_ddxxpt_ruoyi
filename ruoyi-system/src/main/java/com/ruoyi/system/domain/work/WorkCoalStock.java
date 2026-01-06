package com.ruoyi.system.domain.work;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkCoalStock {
    List<WorkCompanyCoalStock> listCompanyCoalStock;//煤气公司精煤库存统计对象
    List<WorkThermalPowerCoalSales> listThermalPowerCoalSales;//热电厂煤种销售库存统计对象
    List<WorkCoalStockSalesStat> listCoalStockSalesStat;//煤炭库存销售统计对象
}
