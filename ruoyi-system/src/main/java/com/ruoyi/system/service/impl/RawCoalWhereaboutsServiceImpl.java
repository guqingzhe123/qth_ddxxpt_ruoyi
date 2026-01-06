package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.RawCoalWhereabouts;
import com.ruoyi.system.mapper.RawCoalWhereaboutsMapper;
import com.ruoyi.system.service.IRawCoalWhereaboutsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 原煤去向对照（记录各煤矿原煤每日及累计去向数据）Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-07
 */
@Slf4j
@Service
public class RawCoalWhereaboutsServiceImpl implements IRawCoalWhereaboutsService {
    @Autowired
    private RawCoalWhereaboutsMapper rawCoalWhereaboutsMapper;

    /**
     * 查询原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     *
     * @param id 原煤去向对照（记录各煤矿原煤每日及累计去向数据）主键
     * @return 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     */
    @Override
    public RawCoalWhereabouts getRawCoalWhereaboutsById(String id) {
        return rawCoalWhereaboutsMapper.selectRawCoalWhereaboutsById(id);
    }

    /**
     * 查询原煤去向对照（记录各煤矿原煤每日及累计去向数据）列表
     *
     * @param rawCoalWhereabouts 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     * @return 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     */
    @Override
    public List<RawCoalWhereabouts> listRawCoalWhereabouts(RawCoalWhereabouts rawCoalWhereabouts) {
        return rawCoalWhereaboutsMapper.selectRawCoalWhereaboutsList(rawCoalWhereabouts);
    }
    /**
     * 查询原煤去向对照（记录各煤矿原煤每日及累计去向数据）列表
     *
     * @param statsDate 查询时间
     * @return 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     */
    @Override
    public List<RawCoalWhereabouts> Alllist(Date statsDate) {
        return rawCoalWhereaboutsMapper.Alllist(statsDate);
    }

    /**
     * 新增原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     *
     * @param rawCoalWhereabouts 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     * @return 结果
     */
    @Override
    public int saveRawCoalWhereabouts(List<RawCoalWhereabouts> rawCoalWhereabouts) {
        for (RawCoalWhereabouts raw:rawCoalWhereabouts) {
            raw.setCreateTime(DateUtils.getNowDate());
            raw.setUpdateTime(DateUtils.getNowDate());
        }
        return rawCoalWhereaboutsMapper.insertRawCoalWhereabouts(rawCoalWhereabouts);
    }

    /**
     * 修改原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     *
     * @param rawCoalWhereabouts 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     * @return 结果
     */
    @Override
    public int updateRawCoalWhereabouts(RawCoalWhereabouts rawCoalWhereabouts) {
        rawCoalWhereabouts.setUpdateTime(DateUtils.getNowDate());
        return rawCoalWhereaboutsMapper.updateRawCoalWhereabouts(rawCoalWhereabouts);
    }

    /**
     * 批量删除原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     *
     * @param ids 需要删除的原煤去向对照（记录各煤矿原煤每日及累计去向数据）主键
     * @return 结果
     */
    @Override
    public int deleteRawCoalWhereaboutsByIds(String[] ids) {
        return rawCoalWhereaboutsMapper.deleteRawCoalWhereaboutsByIds(ids);
    }

    /**
     * 删除原煤去向对照（记录各煤矿原煤每日及累计去向数据）信息
     *
     * @param id 原煤去向对照（记录各煤矿原煤每日及累计去向数据）主键
     * @return 结果
     */
    @Override
    public int deleteRawCoalWhereaboutsById(String id) {
        return rawCoalWhereaboutsMapper.deleteRawCoalWhereaboutsById(id);
    }
}
