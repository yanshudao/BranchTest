package io.renren.modules.zd.controller.v2;

import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.*;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdRefundEntity;
import io.renren.modules.zd.entity.ZdRefundResourceDetailEntity;
import io.renren.modules.zd.form.CountyRefundSubmitFrom;
import io.renren.modules.zd.form.ZdRefundCreateOrderFrom;
import io.renren.modules.zd.form.ZdRefundForm;
import io.renren.modules.zd.service.ZdRefundCountryService;
import io.renren.modules.zd.service.ZdRefundProvinceService;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.RefundResourceVo;
import io.renren.modules.zd.vo.ZdCountyRefundResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("zdRefundCountyControllerV2")
@RequestMapping("/v2/county/refund")
@Api("县级退货单接口")
public class ZdRefundCountyController extends AbstractController {

    @Resource
    private ZdRefundCountryService zdRefundCountryService;
    @Resource
    private ZdRefundProvinceService zdRefundProvinceService;

    @Resource
    private SysSemesterService sysSemesterService;

    @Resource
    private SysOrgService sysOrgService;
    @Resource
    private SysOrgSettingService sysOrgSettingService;

    /**
     * 退货上报
     * @param refundId
     * @return
     */
    @PostMapping("/confirmRefund")
    @SysLog("县级上报退货单")
    public R confirmRefund(@RequestBody CountyRefundSubmitFrom updateForm){
        SysUserEntity sysUserEntity=getUser();
        zdRefundCountryService.confirmRefund(updateForm,sysUserEntity);
        return R.ok("上报成功");
    }
    /**
     * 退货上报
     * @param refundId
     * @return
     */
    @PostMapping("/confirmAddress")
    @SysLog("县级输入物流信息并上报")
    public R confirmAddress(@RequestBody ZdRefundEntity zdRefundEntity){
        zdRefundCountryService.confirmAddress(zdRefundEntity);
        return R.ok("上报成功");
    }

    /**
     * 获取主单据详情
     * @param id
     * @return
     */
    @GetMapping("/getRefundInfo")
    public R getRefundInfo(@RequestParam("id")String id){
        ZdRefundEntity zdRefundEntity=zdRefundCountryService.selectById(id);
        return R.ok().put("data",zdRefundEntity);
    }


    /**
     * 生成退货单据
     *
     * @param req
     * @return
     */
    @SysLog("县级新增退货单")
    @PostMapping("/new-refund-order")
    public R saveRefundOrder(@RequestBody ZdRefundCreateOrderFrom req) {
        req.setFromOrgCode(ShiroUtils.getUserEntity().getOrgCode());
//        req.setStatus(Constant.REFUND_STATUS.NEW);

        int res = zdRefundCountryService.insertRefundOrder(req);
        if (res == 1) {
            return R.ok("生成退货单据成功。");
        } else {
            return R.error("生成退货单据失败。");
        }
    }


    ///////////////////////////////////////
    /**
     * 获取退货地址信息
     *
     * @return
     */
    @GetMapping("/refund-org-info")
    @ApiOperation("获取退货地址信息")
    public R getRefundOrgInfo(@RequestParam(required = false) String orgCode) {
        if(StringUtils.isBlank(orgCode)){
            SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
            SysOrgSettingEntity settingEntity=sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
            SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(settingEntity.getToOrgCode());
            return R.ok().put("data", sysOrgSettingEntity);
        }else{
            SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(orgCode);
            SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(sysOrgEntity.getOrgCode());
            return R.ok().put("data", sysOrgSettingEntity);
        }
      /*  ZdRefundOrgInfoEntity res;
        if(orgCode!=null){
            res = zdRefundCountryService.getRefundOrgInfoMap(orgCode);
        }else{

            res = zdRefundCountryService.getRefundOrgInfoMap(sysUserEntity.getOrgCode());
        }*/

    }

    /**
     * 查询可退货教材列表
     *
     * @param params
     * 请求参数中，必填:userId;选填:resourceCode,resourceType,isRefundable
     * @return
     */
    @GetMapping("/refundable-resources")
    @ApiOperation("查询可退货教材列表")
    public R listResourcesRefundable(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
//        params.put("isRefund","1");
//        params.put("orgCode",sysOrgEntity.getOrgCode());
//        params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
//        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();
//        String semesterCode= MapUtils.getString(params,"semesterCode");
        params.put("userId", sysUserEntity.getUserId().toString());
//        params.put("semesterList", Arrays.asList(semesterCode));
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("toOrgCode",sysOrgSettingEntity.getToOrgCode());
        PageUtils page = zdRefundCountryService.listResourcesRefundable(params);
        return R.ok().put("page", page);
    }


