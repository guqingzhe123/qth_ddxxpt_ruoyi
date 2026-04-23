package com.ruoyi.system.mapper.export;

import com.ruoyi.system.domain.export.WCargoReceipt;

import java.util.List;

/**
 * 七矿焦炭铁路外运承认车情况Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface WCargoReceiptMapper {
    /**
     * 查询七矿焦炭铁路外运承认车情况
     * 
     * @param id 七矿焦炭铁路外运承认车情况主键
     * @return 七矿焦炭铁路外运承认车情况
     */
    public WCargoReceipt selectWCargoReceiptById(String id);

    /**
     * 查询七矿焦炭铁路外运承认车情况列表
     * 
     * @param wCargoReceipt 七矿焦炭铁路外运承认车情况
     * @return 七矿焦炭铁路外运承认车情况集合
     */
    public List<WCargoReceipt> selectWCargoReceiptList(WCargoReceipt wCargoReceipt);

    /**
     * 新增七矿焦炭铁路外运承认车情况
     * 
     * @param wCargoReceipt 七矿焦炭铁路外运承认车情况
     * @return 结果
     */
    public int insertWCargoReceipt(WCargoReceipt wCargoReceipt);

    /**
     * 修改七矿焦炭铁路外运承认车情况
     * 
     * @param wCargoReceipt 七矿焦炭铁路外运承认车情况
     * @return 结果
     */
    public int updateWCargoReceipt(WCargoReceipt wCargoReceipt);

    /**
     * 删除七矿焦炭铁路外运承认车情况
     * 
     * @param id 七矿焦炭铁路外运承认车情况主键
     * @return 结果
     */
    public int deleteWCargoReceiptById(String id);

    /**
     * 批量删除七矿焦炭铁路外运承认车情况
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWCargoReceiptByIds(String[] ids);
}
