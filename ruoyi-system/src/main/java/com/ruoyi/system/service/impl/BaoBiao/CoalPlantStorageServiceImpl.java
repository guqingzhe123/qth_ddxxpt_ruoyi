package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.dto.cps.CpsCreateDTO;
import com.ruoyi.system.domain.BaoBiao.dto.cps.CpsPageQueryDTO;
import com.ruoyi.system.domain.BaoBiao.dto.cps.CpsSubItemDTO;
import com.ruoyi.system.domain.BaoBiao.dto.cps.CpsUpdateDTO;
import com.ruoyi.system.domain.BaoBiao.po.CoalPlantStoragePO;
import com.ruoyi.system.domain.BaoBiao.po.SubCoalPlantStoragePO;
import com.ruoyi.system.domain.BaoBiao.vo.cps.CpsVO;
import com.ruoyi.system.mapper.BaoBiao.CoalPlantStorageMapper;
import com.ruoyi.system.mapper.BaoBiao.SubCoalPlantStorageMapper;
import com.ruoyi.system.service.BaoBiao.ICoalPlantStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoalPlantStorageServiceImpl implements ICoalPlantStorageService {

    @Resource private CoalPlantStorageMapper mainMapper;
    @Resource private SubCoalPlantStorageMapper subMapper;

    @Override
    public int updateState(CpsCreateDTO dto) {
        Map<String, Object> p = new HashMap<>();
        p.put("unitName", dto.getUnit_name());
        p.put("recordDate", parseDate(dto.getRecord_time()));
        List<CoalPlantStoragePO> mains = mainMapper.selectList(p);

        if(mains.size()>0){
            mains.get(0).setIsDeleted(2);
           return  mainMapper.update(mains.get(0));
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(CpsCreateDTO dto) {
        Map<String, Object> p = new HashMap<>();
        p.put("unitName", dto.getUnit_name());
        p.put("recordDate", parseDate(dto.getRecord_time()));
        List<CoalPlantStoragePO> mains = mainMapper.selectList(p);
        Long id;
        Integer del=null;
        if(mains.size()>0){
            id=mains.get(0).getId();
            del=mains.get(0).getIsDeleted();
        }else {
            CoalPlantStoragePO po = new CoalPlantStoragePO();
            po.setUnitName(dto.getUnit_name());
            po.setRecordTime(parseDate(dto.getRecord_time()));
            po.setUserId(dto.getUser_id());
            po.setMineCategory(dto.getMine_category());
            po.setIsDeleted(0);
            mainMapper.insert(po);
            id = po.getId();
            del=0;
        }
        List<SubCoalPlantStoragePO> subs = toSubs(id, dto.getList(),del);
        if (!subs.isEmpty()) subMapper.batchInsert(subs);
        if(del!=null&&subs.size()==0){
            return 0L;
        }
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(CpsUpdateDTO dto) {
        CoalPlantStoragePO po = new CoalPlantStoragePO();
        po.setId(dto.getId());
        po.setUnitName(dto.getUnit_name());
        po.setRecordTime(parseDateTime(dto.getRecord_time()));
        po.setUserId(dto.getUser_id());
        po.setMineCategory(dto.getMine_category());
        int n = mainMapper.update(po);

        subMapper.deleteByParentId(dto.getId());
        List<SubCoalPlantStoragePO> subs = toSubs(dto.getId(), dto.getList(),0);
        if (!subs.isEmpty()) subMapper.batchInsert(subs);
        return n;
    }

    @Override
    public int remove(Long id) {
//        return mainMapper.softDeleteById(id);
        return subMapper.deleteById(id);
    }

    @Override
    public CpsVO get(Long id) {
        CoalPlantStoragePO po = mainMapper.selectById(id);
        if (po == null) return null;
        List<SubCoalPlantStoragePO> subs = subMapper.selectByParentId(id);
        return toVO(po, subs);
    }

    @Override
    public List<CpsVO> page(CpsPageQueryDTO q) {
        Map<String, Object> p = new HashMap<>();
        p.put("unitName", q.getUnit_name());
//        p.put("userId", q.getUser_id());
//        p.put("mineCategory", q.getMine_category());
//        p.put("isDeleted", q.getIs_deleted());
//        p.put("recordTimeFrom", parseDateTime(q.getRecord_time_from()));
//        p.put("recordTimeTo", parseDateTime(q.getRecord_time_to()));
        p.put("recordDate", parseDate(q.getRecord_date()));
//        p.put("recordDateFrom", parseDate(q.getRecord_date_from()));
//        p.put("recordDateTo", parseDate(q.getRecord_date_to()));

        List<CoalPlantStoragePO> mains = mainMapper.selectList(p);
        if (mains.isEmpty()) return Collections.emptyList();

        List<Long> ids = mains.stream().map(CoalPlantStoragePO::getId).collect(Collectors.toList());
        List<SubCoalPlantStoragePO> allSubs = subMapper.selectByParentIds(ids);
        Map<Long, List<SubCoalPlantStoragePO>> grouped = allSubs.stream()
                .collect(Collectors.groupingBy(SubCoalPlantStoragePO::getCoalPlantStorageID, LinkedHashMap::new, Collectors.toList()));

        List<CpsVO> out = new ArrayList<>(mains.size());
        for (CoalPlantStoragePO m : mains) {
            out.add(toVO(m, grouped.getOrDefault(m.getId(), Collections.emptyList())));
        }
        return out;
    }

    @Override
    public List<CpsVO> pageALL(CpsPageQueryDTO q) {
        Map<String, Object> p = new HashMap<>();
        p.put("unitName", q.getUnit_name());
//        p.put("userId", q.getUser_id());
//        p.put("mineCategory", q.getMine_category());
//        p.put("isDeleted", q.getIs_deleted());
//        p.put("recordTimeFrom", parseDateTime(q.getRecord_time_from()));
//        p.put("recordTimeTo", parseDateTime(q.getRecord_time_to()));
        p.put("recordDate", parseDate(q.getRecord_date()));
//        p.put("recordDateFrom", parseDate(q.getRecord_date_from()));
//        p.put("recordDateTo", parseDate(q.getRecord_date_to()));

        List<CoalPlantStoragePO> mains = mainMapper.selectListAll(p);
        if (mains.isEmpty()) return Collections.emptyList();

        List<Long> ids = mains.stream().map(CoalPlantStoragePO::getId).collect(Collectors.toList());
        List<SubCoalPlantStoragePO> allSubs = subMapper.selectByParentIds(ids);
        Map<Long, List<SubCoalPlantStoragePO>> grouped = allSubs.stream()
                .collect(Collectors.groupingBy(SubCoalPlantStoragePO::getCoalPlantStorageID, LinkedHashMap::new, Collectors.toList()));

        List<CpsVO> out = new ArrayList<>(mains.size());
        for (CoalPlantStoragePO m : mains) {
            out.add(toVO(m, grouped.getOrDefault(m.getId(), Collections.emptyList())));
        }
        return out;
    }

    // ===== helpers =====
    private static String parseDateTime(String s) {
        if (s == null || s.trim().isEmpty()) return null;
        String trimed = s.trim();
        // 定义支持的格式（可扩展更多格式）
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .optionalStart() // 可选的时间部分
                .appendPattern(" HH:mm:ss")
                .optionalEnd()
                .parseLenient() // 宽松解析，忽略多余字符（如末尾空格）
                .toFormatter();

        // 优先解析为LocalDateTime，无时间则默认00:00:00
        TemporalAccessor temporal = formatter.parseBest(trimed, LocalDateTime::from, LocalDate::from);
        LocalDateTime dt;
        if (temporal instanceof LocalDateTime) {
            dt = (LocalDateTime) temporal;
        } else {
            dt = ((LocalDate) temporal).atStartOfDay(); // 纯日期转为当天0点
        }
        return dt.toString();
//        if (s == null || s.trim().isEmpty()) return null;
//        // "yyyy-MM-dd HH:mm:ss"
//        LocalDateTime dt = LocalDateTime.parse(s.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd "));
//        return Timestamp.valueOf(dt);
    }
    private static String parseDate(String s) {
        if (s == null || s.trim().isEmpty()) return null;
        LocalDate d = LocalDate.parse(s.trim().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return d.toString();
    }
    private static String parseTime(String s) {
        if (s == null || s.trim().isEmpty()) return null;
        String trimed = s.trim();
        // 从完整字符串中提取时间部分（假设格式固定为 "yyyy-MM-dd HH:mm:ss"）
        // 先判断是否包含空格，避免截取越界
        if (trimed.contains(" ")) {
            String timePart = trimed.split(" ")[1]; // 分割后取第二部分（HH:mm:ss）
            // 用时间格式解析（LocalTime专门处理时分秒）
            LocalTime time = LocalTime.parse(timePart, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return time.toString(); // 返回时分秒字符串（如 "08:30:45"）
        } else {
            // 若输入无日期，直接按时间解析（如输入就是 "08:30:45"）
            LocalTime time = LocalTime.parse(trimed, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return time.toString();
        }
    }
    private List<SubCoalPlantStoragePO> toSubs(Long parentId, List<CpsSubItemDTO> list,Integer  del) {
        List<SubCoalPlantStoragePO> out = new ArrayList<>();
        if (list == null) return out;
        List<SubCoalPlantStoragePO> subCoalPlantStoragePOS = subMapper.selectByParentId(parentId);
        for (CpsSubItemDTO it : list) {

            String targetTime = parseTime(it.getRecord_date());

            // 查找子列表中 recordDate 等于 targetTime 的现有记录
            SubCoalPlantStoragePO existing = subCoalPlantStoragePOS.stream()
                    .filter(sub -> targetTime.equals(sub.getRecordDate()))
                    .findFirst()
                    .orElse(null);

            if (existing != null) {
                if(del !=0){
                    // 如果存在，更新现有记录的数据
                    existing.setCleanCoal(it.getClean_coal());
                    existing.setSlackCoal(it.getSlack_coal());
                    existing.setLumpCoal(it.getLump_coal());
                    existing.setRawCoal(it.getRaw_coal());
                    subMapper.updateById(existing); // 直接更新数据库
                }
            }else {
                SubCoalPlantStoragePO s = new SubCoalPlantStoragePO();
                s.setCoalPlantStorageID(parentId);
                s.setRecordDate(parseTime(it.getRecord_date()));
                s.setCleanCoal(it.getClean_coal());
                s.setSlackCoal(it.getSlack_coal());
                s.setLumpCoal(it.getLump_coal());
                s.setRawCoal(it.getRaw_coal());
                out.add(s);
            }

        }
        return out;
    }

    private CpsVO toVO(CoalPlantStoragePO m, List<SubCoalPlantStoragePO> subs) {
        CpsVO vo = new CpsVO();
        vo.setId(m.getId());
        vo.setUnit_name(m.getUnitName());
        vo.setRecord_time(m.getRecordTime());
        vo.setUser_id(m.getUserId());
        vo.setMine_category(m.getMineCategory());
        vo.setCreate_time(m.getCreateTime());
        vo.setUpdate_time(m.getUpdateTime());

        List<CpsSubItemDTO> items = new ArrayList<>();
        for (SubCoalPlantStoragePO s : subs) {
            CpsSubItemDTO it = new CpsSubItemDTO();
            it.setId(s.getId());
            it.setRecord_date(s.getRecordDate());
            it.setClean_coal(s.getCleanCoal());
            it.setSlack_coal(s.getSlackCoal());
            it.setLump_coal(s.getLumpCoal());
            it.setRaw_coal(s.getRawCoal());
            items.add(it);
        }
        vo.setData_JSON(items);
        return vo;
    }
}
