package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.dto.cpi.SubCoalProductInventory;

import java.util.Date;
import java.util.List;

public interface ICoalProductInventoryService {
    /**
     * 查询洗煤产品库存及自用煤
     *
     * @param id 洗煤产品库存及自用煤主键
     * @return 洗煤产品库存及自用煤
     */
    public SubCoalProductInventory getSubCoalProductInventoryById(Long id);

    /**
     * 查询洗煤产品库存及自用煤列表
     *
     * @param subCoalProductInventory 洗煤产品库存及自用煤
     * @return 洗煤产品库存及自用煤集合
     */
    public List<SubCoalProductInventory> listSubCoalProductInventory(SubCoalProductInventory subCoalProductInventory);

    /**
     * 新增洗煤产品库存及自用煤
     *
     * @param subCoalProductInventory 洗煤产品库存及自用煤
     * @return 结果
     */
    public int saveSubCoalProductInventory(List<SubCoalProductInventory> subCoalProductInventory);

    /**
     * 修改洗煤产品库存及自用煤
     *
     * @param subCoalProductInventory 洗煤产品库存及自用煤
     * @return 结果
     */
    public int updateSubCoalProductInventory(SubCoalProductInventory subCoalProductInventory);

    /**
     * 批量删除洗煤产品库存及自用煤
     *
     * @param ids 需要删除的洗煤产品库存及自用煤主键集合
     * @return 结果
     */
    public int deleteSubCoalProductInventoryByIds(Long[] ids);

    /**
     * 删除洗煤产品库存及自用煤信息
     *
     * @param id 洗煤产品库存及自用煤主键
     * @return 结果
     */
    public int deleteSubCoalProductInventoryById(Long id);

    /**
     * 查询洗煤产品库存及自用煤列表
     *
     * @param date 日期
     * @return 洗煤产品库存及自用煤集合
     */
    List<SubCoalProductInventory> selectProductMonth(Date date);
}
