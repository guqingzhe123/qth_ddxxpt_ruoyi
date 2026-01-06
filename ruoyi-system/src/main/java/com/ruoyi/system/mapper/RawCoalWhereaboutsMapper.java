package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.RawCoalWhereabouts;

import java.util.Date;
import java.util.List;

/**
 * 原煤去向对照（记录各煤矿原煤每日及累计去向数据）Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-07
 */
public interface RawCoalWhereaboutsMapper {
    /**
     * 查询原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     *
     * @param id 原煤去向对照（记录各煤矿原煤每日及累计去向数据）主键
     * @return 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     */
    public RawCoalWhereabouts selectRawCoalWhereaboutsById(String id);

    /**
     * 查询原煤去向对照（记录各煤矿原煤每日及累计去向数据）列表
     *
     * @param rawCoalWhereabouts 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     * @return 原煤去向对照（记录各煤矿原煤每日及累计去向数据）集合
     */
    public List<RawCoalWhereabouts> selectRawCoalWhereaboutsList(RawCoalWhereabouts rawCoalWhereabouts);
    /**
     * 查询原煤去向对照（记录各煤矿原煤每日及累计去向数据）列表
     *
     * @param statsDate 日期
     * @return 原煤去向对照（记录各煤矿原煤每日及累计去向数据）集合
     */
    public List<RawCoalWhereabouts> Alllist(Date statsDate);

    /**
     * 新增原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     *
     * @param rawCoalWhereabouts 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     * @return 结果
     */
    public int insertRawCoalWhereabouts(List<RawCoalWhereabouts> rawCoalWhereabouts);

    /**
     * 修改原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     *
     * @param rawCoalWhereabouts 原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     * @return 结果
     */
    public int updateRawCoalWhereabouts(RawCoalWhereabouts rawCoalWhereabouts);

    /**
     * 删除原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     *
     * @param id 原煤去向对照（记录各煤矿原煤每日及累计去向数据）主键
     * @return 结果
     */
    public int deleteRawCoalWhereaboutsById(String id);

    /**
     * 批量删除原煤去向对照（记录各煤矿原煤每日及累计去向数据）
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRawCoalWhereaboutsByIds(String[] ids);
}
