package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.EnteringExitingMinePO;
import com.ruoyi.system.domain.ribaobaobiao.EnterExitQueryDTO;
import com.ruoyi.system.domain.ribaobaobiao.EnterExitShiftVO;
import com.ruoyi.system.mapper.BaoBiao.EnteringExitingMineMapper;
import com.ruoyi.system.service.BaoBiao.IEnteringExitingMineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EnteringExitingMineServiceImpl implements IEnteringExitingMineService {

    @Resource
    private EnteringExitingMineMapper mapper;

    private static final SimpleDateFormat DAY_FMT = new SimpleDateFormat("yyyy-M-d"); // 兼容 2025-10-1
    private static final String ZERO = "0";

    @Override
    public List<EnterExitShiftVO> shiftReport(EnterExitQueryDTO dto) {
        if (dto == null || dto.getRecord_date() == null || dto.getRecord_date().trim().isEmpty()) {
            throw new IllegalArgumentException("record_date 不能为空");
        }

        // 当天起止时间 [00:00:00, +1day 00:00:00)
        Date start, end;
        try {
            Date d = DAY_FMT.parse(dto.getRecord_date().trim());
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            start = c.getTime();
            c.add(Calendar.DATE, 1);
            end = c.getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("record_date 格式错误，应为 yyyy-MM-dd，例如 2025-10-01", e);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("startTime", start);
        params.put("endTime", end);

        // 查询当天所有单位的三班明细
        List<EnteringExitingMinePO> rows = mapper.selectByDate(params);
        if (rows == null || rows.isEmpty()) {
            return Collections.emptyList();
        }

        // unit_name 升序 + 按班次组装
        Map<String, List<EnteringExitingMinePO>> byUnit =
                rows.stream().collect(Collectors.groupingBy(
                        EnteringExitingMinePO::getUnitName,
                        TreeMap::new, // key 排序
                        Collectors.toList()
                ));

        List<EnterExitShiftVO> result = new ArrayList<>(byUnit.size());
        for (Map.Entry<String, List<EnteringExitingMinePO>> e : byUnit.entrySet()) {
            String unit = e.getKey();
            List<EnteringExitingMinePO> unitRows = e.getValue();

            EnterExitShiftVO vo = new EnterExitShiftVO();
            vo.setUnit_name(unit);

            // 缺班补零
            vo.setClass1(emptyStats());
            vo.setClass2(emptyStats());
            vo.setClass3(emptyStats());

            for (EnteringExitingMinePO r : unitRows) {
                EnterExitShiftVO.Stats s = convert(r);
                Integer cs = r.getCurrentShift();
                if (cs == null) continue;
                if (cs == 1) vo.setClass1(s);
                else if (cs == 2) vo.setClass2(s);
                else if (cs == 3) vo.setClass3(s);
            }
            result.add(vo);
        }
        return result;
    }

    private EnterExitShiftVO.Stats emptyStats() {
        EnterExitShiftVO.Stats s = new EnterExitShiftVO.Stats();
        s.setTotal_down_count(ZERO);
        s.setMining_down_count(ZERO);
        s.setDriving_down_count(ZERO);
        s.setOther_down_count(ZERO);
        s.setTotal_up_count(ZERO);
        s.setMining_up_count(ZERO);
        s.setDriving_up_count(ZERO);
        s.setOther_up_count(ZERO);
        return s;
    }

    private EnterExitShiftVO.Stats convert(EnteringExitingMinePO r) {
        EnterExitShiftVO.Stats s = new EnterExitShiftVO.Stats();
        s.setTotal_down_count(i2s(r.getTotalDownCount()));
        s.setMining_down_count(i2s(r.getMiningDownCount()));
        s.setDriving_down_count(i2s(r.getDrivingDownCount()));
        s.setOther_down_count(i2s(r.getOtherDownCount()));
        s.setTotal_up_count(i2s(r.getTotalUpCount()));
        s.setMining_up_count(i2s(r.getMiningUpCount()));
        s.setDriving_up_count(i2s(r.getDrivingUpCount()));
        s.setOther_up_count(i2s(r.getOtherUpCount()));
        return s;
    }

    private String i2s(Integer v) { return v == null ? ZERO : String.valueOf(v); }
}
