package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.BaoBiao.dto.cps.*;
import com.ruoyi.system.domain.BaoBiao.vo.cps.CpsVO;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.UserMessage;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserMessageMapper;
import com.ruoyi.system.service.BaoBiao.ICoalPlantStorageService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(tags = "洗煤厂仓存")
@RestController
@RequestMapping("/dev-api/system/coalPlantStorage")
public class CoalPlantStorageController extends BaseController {

    @Resource
    private ICoalPlantStorageService service;

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private UserMessageMapper messageMapper;
    @Anonymous
    @Operation(summary = "新增仓存（入参含 data_JSON）")
    @PostMapping
    public AjaxResult add(@RequestBody CpsCreateDTO dto) {
        dto.setUser_id(SecurityUtils.getUserId());
        dto.setMine_category("0");
        Long id = service.add(dto);

        SysRole 七煤集团权限 = sysRoleMapper.checkRoleNameUnique("七煤集团权限");
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectRoleUserInfos(Arrays.asList(七煤集团权限.getRoleId()));
        for (SysUserRole userRole:sysUserRoles) {
            String message=dto.getUnit_name()+"提交了洗煤厂仓存数据录入表";
            messageMapper.insertUserMessage(new UserMessage(SecurityUtils.getUserId(),userRole.getUserId(),message,new Date()));
        }
        return AjaxResult.success(id);
    }

    @Anonymous
    @Operation(summary = "修改（全量替换子表 data_JSON）")
    @PutMapping
    public AjaxResult edit(@RequestBody CpsUpdateDTO dto) {
        return AjaxResult.toAjax(service.edit(dto));
    }

    @Anonymous
    @Operation(summary = "删除（软删）")
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
    @Operation(summary = "分页列表（含 data_JSON；支持主表时间/子表日期过滤）")
    @GetMapping("/page")
    public TableDataInfo page(CpsPageQueryDTO query) {
        List<CpsVO> list = service.page(query);
        return getDataTable(list);
    }
}
