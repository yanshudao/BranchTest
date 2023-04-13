package io.renren.modules.zd.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.ShiroUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.subject.service.SubjectCourseService;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import io.renren.modules.zd.entity.ZdOrgMajorCourseEntity;
import io.renren.modules.zd.service.ZdOrgMajorCourseService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 单位开设专业课程
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@RestController
@Api("单位开设课程接口")
@RequestMapping("zd/zdorgmajorcourse")
public class ZdOrgMajorCourseController {
    @Autowired
    private ZdOrgMajorCourseService zdOrgMajorCourseService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SubjectCourseService subjectCourseService;

    /**
     * 保存
     */
    @SysLog("省保存屏蔽的专业课程")
    @PostMapping("/save")
    //@RequiresPermissions("zd:subjectmajor:save")
    @ApiOperation("省保存屏蔽的专业课程")
    public R save(@RequestBody ZdOrgMajorCourseEntity zdOrgMajorCourse){
         ValidatorUtils.validateEntity(zdOrgMajorCourse);
         SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        zdOrgMajorCourse.setSemesterCode(sysSemesterEntity.getSemesterCode());
         zdOrgMajorCourseService.insert(zdOrgMajorCourse);
        return R.ok();
    }
    @SysLog("集合保存屏蔽的专业课程")
    @PostMapping("/saveList")
    //@RequiresPermissions("zd:subjectmajor:save")
    @ApiOperation("集合保存屏蔽的专业课程")
    public R saveList(@RequestBody List<ZdOrgMajorCourseEntity> zdOrgMajorCourse){
        ValidatorUtils.validateEntity(zdOrgMajorCourse);
        List<String> courseIds=new ArrayList<String>();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        for(ZdOrgMajorCourseEntity courseEntity:zdOrgMajorCourse)
        {
            courseEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
            courseIds.add(courseEntity.getCourseId());
        }
        int count=subjectCourseService.countByIds(courseIds);
        if(count!=courseIds.size())
        {
            return R.error("课程不存在！");
        }

        zdOrgMajorCourseService.insertBatch(zdOrgMajorCourse);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除屏蔽的专业课程")
    @PostMapping("/delete")
    //@RequiresPermissions("zd:subjectmajor:delete")
    @ApiOperation("删除屏蔽的专业课程")
    public R delete(@RequestBody DeleteForm deleteForm){
        ValidatorUtils.validateEntity(deleteForm.getIds());
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        zdOrgMajorCourseService.deleteByIds(deleteForm.getIds(),sysUserEntity.getOrgCode(),sysSemesterEntity.getSemesterCode());

        return R.ok();
    }
    @GetMapping("/listNotHave")
    //@RequiresPermissions("zd:subjectmajor:list")
    @ApiOperation("获取单位未开设的课程列表")
    public R listNotHave(@RequestParam Map<String, Object> params,String majorId){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());

        params.put("majorId",majorId);
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentOrgCode",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdOrgMajorCourseService.queryNotHaveByOrg(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/listHave")
    //@RequiresPermissions("zd:subjectmajor:list")
    @ApiOperation("获取单位已开设的课程列表")
    public R listHave(@RequestParam Map<String, Object> params,String majorId){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());

        params.put("majorId",majorId);
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentOrgCode",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdOrgMajorCourseService.queryHaveByOrg(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/listDisableCourse")
    //@RequiresPermissions("zd:subjectmajor:list")
    @ApiOperation("传majorId 市级/县级获取屏蔽的专业下的课程（不传majorId 获取本单位屏蔽的课程列表）")
    public R listDisableCourse(@RequestParam Map<String, Object> params){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
//        params.put("courseId",courseId);
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentOrgCode",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdOrgMajorCourseService.queryDisableCourseByOrg(params);
        return R.ok().put("page", page);
    }

  /*  @PostMapping("/list")
    //@RequiresPermissions("zd:zdorderresource:list")
    @ApiOperation("省级获取可开设的专业课程列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdOrgMajorCourseService.queryPage(params);
        return R.ok().put("page", page);
    }*/

   /* @PostMapping("/listByOrg")
    //@RequiresPermissions("zd:subjectmajor:list")
    @ApiOperation("市级/县级获取可开设的专业课程列表")
    public R listByOrg(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
        params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        PageUtils page = zdOrgMajorCourseService.queryByOrg(params);
        return R.ok().put("page", page);
    }*/


}
