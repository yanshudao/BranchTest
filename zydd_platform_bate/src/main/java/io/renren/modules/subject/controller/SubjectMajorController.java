package io.renren.modules.subject.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.subject.entity.SubjectMajorBaseEntity;
import io.renren.modules.subject.entity.SubjectMajorEntity;
import io.renren.modules.subject.form.MajorCourseForm;
import io.renren.modules.subject.service.SubjectMajorBaseService;
import io.renren.modules.subject.service.SubjectMajorService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



/**
 * 专业
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@RestController
@Api("专业接口")
@RequestMapping("subject/subjectmajor")
public class SubjectMajorController extends AbstractController {
    @Autowired
    private SubjectMajorService subjectMajorService;
    @Autowired
    private SubjectMajorBaseService subjectMajorBaseService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysSemesterService sysSemesterService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("subject:subjectmajor:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("orgCode",sysUserEntity.getOrgCode());
        PageUtils page = subjectMajorService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("subject:subjectmajor:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			SubjectMajorEntity subjectMajor = subjectMajorService.selectById(id);

        return R.ok().put("subjectMajor", subjectMajor);
    }

    /**
     * 保存
     */
    @SysLog("保存专业")
    @PostMapping("/save")
    //@RequiresPermissions("subject:subjectmajor:save")
    @ApiOperation("保存")
    public R save(@RequestBody SubjectMajorEntity subjectMajor){
        subjectMajor.setMajorCode(StringUtils.trim(subjectMajor.getMajorCode()));
        /*if(StringUtils.length(subjectMajor.getMajorCode())!=8){
            return R.error("专业代码不足8位，请补全");
        }*/
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
        SubjectMajorEntity subjectMajorEntity=subjectMajorService.selectByCode(subjectMajor.getMajorCode(),subjectMajor.getStudentType(),subjectMajor.getMajorType(),
                sysOrgEntity.getOrgCode(),sysOrgEntity.getParentCodes());
       if(subjectMajorEntity!=null){
           return R.error("专业已存在！");
       }
        SubjectMajorBaseEntity subjectMajorBaseEntity=subjectMajorBaseService.selectOne(new EntityWrapper<SubjectMajorBaseEntity>().eq("major_code",subjectMajor.getMajorCode()));
        if(subjectMajorBaseEntity==null){
            subjectMajorBaseEntity=new SubjectMajorBaseEntity();
           BeanUtils.copyProperties(subjectMajor,subjectMajorBaseEntity);
           subjectMajorBaseService.insert(subjectMajorBaseEntity);
        }
       subjectMajorService.insert(subjectMajor);


        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("更新专业")
    @PostMapping("/update")
    //@RequiresPermissions("subject:subjectmajor:update")
    @ApiOperation("更新")
    public R update(@RequestBody SubjectMajorEntity subjectMajor){
        subjectMajorService.updateById(subjectMajor);
        SubjectMajorBaseEntity subjectMajorBaseEntity=subjectMajorBaseService.selectOne(new EntityWrapper<SubjectMajorBaseEntity>().eq("major_code",subjectMajor.getMajorCode()));
        if(subjectMajorBaseEntity!=null){
            subjectMajorBaseEntity.setMajorName(subjectMajor.getMajorName());
            subjectMajorBaseService.updateById(subjectMajorBaseEntity);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除专业")
    @PostMapping("/delete")
    //@RequiresPermissions("subject:subjectmajor:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm){
        ValidatorUtils.validateEntity(deleteForm);
        subjectMajorService.deleteBatchIds(deleteForm.getIds());
        return R.ok();
    }
    @SysLog("保存专业课程对应关系")
    @PostMapping("/saveMajorCourse")
    //@RequiresPermissions("subject:subjectmajor:update")
    @ApiOperation("保存专业课程对应")
    public R saveMajorCourse(@RequestBody MajorCourseForm courseResourceForm)
    {
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        subjectMajorService.saveMajorCourse(courseResourceForm.getMajorId(),courseResourceForm.getCourseIds(),sysSemesterEntity.getSemesterCode());
        return R.ok();
    }
    @SysLog("删除专业课程对应关系")
    @PostMapping("/deleteMajorCourse")
    //@RequiresPermissions("subject:subjectmajor:update")
    @ApiOperation("删除专业课程对应")
    public R deleteCourseResource(@RequestBody DeleteForm deleteForm)
    {
        subjectMajorService.deleteMajorCourse(deleteForm.getIds());
        return R.ok();
    }
  /*  *//**
     * 列表
     *//*
    @PostMapping("/listByOrg")
    //@RequiresPermissions("zd:zdorgcourseresource:list")
    @ApiOperation("获取可开设专业列表")
    public R listByOrg(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
        params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        PageUtils page = subjectMajorService.queryMajorByPage(params);
        return R.ok().put("page", page);
    }*/

}
