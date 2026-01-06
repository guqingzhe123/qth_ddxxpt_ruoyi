package com.ruoyi.system.domain.work;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 各选煤厂库存煤明细表实体类
 *
 * @author ruoyi
 */
@Data
public class CoalStockDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 单位
     */
    private String unitName;


    /**
     * 新建矿库存量（吨）
     */
    private String newJianMineStock;

    /**
     * 新兴矿库存量（吨）
     */
    private String xinXingMineStock;

    /**
     * 原煤库存量（吨）
     */
    private String rawCoalMineStock;

    /**
     * 筛上物库存量（吨）
     */
    private String oversizeMaterialMineStock;

    /**
     * 新立矿库存量（吨）
     */
    private String xinLiMineStock;

    /**
     * 新铁矿库存量（吨）
     */
    private String xinTieMineStock;

    /**
     * 龙选货场库存量（吨）
     */
    private String longXuanCargoStock;

    /**
     * 龙湖矿库存量（吨）
     */
    private String longHuMineStock;

    /**
     * 向阳矿库存量（吨）
     */
    private String xiangYangMineStock;

    /**
     * 胜利矿库存量（吨）
     */
    private String shengLiMineStock;

    /**
     * 一采一井库存量（吨）
     */
    private String oneCaiOneWellStock;

    /**
     * 一采六井库存量（吨）
     */
    private String oneCaiSixWellStock;

    /**
     * 建设矿库存量（吨）
     */
    private String jianSheMineStock;

    /**
     * 小计（本行合计）
     */
    private String subtotal;

    /**
     * 总计（所有矿合计）
     */
    private String total;

    /**
     * 报告日期
     */
    private String reportDate;
}

