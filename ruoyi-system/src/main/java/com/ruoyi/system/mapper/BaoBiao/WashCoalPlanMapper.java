package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.WashCoalPlan;
import com.ruoyi.system.domain.BaoBiao.po.WashCoalPlanPO;
import com.ruoyi.system.vo.WashCoalPlanPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.vo.dto.wash.WashCoalPlanPageQueryDTO;
import com.ruoyi.system.domain.BaoBiao.entity.WashCoalPlanRow;

import java.util.Date;
import java.util.List;


@Mapper
public interface WashCoalPlanMapper {
    int insert(WashCoalPlan plan);
    //int updateById(WashCoalPlan plan);
    //WashCoalPlan selectById(@Param("id") Long id);
    WashCoalPlanPO selectById(Long id);
    /** 分页列表（摘要，不还原 data_JSON） */
    List<WashCoalPlanPageVO> selectPageList(@Param("planMonth") String planMonth,
                                            @Param("mineCategory") String mineCategory,
                                            @Param("userId") String userId);

    /**
     * 分页/条件查询（只做“查”，分页由 PageHelper/Controller startPage 驱动）
     */
    List<WashCoalPlanRow> selectPage(@Param("q") WashCoalPlanPageQueryDTO q);


    int insert(WashCoalPlanPO po);
    int update(WashCoalPlanPO po);
    int softDeleteById(Long id);
    List<WashCoalPlanPO> selectList(WashCoalPlanPO criteria);

    WashCoalPlanPO selectByPlan(@Param("planMonth") Date planMonth);

}