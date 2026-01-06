package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.InitialInventoryOfEachMine;

import java.util.List;

/**
 * 各矿期初库存录入Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface InitialInventoryOfEachMineMapper {
    /**
     * 查询各矿期初库存录入
     * 
     * @param id 各矿期初库存录入主键
     * @return 各矿期初库存录入
     */
    public InitialInventoryOfEachMine selectInitialInventoryOfEachMineById(Long id);

    /**
     * 查询各矿期初库存录入列表
     * 
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 各矿期初库存录入集合
     */
    public List<InitialInventoryOfEachMine> selectInitialInventoryOfEachMineList(InitialInventoryOfEachMine initialInventoryOfEachMine);
    /**
     * 查询各矿期初库存录入列表
     *
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 各矿期初库存录入集合
     */
    public InitialInventoryOfEachMine selectInitialInventoryOfEachMine(InitialInventoryOfEachMine initialInventoryOfEachMine);

    /**
     * 新增各矿期初库存录入
     * 
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 结果
     */
    public int insertInitialInventoryOfEachMine(InitialInventoryOfEachMine initialInventoryOfEachMine);

    /**
     * 修改各矿期初库存录入
     * 
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 结果
     */
    public int updateInitialInventoryOfEachMine(InitialInventoryOfEachMine initialInventoryOfEachMine);

    /**
     * 删除各矿期初库存录入
     * 
     * @param id 各矿期初库存录入主键
     * @return 结果
     */
    public int deleteInitialInventoryOfEachMineById(Long id);

    /**
     * 批量删除各矿期初库存录入
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInitialInventoryOfEachMineByIds(Long[] ids);

}
