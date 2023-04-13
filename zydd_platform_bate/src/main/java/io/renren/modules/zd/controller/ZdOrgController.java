package io.renren.modules.zd.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgResourceTypeEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgResourceTypeService;
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
@Api("县级征订主页面接口")
@RequestMapping("zd/zdorg")
public class ZdOrgController {
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

    @Autowired
    private SysOrgResourceTypeService sysOrgResourceTypeService;

    @GetMapping("/listGroup")
    //@RequiresPermissions("zd:zdorgmajor:list")
    @ApiOperation("获取本单位可以报订的专业分类")
    public R listGroup(){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        Map<String,Object> params=new HashMap<>();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        List list = zdOrgMajorService.queryPageGroup(params);
        return R.ok().put("data", list);
    }
    @GetMapping("/course/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("分页获取县级可报订的课程")
    public R listCourse(@RequestParam Map<String, Object> params){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("orgType",sysOrgEntity.getOrgType());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        PageUtils page = zdOrgMajorCourseService.queryByOrg(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/course/resource/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("分页获取县级可报订的课程教材")
    public R listCourseResource(@RequestParam Map<String, Object> params){
        String majorId=params.get("majorId")==null?"":params.get("majorId").toString();
        String courseId=params.get("courseId")==null?"":params.get("courseId").toString();
        if(StringUtils.isBlank(majorId)||StringUtils.isBlank(courseId))
        {
            return R.error("请选择专业课程");
        }
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("orgType","1");
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        PageUtils page = zdOrgMajorCourseService.queryByOrgCourseResource(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/course/listAll")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("获取全部县级获取可报订的课程")
    public R listAllCourse(@RequestParam Map<String, Object> params){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentCodes",sysOrgEntity.getParentCodes());
        List list = zdOrgMajorCourseService.queryAllByOrg(params);
        return R.ok().put("data", list);
    }
    //县级根据专业或者课程获取教材

    @GetMapping("/resource/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("县级分页获取可报订的教材 majorId 筛选专业，courseId 筛选课程")
    public R listResource(@RequestParam Map<String, Object> params){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentCodes",sysOrgEntity.getParentCodes());
        PageUtils page = zdOrgCourseResourceService.queryByOrg(params);
        return R.ok().put("page", page);
    }


    @GetMapping("/v2/listGroup")
    //@RequiresPermissions("zd:zdorgmajor:list")
    @ApiOperation("获取本单位可以报订的专业分类")
    public R v2ListGroup(){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        Map<String,Object> params=new HashMap<>();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        List list = zdMajorCourseResourceService.queryOrgZdGroup(params);
        return R.ok().put("data", list);
    }

    @GetMapping("/v2/list")
    //@RequiresPermissions("zd:zdorgmajor:list")
    @ApiOperation("获取县级单位可以报订的专业分类 majorType,studentType")
    public R listGroup(String studentType,String majorType,String majorName){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        Map<String,Object> params=new HashMap<>();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("majorType",majorType);
        params.put("studentType",studentType);
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        List list = zdMajorCourseResourceService.selectOrgZdMajorMap(params);
        return R.ok().put("data", list);

    }

    @GetMapping("/v2/course/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("分页获取县级可报订的课程")
    public R v2ListCourse(@RequestParam Map<String, Object> params){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("orgType",sysOrgEntity.getOrgType());
        params.put("isShow", Constant.RESOURCE_SHOW.SHOW);
        params.put("isArchives", Constant.ZMCR_ARCHIVES.NOT_ARCHIVES);
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        //查找当前单位的教材类型
        List<SysOrgResourceTypeEntity> typeEntityList=sysOrgResourceTypeService.selectList(new EntityWrapper<SysOrgResourceTypeEntity>().eq("org_code",sysUserEntity.getOrgCode()));
        params.put("resourceTypeList",typeEntityList);
        PageUtils page = zdMajorCourseResourceService.queryCourseByOrg(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/v2/course/resource/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("分页获取县级可报订的课程教材")
    public R v2ListCourseResource(@RequestParam Map<String, Object> params){
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
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("isShow", Constant.RESOURCE_SHOW.SHOW);
        params.put("isArchives", Constant.ZMCR_ARCHIVES.NOT_ARCHIVES);
        params.put("orgType",sysOrgEntity.getOrgType());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        //查找当前单位的教材类型
        List<SysOrgResourceTypeEntity> typeEntityList=sysOrgResourceTypeService.selectList(new EntityWrapper<SysOrgResourceTypeEntity>().eq("org_code",sysUserEntity.getOrgCode()));
        params.put("resourceTypeList",typeEntityList);
        PageUtils page = zdMajorCourseResourceService.queryResourceByOrg(params);
        return R.ok().put("page", page);
    }

}
