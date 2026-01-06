package com.ruoyi.system.service.work;


import com.ruoyi.system.domain.work.CoalStockDetail;

import java.util.List;

/**
 * 各选煤厂库存煤明细表服务接口
 *
 * @author ruoyi
 */
public interface ICoalStockDetailService {

    /**
     * 查询库存明细列表
     */
    List<CoalStockDetail> listCoalStockDetail(CoalStockDetail coalStockDetail);


    /**
     * 根据ID获取详情
     */
    CoalStockDetail getCoalStockDetailById(String id);

    /**
     * 新增库存明细
     */
    int saveCoalStockDetail(CoalStockDetail coalStockDetail);

    /**
     * 批量保存库存煤明细
     *
     * @param coalStockDetails 库存煤明细列表
     * @return 是否成功
     */
    boolean saveCoalStockDetails(List<CoalStockDetail> coalStockDetails);


    /**
     * 修改库存明细
     */
    int updateCoalStockDetail(CoalStockDetail coalStockDetail);

    /**
     * 删除库存明细
     */
    int deleteCoalStockDetailByIds(String[] ids);
}
