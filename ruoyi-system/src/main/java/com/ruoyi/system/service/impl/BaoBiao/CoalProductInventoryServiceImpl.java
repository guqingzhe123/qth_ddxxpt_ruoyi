package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BaoBiao.dto.cpi.SubCoalProductInventory;
import com.ruoyi.system.mapper.BaoBiao.SubCoalProductInventoryMapper;
import com.ruoyi.system.service.BaoBiao.ICoalProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CoalProductInventoryServiceImpl implements ICoalProductInventoryService {

    @Autowired
    private SubCoalProductInventoryMapper subCoalProductInventoryMapper;

    /**
     * 查询洗煤产品库存及自用煤
     *
     * @param id 洗煤产品库存及自用煤主键
     * @return 洗煤产品库存及自用煤
     */
    @Override
    public SubCoalProductInventory getSubCoalProductInventoryById(Long id) {
        return subCoalProductInventoryMapper.selectSubCoalProductInventoryById(id);
    }

    /**
     * 查询洗煤产品库存及自用煤列表
     *
     * @param subCoalProductInventory 洗煤产品库存及自用煤
     * @return 洗煤产品库存及自用煤
     */
    @Override
    public List<SubCoalProductInventory> listSubCoalProductInventory(SubCoalProductInventory subCoalProductInventory) {
        return subCoalProductInventoryMapper.selectSubCoalProductInventoryList(subCoalProductInventory);
    }

    /**
     * 新增洗煤产品库存及自用煤
     *
     * @param subCoalProductInventory 洗煤产品库存及自用煤
     * @return 结果
     */
    @Override
    public int saveSubCoalProductInventory(List<SubCoalProductInventory> subCoalProductInventory) {
//        dto.setCreateTime(DateUtils.getNowDate());
//        dto.setUserId(SecurityUtils.getUserId());
        List<SubCoalProductInventory>  subCoal=new ArrayList<>();
        for (SubCoalProductInventory sub:subCoalProductInventory) {
            sub.setCreateTime(DateUtils.getNowDate());
            sub.setUserId(SecurityUtils.getUserId());
            SubCoalProductInventory subc=new SubCoalProductInventory();
            subc.setUnitCode(sub.getUnitCode());
            subc.setRecordDate(sub.getRecordDate());

            List<SubCoalProductInventory> subCoalProductInventories = subCoalProductInventoryMapper.selectSubCoalProductInventoryList(subc);
            if(subCoalProductInventories.size()>0){
                sub.setId(subCoalProductInventories.get(0).getId());
                subCoalProductInventoryMapper.updateSubCoalProductInventory(sub);
            }else {
                subCoal.add(sub);
            }

        }

        if(subCoal.size()>0){
            return subCoalProductInventoryMapper.insertSubCoalProductInventory(subCoalProductInventory);
        }else {
            return 1;
        }

    }

    /**
     * 修改洗煤产品库存及自用煤
     *
     * @param subCoalProductInventory 洗煤产品库存及自用煤
     * @return 结果
     */
    @Override
    public int updateSubCoalProductInventory(SubCoalProductInventory subCoalProductInventory) {
        subCoalProductInventory.setUpdateTime(DateUtils.getNowDate());
        return subCoalProductInventoryMapper.updateSubCoalProductInventory(subCoalProductInventory);
    }

    /**
     * 批量删除洗煤产品库存及自用煤
     *
     * @param ids 需要删除的洗煤产品库存及自用煤主键
     * @return 结果
     */
    @Override
    public int deleteSubCoalProductInventoryByIds(Long[] ids) {
        return subCoalProductInventoryMapper.deleteSubCoalProductInventoryByIds(ids);
    }

    /**
     * 删除洗煤产品库存及自用煤信息
     *
     * @param id 洗煤产品库存及自用煤主键
     * @return 结果
     */
    @Override
    public int deleteSubCoalProductInventoryById(Long id) {
        return subCoalProductInventoryMapper.deleteSubCoalProductInventoryById(id);
    }

    /**
     * 查询洗煤产品库存及自用煤列表
     *
     * @param date 日期
     * @return 洗煤产品库存及自用煤
     */
    @Override
    public List<SubCoalProductInventory> selectProductMonth(Date date) {
        return subCoalProductInventoryMapper.selectProductMonth(date);
    }


}
