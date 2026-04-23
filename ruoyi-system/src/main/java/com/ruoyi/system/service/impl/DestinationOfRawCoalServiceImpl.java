package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.DestinationOfRawCoal;
import com.ruoyi.system.mapper.DestinationOfRawCoalMapper;
import com.ruoyi.system.service.IDestinationOfRawCoalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 原煤去向/各矿日实际产量录入Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class DestinationOfRawCoalServiceImpl implements IDestinationOfRawCoalService {
    @Autowired
    private DestinationOfRawCoalMapper destinationOfRawCoalMapper;

    /**
     * 查询原煤去向/各矿日实际产量录入
     * 
     * @param id 原煤去向/各矿日实际产量录入主键
     * @return 原煤去向/各矿日实际产量录入
     */
    @Override
    public DestinationOfRawCoal getDestinationOfRawCoalById(Long id) {
        return destinationOfRawCoalMapper.selectDestinationOfRawCoalById(id);
    }

    /**
     * 查询原煤去向/各矿日实际产量录入列表
     * 
     * @param destinationOfRawCoal 原煤去向/各矿日实际产量录入
     * @return 原煤去向/各矿日实际产量录入
     */
    @Override
    public List<DestinationOfRawCoal> listDestinationOfRawCoal(DestinationOfRawCoal destinationOfRawCoal) {
        return destinationOfRawCoalMapper.selectDestinationOfRawCoalList(destinationOfRawCoal);
    }

    /**
     * 新增原煤去向/各矿日实际产量录入
     * 
     * @param destinationOfRawCoal 原煤去向/各矿日实际产量录入
     * @return 结果
     */
    @Override
    public int saveDestinationOfRawCoal(List<DestinationOfRawCoal> destinationOfRawCoal) {
        DestinationOfRawCoal coal=new DestinationOfRawCoal();
        coal.setRecordDate(destinationOfRawCoal.get(0).getRecordDate());
        List<DestinationOfRawCoal> destinationOfRawCoals = destinationOfRawCoalMapper.selectDestinationOfRawCoalList(coal);
        if(destinationOfRawCoals.size()>0){
            for (DestinationOfRawCoal coal2:destinationOfRawCoal) {
                DestinationOfRawCoal Coal = destinationOfRawCoals.stream().filter(item -> coal2.getUnitName().equals(item.getUnitName())).findFirst().orElse(new DestinationOfRawCoal());
                if(Coal.getId() !=null){

                    if(Coal.getIsDeleted()==2){
                        Coal.setIsDeleted(0);
                        Coal.setSalesVolume(coal2.getSalesVolume());
                        Coal.setRailwayTransportVolume(coal2.getRailwayTransportVolume());
                        Coal.setWashPlantInbound(coal2.getWashPlantInbound());
                        Coal.setSalesYardInbound(coal2.getSalesYardInbound());
                        Coal.setGasCompanySupply(coal2.getGasCompanySupply());
                        Coal.setSelfUse(coal2.getSelfUse());
                        Coal.setOtherUse(coal2.getOtherUse());
                        Coal.setInventory(coal2.getInventory());
                        destinationOfRawCoalMapper.updateDestinationOfRawCoal(Coal);
                    }else {
                        return 0;
                    }



                }else {
                    coal2.setCreateTime(DateUtils.getNowDate());
                    List<DestinationOfRawCoal> list = new ArrayList<>();
                    list.add(coal2);
                    destinationOfRawCoalMapper.insertDestinationOfRawCoal(list);
                }
            }
            return 1;
        }else {
            for (DestinationOfRawCoal dest:destinationOfRawCoal) {
                dest.setCreateTime(DateUtils.getNowDate());
            }
            return destinationOfRawCoalMapper.insertDestinationOfRawCoal(destinationOfRawCoal);
        }
    }

    /**
     * 修改原煤去向/各矿日实际产量录入
     * 
     * @param destinationOfRawCoal 原煤去向/各矿日实际产量录入
     * @return 结果
     */
    @Override
    public int updateDestinationOfRawCoal(DestinationOfRawCoal destinationOfRawCoal) {
        destinationOfRawCoal.setUpdateTime(DateUtils.getNowDate());
        return destinationOfRawCoalMapper.updateDestinationOfRawCoal(destinationOfRawCoal);
    }

    /**
     * 批量删除原煤去向/各矿日实际产量录入
     * 
     * @param ids 需要删除的原煤去向/各矿日实际产量录入主键
     * @return 结果
     */
    @Override
    public int deleteDestinationOfRawCoalByIds(Long[] ids) {
        return destinationOfRawCoalMapper.deleteDestinationOfRawCoalByIds(ids);
    }

    /**
     * 删除原煤去向/各矿日实际产量录入信息
     * 
     * @param id 原煤去向/各矿日实际产量录入主键
     * @return 结果
     */
    @Override
    public int deleteDestinationOfRawCoalById(Long id) {
        return destinationOfRawCoalMapper.deleteDestinationOfRawCoalById(id);
    }
}
