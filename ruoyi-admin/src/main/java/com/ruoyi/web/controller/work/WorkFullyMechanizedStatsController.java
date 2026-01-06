package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.work.WorkFullyMechanizedStats;
import com.ruoyi.system.domain.work.WorkFullyMechanizedStatsJu;
import com.ruoyi.system.domain.work.WorkFullyMechanizedStatsList;
import com.ruoyi.system.domain.work.WorkFullyMechanizedStatsListju;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import com.ruoyi.system.service.work.IWorkFullyMechanizedStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 综采综掘统计Controller
 * 
 * @author ruoyi
 * @date 2025-12-04
 */
@RestController
@RequestMapping("/system/WorkFullyMechanizedStatsController")
public class WorkFullyMechanizedStatsController extends BaseController {
    @Autowired
    private IWorkFullyMechanizedStatsService workFullyMechanizedStatsService;

    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表
    /**
     * 查询综采综掘统计列表
     */
    @GetMapping("/list")
    public TableDataInfo<BaseEntity> list(WorkFullyMechanizedStats workFullyMechanizedStats) {
        List<WorkFullyMechanizedStats> list = workFullyMechanizedStatsService.listWorkFullyMechanizedStats(workFullyMechanizedStats);
        return getDataTable(list);
    }
    /**
     * 查询综采综掘统计列表   局端
     */
    @GetMapping("/ALLlist")
    public AjaxResult ALLlist(WorkFullyMechanizedStats workFullyMechanizedStats) {
        workFullyMechanizedStats.setStatsType("综采");
        List<WorkFullyMechanizedStats> fullyMining = workFullyMechanizedStatsService.listWorkFullyMechanizedStats(workFullyMechanizedStats);
        List<WorkFullyMechanizedStats> allFullyMining = workFullyMechanizedStatsService.listWorkFullyMechanizedStatsALL(workFullyMechanizedStats);
        workFullyMechanizedStats.setStatsType("综掘");
        List<WorkFullyMechanizedStats> comprehensive = workFullyMechanizedStatsService.listWorkFullyMechanizedStats(workFullyMechanizedStats);
        List<WorkFullyMechanizedStats> allcomprehensive = workFullyMechanizedStatsService.listWorkFullyMechanizedStatsALL(workFullyMechanizedStats);

        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setLevel(1);
        fac.setIsSealed(0);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);

        WorkFullyMechanizedStatsListju work=new WorkFullyMechanizedStatsListju();
        List<WorkFullyMechanizedStatsJu> 综采=new ArrayList<>();

        WorkFullyMechanizedStatsJu zc=new WorkFullyMechanizedStatsJu();
        zc.setStatsType("综采");
        zc.setUnitName("全公司合计");
        zc.setUnitCode(workFullyMechanizedStats.getUnitCode());
        zc.setDutyDate(workFullyMechanizedStats.getDutyDate());
        zc.setOutputShift1(fullyMining.stream().filter(Objects::nonNull).mapToInt(po -> po.getOutputShift1() != null ? po.getOutputShift1() : 0).sum());
        zc.setOutputShift2(fullyMining.stream().filter(Objects::nonNull).mapToInt(po -> po.getOutputShift2() != null ? po.getOutputShift2() : 0).sum());
        zc.setOutputShift3(fullyMining.stream().filter(Objects::nonNull).mapToInt(po -> po.getOutputShift3() != null ? po.getOutputShift3() : 0).sum());
        zc.setOriginalOutput(fullyMining.stream().filter(Objects::nonNull).mapToInt(po -> po.getOriginalOutput() != null ? po.getOriginalOutput() : 0).sum());
        zc.setCumulativeOutput(allFullyMining.stream().filter(Objects::nonNull).mapToInt(po -> po.getOriginalOutput() != null ? po.getOriginalOutput() : 0).sum());
        综采.add(zc);
        List<WorkFullyMechanizedStatsJu> 综掘=new ArrayList<>();

        // 按照miningAreaCategories的顺序添加综采数据
        for (MiningAreaCategory miningAreaCategory : miningAreaCategories) {
            String unitCode = miningAreaCategory.getAreaCode();
            // 添加该unitCode下的所有团队数据
            for (WorkFullyMechanizedStats workFully : fullyMining) {
                if (unitCode.equals(workFully.getUnitCode())) {
                    WorkFullyMechanizedStatsJu zc1 = new WorkFullyMechanizedStatsJu();
                    zc1.setStatsType("综采");
                    zc1.setId(workFully.getId());
                    zc1.setStatus(workFully.getStatus());
                    zc1.setUnitName(workFully.getUnitName());
                    zc1.setUnitCode(workFully.getUnitCode());
                    zc1.setTeamName(workFully.getTeamName());
                    zc1.setDutyDate(workFullyMechanizedStats.getDutyDate());
                    zc1.setOutputShift1(workFully.getOutputShift1());
                    zc1.setOutputShift2(workFully.getOutputShift2());
                    zc1.setOutputShift3(workFully.getOutputShift3());
                    zc1.setOriginalOutput(workFully.getOriginalOutput());
                    zc1.setCumulativeOutput(allFullyMining.stream().filter(po -> po != null)
                            .filter(item -> workFully.getUnitName().equals(item.getUnitName()))
                            .filter(item -> workFully.getUnitCode().equals(item.getUnitCode()))
                            .filter(item -> workFully.getTeamName().equals(item.getTeamName())).mapToInt(po -> po.getOriginalOutput()).sum());
                    zc1.setRemarks(workFully.getRemarks());
                    综采.add(zc1);
                }
            }
        }

