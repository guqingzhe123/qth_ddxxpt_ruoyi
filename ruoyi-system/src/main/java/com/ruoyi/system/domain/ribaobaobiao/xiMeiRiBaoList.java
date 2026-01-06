package com.ruoyi.system.domain.ribaobaobiao;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 洗煤生产日报表
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class xiMeiRiBaoList extends BaseEntity {

    /** 本日 */
    private List<xiMeiRiBaoBaoBiao> day;
    /** 本月 */
    private List<xiMeiRiBaoBaoBiao> month;
    /** 本年 */
    private List<xiMeiRiBaoBaoBiao> year;

}
