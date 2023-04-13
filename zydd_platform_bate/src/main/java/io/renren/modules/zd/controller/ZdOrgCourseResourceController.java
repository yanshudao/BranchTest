package io.renren.modules.zd.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.subject.service.SubjectMajorService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdOrgCourseResourceEntity;
import io.renren.modules.zd.service.ZdOrgCourseResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



/**
 * 单位开设课程教材
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@RestController
@Api("开设专业下的课程相关接口")
@RequestMapping("zd/zdorgcourseresource")
public class ZdOrgCourseResourceController extends AbstractController {
    @Autowired
    private ZdOrgCourseResourceService zdOrgCourseResourceService;
    @Autowired
    private SubjectMajorService subjectMajorService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysSemesterService sysSemesterService;

    /**
     * 保存
     */
    @SysLog("保存屏蔽的课程教材")
    @PostMapping("/save")
    //@RequiresPermissions("zd:subjectcourse:save")
    @ApiOperation("保存屏蔽的课程教材")
    public R save(@RequestBody ZdOrgCourseResourceEntity zdOrgCourseResource){
        ValidatorUtils.validateEntity(zdOrgCourseResource);
	   zdOrgCourseResourceService.insert(zdOrgCourseResource);
        return R.ok();
    }
    /**
     * 保存
     */
    @SysLog("集合保存屏蔽课程教材")
    @PostMapping("/saveList")
    //@RequiresPermissions("zd:subjectcourse:save")
    @ApiOperation("集合保存屏蔽课程教材")
    public R saveList(@RequestBody List<ZdOrgCourseResourceEntity> zdOrgCourseResources){
        ValidatorUtils.validateEntity(zdOrgCourseResources);
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=getUser();
        for(ZdOrgCourseResourceEntity zdOrgCourseResourceEntity:zdOrgCourseResources)
        {
            zdOrgCourseResourceEntity.setOrgCode(sysUserEntity.getOrgCode());
            zdOrgCourseResourceEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
        }
        zdOrgCourseResourceService.insertBatch(zdOrgCourseResources);
        return R.ok();
    }
    /**
     * 删除
     */
    @SysLog("省删除屏蔽的教材")
    @PostMapping("/delete")
    //@RequiresPermissions("zd:subjectcourse:delete")
    @ApiOperation("省删除屏蔽的教材")
    public R delete(@RequestBody DeleteForm form){
        ValidatorUtils.validateEntity(form.getIds());
			zdOrgCourseResourceService.deleteBatchIds(form.getIds());

        return R.ok();
    }
    /*@GetMapping("/listNotHave")
    //@RequiresPermissions("zd:subjectmajor:list")
    @ApiOperation("获取单位未开设的教材列表")
    public R listNotHave(@RequestParam Map<String, Object> params,String courseId){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("courseId",courseId);
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentOrgCode",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdOrgCourseResourceService.queryNotHaveByOrg(params);
        return R.ok().put("page", page);
    }*/
    @GetMapping("/listHave")
    //@RequiresPermissions("zd:subjectmajor:list")
    @ApiOperation("获取单位已开设的教材列表")
    public R listHave(@RequestParam Map<String, Object> params,String courseId){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());

        params.put("courseId",courseId);
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentOrgCode",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdOrgCourseResourceService.queryHaveByOrg(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/listDisableResource")
    //@RequiresPermissions("zd:subjectmajor:list")
    @ApiOperation("传courseId 单位获取课程下屏蔽的教材（不传courseId 获取本单位屏蔽的教材列表）")
    public R listDisableResource(@RequestParam Map<String, Object> params){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
//        params.put("courseId",courseId);
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentOrgCode",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdOrgCourseResourceService.queryDisableResourceByOrg(params);
        return R.ok().put("page", page);
    }

    /*@GetMapping("/list")
//    //@RequiresPermissions("zd:subjectcourse:list")
    @ApiOperation("获取单位开设教材列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="majorId",value="专业ID",dataType="string",required = false,paramType = "query"),
            @ApiImplicitParam(name="courseId",value="课程ID",dataType="string",required = false, paramType = "query"),
    })
    public R list(@RequestParam Map<String, Object> params){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        params.put("parentOrgCode",sysOrgEntity.getOrgCode());
          PageUtils page = zdOrgCourseResourceService.queryByOrg(params);
        return R.ok().put("page", page);
    }*/
}
