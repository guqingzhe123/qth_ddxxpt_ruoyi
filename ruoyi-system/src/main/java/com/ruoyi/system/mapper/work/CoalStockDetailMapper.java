package com.ruoyi.system.mapper.work;


import com.ruoyi.system.domain.work.CoalStockDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoalStockDetailMapper {

    /**
     * 查询库存明细列表
     */
    List<CoalStockDetail> selectCoalStockDetailList(CoalStockDetail coalStockDetail);

    /**
     * 根据ID查询详情
     */
    CoalStockDetail selectCoalStockDetailById(String id);

    /**
     * 新增库存明细
     */
    int insertCoalStockDetail(CoalStockDetail coalStockDetail);

    /**
     * 批量插入库存煤明细
     *
     * @param coalStockDetails 库存煤明细列表
     * @return 插入记录数
     */
    int insertCoalStockDetails(List<CoalStockDetail> coalStockDetails);


    /**
     * 修改库存明细
     */
    int updateCoalStockDetail(CoalStockDetail coalStockDetail);

    /**
     * 批量删除
     */
    int deleteCoalStockDetailByIds(String[] ids);
}
