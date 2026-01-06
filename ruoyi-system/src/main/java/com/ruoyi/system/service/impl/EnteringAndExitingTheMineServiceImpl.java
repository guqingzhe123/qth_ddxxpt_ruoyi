package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.EnteringAndExitingTheMine;
import com.ruoyi.system.mapper.EnteringAndExitingTheMineMapper;
import com.ruoyi.system.service.IEnteringAndExitingTheMineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员入井出井日报录入Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class EnteringAndExitingTheMineServiceImpl implements IEnteringAndExitingTheMineService {
    @Autowired
    private EnteringAndExitingTheMineMapper enteringAndExitingTheMineMapper;

    /**
     * 查询人员入井出井日报录入
     *
     * @param id 人员入井出井日报录入主键
     * @return 人员入井出井日报录入
     */
    @Override
    public EnteringAndExitingTheMine getEnteringAndExitingTheMineById(Long id) {
        return enteringAndExitingTheMineMapper.selectEnteringAndExitingTheMineById(id);
    }

    /**
     * 查询人员入井出井日报录入列表
     *
     * @param enteringAndExitingTheMine 人员入井出井日报录入
     * @return 人员入井出井日报录入
     */
    @Override
    public List<EnteringAndExitingTheMine> listEnteringAndExitingTheMine(EnteringAndExitingTheMine enteringAndExitingTheMine) {
        return enteringAndExitingTheMineMapper.selectEnteringAndExitingTheMineList(enteringAndExitingTheMine);
    }

    /**
     * 新增人员入井出井日报录入
     *
     * @param enteringAndExitingTheMine 人员入井出井日报录入
     * @return 结果
     */
    @Override
    public int saveEnteringAndExitingTheMine(EnteringAndExitingTheMine enteringAndExitingTheMine) {
        enteringAndExitingTheMine.setCreateTime(DateUtils.getNowDate());
        return enteringAndExitingTheMineMapper.insertEnteringAndExitingTheMine(enteringAndExitingTheMine);
    }

    /**
     * 修改人员入井出井日报录入
     *
     * @param enteringAndExitingTheMine 人员入井出井日报录入
     * @return 结果
     */
    @Override
    public int updateEnteringAndExitingTheMine(EnteringAndExitingTheMine enteringAndExitingTheMine) {
        enteringAndExitingTheMine.setUpdateTime(DateUtils.getNowDate());
        return enteringAndExitingTheMineMapper.updateEnteringAndExitingTheMine(enteringAndExitingTheMine);
    }

    /**
     * 批量删除人员入井出井日报录入
     *
     * @param ids 需要删除的人员入井出井日报录入主键
     * @return 结果
     */
    @Override
    public int deleteEnteringAndExitingTheMineByIds(Long[] ids) {
        return enteringAndExitingTheMineMapper.deleteEnteringAndExitingTheMineByIds(ids);
    }

    /**
     * 删除人员入井出井日报录入信息
     *
     * @param id 人员入井出井日报录入主键
     * @return 结果
     */
    @Override
    public int deleteEnteringAndExitingTheMineById(Long id) {
        return enteringAndExitingTheMineMapper.deleteEnteringAndExitingTheMineById(id);
    }
}
