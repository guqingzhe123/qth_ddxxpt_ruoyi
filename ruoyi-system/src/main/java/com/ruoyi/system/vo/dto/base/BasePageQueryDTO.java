package com.ruoyi.system.vo.dto.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 通用分页查询入参
 */
@Data
public class BasePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 页码（从1开始） */
    private Integer pageNum = 1;

    /** 页大小 */
    private Integer pageSize = 10;

    /** 关键字（按需匹配 unitCode/unitName/备注等） */
    private String keyword;

    /** 过滤-单位编码 */
    private String unitCode;

    /** 过滤-矿类别 */
    private String mineCategory;

    /** 过滤-用户ID */
    private String userId;

    /** 时间范围（按各表含义） */
    private LocalDate beginDate;
    private LocalDate endDate;

    /** 备用：精确到时间的范围（某些表按 recordTime） */
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    /** 排序字段、方向（可交给 PageHelper/RuoYi 封装处理） */
    private String orderByColumn;
    private String isAsc; // asc/desc
}
