package com.ruoyi.system.service.export;

import com.ruoyi.system.domain.export.WDataRecord;

import java.util.List;

/**
 * 驻矿公司煤炭发运承认车情况_详情Service接口
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
public interface IWDataRecordService {
    /**
     * 查询驻矿公司煤炭发运承认车情况_详情
     * 
     * @param id 驻矿公司煤炭发运承认车情况_详情主键
     * @return 驻矿公司煤炭发运承认车情况_详情
     */
    public WDataRecord getWDataRecordById(String id);

    /**
     * 查询驻矿公司煤炭发运承认车情况_详情列表
     * 
     * @param wDataRecord 驻矿公司煤炭发运承认车情况_详情
     * @return 驻矿公司煤炭发运承认车情况_详情集合
     */
    public List<WDataRecord> listWDataRecord(WDataRecord wDataRecord);

    /**
     * 新增驻矿公司煤炭发运承认车情况_详情
     * 
     * @param wDataRecord 驻矿公司煤炭发运承认车情况_详情
     * @return 结果
     */
    public int saveWDataRecord(WDataRecord wDataRecord);

    /**
     * 修改驻矿公司煤炭发运承认车情况_详情
     * 
     * @param wDataRecord 驻矿公司煤炭发运承认车情况_详情
     * @return 结果
     */
    public int updateWDataRecord(WDataRecord wDataRecord);

    /**
     * 批量删除驻矿公司煤炭发运承认车情况_详情
     * 
     * @param ids 需要删除的驻矿公司煤炭发运承认车情况_详情主键集合
     * @return 结果
     */
    public int deleteWDataRecordByIds(String[] ids);

    /**
     * 删除驻矿公司煤炭发运承认车情况_详情信息
     * 
     * @param id 驻矿公司煤炭发运承认车情况_详情主键
     * @return 结果
     */
    public int deleteWDataRecordById(String id);
}
