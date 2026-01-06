package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SubInitialInventoryOfEachMine;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 各矿期初库存录入子Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
public interface SubInitialInventoryOfEachMineMapper {
    /**
     * 查询各矿期初库存录入子
     * 
     * @param id 各矿期初库存录入子主键
     * @return 各矿期初库存录入子
     */
    public SubInitialInventoryOfEachMine selectSubInitialInventoryOfEachMineById(Long id);

    /**
     * 查询各矿期初库存录入子列表
     * 
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 各矿期初库存录入子集合
     */
    public List<SubInitialInventoryOfEachMine> selectSubInitialInventoryOfEachMineList(SubInitialInventoryOfEachMine subInitialInventoryOfEachMine);

    /**
     * 新增各矿期初库存录入子
     *
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 结果
     */
    public int insertSubInitialInventoryOfEachMine(SubInitialInventoryOfEachMine subInitialInventoryOfEachMine);
    /**
     * 新增各矿期初库存录入子
     *
     * @param list 各矿期初库存录入子
     * @return 结果
     */
    public int insertSubInitialInventoryOfEachMineBatch(List<SubInitialInventoryOfEachMine> list);
    /**
     * 修改各矿期初库存录入子
     * 
     * @param subInitialInventoryOfEachMine 各矿期初库存录入子
     * @return 结果
     */
    public int updateSubInitialInventoryOfEachMine(SubInitialInventoryOfEachMine subInitialInventoryOfEachMine);

    /**
     * 删除各矿期初库存录入子
     * 
     * @param id 各矿期初库存录入子主键
     * @return 结果
     */
    public int deleteSubInitialInventoryOfEachMineById(Long id);

    /**
     * 批量删除各矿期初库存录入子
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubInitialInventoryOfEachMineByIds(Long[] ids);

    public List<SubInitialInventoryOfEachMine> selectSubInitialInventoryOfEachList(@Param("unitType")String unitType, @Param("statsDate") Date statsDate);
    public List<SubInitialInventoryOfEachMine> selectSubInitialInventoryOfEachMonthList(@Param("unitType")String unitType, @Param("statsDate") Date statsDate);

}
