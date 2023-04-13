package io.renren.modules.subject.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.subject.entity.SubjectMajorCourseEntity;
import io.renren.modules.subject.form.CourseResourceForm;
import io.renren.modules.subject.service.SubjectMajorCourseService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import io.renren.modules.subject.entity.SubjectCourseEntity;
import io.renren.modules.subject.service.SubjectCourseService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@RestController
@Api("课程接口")
@RequestMapping("subject/subjectcourse")
public class SubjectCourseController extends AbstractController{
    @Autowired
    private SubjectCourseService subjectCourseService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysSemesterService sysSemesterService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("获取列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="majorId",value="专业ID",dataType="string", paramType = "query"),
    })
    public R list(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
        if(sysOrgEntity!=null)
        {
            params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        }
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        PageUtils page = subjectCourseService.queryListPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("subject:subjectcourse:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			SubjectCourseEntity subjectCourse = subjectCourseService.selectById(id);

        return R.ok().put("subjectCourse", subjectCourse);
    }

    /**
     * 保存
     */
    @SysLog("保存课程")
    @PostMapping("/save")
    //@RequiresPermissions("subject:subjectcourse:save")
    @ApiOperation("保存")
    public R save(@RequestBody SubjectCourseEntity subjectCourse){

        subjectCourseService.insert(subjectCourse);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("更新课程")
    @PostMapping("/update")
    //@RequiresPermissions("subject:subjectcourse:update")
    @ApiOperation("更新")
    public R update(@RequestBody SubjectCourseEntity subjectCourse){
        subjectCourse.setOrgCode(getUser().getOrgCode());
        subjectCourseService.updateById(subjectCourse);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除课程")
    @PostMapping("/delete")
    //@RequiresPermissions("subject:subjectcourse:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm){
		subjectCourseService.deleteCourse(deleteForm.getIds());
        return R.ok();
    }
    @SysLog("保存课程教材关系")
    @PostMapping("/saveCourseResource")
    //@RequiresPermissions("subject:subjectcourse:update")
    @ApiOperation("保存课程教材")
    public R saveCourseResource(@RequestBody CourseResourceForm courseResourceForm)
    {
        subjectCourseService.saveCourseResource(courseResourceForm.getCourseId(),courseResourceForm.getResourceIds());
        return R.ok();
    }
    @SysLog("删除课程教材关系")
    @PostMapping("/deleteCourseResource")
    //@RequiresPermissions("subject:subjectcourse:update")
    @ApiOperation("删除课程教材对应")
    public R deleteCourseResource(@RequestBody DeleteForm deleteForm)
    {
        subjectCourseService.deleteCourseResource(deleteForm.getIds());
        return R.ok();
    }
    @GetMapping("/listCourse")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("获取列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="majorId",value="专业ID",dataType="string", paramType = "query"),
    })
    public R listCourse(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        if(sysOrgEntity!=null)
        {
            params.put("parentCodes",sysOrgEntity.getParentCodes());
        }

        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        PageUtils page = subjectCourseService.queryListCoursePage(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    /*@GetMapping("/listByMajorId")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("根据专业获取课程")
    public R listByMajorId(@RequestParam Map<String, Object> params){
        PageUtils page = subjectCourseService.queryListPage(params);
        return R.ok().put("page", page);
    }*/

}
