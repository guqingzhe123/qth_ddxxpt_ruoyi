package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 原煤去向对照（记录各煤矿原煤每日及累计去向数据）对象 raw_coal_whereabouts
 *
 * @author ruoyi
 * @date 2025-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RawCoalWhereabouts extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID（唯一标识记录） */
    private String id;

    /** 煤矿/单位名称（如“新建矿”“货场处”） */
    @Excel(name = "煤矿/单位名称", readConverterExp = "如=“新建矿”“货场处”")
    private String danwei;

    /** 数据日期（格式：YYYY-MM-DD） */
    @Excel(name = "数据日期", readConverterExp = "格=式：YYYY-MM-DD")
    private Date rq;

    /** 洗厂-日产量 */
    @Excel(name = "洗厂-日产量")
    private BigDecimal xichangRi;

    /** 洗厂-累计产量 */
    @Excel(name = "洗厂-累计产量")
    private BigDecimal xichangLeiji;

    /** 挑选厂-日产量 */
    @Excel(name = "挑选厂-日产量")
    private BigDecimal tiaoxianchangRi;

    /** 挑选厂-累计产量 */
    @Excel(name = "挑选厂-累计产量")
    private BigDecimal tiaoxianchangLeiji;

    /** 新选厂-日产量 */
    @Excel(name = "新选厂-日产量")
    private BigDecimal xinxuanchangRi;

    /** 新选厂-累计产量 */
    @Excel(name = "新选厂-累计产量")
    private BigDecimal xinxuanchangLeiji;

    /** 铁选厂-日产量 */
    @Excel(name = "铁选厂-日产量")
    private BigDecimal tiexuanchangRi;

    /** 铁选厂-累计产量 */
    @Excel(name = "铁选厂-累计产量")
    private BigDecimal tiexuanchangLeiji;

    /** 龙洗厂-日产量 */
    @Excel(name = "龙洗厂-日产量")
    private BigDecimal longxichangRi;

    /** 龙洗厂-累计产量 */
    @Excel(name = "龙洗厂-累计产量")
    private BigDecimal longxichangLeiji;

    /** 富洗厂-日产量 */
    @Excel(name = "富洗厂-日产量")
    private BigDecimal fuxichangRi;

    /** 富洗厂-累计产量 */
    @Excel(name = "富洗厂-累计产量")
    private BigDecimal fuxichangLeiji;

    /** 煤气公司-日产量 */
    @Excel(name = "煤气公司-日产量")
    private BigDecimal meiqigongsiRi;

    /** 煤气公司-累计产量 */
    @Excel(name = "煤气公司-累计产量")
    private BigDecimal meiqigongsiLeiji;

    /** 自用-日产量 */
    @Excel(name = "自用-日产量")
    private BigDecimal ziyongRi;

    /** 自用-累计产量 */
    @Excel(name = "自用-累计产量")
    private BigDecimal ziyongLeiji;

    /** 矸石电厂-日产量 */
    @Excel(name = "矸石电厂-日产量")
    private BigDecimal ganshidianchangRi;

    /** 矸石电厂-累计产量 */
    @Excel(name = "矸石电厂-累计产量")
    private BigDecimal ganshidianchangLeiji;

    /** 货场处-日产量 */
    @Excel(name = "货场处-日产量")
    private BigDecimal huochangchuRi;

    /** 货场处-累计产量 */
    @Excel(name = "货场处-累计产量")
    private BigDecimal huochangchuLeiji;

    /** 铁外销-日产量 */
    @Excel(name = "铁外销-日产量")
    private BigDecimal tiewaixiaoRi;

    /** 铁外销-累计产量 */
    @Excel(name = "铁外销-累计产量")
    private BigDecimal tiewaixiaoLeiji;

    /** 其它-日产量 */
    @Excel(name = "其它-日产量")
    private BigDecimal qitaRi;

    /** 其它-累计产量 */
    @Excel(name = "其它-累计产量")
    private BigDecimal qitaLeiji;

    /** 总合计-日产量（各去向日产量之和） */
    @Excel(name = "总合计-日产量", readConverterExp = "各=去向日产量之和")
    private BigDecimal zonghejiRi;

    /** 总合计-累计产量（各去向累计之和） */
    @Excel(name = "总合计-累计产量", readConverterExp = "各=去向累计之和")
    private BigDecimal zonghejiLeiji;

    /** 对比-日生产（计划/实际日产量） */
    @Excel(name = "对比-日生产", readConverterExp = "计=划/实际日产量")
    private BigDecimal riShengchan;

    /** 对比-日差（日合计 - 日生产） */
    @Excel(name = "对比-日差", readConverterExp = "日=合计,-=,日=生产")
    private BigDecimal riCha;

    /** 对比-累计差（累计合计 - 累计生产） */
    @Excel(name = "对比-累计差", readConverterExp = "累=计合计,-=,累=计生产")
    private BigDecimal leijiCha;

    /** 备注（如异常数据说明、调整记录） */
    @Excel(name = "备注", readConverterExp = "如=异常数据说明、调整记录")
    private String beizhu;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("danwei", getDanwei())
                .append("rq", getRq())
                .append("xichangRi", getXichangRi())
                .append("xichangLeiji", getXichangLeiji())
                .append("tiaoxianchangRi", getTiaoxianchangRi())
                .append("tiaoxianchangLeiji", getTiaoxianchangLeiji())
                .append("xinxuanchangRi", getXinxuanchangRi())
                .append("xinxuanchangLeiji", getXinxuanchangLeiji())
                .append("tiexuanchangRi", getTiexuanchangRi())
                .append("tiexuanchangLeiji", getTiexuanchangLeiji())
                .append("longxichangRi", getLongxichangRi())
                .append("longxichangLeiji", getLongxichangLeiji())
                .append("fuxichangRi", getFuxichangRi())
                .append("fuxichangLeiji", getFuxichangLeiji())
                .append("meiqigongsiRi", getMeiqigongsiRi())
                .append("meiqigongsiLeiji", getMeiqigongsiLeiji())
                .append("ziyongRi", getZiyongRi())
                .append("ziyongLeiji", getZiyongLeiji())
                .append("ganshidianchangRi", getGanshidianchangRi())
                .append("ganshidianchangLeiji", getGanshidianchangLeiji())
                .append("huochangchuRi", getHuochangchuRi())
                .append("huochangchuLeiji", getHuochangchuLeiji())
                .append("tiewaixiaoRi", getTiewaixiaoRi())
                .append("tiewaixiaoLeiji", getTiewaixiaoLeiji())
                .append("qitaRi", getQitaRi())
                .append("qitaLeiji", getQitaLeiji())
                .append("zonghejiRi", getZonghejiRi())
                .append("zonghejiLeiji", getZonghejiLeiji())
                .append("riShengchan", getRiShengchan())
                .append("riCha", getRiCha())
                .append("leijiCha", getLeijiCha())
                .append("beizhu", getBeizhu())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
