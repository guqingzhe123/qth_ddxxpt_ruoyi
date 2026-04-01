package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.message.service.impl.BusinessSystemMessageService;
import com.ruoyi.system.domain.BaoBiao.dto.dev.MddCreateDTO;
import com.ruoyi.system.domain.BaoBiao.dto.dev.MddPageQueryDTO;
import com.ruoyi.system.domain.BaoBiao.dto.dev.MddUpdateDTO;
import com.ruoyi.system.domain.BaoBiao.vo.dev.MddVO;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MinePlanThree;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.UserMessage;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserMessageMapper;
import com.ruoyi.system.service.BaoBiao.IMineDevelopmentDataService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(tags = "开拓/进尺/生产")
@RestController
@RequestMapping("/dev-api/system/mineDevData")
public class MineDevelopmentDataController extends BaseController {

    @Resource
    private IMineDevelopmentDataService service;

    @Resource
    private UserMessageMapper messageMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private BusinessSystemMessageService businessSystemMessageService;

    @Anonymous
    @Operation(summary = "新增矿开拓/进拓/升拓数据（入参含 data_JSON）")
    @PostMapping
    public AjaxResult add(@RequestBody MddCreateDTO dto) {
        Long id = service.add(dto);
        if(id==null){
            return AjaxResult.error("联系局里删除后重新保存");
        }else {
            String message=dto.getUnit_name()+"提交了"+dto.getData_type()+dto.getCurrent_shift()+"班日报";
//            List<String> recvIds=new ArrayList<>();

            SysRole 七煤集团权限 = sysRoleMapper.checkRoleNameUnique("七煤集团权限");
            List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectRoleUserInfos(Arrays.asList(七煤集团权限.getRoleId()));
            for (SysUserRole userRole:sysUserRoles) {
                messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(),userRole.getUserId(),message,new Date()));
//                recvIds.add(userRole.getUserId());
            }
//            if(recvIds.size()>0){
//                businessSystemMessageService.sendBusinessSystemMessage(BusinessMessageType.TODO_RECEIVE.getCode(),
//                        SecurityUtils.getUserId(), recvIds, message);
//            }
            return AjaxResult.success(id);
        }
    }

    @Anonymous
    @Operation(summary = "修改矿开拓/进拓/升拓数据（全量替换子表）")
    @PutMapping
    public AjaxResult edit(@RequestBody MddUpdateDTO dto) {
        return AjaxResult.toAjax(service.edit(dto));
    }

    @Anonymous
    @Operation(summary = "删除（软删主表）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(service.remove(id));
    }

    @Anonymous
    @Operation(summary = "按ID查询详情（含 data_JSON）")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(service.get(id));
    }

    @Anonymous
    @Operation(summary = "分页列表（含 data_JSON）")
    @GetMapping("/page")
    public TableDataInfo page(MddPageQueryDTO query) {
        if (query.getPageNum() == null || query.getPageNum() < 1) query.setPageNum(1);
        if (query.getPageSize() == null || query.getPageSize() < 1) query.setPageSize(10);
        startPage(query.getPageNum(), query.getPageSize());
        List<MddVO> list = service.page(query);
        return getDataTable(list);
    }


    @Anonymous
    @Operation(summary = "分页列表（含 data_JSON）")
    @GetMapping("/classThreestatus")
    public AjaxResult classThreestatus(MddPageQueryDTO query) {
//        ///数据从2026-03-31开始
//        if(parseDate(query.getRecord_date())
//                .before(java.sql.Date.valueOf("2026-03-31"))){
//            MinePlanThree ju=new MinePlanThree();
//            ju.setPlanType(query.getData_type());
//            ju.setOneClass(0);
//            ju.setTwoClass(0);
//            ju.setThreeClass(0);
//            return success(ju);
//        }

        List<MddVO> list = service.page(query);
        MinePlanThree ju=new MinePlanThree();
        ju.setPlanType(query.getData_type());
        //空是待提交  0是已提交  1是已退回
        for (MddVO vo: list) {
            if(vo.getCurrent_shift() ==1 ){
                ju.setOneClass(vo.getIs_deleted());
            }
            if(vo.getCurrent_shift() ==2 ){
                ju.setTwoClass(vo.getIs_deleted());
            }
            if(vo.getCurrent_shift() ==3 ){
                ju.setThreeClass(vo.getIs_deleted());
            }
        }
        return success(ju);
    }

    private static java.sql.Date parseDate(String s) {
        if (s == null || s.trim().isEmpty()) return null;
        LocalDate d = LocalDate.parse(s.trim().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return java.sql.Date.valueOf(d);
    }


}

