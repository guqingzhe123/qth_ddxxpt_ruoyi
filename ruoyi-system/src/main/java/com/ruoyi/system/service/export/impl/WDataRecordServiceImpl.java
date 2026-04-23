package com.ruoyi.system.service.export.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.export.WDataRecord;
import com.ruoyi.system.mapper.export.WDataRecordMapper;
import com.ruoyi.system.service.export.IWDataRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 驻矿公司煤炭发运承认车情况_详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WDataRecordServiceImpl implements IWDataRecordService {
    @Autowired
    private WDataRecordMapper wDataRecordMapper;

    /**
     * 查询驻矿公司煤炭发运承认车情况_详情
     * 
     * @param id 驻矿公司煤炭发运承认车情况_详情主键
     * @return 驻矿公司煤炭发运承认车情况_详情
     */
    @Override
    public WDataRecord getWDataRecordById(String id) {
        return wDataRecordMapper.selectWDataRecordById(id);
    }

    /**
     * 查询驻矿公司煤炭发运承认车情况_详情列表
     * 
     * @param wDataRecord 驻矿公司煤炭发运承认车情况_详情
     * @return 驻矿公司煤炭发运承认车情况_详情
     */
    @Override
    public List<WDataRecord> listWDataRecord(WDataRecord wDataRecord) {
        return wDataRecordMapper.selectWDataRecordList(wDataRecord);
    }

    /**
     * 新增驻矿公司煤炭发运承认车情况_详情
     * 
     * @param wDataRecord 驻矿公司煤炭发运承认车情况_详情
     * @return 结果
     */
    @Override
    public int saveWDataRecord(WDataRecord wDataRecord) {
        wDataRecord.setCreateTime(DateUtils.getNowDate());
        return wDataRecordMapper.insertWDataRecord(wDataRecord);
    }

    /**
     * 修改驻矿公司煤炭发运承认车情况_详情
     * 
     * @param wDataRecord 驻矿公司煤炭发运承认车情况_详情
     * @return 结果
     */
    @Override
    public int updateWDataRecord(WDataRecord wDataRecord) {
        wDataRecord.setUpdateTime(DateUtils.getNowDate());
        return wDataRecordMapper.updateWDataRecord(wDataRecord);
    }

    /**
     * 批量删除驻矿公司煤炭发运承认车情况_详情
     * 
     * @param ids 需要删除的驻矿公司煤炭发运承认车情况_详情主键
     * @return 结果
     */
    @Override
    public int deleteWDataRecordByIds(String[] ids) {
        return wDataRecordMapper.deleteWDataRecordByIds(ids);
    }

    /**
     * 删除驻矿公司煤炭发运承认车情况_详情信息
     * 
     * @param id 驻矿公司煤炭发运承认车情况_详情主键
     * @return 结果
     */
    @Override
    public int deleteWDataRecordById(String id) {
        return wDataRecordMapper.deleteWDataRecordById(id);
    }
}
