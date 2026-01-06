package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SubInitialInventoryOfEachMine;
import com.ruoyi.system.mapper.SubInitialInventoryOfEachMineMapper;
import com.ruoyi.system.service.ISubInitialInventoryOfEachMineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各矿期初库存录入子Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class SubInitialInventoryOfEachMineServiceImpl implements ISubInitialInventoryOfEachMineService {
    @Autowired
    private SubInitialInventoryOfEachMineMapper subInitialInventoryOfEachMineMapper;

    /**
     * 查询各矿期初库存录入子
     * 
     * @param id 各矿期初库存录入子主键
     * @return 各矿期初库存录入子
     */
    @Override
    public SubInitialInventoryOfEachMine getSubInitialInventoryOfEachMineById(Long id) {
        return subInitialInventoryOfEachMineMapper.selectSubInitialInventoryOfEachMineById(id);
    }

    /**
     * 查询各矿期初库存录入子列表
     * 
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 各矿期初库存录入子
     */
    @Override
    public List<SubInitialInventoryOfEachMine> listSubInitialInventoryOfEachMine(SubInitialInventoryOfEachMine subInitialInventoryOfEachMine) {
        return subInitialInventoryOfEachMineMapper.selectSubInitialInventoryOfEachMineList(subInitialInventoryOfEachMine);
    }

    /**
     * 新增各矿期初库存录入子
     * 
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 结果
     */
    @Override
    public int saveSubInitialInventoryOfEachMine(SubInitialInventoryOfEachMine subInitialInventoryOfEachMine) {
        subInitialInventoryOfEachMine.setCreateTime(DateUtils.getNowDate());
        return subInitialInventoryOfEachMineMapper.insertSubInitialInventoryOfEachMine(subInitialInventoryOfEachMine);
    }
    /**
     * 新增各矿期初库存录入子
     *
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 结果
     */
    @Override
    public int saveSubInitialInventoryOfEachMineBatch(List<SubInitialInventoryOfEachMine> subInitialInventoryOfEachMine) {
        return subInitialInventoryOfEachMineMapper.insertSubInitialInventoryOfEachMineBatch(subInitialInventoryOfEachMine);
    }

    /**
     * 修改各矿期初库存录入子
     * 
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 结果
     */
    @Override
    public int updateSubInitialInventoryOfEachMine(SubInitialInventoryOfEachMine subInitialInventoryOfEachMine) {
        subInitialInventoryOfEachMine.setUpdateTime(DateUtils.getNowDate());
        return subInitialInventoryOfEachMineMapper.updateSubInitialInventoryOfEachMine(subInitialInventoryOfEachMine);
    }

    /**
     * 批量删除各矿期初库存录入子
     * 
     * @param ids 需要删除的各矿期初库存录入子主键
     * @return 结果
     */
    @Override
    public int deleteSubInitialInventoryOfEachMineByIds(Long[] ids) {
        return subInitialInventoryOfEachMineMapper.deleteSubInitialInventoryOfEachMineByIds(ids);
    }

    /**
     * 删除各矿期初库存录入子信息
     * 
     * @param id 各矿期初库存录入子主键
     * @return 结果
     */
    @Override
    public int deleteSubInitialInventoryOfEachMineById(Long id) {
        return subInitialInventoryOfEachMineMapper.deleteSubInitialInventoryOfEachMineById(id);
    }
}