        WorkFullyMechanizedStatsJu zj=new WorkFullyMechanizedStatsJu();
        zj.setStatsType("综掘");
        zj.setUnitName("全公司合计");
        zj.setUnitCode(workFullyMechanizedStats.getUnitCode());
        zj.setDutyDate(workFullyMechanizedStats.getDutyDate());
        zj.setDutyDate(workFullyMechanizedStats.getDutyDate());
        zj.setOutputShift1(comprehensive.stream().filter(Objects::nonNull).mapToInt(po -> po.getOutputShift1() != null ? po.getOutputShift1() : 0).sum());
        zj.setOutputShift2(comprehensive.stream().filter(Objects::nonNull).mapToInt(po -> po.getOutputShift2() != null ? po.getOutputShift2() : 0).sum());
        zj.setOutputShift3(comprehensive.stream().filter(Objects::nonNull).mapToInt(po -> po.getOutputShift3() != null ? po.getOutputShift3() : 0).sum());
        zj.setOriginalOutput(comprehensive.stream().filter(Objects::nonNull).mapToInt(po -> po.getOriginalOutput() != null ? po.getOriginalOutput() : 0).sum());
        zj.setCumulativeOutput(allcomprehensive.stream().filter(Objects::nonNull).mapToInt(po -> po.getOriginalOutput() != null ? po.getOriginalOutput() : 0).sum());

        综掘.add(zj);

        // 按照miningAreaCategories的顺序添加综掘数据
        for (MiningAreaCategory miningAreaCategory : miningAreaCategories) {
            String unitCode = miningAreaCategory.getAreaCode();
            // 添加该unitCode下的所有团队数据
            for (WorkFullyMechanizedStats workFully : comprehensive) {
                if (unitCode.equals(workFully.getUnitCode())) {
                    WorkFullyMechanizedStatsJu zc1 = new WorkFullyMechanizedStatsJu();
                    zc1.setStatsType("综掘");
                    zc1.setId(workFully.getId());
                    zc1.setStatus(workFully.getStatus());
                    zc1.setUnitName(workFully.getUnitName());
                    zc1.setUnitCode(workFully.getUnitCode());
                    zc1.setTeamName(workFully.getTeamName());
                    zc1.setDutyDate(workFullyMechanizedStats.getDutyDate());
                    zc1.setOutputShift1(workFully.getOutputShift1());
                    zc1.setOutputShift2(workFully.getOutputShift2());
                    zc1.setOutputShift3(workFully.getOutputShift3());
                    zc1.setOriginalOutput(workFully.getOriginalOutput());

                    zc1.setCumulativeOutput(allcomprehensive.stream().filter(po -> po != null)
                            .filter(item -> workFully.getUnitName().equals(item.getUnitName()))
                            .filter(item -> workFully.getUnitCode().equals(item.getUnitCode()))
                            .filter(item -> workFully.getTeamName().equals(item.getTeamName())).mapToInt(po -> po.getOriginalOutput()).sum());

                    zc1.setRemarks(workFully.getRemarks());
                    综掘.add(zc1);
                }
            }
        }


        work.setFullyMining(综采);//综采
        work.setComprehensive(综掘);//综掘
        return success(work);
    }
    /**
     * 新增综采综掘统计  矿端
     */
    @Log(title = "综采综掘统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkFullyMechanizedStatsList workFullyMechanizedStats) {
        int i = workFullyMechanizedStatsService.saveWorkFullyMechanizedStats(workFullyMechanizedStats);

        return toAjax(i);
    }

    /**
     * 删除综采综掘统计退回
     */
    @Log(title = "删除综采综掘统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        WorkFullyMechanizedStats workFullyMechanizedStatsById = workFullyMechanizedStatsService.getWorkFullyMechanizedStatsById(String.valueOf(ids));
        if(workFullyMechanizedStatsById.getStatus().equals("0")){
            WorkFullyMechanizedStats stats=new WorkFullyMechanizedStats();
            stats.setDutyDate(workFullyMechanizedStatsById.getDutyDate());
            stats.setUnitName(workFullyMechanizedStatsById.getUnitName());
            stats.setUnitCode(workFullyMechanizedStatsById.getUnitCode());
            List<WorkFullyMechanizedStats> workFullyMechanizedStats = workFullyMechanizedStatsService.listWorkFullyMechanizedStats(stats);
            for (WorkFullyMechanizedStats workFully:workFullyMechanizedStats) {
                workFully.setStatus("2");
                workFullyMechanizedStatsService.updateWorkFullyMechanizedStats(workFully);
            }
        }
        return toAjax(1);
    }
}
