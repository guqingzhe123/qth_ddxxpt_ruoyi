package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BaoBiao.dto.dev.*;
import com.ruoyi.system.domain.BaoBiao.po.MineDevelopmentDataPO;
import com.ruoyi.system.domain.BaoBiao.po.SubMineDevelopmentDataPO;
import com.ruoyi.system.domain.BaoBiao.vo.dev.MddVO;
import com.ruoyi.system.mapper.BaoBiao.MineDevelopmentDataMapper;
import com.ruoyi.system.mapper.BaoBiao.SubMineDevelopmentDataMapper;
import com.ruoyi.system.service.BaoBiao.IMineDevelopmentDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MineDevelopmentDataServiceImpl implements IMineDevelopmentDataService {

    @Resource private MineDevelopmentDataMapper mddMapper;
    @Resource private SubMineDevelopmentDataMapper subMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(MddCreateDTO dto) {
        MineDevelopmentDataPO po = toPO(dto);
        Long id;
        List<MineDevelopmentDataPO> mineDevelopmentDataPOS = mddMapper.selectList(po);
        if(mineDevelopmentDataPOS.size()>0){
            if(mineDevelopmentDataPOS.get(0).getIsDeleted()==1){
                id=mineDevelopmentDataPOS.get(0).getId();

                mddMapper.softUpdeById(id,po.getTotalDownCount(),po.getTotalUpCount());
                subMapper.deleteByMddId(mineDevelopmentDataPOS.get(0).getId());
                List<SubMineDevelopmentDataPO> subs = toSubs(id, dto.getData_JSON());
                if (!subs.isEmpty()) subMapper.batchInsert(subs);
                return id;
            }
            return null;

        }else {
            po.setIsDeleted(0);
            po.setUserId(SecurityUtils.getUserId());
            po.setMineCategory("0");
            mddMapper.insert(po);
            id = po.getId();

            List<SubMineDevelopmentDataPO> subs = toSubs(id, dto.getData_JSON());
            if (!subs.isEmpty()) subMapper.batchInsert(subs);
            return id;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(MddUpdateDTO dto) {
        MineDevelopmentDataPO po = toPO(dto);
        po.setId(dto.getId());
        int n = mddMapper.update(po);

        subMapper.deleteByMddId(dto.getId());
        List<SubMineDevelopmentDataPO> subs = toSubs(dto.getId(), dto.getData_JSON());
        if (!subs.isEmpty()) subMapper.batchInsert(subs);

        return n;
    }

    @Override
    public int remove(Long id) {
        return mddMapper.softDeleteById(id);
    }

    @Override
    public MddVO get(Long id) {
        MineDevelopmentDataPO po = mddMapper.selectById(id);
        if (po == null) return null;
        List<SubMineDevelopmentDataPO> subs = subMapper.selectByMddId(id);
        return toVO(po, subs);
    }

    @Override
    public List<MddVO> page(MddPageQueryDTO query) {
        MineDevelopmentDataPO c = new MineDevelopmentDataPO();
        c.setDataType(query.getData_type());
        c.setUnitCode(query.getUnit_code());
        c.setUnitName(query.getUnit_name());
        c.setCurrentShift(query.getCurrent_shift());
        c.setMineCategory(query.getMine_category());
        c.setIsDeleted(query.getIs_deleted());
        c.setRecordDate(parseDate(query.getRecord_date()));

        List<MineDevelopmentDataPO> masters = mddMapper.selectList(c);
        if (masters.isEmpty()) return Collections.emptyList();

        List<Long> ids = masters.stream().map(MineDevelopmentDataPO::getId).collect(Collectors.toList());
        List<SubMineDevelopmentDataPO> allSubs = subMapper.selectByMddIds(ids);
        Map<Long, List<SubMineDevelopmentDataPO>> grouped = allSubs.stream()
                .collect(Collectors.groupingBy(SubMineDevelopmentDataPO::getMineDevelopmentDataID, LinkedHashMap::new, Collectors.toList()));

        List<MddVO> out = new ArrayList<>(masters.size());
        for (MineDevelopmentDataPO m : masters) {
            out.add(toVO(m, grouped.getOrDefault(m.getId(), Collections.emptyList())));
        }
        return out;
    }

    // ======== helpers ========
    private static Date parseDate(String s) {
        if (s == null || s.trim().isEmpty()) return null;
        LocalDate d = LocalDate.parse(s.trim().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Date.valueOf(d);
    }

    private MineDevelopmentDataPO toPO(MddCreateDTO dto) {
        MineDevelopmentDataPO po = new MineDevelopmentDataPO();
        po.setDataType(dto.getData_type());
        po.setUnitCode(dto.getUnit_code());
        po.setUnitName(dto.getUnit_name());
        po.setRecordDate(parseDate(dto.getRecord_date()));
        po.setCurrentShift(dto.getCurrent_shift());

        po.setTotalDownCount(dto.getTotal_down_count());
        po.setMiningDownCount(dto.getMining_down_count());
        po.setDrivingDownCount(dto.getDriving_down_count());
        po.setOtherDownCount(dto.getOther_down_count());

        po.setTotalUpCount(dto.getTotal_up_count());
        po.setMiningUpCount(dto.getMining_up_count());
        po.setDrivingUpCount(dto.getDriving_up_count());
        po.setOtherUpCount(dto.getOther_up_count());

        po.setUserId(dto.getUser_id());
        po.setMineCategory(dto.getMine_category());
        return po;
    }

    private List<SubMineDevelopmentDataPO> toSubs(Long id, List<MddSubItemDTO> list) {
        List<SubMineDevelopmentDataPO> out = new ArrayList<>();
        if (list == null) return out;
        for (MddSubItemDTO it : list) {
            SubMineDevelopmentDataPO s = new SubMineDevelopmentDataPO();
            s.setMineDevelopmentDataID(id);
            s.setUnitNameJSON(it.getUnit_nameJSON());
            s.setProductionData(it.getProductionData());
            s.setExpandData(it.getExpandData());
            s.setFootageData(it.getFootageData());
            s.setEnterWellNum(it.getEnterWellNum());
            s.setComeOutWellNum(it.getComeOutWellNum());
            s.setUnitCode(it.getUnit_code());
            out.add(s);
        }
        return out;
    }

    private MddVO toVO(MineDevelopmentDataPO m, List<SubMineDevelopmentDataPO> subs) {
        MddVO vo = new MddVO();
        vo.setId(m.getId());
        vo.setData_type(m.getDataType());
        vo.setUnit_code(m.getUnitCode());
        vo.setUnit_name(m.getUnitName());
        vo.setRecord_date(m.getRecordDate());
        vo.setCurrent_shift(m.getCurrentShift());

        vo.setTotal_down_count(m.getTotalDownCount());
        vo.setMining_down_count(m.getMiningDownCount());
        vo.setDriving_down_count(m.getDrivingDownCount());
        vo.setOther_down_count(m.getOtherDownCount());
        vo.setTotal_up_count(m.getTotalUpCount());
        vo.setMining_up_count(m.getMiningUpCount());
        vo.setDriving_up_count(m.getDrivingUpCount());
        vo.setOther_up_count(m.getOtherUpCount());

        vo.setUser_id(m.getUserId());
        vo.setMine_category(m.getMineCategory());
        vo.setIs_deleted(m.getIsDeleted());

        List<MddSubItemDTO> items = new ArrayList<>();
        for (SubMineDevelopmentDataPO s : subs) {
            MddSubItemDTO it = new MddSubItemDTO();
            it.setUnit_nameJSON(s.getUnitNameJSON());
            it.setUnit_code(s.getUnitCode());
            it.setProductionData(s.getProductionData());
            it.setExpandData(s.getExpandData());
            it.setFootageData(s.getFootageData());
            it.setEnterWellNum(s.getEnterWellNum());
            it.setComeOutWellNum(s.getComeOutWellNum());
            items.add(it);
        }
        vo.setData_JSON(items);
        return vo;
    }
}