    @GetMapping("/refund-order")
    public R listRefundOrderList(@RequestParam Map<String,Object> params){
        params.put("fromOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils resPage = zdRefundCountryService.queryRefundOrderList(params);
        if(resPage.getList().size() >0){
            return R.ok().put("page", resPage);
        }else{
            return R.ok("未找到退货单据").put("page", resPage);
        }
    }

    @GetMapping("/refund-resources")
    public R listRefundResourceList(@RequestParam Map<String,Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("fromOrgCode",sysUserEntity.getOrgCode());
        if(params.get("semesterCode")==null)
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }
        PageUtils resPage = zdRefundCountryService.queryRefundResourcesList(params);
        if(resPage.getList().size() >0){
            return R.ok().put("page", resPage);
        }else{
            return R.ok("未找到退货教材").put("page", resPage);
        }
    }
    @GetMapping("/exportRefundResource")
    public void exportRefundResource(@RequestParam Map<String,Object> params,HttpServletResponse response){
        String semesterCode= StringUtils.isBlank(params.get("semesterCode").toString())?sysSemesterService.getCurrentSemester().getSemesterCode():params.get("semesterCode").toString();
        String fileName="";
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getSemesterByCode(semesterCode);
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("fromOrgCode",sysUserEntity.getOrgCode());
        List<ZdRefundResourceDetailEntity> list = zdRefundCountryService.queryAllRefundResourcesList(params);
        fileName=sysOrgEntity.getOrgName()+sysSemesterEntity.getSemesterCode()+"";
        FileUtil.exportExcel(list,fileName,fileName,ZdRefundResourceDetailEntity.class,fileName+".xls",response);

    }
   /* @PostMapping("/report-refund-order")
    public R reportRefundOrder(String id){
        if(zdRefundCountryService.reportRefundOrder(id) >0){
            return R.ok("上报退货单据成功");
        }else{
            return R.ok("上报退货单据失败");
        }
    }*/

    @GetMapping("/refund-agency-list/{orgCode}")
    public R listRefundResourceByOrgCode(@PathVariable("orgCode") String orgCode, @RequestParam String page){
        String userId = zdRefundCountryService.getUserIdByOrgCode(orgCode);
        Map params = new HashMap();
        params.put("page",page);
        params.put("userId", userId);
        PageUtils _page = zdRefundCountryService.listResourcesRefundable(params);
        return R.ok().put("page", _page);
    }

    /**
     * 根据退货单据号查询相关联的教材
     * @param refundId
     * @return
     */
    @GetMapping("/{refundId}/refund-resource")
    public R listRefundResourcesByRefundId(@PathVariable("refundId") String refundId){
        List<ZdCountyRefundResourceVO> list = zdRefundCountryService.listResourceByRefundId(refundId);
        if(list.size()>0){
            return R.ok().put("resourceList", list);
        }else{
            return R.ok("未找到对应的教材");
        }
    }


    @SysLog("修改退货详情单")
    @PostMapping("/saveRefundResource")
    @ApiOperation("修改退货详情单")
    public R saveRefundResource(@RequestBody ZdRefundForm zdRefundForm){
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        zdRefundForm.setOrgCode(sysUserEntity.getOrgCode());
        int res = zdRefundCountryService.saveRefundResource(zdRefundForm);
        if(res > 0){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }
    @SysLog("删除退货详情单")
    @PostMapping("/deleteRefundResource")
    @ApiOperation("删除退货详情单")
    public R deleteRefundResource(String id){
        int res = zdRefundCountryService.deleteRefundResource(id);
        if(res > 0){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }
    @SysLog("删除退货单")
    @PostMapping("/deleteRefund")
    @ApiOperation("删除退货单")
    public R deleteRefund(@RequestBody DeleteForm deleteForm){
        int res = zdRefundCountryService.deleteRefund(deleteForm.getIds());
        if(res > 0){
            return R.ok("删除成功");
        }else{
            return R.ok("删除失败");
        }
    }
    @SysLog("县级退货单导出")
    //县级保存报订(根据教材)
    @GetMapping("/exportRefund")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("县级退货单导出")
    public void exportOrder(@RequestParam(name = "ids",required = false) String refundId,HttpServletResponse response){
        // 当前日期，用于导出文件名称
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String dateStr = sdf.format(new Date());
        // 指定下载的文件名--设置响应头
        Map<String,Object> params=new HashMap<>();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        ZdRefundEntity zdRefundEntity=zdRefundCountryService.selectById(refundId);
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getSemesterByCode(zdRefundEntity.getSemesterCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        String fileName="";
        fileName+=sysOrgEntity.getOrgName()+sysSemesterEntity.getSemesterCode();
        params.put("refundid",refundId);
        params.put("fromOrgCode",sysUserEntity.getOrgCode());
        params.put("modules","export");
        List<RefundResourceVo> list = zdRefundProvinceService.getRefundResourceinfoAll(params);
        FileUtil.exportExcel(list,fileName,fileName,RefundResourceVo.class,fileName+".xls",response);
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

    @GetMapping("/getRefundLimitInfo")
    @ApiOperation("获取退货财务信息")
    public R getRefundLimitInfo(String semesterCode){
        if(StringUtils.isBlank(semesterCode))
        {
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }
//        Map<String,Object> params=new HashMap<>();
//        params.put("isRefund","1");
//        params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();

//        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        RefundLimitInfoVO refundLimitInfoVo=zdRefundCountryService.getRefundLimitInfo(sysUserEntity.getOrgCode(),semesterEntityList);
        return R.ok().put("data",refundLimitInfoVo);
    }
    @GetMapping("/getRefundLimitInfo2")
    @ApiOperation("获取退货财务信息")
    public R getRefundLimitInfo2(String semesterCode){
        if(StringUtils.isBlank(semesterCode))
        {
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }
//        Map<String,Object> params=new HashMap<>();
//        params.put("isRefund","1");
//        params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
//        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();

//        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        RefundLimitInfoVO refundLimitInfoVo=zdRefundCountryService.getRefundLimitInfo2(sysUserEntity.getOrgCode(),semesterCode);
        return R.ok().put("data",refundLimitInfoVo);
    }
}
