package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.dto.cpi.SubCoalProductInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface SubCoalProductInventoryMapper {
    /**
     * 查询洗煤产品库存及自用煤
     *
     * @param id 洗煤产品库存及自用煤主键
     * @return 洗煤产品库存及自用煤
     */
    public SubCoalProductInventory selectSubCoalProductInventoryById(Long id);

    /**
     * 查询洗煤产品库存及自用煤列表
     *
     * @param subCoalProductInventory 洗煤产品库存及自用煤
     * @return 洗煤产品库存及自用煤集合
     */
    public List<SubCoalProductInventory> selectSubCoalProductInventoryList(SubCoalProductInventory subCoalProductInventory);

    /**
     * 新增洗煤产品库存及自用煤
     *
     * @param subCoalProductInventory 洗煤产品库存及自用煤
     * @return 结果
     */
    public int insertSubCoalProductInventory(List<SubCoalProductInventory> subCoalProductInventory);

    /**
     * 修改洗煤产品库存及自用煤
     *
     * @param subCoalProductInventory 洗煤产品库存及自用煤
     * @return 结果
     */
    public int updateSubCoalProductInventory(SubCoalProductInventory subCoalProductInventory);

    /**
     * 删除洗煤产品库存及自用煤
     *
     * @param id 洗煤产品库存及自用煤主键
     * @return 结果
     */
    public int deleteSubCoalProductInventoryById(Long id);

    /**
     * 批量删除洗煤产品库存及自用煤
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubCoalProductInventoryByIds(Long[] ids);

    /**
     * 查询洗煤产品库存及自用煤列表
     *
     * @param date 查询日期
     * @return 洗煤产品库存及自用煤集合
     */
    public List<SubCoalProductInventory> selectProductMonth(Date date);


    /**
     * 查询洗煤产品库存及自用煤列表
     *
     * @param date 查询日期
     * @return 洗煤产品库存及自用煤集合
     */
    public List<SubCoalProductInventory> selectProductInventoryDay(Date date);
    /**
     * 查询洗煤产品库存及自用煤列表
     *
     * @param date 查询日期
     * @return 洗煤产品库存及自用煤集合
     */
    public List<SubCoalProductInventory> selectProductInventoryMonth(Date date);
    /**
     * 查询洗煤产品库存及自用煤列表
     *
     * @param date 查询日期
     * @return 洗煤产品库存及自用煤集合
     */
    public List<SubCoalProductInventory> selectProductInventoryYear(Date date);
}
