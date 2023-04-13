package io.renren.modules.zd.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.service.ZdMajorCourseResourceService;
import io.renren.modules.zd.service.ZdOrgCourseResourceService;
import io.renren.modules.zd.service.ZdOrgMajorCourseService;
import io.renren.modules.zd.service.ZdOrgMajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api("省级代报征订主页面接口")
@RequestMapping("zd/zdsheng")
public class ZdShengOrgController {
    @Autowired
    private ZdOrgMajorService zdOrgMajorService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private ZdOrgMajorCourseService zdOrgMajorCourseService;
    @Autowired
    private ZdOrgCourseResourceService zdOrgCourseResourceService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private ZdMajorCourseResourceService zdMajorCourseResourceService;

    @GetMapping("/listGroup")
    //@RequiresPermissions("zd:zdorgmajor:list")
    @ApiOperation("获取县级单位可以报订的专业分类 传参orgCode")
    public R listGroup(String orgCode){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        Map<String,Object> params=new HashMap<>();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",orgCode);
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        List list = zdMajorCourseResourceService.queryOrgZdGroup(params);
        return R.ok().put("data", list);

    }


    @GetMapping("/course/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("分页获取县级可报订的课程 传参orgCode")
    public R listCourse(@RequestParam Map<String, Object> params,String orgCode){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",orgCode);
        params.put("orgType","1");
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        PageUtils page = zdMajorCourseResourceService.queryCourseByOrg(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/course/listAll")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("获取全部县级获取可报订的课程 传参orgCode")
    public R listAllCourse(@RequestParam Map<String, Object> params,String orgCode){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",orgCode);
        params.put("orgType","1");
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        PageUtils page = zdMajorCourseResourceService.queryCourseByOrg(params);
     //   List list = zdOrgMajorCourseService.queryAllByOrg(params);
        return R.ok().put("data", page);
    }
    //县级根据专业或者课程获取教材

    @GetMapping("/resource/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("县级分页获取可报订的教材 majorId 筛选专业，courseId 筛选课程 传参orgCode")
    public R listResource(@RequestParam Map<String, Object> params, String orgCode){
        String majorId=params.get("majorCode")==null?"":params.get("majorCode").toString();
        String courseId=params.get("courseCode")==null?"":params.get("courseCode").toString();
        if(StringUtils.isBlank(majorId)||StringUtils.isBlank(courseId))
        {
            return R.error("请选择专业课程");
        }
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",orgCode);
        params.put("orgType","1");
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        PageUtils page = zdMajorCourseResourceService.queryResourceByOrg(params);
        return R.ok().put("page", page);
    }
}
