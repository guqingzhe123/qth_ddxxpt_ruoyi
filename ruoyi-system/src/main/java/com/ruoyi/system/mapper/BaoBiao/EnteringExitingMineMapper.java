package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.EnteringExitingMinePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EnteringExitingMineMapper {

    /**
     * 按日期区间查询当天所有单位记录
     * params:
     *   startTime: Date (当天 00:00:00)
     *   endTime:   Date (次日 00:00:00)
     */
    List<EnteringExitingMinePO> selectByDate(Map<String, Object> params);
}
