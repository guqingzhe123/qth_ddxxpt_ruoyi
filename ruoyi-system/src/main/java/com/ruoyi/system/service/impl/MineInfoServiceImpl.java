package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.MineInfo;
import com.ruoyi.system.mapper.MineInfoMapper;
import com.ruoyi.system.service.IMineInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 退回Service业务层处理
 *
 * @author ruoyi
 * @date 2026-04-08
 */
@Slf4j
@Service
public class MineInfoServiceImpl implements IMineInfoService {
    @Autowired
    private MineInfoMapper mineInfoMapper;

    /**
     * 查询退回
     *
     * @param id 退回主键
     * @return 退回
     */
    @Override
    public MineInfo getMineInfoById(Long id) {
        return mineInfoMapper.selectMineInfoById(id);
    }

    /**
     * 查询退回列表
     *
     * @param mineInfo 退回
     * @return 退回
     */
    @Override
    public List<MineInfo> listMineInfo(MineInfo mineInfo) {
        return mineInfoMapper.selectMineInfoList(mineInfo);
    }

    /**
     * 新增退回
     *
     * @param mineInfo 退回
     * @return 结果
     */
    @Override
    public int saveMineInfo(MineInfo mineInfo) {
        mineInfo.setCreateTime(DateUtils.getNowDate());
        return mineInfoMapper.insertMineInfo(mineInfo);
    }

    /**
     * 修改退回
     *
     * @param mineInfo 退回
     * @return 结果
     */
    @Override
    public int updateMineInfo(MineInfo mineInfo) {
        return mineInfoMapper.updateMineInfo(mineInfo);
    }

    /**
     * 批量删除退回
     *
     * @param ids 需要删除的退回主键
     * @return 结果
     */
    @Override
    public int deleteMineInfoByIds(Long[] ids) {
        return mineInfoMapper.deleteMineInfoByIds(ids);
    }

    /**
     * 删除退回信息
     *
     * @param id 退回主键
     * @return 结果
     */
    @Override
    public int deleteMineInfoById(Long id) {
        return mineInfoMapper.deleteMineInfoById(id);
    }
    /**
     * 删除退回信息
     *
     * @param 对应字段
     * @return 结果
     */
    @Override
    public int deleteMineInfoByDate(MineInfo mineInfo) {
        return mineInfoMapper.deleteMineInfoByDate(mineInfo);
    }



}
