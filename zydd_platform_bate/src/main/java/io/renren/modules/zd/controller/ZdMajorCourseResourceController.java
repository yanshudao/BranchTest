package io.renren.modules.zd.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.*;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.subject.entity.SubjectCourseEntity;
import io.renren.modules.subject.entity.SubjectMajorEntity;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.service.SubjectCourseService;
import io.renren.modules.subject.service.SubjectMajorService;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdMajorCourseResourceEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.form.UpdateResourceVersionForm;
import io.renren.modules.zd.service.ZdCartService;
import io.renren.modules.zd.service.ZdMajorCourseResourceService;
import io.renren.modules.zd.service.ZdOrderResourceService;
import io.renren.modules.zd.service.ZdSourceService;
import io.renren.modules.zd.vo.OrderResourceVO;
import io.renren.modules.zd.vo.ZdCatVO;
import io.renren.modules.zd.vo.ZdMajorCourseResourceVO;
import io.renren.modules.zd.vo.ZdSourceDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


/**
 * 征订关系
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-10-22 11:01:03
 */
@RestController
@Api("征订关系接口")
@RequestMapping("zdRelation")
public class ZdMajorCourseResourceController extends AbstractController{
    @Resource
    private ZdMajorCourseResourceService zdMajorCourseResourceService;
    @Resource
    private SysOrgService sysOrgService;
    @Resource
    private SysSemesterService sysSemesterService;
    @Resource
    private SubjectResourceService subjectResourceService;
    @Resource
    private SubjectCourseService subjectCourseService;
    @Resource
    private SubjectMajorService subjectMajorService;
    @Resource
    private ZdOrderResourceService zdOrderResourceService;
    @Value("${fxfw.filePath}")
    private String filePath;
    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("sys:zdmajorcourseresource:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity=getUser();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        Object semester=params.get("semesterCode");
        if(semester==null||"".equals(semester.toString()))
        {
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        }
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        if(!"0".equals(sysUserEntity.getOrgType())){
            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("parentOrgCode",sysOrgEntity.getParentCodes());
        }

        PageUtils page = zdMajorCourseResourceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    @SysLog("报订规则保存")
//    @RequiresPermissions("sys:zdmajorcourseresource:save")
    @ApiOperation("保存")
    public R save(@RequestBody ZdMajorCourseResourceEntity zdMajorCourseResource){
        ValidatorUtils.validateEntity(zdMajorCourseResource);
        SysUserEntity user=getUser();
        SubjectResourceEntity resourceEntity=subjectResourceService.selectByCode(zdMajorCourseResource.getResourceCode());
        if(resourceEntity==null)
        {
            return R.error("教材未找到");
        }
        SubjectCourseEntity courseEntity=subjectCourseService.selectByCode(zdMajorCourseResource.getCourseCode());
        if(courseEntity==null)
        {
            return R.error("课程未找到");
        }
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(user.getOrgCode());
        SubjectMajorEntity subjectMajorEntity=subjectMajorService.selectByCode(zdMajorCourseResource.getMajorCode(),zdMajorCourseResource.getStudentType(),zdMajorCourseResource.getMajorType(),sysOrgEntity.getOrgCode(),sysOrgEntity.getParentCodes());
        if(subjectMajorEntity==null)
        {
            return R.error("专业未找到");
        }
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        zdMajorCourseResource.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdMajorCourseResource.setOrgCode(getUser().getOrgCode());
        if(!"0".equals(user.getOrgType())){
            int count=zdMajorCourseResourceService.selectCount(new EntityWrapper<ZdMajorCourseResourceEntity>().eq("semester_code",sysSemesterEntity.getSemesterCode()).eq("org_code","ZYDD")
                    .eq("major_code",zdMajorCourseResource.getMajorCode()).eq("student_type",zdMajorCourseResource.getStudentType()).eq("course_code",zdMajorCourseResource.getCourseCode())
                    .eq("resource_code",zdMajorCourseResource.getResourceCode()));
            if(count>0){
                return R.error("规则已在中央维护");
            }
        }
        ZdMajorCourseResourceEntity zdMajorCourseResourceEntity=zdMajorCourseResourceService.selectByUk(zdMajorCourseResource.getMajorCode(),zdMajorCourseResource.getMajorType(),zdMajorCourseResource.getStudentType(),
                zdMajorCourseResource.getSubjectType(),zdMajorCourseResource.getCourseCode(),
                zdMajorCourseResource.getResourceCode(),user.getOrgCode(),sysSemesterEntity.getSemesterCode());
        if(zdMajorCourseResourceEntity==null){
            return R.error("规则已存在！");
        }
        zdMajorCourseResourceService.insert(zdMajorCourseResource);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @SysLog("报订规则更新")
//    @RequiresPermissions("sys:zdmajorcourseresource:update")
    @ApiOperation("更新")
    public R update(@RequestBody ZdMajorCourseResourceEntity zdMajorCourseResource){
        ValidatorUtils.validateEntity(zdMajorCourseResource);
        SysUserEntity user=getUser();
        SubjectResourceEntity resourceEntity=subjectResourceService.selectByCode(zdMajorCourseResource.getResourceCode());
        if(resourceEntity==null)
        {
            return R.error("教材未找到");
        }
        SubjectCourseEntity courseEntity=subjectCourseService.selectByCode(zdMajorCourseResource.getCourseCode());
        if(courseEntity==null)
        {
            return R.error("课程未找到");
        }
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(user.getOrgCode());
        SubjectMajorEntity subjectMajorEntity=subjectMajorService.selectByCode(zdMajorCourseResource.getMajorCode(),zdMajorCourseResource.getStudentType(),zdMajorCourseResource.getMajorType(),sysOrgEntity.getOrgCode(),sysOrgEntity.getParentCodes());
        if(subjectMajorEntity==null)
        {
            return R.error("专业未找到");
        }
        ZdMajorCourseResourceEntity zdMajorCourseResourceEntity=zdMajorCourseResourceService.selectById(zdMajorCourseResource.getId());
        if(zdMajorCourseResourceEntity==null)
        {
            return R.error("关系未找到！");
        }
        if(!zdMajorCourseResourceEntity.getOrgCode().equals(getUser().getOrgCode()))
        {
            return R.error("只允许维护单位维护！");
        }
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        if(!"0".equals(user.getOrgType())){
            int count=zdMajorCourseResourceService
                    .selectCount(new EntityWrapper<ZdMajorCourseResourceEntity>().eq("semester_code",sysSemesterEntity.getSemesterCode()).eq("org_code","ZYDD")
                    .eq("major_code",zdMajorCourseResource.getMajorCode()).eq("major_type",zdMajorCourseResource.getMajorType()).eq("student_type",zdMajorCourseResource.getStudentType()).eq("course_code",zdMajorCourseResource.getCourseCode())
                    .eq("resource_code",zdMajorCourseResource.getResourceCode()));
            if(count>0){
                return R.error("规则已在中央维护");
            }
        }
        if(StringUtils.isBlank(zdMajorCourseResource.getId())){
            ZdMajorCourseResourceEntity entity=zdMajorCourseResourceService.selectByUk(zdMajorCourseResource.getMajorCode(),zdMajorCourseResource.getMajorType(),zdMajorCourseResource.getStudentType(),
                    zdMajorCourseResource.getSubjectType(),zdMajorCourseResource.getCourseCode(),
                    zdMajorCourseResource.getResourceCode(),user.getOrgCode(),sysSemesterEntity.getSemesterCode());
            if(entity!=null){
                return R.error("规则已存在！");
            }
        }
        zdMajorCourseResourceService.updateById(zdMajorCourseResource);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @SysLog("报订规则删除")
//    @RequiresPermissions("sys:zdmajorcourseresource:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm){
        Map<String,Object> map=new HashMap<>();
        map.put("ids",deleteForm.getIds());
        int orderCount=zdOrderResourceService.countZMCRIds(deleteForm.getIds());
        if(orderCount>0)
        {
            return R.error("有报订不允许删除！");
        }
        map.put("notEqOrgCode",getUser().getOrgCode());
        int count=zdMajorCourseResourceService.countByMap(map);
        if(count>0)
        {
            return R.error("不允许非维护单位删除！");
        }


		zdMajorCourseResourceService.deleteBatchIds(deleteForm.getIds());
        return R.ok();
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("zd:zdorgmajor:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
        ZdMajorCourseResourceEntity zdOrgMajor = zdMajorCourseResourceService.selectById(id);

        return R.ok().put("zdMajorCourseResource", zdOrgMajor);
    }

    @SysLog("导入报订规则")
    //县级保存报订(根据教材)
    @PostMapping("/importZMCR")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("导入报订规则")
    public R importZMCR(@RequestParam("excelFile") MultipartFile file, HttpServletResponse response){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        String fileName= null;
        String date= DateUtils.format(new Date(),"/yyyy/MM/dd/");
        try {
            List<ZdMajorCourseResourceVO> list= FileUtil.importExcel(file,1,1,ZdMajorCourseResourceVO.class,new ExcelDictHandlerImpl());

            String time= DateUtils.format(new Date(),"yyyyMMddHHmmssSSS");
            list=zdMajorCourseResourceService.importZMCR(list,getUser(),sysSemesterEntity.getSemesterCode());
            fileName = "";
            date="rule"+date;
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
            fileName=sysOrgEntity.getOrgName()+time+"报订规则导入结果.xls";
            //FileUtil.exportExcel(list,filePath+fileName,"报订规则导入结果",ZdMajorCourseResourceVO.class,fileName+".xls",response);
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(fileName,fileName),
                    ZdMajorCourseResourceVO.class, list);
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
    @SysLog("导出报订规则")
    //县级保存报订(根据教材)
    @GetMapping("/exportZMCR")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("导出报订规则")
    public void exportZMCR(@RequestParam Map<String, Object> params, HttpServletResponse response){
       SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
        String semesterCode=params.get("semesterCode")==null?"":params.get("semesterCode").toString();
        if(StringUtils.isBlank(semesterCode)) {
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        }
        if(!"zydd".equals(getUser().getOrgCode())) {
            params.put("orgCode",getUser().getOrgCode());
            params.put("parentOrgCode",sysOrgEntity.getParentCodes());
        }
        params.put("isArchives", Constant.ZMCR_ARCHIVES.NOT_ARCHIVES);
        List<ZdMajorCourseResourceVO> list=zdMajorCourseResourceService.queryAll(params);
        String fileName="";
        fileName=sysOrgEntity.getOrgName()+"报订规则导出";
        FileUtil.exportExcel(list,fileName,fileName,ZdMajorCourseResourceVO.class,fileName+".xls",response);
    }

    //提取报订规则
    /**
     * 列表
     */
    @PostMapping("/listAll")
//    @RequiresPermissions("sys:zdmajorcourseresource:list")
    @ApiOperation("获得所有规则")
    public R listAll(@RequestBody Map<String, Object> params){
        String resourceCode= MapUtils.getString(params,"resourceCode");
        if(StringUtils.isBlank(resourceCode)){
            return R.error("请输入教材代码");
        }
        SysUserEntity sysUserEntity=getUser();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
         params.put("eqResourceCode",params.get("resourceCode"));
         params.put("semesterCode",sysSemesterEntity.getSemesterCode());
//        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());

        params.put("ownOrgCode",sysUserEntity.getOrgCode());
//        params.put("parentOrgCode",sysOrgEntity.getParentCodes());
        List page = zdMajorCourseResourceService.queryAll(params);

        return R.ok().put("list", page);
    }
    //获得报订规则购物车、获得报订单、获得采购单
    @Autowired
    private ZdCartService zdCartService;
    @Autowired
    private ZdSourceService zdSourceService;
    @PostMapping("/cart/list")
    @ApiOperation("根据报订规则获得购物车")
    public R listCart(@RequestBody SearchForm searchForm){

        List<ZdCatVO> list=zdCartService.listAll(searchForm);
        return R.ok().put("list", list);
    }
    @PostMapping("/order/list")
    @ApiOperation("根据报订规则相关的报订单")
    public R listOrder(@RequestBody SearchForm searchForm){
        List<OrderResourceVO> list=zdOrderResourceService.listAll(searchForm);
        return R.ok().put("list", list);
    }

    @PostMapping("/source/list")
    @ApiOperation("根据报订规则相关的采购单")
    public R listSource(@RequestBody SearchForm searchForm){
        searchForm.setIsSync("0");
        List<ZdSourceDetailVO> list=zdSourceService.listResourceAll(searchForm);
        return R.ok().put("list", list);
    }
    @PostMapping("/province/updateVersion")
    @ApiOperation("省级教材换版")
    @SysLog("省级教材换版")
    public R provinceUpdateVersion(@RequestBody UpdateResourceVersionForm resourceVersionForm){
        SubjectResourceEntity subjectResourceEntity=subjectResourceService.selectByCode(resourceVersionForm.getResourceCode());
        if(subjectResourceEntity==null){
            return R.error("教材不存在！");
        }

        for(String zmcrId:resourceVersionForm.getZmcrList()){
            ZdMajorCourseResourceEntity zdMajorCourseResourceEntity=zdMajorCourseResourceService.selectById(zmcrId);
            if(zdMajorCourseResourceEntity.getResourceCode().equals(subjectResourceEntity.getResourceCode())){
                return R.error("原教材与新教材不能一致！");
            }
        }
       resourceVersionForm.setOrgList(Arrays.asList(getUser().getOrgCode()));
        zdMajorCourseResourceService.updateVersion(resourceVersionForm);
        return R.ok();
    }

    @PostMapping("updateVersion")
    @ApiOperation("教材换版")
    @SysLog("教材换版")
    public R updateVersion(@RequestBody UpdateResourceVersionForm resourceVersionForm){
        SubjectResourceEntity subjectResourceEntity=subjectResourceService.selectByCode(resourceVersionForm.getResourceCode());
        if(subjectResourceEntity==null){
            return R.error("教材不存在！");
        }
        for(String zmcrId:resourceVersionForm.getZmcrList()){
            ZdMajorCourseResourceEntity zdMajorCourseResourceEntity=zdMajorCourseResourceService.selectById(zmcrId);
            if(zdMajorCourseResourceEntity.getResourceCode().equals(subjectResourceEntity.getResourceCode())){
                return R.error("原教材与新教材不能一致！");
            }
        }

        zdMajorCourseResourceService.updateVersion(resourceVersionForm);
        return R.ok();
    }

    @PostMapping("syncCourse")
    @ApiOperation("同步报订规则的开设信息")
    @SysLog("同步报订规则的开设信息")
    public R syncCourse(@RequestBody DeleteForm deleteForm){
        int count=zdMajorCourseResourceService.syncCourse(deleteForm.getIds());
        return R.ok("同步成功！");
    }


}
