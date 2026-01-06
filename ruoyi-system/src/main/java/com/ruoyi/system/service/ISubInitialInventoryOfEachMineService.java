package com.ruoyi.system.service;

import com.ruoyi.system.domain.SubInitialInventoryOfEachMine;

import java.util.List;

/**
 * 各矿期初库存录入子Service接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface ISubInitialInventoryOfEachMineService {
    /**
     * 查询各矿期初库存录入子
     * 
     * @param id 各矿期初库存录入子主键
     * @return 各矿期初库存录入子
     */
    public SubInitialInventoryOfEachMine getSubInitialInventoryOfEachMineById(Long id);

    /**
     * 查询各矿期初库存录入子列表
     * 
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 各矿期初库存录入子集合
     */
    public List<SubInitialInventoryOfEachMine> listSubInitialInventoryOfEachMine(SubInitialInventoryOfEachMine subInitialInventoryOfEachMine);

    /**
     * 新增各矿期初库存录入子
     * 
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 结果
     */
    public int saveSubInitialInventoryOfEachMine(SubInitialInventoryOfEachMine subInitialInventoryOfEachMine);

    /**
     * 新增各矿期初库存录入子
     *
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 结果
     */
    public int saveSubInitialInventoryOfEachMineBatch(List<SubInitialInventoryOfEachMine> subInitialInventoryOfEachMine);

    /**
     * 修改各矿期初库存录入子
     * 
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 结果
     */
    public int updateSubInitialInventoryOfEachMine(SubInitialInventoryOfEachMine subInitialInventoryOfEachMine);

    /**
     * 批量删除各矿期初库存录入子
     * 
     * @param ids 需要删除的各矿期初库存录入子主键集合
     * @return 结果
     */
    public int deleteSubInitialInventoryOfEachMineByIds(Long[] ids);

    /**
     * 删除各矿期初库存录入子信息
     * 
     * @param id 各矿期初库存录入子主键
     * @return 结果
     */
    public int deleteSubInitialInventoryOfEachMineById(Long id);
}
