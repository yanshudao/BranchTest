package io.renren.modules.subject.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.*;
import io.renren.modules.search.vo.StatisticsResourceDetailVO;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.entity.SubjectResourceScopeEntity;
import io.renren.modules.subject.entity.SubjectResourceTypeEntity;
import io.renren.modules.subject.form.SubjectResourceVersionForm;
import io.renren.modules.subject.form.UpdateResourceVersionForm;
import io.renren.modules.subject.form.UpdateScopeForm;
import io.renren.modules.subject.service.SubjectResourceScopeService;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.subject.service.SubjectResourceTypeService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgResourceTypeEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgResourceTypeService;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.zd.vo.ZdMajorCourseResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 教材
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@RestController
@Api("教材接口")
@RequestMapping("subject/subjectresource")
public class SubjectResourceController extends AbstractController {
    @Autowired
    private SubjectResourceService subjectResourceService;
    @Autowired
    private SubjectResourceScopeService subjectResourceScopeService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SubjectResourceTypeService subjectResourceTypeService;
    @Autowired
    private SysOrgResourceTypeService sysOrgResourceTypeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("subject:subjectresource:list")
    @ApiOperation("获取列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="courseId",value="課程ID",dataType="string", paramType = "query"),
          })
    public R list(@RequestParam(required = false) Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
        if(sysOrgEntity!=null)
        {
            params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        }
        params.put("orgCode",sysUserEntity.getOrgCode());
        List<SysOrgResourceTypeEntity> typeEntityList=sysOrgResourceTypeService.selectList(new EntityWrapper<SysOrgResourceTypeEntity>().eq("org_code",sysUserEntity.getOrgCode()));
        params.put("resourceTypeList",typeEntityList);
        PageUtils page = subjectResourceService.queryListPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("subject:subjectresource:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
        SubjectResourceEntity subjectResource = subjectResourceService.selectById(id);
        subjectResource.setScopeList(subjectResourceScopeService.selectList(new EntityWrapper<SubjectResourceScopeEntity>().eq("resource_id",subjectResource.getId())));
        return R.ok().put("subjectResource", subjectResource);
    }

    /**
     * 保存
     */
    @SysLog("保存教材")
    @PostMapping("/save")
    //@RequiresPermissions("subject:subjectresource:save")
    @ApiOperation("保存")
    public R save(@RequestBody SubjectResourceEntity subjectResource){
        subjectResourceService.insert(subjectResource);
        return R.ok();
    }
    /**
     * 修改
     */
    @SysLog("更新教材")
    @PostMapping("/update")
    //@RequiresPermissions("subject:subjectresource:update")
    @ApiOperation("更新")
    public R update(@RequestBody SubjectResourceEntity subjectResource){

        subjectResourceService.updateResource(subjectResource);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除教材")
    @PostMapping("/delete")
    //@RequiresPermissions("subject:subjectresource:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm){
        subjectResourceService.deleteResource(deleteForm.getIds());
        return R.ok();
    }
    @GetMapping("/listResource")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("获取列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="courseId",value="课程ID",dataType="string", paramType = "query"),
    })
    public R listResource(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
        if(sysOrgEntity!=null)
        {
            params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        }
        params.put("orgCode",sysUserEntity.getOrgCode());
        PageUtils page = subjectResourceService.queryListResourcePage(params);
        return R.ok().put("page", page);
    }
    @SysLog("教材准转现")
    @PostMapping("/updateCurrent")
    //@RequiresPermissions("subject:subjectresource:update")
    @ApiOperation("教材准转现")
    public R updateCurrent(@RequestBody SubjectResourceVersionForm subjectResourceVersionForm){
        subjectResourceService.updateCurrent(subjectResourceVersionForm.getOldResource(),subjectResourceVersionForm.getNewResource());
        return R.ok();
    }
    @SysLog("换版教材")
    @PostMapping("/updateVersion")
    //@RequiresPermissions("subject:subjectresource:update")
    @ApiOperation("教材换版")
    public R updateVersion(@RequestBody UpdateResourceVersionForm updateResourceVersionForm){
        if(updateResourceVersionForm.getOldResource().equals(updateResourceVersionForm.getNewResource()))
        {
            return R.error("新书旧书不能一样");
        }
        subjectResourceService.updateVersion(updateResourceVersionForm);
        return R.ok();
    }
    @SysLog("关系替换")
    @PostMapping("/updateRelation")
    //@RequiresPermissions("subject:subjectresource:update")
    @ApiOperation("关系替换")
    public R updateRelation(@RequestBody UpdateResourceVersionForm updateResourceVersionForm){
        if(updateResourceVersionForm.getOldResource().equals(updateResourceVersionForm.getNewResource()))
        {
            return R.error("新书旧书不能一样");
        }
        subjectResourceService.updateVersion(updateResourceVersionForm);
        return R.ok();
    }

    @SysLog("下架教材")
    @PostMapping("/notShowResource")
    @ApiOperation("教材下架")
    public R notShowResource(@RequestBody DeleteForm deleteForm){
        if(deleteForm==null||CollectionUtils.isEmpty(deleteForm.getIds())){
            return R.error("请选择一条记录！");
        }
        int count=subjectResourceService.selectCount(new EntityWrapper<SubjectResourceEntity>().in("id_",deleteForm.getIds()).ne("org_code",getUser().getOrgCode()));
        if(count>0){
            return R.error("存在不属于本单位的教材，无法设置范围！");
        }
        SubjectResourceEntity subjectResourceEntity=new SubjectResourceEntity();
        subjectResourceEntity.setIsShow(Constant.RESOURCE_SHOW.NOT_SHOW);
        subjectResourceService.update(subjectResourceEntity,new EntityWrapper<SubjectResourceEntity>().in("id_",deleteForm.getIds()));
        return R.ok();
    }
    @SysLog("上架教材")
    @PostMapping("/showResource")
    @ApiOperation("教材上架")
    public R showResource(@RequestBody DeleteForm deleteForm){
        if(deleteForm==null||CollectionUtils.isEmpty(deleteForm.getIds())){
            return R.error("请选择一条记录！");
        }
        int count=subjectResourceService.selectCount(new EntityWrapper<SubjectResourceEntity>().in("id_",deleteForm.getIds()).ne("org_code",getUser().getOrgCode()));
        if(count>0){
            return R.error("存在不属于本单位的教材，无法设置范围！");
        }
        SubjectResourceEntity subjectResourceEntity=new SubjectResourceEntity();
        subjectResourceEntity.setIsShow(Constant.RESOURCE_SHOW.SHOW);
        subjectResourceService.update(subjectResourceEntity,new EntityWrapper<SubjectResourceEntity>().in("id_",deleteForm.getIds()));
        return R.ok();

    }

    @SysLog("设定显示范围")
    @PostMapping("/updateScope")
    @ApiOperation("设定显示范围")
    public R updateScope(@RequestBody UpdateScopeForm updateScopeForm){
        if(updateScopeForm.getResourceList()==null||updateScopeForm.getResourceList().size()<=0){
            return R.error("请选择改动的教材！");
        }
        List<String> ids=updateScopeForm.getResourceList().stream().map(SubjectResourceEntity::getId).collect(Collectors.toList());
        int count=subjectResourceService.selectCount(new EntityWrapper<SubjectResourceEntity>().in("id_",ids).ne("org_code",getUser().getOrgCode()));
        if(count>0){
            return R.error("存在不属于本单位的教材，无法设置范围！");
        }
        boolean b=subjectResourceService.updateScope(updateScopeForm);

       return R.ok();

    }
    @SysLog("本单位教材导出")
    //县级保存报订(根据教材)
    @GetMapping("/export")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("本单位教材导出")
    public void export(@RequestParam(required = false) Map<String, Object> params,HttpServletResponse response){
//     List<SubjectResourceEntity> list=subjectResourceService.selectList()
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
        if(sysOrgEntity!=null)
        {
            params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        }
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("isShow",1);
        List<SysOrgResourceTypeEntity> typeEntityList=sysOrgResourceTypeService.selectList(new EntityWrapper<SysOrgResourceTypeEntity>().eq("org_code",sysUserEntity.getOrgCode()));
        params.put("resourceTypeList",typeEntityList);
        List<SubjectResourceEntity> listAll = subjectResourceService.listAll(params);
        String fileName="教材导出"+DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        FileUtil.exportExcel(listAll,fileName,fileName, SubjectResourceEntity.class,fileName+".xls",response);
    }
    @Value("${fxfw.filePath}")
    private String filePath;
    @SysLog("导入教材")
    //县级保存报订(根据教材)
    @PostMapping("/importResource")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("本单位教材导出")
    public R importExcel(@RequestParam("excelFile") MultipartFile file, MultipartHttpServletRequest request){
        String fileName= null;
        String date= DateUtils.format(new Date(),"/yyyy/MM/dd/");
        try {
            List<SubjectResourceEntity> list= FileUtil.importExcel(file,1,1,SubjectResourceEntity.class);
            String time= DateUtils.format(new Date(),"yyyyMMddHHmmssSSS");
            list=subjectResourceService.importResource(list,getUser());
            fileName = "";
            date="book"+date;
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
            fileName=sysOrgEntity.getOrgName()+time+"教材导入结果.xls";
            //FileUtil.exportExcel(list,filePath+fileName,"报订规则导入结果",ZdMajorCourseResourceVO.class,fileName+".xls",response);
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(fileName,fileName),
                    SubjectResourceEntity.class, list);
            File savefile = new File(filePath+date);
            if (!savefile.exists()) {
                savefile.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(filePath+date+fileName);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("filePath",date+fileName);

    }

    @GetMapping("/listMajorCourse")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("根据教材获取专业课程列表")
    public R listMajorCourse(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgType",sysOrgEntity.getOrgType());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("orgCode",sysUserEntity.getOrgCode());
        PageUtils page = subjectResourceService.queryMajorCoursePage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/catalog")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("获得教材分类目录")
    public R catalog(@RequestParam Map<String, Object> params){
        List<SubjectResourceTypeEntity> catalogList=subjectResourceTypeService.selectList(new EntityWrapper<SubjectResourceTypeEntity>());
        return R.ok().put("list", catalogList);
    }
  /*  *//**
     * 列表
     *//*
    @GetMapping("/listByCourseId")
    //@RequiresPermissions("subject:subjectresource:list")
    @ApiOperation("根据课程获取教材")
    public R listByCourseId(@RequestParam Map<String, Object> params){
        PageUtils page = subjectResourceService.queryListPage(params);
        return R.ok().put("page", page);
    }
*/
}
