package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.im.chat.enums.BusinessMessageType;
import com.ruoyi.message.service.impl.BusinessSystemMessageService;
import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanCreateDTO;
import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanPageQueryDTO;
import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanUpdateDTO;
import com.ruoyi.system.domain.BaoBiao.po.SubMinePlanPO;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MinePlanJu;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MinePlanVO;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.UserMessage;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserMessageMapper;
import com.ruoyi.system.service.BaoBiao.IMinePlanService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(tags = "矿计划（生产/开拓/进尺）")
@RestController
@RequestMapping("/dev-api/system/minePlan")
public class MinePlanController extends BaseController {

    @Resource
    private IMinePlanService service;
    @Resource
    private UserMessageMapper messageMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private BusinessSystemMessageService businessSystemMessageService;

    @Anonymous
    @Operation(summary = "新增矿生产/开拓/进尺计划（入参含 data_JSON）")
    @PostMapping
    public AjaxResult add(@RequestBody MinePlanCreateDTO dto) {
        dto.setUser_id(SecurityUtils.getUserId());
        Long id = service.add(dto);
        if(id==null){
            return AjaxResult.error("请联系局里进行驳回");
        }
        SysRole 七煤集团权限 = sysRoleMapper.checkRoleNameUnique("七煤集团权限");
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectRoleUserInfos(Arrays.asList(七煤集团权限.getRoleId()));
        List<String> recvIds=new ArrayList<>();
        String message=dto.getUnit_name()+"提交了"+dto.getPlan_type()+"计划";
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
    @Operation(summary = "修改矿生产/开拓/进尺计划（全量替换子表）")
    @PutMapping
    public AjaxResult edit(@RequestBody MinePlanUpdateDTO dto) {
        return AjaxResult.toAjax(service.edit(dto));
    }

    @Anonymous
    @Operation(summary = "删除计划（软删主表）")
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
    @GetMapping("/page")//page
    public TableDataInfo page(MinePlanPageQueryDTO query) {
        List<MinePlanVO> list = service.page(query);
        return getDataTable(list);
    }
    @Anonymous
    @Operation(summary = "公司查看单位名称")
    @GetMapping("/allPage")
    public TableDataInfo allPage(MinePlanPageQueryDTO query) {
        List<MinePlanVO> list = service.allPage(query);
        return getDataTable(list);
    }

    @Anonymous
    @Operation(summary = "局里查看生产数据")
    @GetMapping("/productionData")
    public TableDataInfo productionData(MinePlanJu ju) {
        List<MinePlanJu> list = service.productionData(ju);
        return getDataTable(list);
    }
    @Anonymous
    @Operation(summary = "查看当前生产情况状态")
    @GetMapping("/classThreestatus")
    public AjaxResult classThreestatus(MinePlanPageQueryDTO mine) {
        String state = service.getState(mine);
        return success(state);
    }

    @Anonymous
    @Operation(summary = "公司计划录入确认")//不等于2 不可以修改
    @PostMapping("/confirmationCompanyPlanInput")
    public AjaxResult confirmationCompanyPlanInput(@RequestBody SubMinePlanPO dto)
    {
        return AjaxResult.toAjax(service.subEdit(dto));
    }


    @Anonymous
    @Operation(summary = "删除局中生产数据")
    @PostMapping("/deleteData")
    public AjaxResult deleteData(@RequestBody MinePlanJu ju) {
        String userId = sysUserMapper.selectUserByNickName(ju.getUnitName()).getUserId();
        String message="七煤调度退回了"+ju.getUnitName()+"的日报";
        messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(),userId,message,new Date()));

        return AjaxResult.toAjax(service.deleteData(ju));
    }

}
