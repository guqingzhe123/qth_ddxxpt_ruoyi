package com.ruoyi.system.service.export.impl;

import com.ruoyi.system.domain.export.WCargoReceipt;
import com.ruoyi.system.mapper.export.WCargoReceiptMapper;
import com.ruoyi.system.service.export.IWCargoReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 七矿焦炭铁路外运承认车情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-19
 */
@Slf4j
@Service
public class WCargoReceiptServiceImpl implements IWCargoReceiptService {
    @Autowired
    private WCargoReceiptMapper wCargoReceiptMapper;

    /**
     * 查询七矿焦炭铁路外运承认车情况
     * 
     * @param id 七矿焦炭铁路外运承认车情况主键
     * @return 七矿焦炭铁路外运承认车情况
     */
    @Override
    public WCargoReceipt getWCargoReceiptById(String id) {
        return wCargoReceiptMapper.selectWCargoReceiptById(id);
    }

    /**
     * 查询七矿焦炭铁路外运承认车情况列表
     * 
     * @param wCargoReceipt 七矿焦炭铁路外运承认车情况
     * @return 七矿焦炭铁路外运承认车情况
     */
    @Override
    public List<WCargoReceipt> listWCargoReceipt(WCargoReceipt wCargoReceipt) {
        return wCargoReceiptMapper.selectWCargoReceiptList(wCargoReceipt);
    }

    /**
     * 新增七矿焦炭铁路外运承认车情况
     * 
     * @param wCargoReceipt 七矿焦炭铁路外运承认车情况
     * @return 结果
     */
    @Override
    public int saveWCargoReceipt(WCargoReceipt wCargoReceipt) {
        return wCargoReceiptMapper.insertWCargoReceipt(wCargoReceipt);
    }

    /**
     * 修改七矿焦炭铁路外运承认车情况
     * 
     * @param wCargoReceipt 七矿焦炭铁路外运承认车情况
     * @return 结果
     */
    @Override
    public int updateWCargoReceipt(WCargoReceipt wCargoReceipt) {
        return wCargoReceiptMapper.updateWCargoReceipt(wCargoReceipt);
    }

    /**
     * 批量删除七矿焦炭铁路外运承认车情况
     * 
     * @param ids 需要删除的七矿焦炭铁路外运承认车情况主键
     * @return 结果
     */
    @Override
    public int deleteWCargoReceiptByIds(String[] ids) {
        return wCargoReceiptMapper.deleteWCargoReceiptByIds(ids);
    }

    /**
     * 删除七矿焦炭铁路外运承认车情况信息
     * 
     * @param id 七矿焦炭铁路外运承认车情况主键
     * @return 结果
     */
    @Override
    public int deleteWCargoReceiptById(String id) {
        return wCargoReceiptMapper.deleteWCargoReceiptById(id);
    }
}
