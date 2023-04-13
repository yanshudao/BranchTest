package io.renren.modules.zd.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.*;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.search.vo.StatisticsSourceResourceDetailVO;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgResourceTypeEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgResourceTypeService;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdSourceEntity;
import io.renren.modules.zd.form.ZdOrgSourceForm;
import io.renren.modules.zd.form.ZdSourceForm;
import io.renren.modules.zd.form.ZdSourceResourceForm;
import io.renren.modules.zd.service.ZdSourceService;
import io.renren.modules.zd.vo.ZdOrderOrgVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;


/**
 * 采购主表
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@RestController
@Api("采购主表接口")
@RequestMapping("zd/zdsource")
public class ZdSourceController {
    private static Logger logger = LoggerFactory.getLogger(ZdSourceController.class);

    private int count=0;
    @Autowired
    private ZdSourceService zdSourceService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private SubjectResourceService subjectResourceService;
    @Autowired
    private SysOrgResourceTypeService sysOrgResourceTypeService;




    @GetMapping("/listOrderOrg")
    @ApiOperation("获取单位一件采购列表")
    public R listOrderOrg(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity = sysSemesterService.getCurrentSemester();
        params.put("orgCode", sysUserEntity.getOrgCode());
        params.put("semesterCode", sysSemesterEntity.getSemesterCode());
        List<ZdOrderOrgVO> page = sysOrgService.listOrderOrg(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("获取采购单据")
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
//        SysOrgEntity sysOrgEntity = sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("parentOrgCode", sysUserEntity.getOrgCode());
//        params.put("parentCodes",sysOrgEntity.getParentCodes());
//        params.put("notEqStatus",Constant.SOURCE_STATUS.NEW);
        PageUtils page = zdSourceService.queryListPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/listDetail")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("获取采购单据详情列表 传参sourceId")
    public R listDetail(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        params.put("orgCode", sysUserEntity.getOrgCode());
        PageUtils page = zdSourceService.queryResourceListPage(params);
        return R.ok().put("page", page);
    }

    @SysLog("审核采购单")
    @PostMapping("/completeOrder")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("审核采购单")
    public R completeOrder(@RequestBody DeleteForm deleteForm) {
        ValidatorUtils.validateEntity(deleteForm);
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        zdSourceService.completeOrder(deleteForm.getIds());
        return R.ok();
    }

    @SysLog("同步采购单")
    @PostMapping("/syncOrder")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("同步采购单")
    public R syncOrder(@RequestBody ZdSourceEntity zdSourceEntity) {
        synchronized (zdSourceEntity){
        // ValidatorUtils.validateEntity(id);
        //   SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        ZdSourceEntity s = zdSourceService.selectById(zdSourceEntity.getId());
        if ("1".equals(s.getIsSync())) {
            return R.error("已同步过的单据无法再次同步");
        }
        zdSourceService.updateAddress(zdSourceEntity);
//        s.setNote(zdSourceEntity.getNote());
//        s.setRemarks(zdSourceEntity.getRemarks());
//        s.setAddress(zdSourceEntity.getAddress());
//        s.setLinkman(zdSourceEntity.getLinkman());
//        s.setTelephone(zdSourceEntity.getTelephone());
//        s.setZipCode(zdSourceEntity.getZipCode());
//        zdSourceService.updateById(s);
            String msg = zdSourceService.syncOrder(zdSourceEntity.getId());
            return R.ok(msg);
        }
    }
    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("zd:zdsource:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id) {
        ZdSourceEntity zdSource = zdSourceService.selectById(id);

        return R.ok().put("zdSource", zdSource);
    }

    /*    *//**
     * 保存
     *//*
    @PostMapping("/save")
    //@RequiresPermissions("zd:zdsource:save")
    @ApiOperation("保存")
    public R save(@RequestBody ZdSourceEntity zdSource){
			zdSourceService.insert(zdSource);

        return R.ok();
    }

    *//**
     * 修改
     *//*
    @PostMapping("/update")
    //@RequiresPermissions("zd:zdsource:update")
    @ApiOperation("更新")
    public R update(@RequestBody ZdSourceEntity zdSource){
			zdSourceService.updateById(zdSource);

        return R.ok();
    }*/

    /**
     * 删除
     */
    @SysLog("删除采购单")
    @PostMapping("/delete")
    //@RequiresPermissions("zd:zdsource:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm) {
        if (deleteForm.getIds().size() == 0) {
            return R.error("请选择需要删除的采购单");
        }
        Map map = new HashMap();
        map.put("isSync", "1");
        map.put("ids", deleteForm.getIds());
        int count = zdSourceService.countByMap(map);
        if (count > 0) {
            return R.error("状态不允许删除！");
        }
        zdSourceService.deleteSource(deleteForm.getIds());
        return R.ok();
    }

    @GetMapping("/listOrderResource")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("获取采购教材列表(只看报订)")
    public R listOrderResource(@RequestParam Map<String, Object> params) {
        //SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        //  params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        //    SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode", sysUserEntity.getOrgCode());
        SysOrgEntity sysOrgEntity = sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
        params.put("parentOrgCode", sysOrgEntity.getOrgCode());
        // params.put("parentCodes",sysOrgEntity.getParentCodes());
        PageUtils page = subjectResourceService.queryZdResourceByMap(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/listResource")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("获取采购教材列表(全部)")
    public R listResource(@RequestParam Map<String, Object> params) {
        SysSemesterEntity sysSemesterEntity = sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        if(params.get("supplier")!=null){
            params.put("supplierId", params.get("supplier"));
        }
        if(sysSemesterEntity==null){
            return R.error("请设置当前报订季");
        }
        params.put("semesterCode", sysSemesterEntity.getSemesterCode());
        //    SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode", sysUserEntity.getOrgCode());
        params.put("isShow", Constant.RESOURCE_SHOW.SHOW);
        SysOrgEntity sysOrgEntity = sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("parentCodes", sysOrgEntity.getParentCodes());
        //查找当前单位的教材类型
        List<SysOrgResourceTypeEntity> typeEntityList=sysOrgResourceTypeService.selectList(new EntityWrapper<SysOrgResourceTypeEntity>().eq("org_code",sysUserEntity.getOrgCode()));
        params.put("resourceTypeList",typeEntityList);
        // params.put("parentCodes",sysOrgEntity.getParentCodes());
        PageUtils page = subjectResourceService.queryAllResourceByMap(params);
        return R.ok().put("page", page);
    }

    @SysLog("保存采购单(省)")
    @PostMapping("/saveSource")
    //@RequiresPermissions("zd:zdsource:save")
    @ApiOperation("保存采购")
    public R saveSource(@RequestBody ZdSourceForm zdSource) {
        ValidatorUtils.validateEntity(zdSource);
        zdSource.setSemesterCode(sysSemesterService.getCurrentSemester().getSemesterCode());
        /*if(1==1)
        {
            return R.error("日常采购功能关闭，请联系系统管理员");
        }*/
        zdSource.setStatus(Constant.SOURCE_STATUS.SUBMIT);
        zdSourceService.saveSource(zdSource);
        return R.ok();
    }

    @SysLog("更新采购详情单")
    @PostMapping("/saveSourceResource")
    //@RequiresPermissions("zd:zdsource:save")
    @ApiOperation("保存采购详情单")
    public R saveSourceResource(@RequestBody List<ZdSourceResourceForm> zdSourceResourceForms) {
        ValidatorUtils.validateEntity(zdSourceResourceForms);
        //zdSource.setSemesterCode(sysSemesterService.getCurrentSemester().getSemesterCode());
        zdSourceService.saveSourceResource(zdSourceResourceForms);
        return R.ok();
    }

    @SysLog("删除采购详情单")
    @PostMapping("/deleteSourceResource")
    //@RequiresPermissions("zd:zdsource:save")
    @ApiOperation("删除采购详情单")
    public R deleteSourceResource(@RequestBody DeleteForm deleteForm) {
        ValidatorUtils.validateEntity(deleteForm);
        //zdSource.setSemesterCode(sysSemesterService.getCurrentSemester().getSemesterCode());
        zdSourceService.deleteSourceResource(deleteForm.getIds());
        return R.ok();
    }


    @GetMapping("/orgListResource")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("按单位 获取当前单位下的教材 orgCode,supplierId")
    public R orgListResource(@RequestParam Map<String, Object> params) {
        String orgCode = (String) params.get("orgCode");
        SysSemesterEntity sysSemesterEntity = sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        params.put("semesterCode", sysSemesterEntity.getSemesterCode());
        //    SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        // params.put("orgCode",sysUserEntity.getOrgCode());
        SysOrgEntity sysOrgEntity = sysOrgService.selectByOrgCode(orgCode);
        // params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        params.put("parentCodes", sysOrgEntity.getParentCodes());
        PageUtils page = subjectResourceService.queryOrgResourceByMap(params);
        return R.ok().put("page", page);
    }

    @SysLog("按单位保存报订的教材")
    @PostMapping("/saveSourceByOrg")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("按单位 保存报订的教材 ")
    public R saveSourceByOrg(@RequestBody ZdOrgSourceForm zdOrgSourceForm) {
        SysSemesterEntity sysSemesterEntity = sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        zdOrgSourceForm.setSemesterCode(sysSemesterEntity.getSemesterCode());
        //  params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        //    SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        // params.put("orgCode",sysUserEntity.getOrgCode());
        // SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
        //  params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        // params.put("parentCodes",sysOrgEntity.getParentCodes());
        zdOrgSourceForm.setStatus(Constant.SOURCE_STATUS.SUBMIT);
        zdSourceService.saveSourceByOrg(zdOrgSourceForm);
        return R.ok();
    }

    @GetMapping("/listDetailIncome")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("查询可以入库的教材 传参supplierId 供应商")
    public R listDetailIncome(@RequestParam Map<String, Object> params) {
        String supplierId = (String) params.get("supplierId");
        if (StringUtils.isBlank(supplierId)) {
            return R.error("请选择采购商");
        }
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        SysOrgEntity orgEntity = sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("orgCode", sysUserEntity.getOrgCode());
        params.put("parentCodes", orgEntity.getParentCodes());
        PageUtils page = zdSourceService.queryResourceIncomePage(params);
        return R.ok().put("page", page);
    }

    /* @SysLog("本季采购单导出")
     //县级保存报订(根据教材)
     @GetMapping("/exportSource")
     //@RequiresPermissions("zd:zdorder:save")
     @ApiOperation("本季采购单导出")
     public void exportOrder(@RequestParam(value = "sourceId",required = false)String sourceId,HttpServletResponse response){
         // 当前日期，用于导出文件名称
 //        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
 //        String dateStr = sdf.format(new Date());
         // 指定下载的文件名--设置响应头
         Map<String,Object> params=new HashMap<>();
         SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
         SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
         SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
         String fileName=sysOrgEntity.getOrgName()+sysSemesterEntity.getSemesterCode();
         if(StringUtils.isNotBlank(sourceId))
         {
             params.put("sourceId",sourceId);
             params.put("orgCode",sysUserEntity.getOrgCode());

         }else
         {
             params.put("orgCode",sysUserEntity.getOrgCode());
             params.put("semesterCode",sysSemesterEntity.getSemesterCode());
         }
         List<StatisticsSourceResourceVO> list = zdSourceService.queryStatisticsAllByMap(params);
         FileUtil.exportExcel(list,fileName,fileName,StatisticsSourceResourceVO.class,fileName+".xls",response);
     }
 */
    @SysLog("本季采购单导出")
    //县级保存报订(根据教材)
    @GetMapping("/exportSource")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("本季采购单导出")
    public void exportSourceDetail(@RequestParam(value = "ids", required = false) String sourceId, HttpServletResponse response) {
        // 当前日期，用于导出文件名称
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String dateStr = sdf.format(new Date());
        // 指定下载的文件名--设置响应头
        Map<String, Object> params = new HashMap<>();
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();

        SysOrgEntity sysOrgEntity = sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        String fileName = sysOrgEntity.getOrgName() + System.currentTimeMillis();
        if (StringUtils.isNotBlank(sourceId)) {
            params.put("sourceIds", Arrays.asList(sourceId.split(",")));

        } else {
            if (Constant.ORG_TYPE.SHENG.equals(sysUserEntity.getOrgType())) {
                params.put("orgCode", sysUserEntity.getOrgCode());
            } else {
                params.put("toOrgCode", sysUserEntity.getOrgCode());
            }
            SysSemesterEntity sysSemesterEntity = sysSemesterService.getCurrentSemester();
//            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("semesterCode", sysSemesterEntity.getSemesterCode());
        }
        List<StatisticsSourceResourceDetailVO> list = zdSourceService.queryStatisticsDetailByMap(params);
        FileUtil.exportExcel(list, fileName, fileName, StatisticsSourceResourceDetailVO.class, fileName + ".xls", response);
      /*  Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("报订单据","报订单据"),
                StatisticsResourceVO.class, list);*/
     /*   try {
            response.setHeader("Content-Disposition", "attachment;filename=" +dateStr+".xls");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
/*
        StudentHiderEntity studentEntity = new StudentHiderEntity();
        studentEntity.setId("11231");
        studentEntity.setName("撒旦法司法局");
        studentEntity.setBirthday(new Date());
        studentEntity.setRegistrationDate(new Date());
        studentEntity.setSex(1);
        List<StudentHiderEntity> studentList = new ArrayList<StudentHiderEntity>();
        studentList.add(studentEntity);
        studentList.add(studentEntity);*/


       /* System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/ExcelExportHideCol.xls");
        workbook.write(fos);
        fos.close();*/
      /*  SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("orderId",orderId);
        PageUtils page = zdOrderResourceService.queryListPage(params);
        return R.ok().put("page", page);*/
    }


    @GetMapping("/v2/orgListResource")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("按单位 获取当前单位下的教材 orgCode,supplierId")
    public R v2OrgListResource(@RequestParam Map<String, Object> params) {
        String orgCode = (String) params.get("orgCode");
        SysSemesterEntity sysSemesterEntity = sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        params.put("semesterCode", sysSemesterEntity.getSemesterCode());
        //    SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        // params.put("orgCode",sysUserEntity.getOrgCode());
        SysOrgEntity sysOrgEntity = sysOrgService.selectByOrgCode(orgCode);
        // params.put("parentOrgCode",sysOrgEntity.getOrgCode());
        params.put("parentCodes", sysOrgEntity.getParentCodes());
        PageUtils page = subjectResourceService.queryOrgResourceByMap1(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @GetMapping("/county/list")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("获取上报的采购单")
    public R countyList(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        params.put("toOrgCode", sysUserEntity.getOrgCode());
//        List childList=sysOrgService.selectChildren(queryMap);
//        params.put("orgList",childList);
        PageUtils page = zdSourceService.queryListPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/county/listDetail")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("获取上报的采购详情单")
    public R countyListDetail(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        params.put("toOrgCode", sysUserEntity.getOrgCode());
//        List childList=sysOrgService.selectChildren(queryMap);
//        params.put("orgList",childList);
        PageUtils page = zdSourceService.queryResourceListPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @PostMapping("/county/submit")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("上报采购单")
    @SysLog("上报采购单")
    public R countySubmit(@RequestBody ZdSourceEntity zdSourceEntity) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        ZdSourceEntity source = zdSourceService.selectById(zdSourceEntity.getId());
        if (source != null) {
            source.setSubmitDate(new Date());
            source.setSubmitUser(sysUserEntity.getUserId() + "");
            source.setStatus(Constant.SOURCE_STATUS.SUBMIT);
        }
        zdSourceService.updateById(source);
        return R.ok();
    }

    /**
     * 审核
     */
    @PostMapping("/audit")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("审核采购单")
    @SysLog("审核采购单")
    public R audit(@Valid @RequestBody DeleteForm deleteForm) {
        ZdSourceEntity zdSourceEntity = new ZdSourceEntity();
        zdSourceEntity.setStatus(Constant.SOURCE_STATUS.COMPLETE);
        zdSourceService.update(zdSourceEntity, new EntityWrapper<ZdSourceEntity>().in("id_", deleteForm.getIds()));

        return R.ok();
    }

    /**
     * 审核
     */
    @PostMapping("/reject")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("审核采购单")
    @SysLog("审核采购单")
    public R reject(@Valid @RequestBody ZdSourceEntity zdSourceEntity) {
        ZdSourceEntity update = zdSourceService.selectById(zdSourceEntity.getId());
        if (update == null) {
            return R.error("未找到单据");
        }
        if (Constant.SOURCE_STATUS.COMPLETE.equals(update.getStatus())) {
            return R.error("已通过的不允许拒绝");
        }
        update.setStatus(Constant.SOURCE_STATUS.REJECT);
        zdSourceService.updateById(update);
        return R.ok();
    }


}
