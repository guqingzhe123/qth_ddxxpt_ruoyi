package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.message.enums.BusinessMessageType;
import com.ruoyi.message.service.impl.BusinessSystemMessageService;
import com.ruoyi.system.domain.BaoBiao.dto.wash.*;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.UserMessage;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserMessageMapper;
import com.ruoyi.system.service.BaoBiao.IWashCoalPlanService;
import com.ruoyi.system.service.ISysDeptService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Api(tags = "洗煤计划录入（列式JSON）")
@RestController
@RequestMapping("/dev-api/system/washCoalPlan")
public class WashCoalPlanController extends BaseController {

    @Resource
    private IWashCoalPlanService service;
    @Resource
    private UserMessageMapper messageMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private BusinessSystemMessageService businessSystemMessageService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysDeptService deptService;//部门对象
    @Anonymous
    @Operation(summary = "新增洗煤计划")
    @PostMapping
    public AjaxResult add(@RequestBody WashCoalPlanCreateDTO dto) {
        dto.setUser_id(SecurityUtils.getUserId());
        Long id = service.add(dto);
        if(id==0){
            return AjaxResult.error("请联系局里进行驳回");
        }
        SysRole 七煤集团权限 = sysRoleMapper.checkRoleNameUnique("七煤集团权限");
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectRoleUserInfos(Arrays.asList(七煤集团权限.getRoleId()));

        SysDept sysDept = deptService.selectDeptById(SecurityUtils.getDeptId());
        String message=sysDept.getDeptName()+"提交了洗煤计划，请查看";
        List<String> recvIds=new ArrayList<>();
        for (SysUserRole userRole:sysUserRoles) {
            messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(),userRole.getUserId(),message,new Date()));
            recvIds.add(userRole.getUserId());
        }
        if(recvIds.size()>0){
            businessSystemMessageService.sendBusinessSystemMessage(BusinessMessageType.TODO_RECEIVE.getCode(),
                    SecurityUtils.getUserId(), recvIds, message);
        }
        return AjaxResult.success(id);
    }

    @Anonymous
    @Operation(summary = "修改洗煤计划（全量覆盖子表）")
    @PutMapping
    public AjaxResult edit(@RequestBody WashCoalPlanUpdateDTO dto) {
        return AjaxResult.toAjax(service.edit(dto));
    }

    @Anonymous
    @Operation(summary = "删除洗煤计划（软删主表）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(service.remove(id));
    }

    @Anonymous
    @Operation(summary = "按ID查询详情（返回含 data_JSON）")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(service.get(id));
    }

    @Anonymous
    @Operation(summary = "分页列表（含 data_JSON）")
    @GetMapping("/page")
    public TableDataInfo page(WashCoalPlanPageQueryDTO query) {
        if (query.getPageNum() == null || query.getPageNum() < 1) query.setPageNum(1);
        if (query.getPageSize() == null || query.getPageSize() < 1) query.setPageSize(10);
        startPage(query.getPageNum(), query.getPageSize());
        List<WashCoalPlanVO> list = service.page(query);
        return getDataTable(list);
    }


    @Anonymous
    @Operation(summary = "删除局中洗煤计划")
    @PostMapping("/deleteData")
    public AjaxResult deleteData(@RequestBody WashCoalPlanV1 ju) {
        String userId = sysUserMapper.selectUserByNickName(ju.getUnitName()).getUserId();
        String message="七煤调度退回了"+ju.getUnitName()+"的每月洗煤上报";
        messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(),userId,message,new Date()));
        int i = service.deleteData(ju);
        if(i==0){
            return AjaxResult.error("未上报洗煤计划");
        }
        return AjaxResult.toAjax(1);
    }
}

