package com.ruoyi.system.service;

import com.ruoyi.system.domain.InitialInventoryOfEachMine;
import com.ruoyi.system.domain.InitialInventoryOfEachMineOutput;

import java.util.List;

/**
 * 各矿期初库存录入Service接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface IInitialInventoryOfEachMineService {
    /**
     * 查询各矿期初库存录入
     * 
     * @param id 各矿期初库存录入主键
     * @return 各矿期初库存录入
     */
    public InitialInventoryOfEachMine getInitialInventoryOfEachMineById(Long id);

    /**
     * 查询各矿期初库存录入列表
     * 
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 各矿期初库存录入集合
     */
    public List<InitialInventoryOfEachMine> listInitialInventoryOfEachMine(InitialInventoryOfEachMine initialInventoryOfEachMine);

    List<InitialInventoryOfEachMineOutput> listInitialInventoryOfEachMineList(InitialInventoryOfEachMine initialInventoryOfEachMine);

    /**
     * 新增各矿期初库存录入
     * 
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 结果
     */
    public int saveInitialInventoryOfEachMine(InitialInventoryOfEachMine initialInventoryOfEachMine);

    /**
     * 修改各矿期初库存录入
     * 
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 结果
     */
    public int updateInitialInventoryOfEachMine(InitialInventoryOfEachMine initialInventoryOfEachMine);

    /**
     * 批量删除各矿期初库存录入
     * 
     * @param ids 需要删除的各矿期初库存录入主键集合
     * @return 结果
     */
    public int deleteInitialInventoryOfEachMineByIds(Long[] ids);

    /**
     * 删除各矿期初库存录入信息
     * 
     * @param id 各矿期初库存录入主键
     * @return 结果
     */
    public int deleteInitialInventoryOfEachMineById(Long id);
}
