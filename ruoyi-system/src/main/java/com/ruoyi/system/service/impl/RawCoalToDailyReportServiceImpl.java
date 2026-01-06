package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.RawCoalToDailyReport;
import com.ruoyi.system.mapper.BaoBiao.MiningAreaCategoryMapper;
import com.ruoyi.system.mapper.RawCoalToDailyReportMapper;
import com.ruoyi.system.service.IRawCoalToDailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 原煤去向月报Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class RawCoalToDailyReportServiceImpl implements IRawCoalToDailyReportService {
    @Autowired
    private RawCoalToDailyReportMapper rawCoalToDailyReportMapper;

    @Resource
    private MiningAreaCategoryMapper miningAreaCategoryMapper;
    /**
     * 查询原煤去向月报
     * 
     * @param id 原煤去向月报主键
     * @return 原煤去向月报
     */
    @Override
    public RawCoalToDailyReport getRawCoalToDailyReportById(Long id) {
        return rawCoalToDailyReportMapper.selectRawCoalToDailyReportById(id);
    }

    /**
     * 查询原煤去向月报列表
     * 
     * @param rawCoalToDailyReport 原煤去向月报
     * @return 原煤去向月报
     */
    @Override
    public List<RawCoalToDailyReport> listRawCoalToDailyReport(RawCoalToDailyReport rawCoalToDailyReport) {
        List<RawCoalToDailyReport> rawCoalToDailyReports = rawCoalToDailyReportMapper.selectRawCoalToDailyReportList(rawCoalToDailyReport);

        MiningAreaCategory fac = new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryMapper.selectList(fac);
        // 创建一个临时列表来存储排序后的结果
        List<RawCoalToDailyReport> sortedRawCoalToDailyReports = new java.util.ArrayList<>();
        
        // 按照miningAreaCategories的顺序添加匹配的项目
        for (MiningAreaCategory mining : miningAreaCategories) {
            RawCoalToDailyReport rawCoalToDailyReportStream = rawCoalToDailyReports.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new RawCoalToDailyReport());;
            if(rawCoalToDailyReportStream.getUnitName() !=null){
                sortedRawCoalToDailyReports.add(rawCoalToDailyReportStream);
            }else {
                RawCoalToDailyReport rawCoalToDailyReport1 = new RawCoalToDailyReport();
                rawCoalToDailyReport1.setUnitName(mining.getAreaName());
                sortedRawCoalToDailyReports.add(rawCoalToDailyReport1);
            }


        }


        return sortedRawCoalToDailyReports;
    }

    /**
     * 新增原煤去向月报
     * 
     * @param rawCoalToDailyReport 原煤去向月报
     * @return 结果
     */
    @Override
    public int saveRawCoalToDailyReport(List<RawCoalToDailyReport> rawCoalToDailyReport) {
        RawCoalToDailyReport raw=new RawCoalToDailyReport();
        raw.setExportDate(rawCoalToDailyReport.get(0).getExportDate());
        List<RawCoalToDailyReport> rawCoal = rawCoalToDailyReportMapper.selectRawCoalToDailyReportList(raw);
        if(rawCoal.size()>0){
            for (RawCoalToDailyReport rawCoalToDaily1:rawCoal) {
                for (RawCoalToDailyReport rawCoalToDaily2:rawCoalToDailyReport) {
                    if(rawCoalToDaily1.getUnitName().equals(rawCoalToDaily2.getUnitName())){
                        RawCoalToDailyReport  update=rawCoalToDaily2;
                        update.setId(rawCoalToDaily1.getId());
                        rawCoalToDailyReportMapper.updateRawCoalToDailyReport(update);
                    }
                }
            }
            return 1;
        }else {
            return rawCoalToDailyReportMapper.insertRawCoalToDailyReport(rawCoalToDailyReport);
        }





    }

    /**
     * 修改原煤去向月报
     * 
     * @param rawCoalToDailyReport 原煤去向月报
     * @return 结果
     */
    @Override
    public int updateRawCoalToDailyReport(RawCoalToDailyReport rawCoalToDailyReport) {
        return rawCoalToDailyReportMapper.updateRawCoalToDailyReport(rawCoalToDailyReport);
    }

    /**
     * 批量删除原煤去向月报
     * 
     * @param ids 需要删除的原煤去向月报主键
     * @return 结果
     */
    @Override
    public int deleteRawCoalToDailyReportByIds(Long[] ids) {
        return rawCoalToDailyReportMapper.deleteRawCoalToDailyReportByIds(ids);
    }

    /**
     * 删除原煤去向月报信息
     * 
     * @param id 原煤去向月报主键
     * @return 结果
     */
    @Override
    public int deleteRawCoalToDailyReportById(Long id) {
        return rawCoalToDailyReportMapper.deleteRawCoalToDailyReportById(id);
    }
}
