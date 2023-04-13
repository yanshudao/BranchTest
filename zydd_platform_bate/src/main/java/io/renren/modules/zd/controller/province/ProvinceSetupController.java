package io.renren.modules.zd.controller.province;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.ZdMajorCourseOrgForm;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdMajorOrg;
import io.renren.modules.zd.entity.ZdOrgMajorCourseEntity;
import io.renren.modules.zd.form.BatchForm;
import io.renren.modules.zd.form.ZdMajorOrgForm;
import io.renren.modules.zd.service.ZdMajorCourseOrgService;
import io.renren.modules.zd.service.ZdMajorOrgDisableService;
import io.renren.modules.zd.service.ZdMajorOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/province/setup")
@Api("省级专业开设管理")
public class ProvinceSetupController extends AbstractController {

    @Autowired
    private ZdMajorOrgService zdMajorOrgService;
    @Autowired
    private ZdMajorOrgDisableService zdMajorOrgDisableService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Autowired
    private ZdMajorCourseOrgService zdMajorCourseOrgService;
    @PostMapping("/major/opened/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获得已开设列表")
    public R listOpened(@RequestBody Map<String, Object> params){
        String orgCode=getUser().getOrgCode();
        String semesterCode=params.getOrDefault("semesterCode","").toString();
        if(StringUtils.isBlank(semesterCode)){
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            semesterCode=sysSemesterEntity.getSemesterCode();
        }
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(orgCode);
        params.put("semesterCode",semesterCode);
        params.put("orgCode",orgCode);
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        //获取已开设
        PageUtils page = zdMajorOrgService.queryPage(params);
        return R.ok().put("page", page);

    }

    @PostMapping("/course/opened/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获得已开设列表")
    public R courseList(@RequestBody Map<String, Object> params){
        String orgCode=getUser().getOrgCode();
        String semesterCode=params.getOrDefault("semesterCode","").toString();
        if(StringUtils.isBlank(semesterCode)){
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            semesterCode=sysSemesterEntity.getSemesterCode();
        }
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(orgCode);
        params.put("semesterCode",semesterCode);
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("orgCode",sysOrgEntity.getOrgCode());
        //获取已开设
        PageUtils page = zdMajorCourseOrgService.queryPage(params);
        return R.ok().put("page", page);

    }

    @PostMapping("/major/opening/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获得可开设列表")
    public R listConfirm(@RequestBody Map<String, Object> params){
        String orgCode=getUser().getOrgCode();
        String semesterCode=params.getOrDefault("semesterCode","").toString();
        if(StringUtils.isBlank(semesterCode)){
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            semesterCode=sysSemesterEntity.getSemesterCode();
        }
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(orgCode);
        params.put("orgCode",sysOrgEntity.getOrgCode());
        params.put("semesterCode",semesterCode);
//            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        //            params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        PageUtils page = zdMajorOrgService.queryZMCRPage(params);
        return R.ok().put("page", page);
    }
    @PostMapping("/major/disable/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获得取消开设列表")
    public R disableList(@RequestBody Map<String, Object> params){
        String orgCode=getUser().getOrgCode();
        String semesterCode=params.getOrDefault("semesterCode","").toString();
        if(StringUtils.isBlank(semesterCode)){
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            semesterCode=sysSemesterEntity.getSemesterCode();
        }
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(orgCode);
        params.put("orgCode",orgCode);
        params.put("semesterCode",semesterCode);
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        PageUtils page = zdMajorOrgDisableService.queryPage(params);
        return R.ok().put("page", page);
    }
    @PostMapping("/course/disable/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获得取消开设列表")
    public R disableCourseList(@RequestBody Map<String, Object> params){
        String orgCode=getUser().getOrgCode();
        String semesterCode=params.getOrDefault("semesterCode","").toString();
        if(StringUtils.isBlank(semesterCode)){
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            semesterCode=sysSemesterEntity.getSemesterCode();
        }
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(orgCode);
//        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();

        params.put("orgCode",orgCode);
        params.put("semesterCode",semesterCode);
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        PageUtils page = zdMajorCourseOrgService.queryDisablePage(params);
        return R.ok().put("page", page);
    }

    @PostMapping("/major/saveBatch")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("批量保存开设信息")
    public R saveBatch(@RequestBody ZdMajorOrgForm zdMajorOrgForm){
        String orgCode=getUser().getOrgCode();

        zdMajorOrgForm.getList().forEach(item->{
            item.setToOrgCode(orgCode);
        });
        return zdMajorOrgService.saveBatch(zdMajorOrgForm.getList());
    }

    @PostMapping("/major/saveDisable")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("取消开设")
    public R saveDisable(@RequestBody BatchForm batchForm){
        List<String> ids=batchForm.getList().stream().map(ZdOrgMajorCourseEntity::getId).collect(Collectors.toList());
        List<ZdMajorOrg> zdMajorOrgs=zdMajorOrgService.selectBatchIds(ids);
        SysUserEntity sysUserEntity=getUser();
        zdMajorOrgs.stream().forEach(item->item.setId(IdWorker.get32UUID()));
        return zdMajorOrgDisableService.insertIgnoreByBatch(zdMajorOrgs,sysUserEntity.getOrgCode());
    }
    @PostMapping("/major/deleteDisable")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("取消开设")
    public R deleteDisable(@RequestBody BatchForm batchForm){
        List<String> ids=batchForm.getList().stream().map(ZdOrgMajorCourseEntity::getId).collect(Collectors.toList());
        zdMajorOrgDisableService.deleteBatchIds(ids);
        return R.ok();
    }
    @PostMapping("/course/saveDisable")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("批量保存屏蔽的课程")
    public R saveCourseDisable(@RequestBody ZdMajorCourseOrgForm zdMajorCourseOrgForm){
        String orgCode=getUser().getOrgCode();
        zdMajorCourseOrgForm.getList().forEach(item->{
            item.setToOrgCode(orgCode);
            item.setId(IdWorker.get32UUID());
        });
        return zdMajorCourseOrgService.saveBatch(zdMajorCourseOrgForm.getList());
    }
    @PostMapping("/course/deleteDisable")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("批量保存屏蔽的课程")
    public R deleteCourseDisable(@RequestBody BatchForm batchForm){
        List<String> ids=batchForm.getList().stream().map(ZdOrgMajorCourseEntity::getId).collect(Collectors.toList());
        zdMajorCourseOrgService.deleteBatchIds(ids);
        return  R.ok();
    }
}
