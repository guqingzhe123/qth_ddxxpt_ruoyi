package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BaoBiao.po.CoalWashingProductionPO;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.UserMessage;
import com.ruoyi.system.mapper.BaoBiao.CoalWashingProductionMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserMessageMapper;
import com.ruoyi.system.service.BaoBiao.ICoalWashingProductionService;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionCreateDTO;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionPageQueryDTO;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionUpdateDTO;
import com.ruoyi.system.vo.dto.wash.CoalWashingProductionVO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoalWashingProductionServiceImpl implements ICoalWashingProductionService {

    @Resource
    private CoalWashingProductionMapper mapper;

    @Resource
    private UserMessageMapper messageMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DT_FMT   = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(CoalWashingProductionCreateDTO dto) {
        CoalWashingProductionPO po = toPO(dto);
        try {
            CoalWashingProductionPO exists = mapper.selectByUnique(po.getProductionDate(), po.getUnitCode());
            if (exists != null) {
                po.setId(exists.getId());
                mapper.update(po);
            }else {
                mapper.insert(po);
            }
            SysRole 七煤集团权限 = sysRoleMapper.checkRoleNameUnique("七煤集团权限");
            List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectRoleUserInfos(Arrays.asList(七煤集团权限.getRoleId()));
            for (SysUserRole userRole:sysUserRoles) {
                String message=dto.getUnit_name()+"提交了洗煤生产录入表";
                messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(),userRole.getUserId(),message,new Date()));
            }
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("已上报过内容", e);
        }
        return po.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(CoalWashingProductionUpdateDTO dto) {
        CoalWashingProductionPO po = toPO(dto);
        po.setId(dto.getId());
        // 如果修改了 production_date 或 unit_code，需要自行校验是否与其他记录冲突
        CoalWashingProductionPO other = mapper.selectByUnique(po.getProductionDate(), po.getUnitCode());
        if (other != null && !other.getId().equals(po.getId())) {
            throw new IllegalArgumentException("修改后与其他记录冲突（production_date + unit_code）");
        }
        return mapper.update(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Long id) {
        return mapper.deleteById(id);
    }

    @Override
    public CoalWashingProductionVO get(Long id) {
        CoalWashingProductionPO po = mapper.selectById(id);
        return toVO(po);
    }

    @Override
    public List<CoalWashingProductionVO> page(CoalWashingProductionPageQueryDTO q) {
        Map<String, Object> p = new HashMap<>();
        p.put("productionDate", q.getRecord_time());
        p.put("unitCode", q.getUnit_code());
        p.put("unitName", q.getUnit_name());
        p.put("mineCategory", q.getMine_category());
        p.put("userId", q.getUser_id());
        p.put("isDeleted", q.getIs_deleted());

        List<CoalWashingProductionPO> list = mapper.selectPageList(p);
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    // ===== helpers =====

    private CoalWashingProductionPO toPO(CoalWashingProductionCreateDTO d) {
        CoalWashingProductionPO p = new CoalWashingProductionPO();
        try {
            if (d.getProduction_date() != null) {
                p.setProductionDate(DATE_FMT.parse(d.getProduction_date()));
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("production_date 格式必须为 yyyy-MM-dd");
        }
        p.setUnitCode(d.getUnit_code());
        p.setUnitName(d.getUnit_name());

        p.setDropIn(d.getDrop_in());
        p.setWashIn(d.getWash_in());
        p.setCleanCoal(d.getClean_coal());
        p.setLumpCoal(d.getLump_coal());
        p.setSlackCoal(d.getSlack_coal());
        p.setSludgeCoal(d.getSludge_coal());
        p.setAvailableGangue(d.getAvailable_gangue());
        p.setWaste(d.getWaste());
        p.setTotal(d.getTotal());
        p.setSelfUse(d.getSelf_use());
        p.setCarCount(d.getCar_count());
        p.setSalesVolume(d.getSales_volume());
        p.setCleanCoalYield(d.getClean_coal_yield());
        p.setComprehensiveYield(d.getComprehensive_yield());
        p.setDailyStock(d.getDaily_stock());

        p.setUserId(d.getUser_id());
        p.setIsDeleted(d.getIs_deleted() == null ? 0 : d.getIs_deleted());
        p.setMineCategory(d.getMine_category());
        return p;
    }

    private CoalWashingProductionVO toVO(CoalWashingProductionPO p) {
        if (p == null) return null;
        CoalWashingProductionVO v = new CoalWashingProductionVO();
        v.setId(p.getId());
        v.setProduction_date(p.getProductionDate() == null ? null : DATE_FMT.format(p.getProductionDate()));
        v.setUnit_code(p.getUnitCode());
        v.setUnit_name(p.getUnitName());

        v.setDrop_in(p.getDropIn());
        v.setWash_in(p.getWashIn());
        v.setClean_coal(p.getCleanCoal());
        v.setLump_coal(p.getLumpCoal());
        v.setSlack_coal(p.getSlackCoal());
        v.setSludge_coal(p.getSludgeCoal());
        v.setAvailable_gangue(p.getAvailableGangue());
        v.setWaste(p.getWaste());
        v.setTotal(p.getTotal());
        v.setSelf_use(p.getSelfUse());
        v.setCar_count(p.getCarCount());
        v.setSales_volume(p.getSalesVolume());
        v.setClean_coal_yield(p.getCleanCoalYield());
        v.setComprehensive_yield(p.getComprehensiveYield());
        v.setDaily_stock(p.getDailyStock());

        v.setCreate_time(p.getCreateTime() == null ? null : DT_FMT.format(p.getCreateTime()));
        v.setUpdate_time(p.getUpdateTime() == null ? null : DT_FMT.format(p.getUpdateTime()));
        v.setUser_id(p.getUserId());
        v.setIs_deleted(p.getIsDeleted());
        v.setMine_category(p.getMineCategory());
        return v;
    }
}
