package com.ruoyi.system.service.work.impl;

import com.ruoyi.system.mapper.work.CoalStockDetailMapper;
import com.ruoyi.system.domain.work.CoalStockDetail;
import com.ruoyi.system.service.work.ICoalStockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoalStockDetailServiceImpl implements ICoalStockDetailService {

    @Autowired
    private CoalStockDetailMapper coalStockDetailMapper;

    @Override
    public List<CoalStockDetail> listCoalStockDetail(CoalStockDetail coalStockDetail) {
        return coalStockDetailMapper.selectCoalStockDetailList(coalStockDetail);
    }

    @Override
    public CoalStockDetail getCoalStockDetailById(String id) {
        return coalStockDetailMapper.selectCoalStockDetailById(id);
    }

    @Override
    public int saveCoalStockDetail(CoalStockDetail coalStockDetail) {
        return coalStockDetailMapper.insertCoalStockDetail(coalStockDetail);
    }

    /**
     * 批量保存库存煤明细
     *
     * @param coalStockDetails 库存煤明细列表
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveCoalStockDetails(List<CoalStockDetail> coalStockDetails) {
        return coalStockDetailMapper.insertCoalStockDetails(coalStockDetails) > 0;
    }


    @Override
    public int updateCoalStockDetail(CoalStockDetail coalStockDetail) {
        return coalStockDetailMapper.updateCoalStockDetail(coalStockDetail);
    }

    @Override
    public int deleteCoalStockDetailByIds(String[] ids) {
        return coalStockDetailMapper.deleteCoalStockDetailByIds(ids);
    }
}
