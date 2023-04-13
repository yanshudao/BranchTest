package io.renren.modules.zd.controller.v2;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.*;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdRefundCheckEntity;
import io.renren.modules.zd.entity.ZdRefundEntity;
import io.renren.modules.zd.entity.ZdRefundSupplierEntity;
import io.renren.modules.zd.form.*;
import io.renren.modules.zd.service.ZdRefundCountryService;
import io.renren.modules.zd.service.ZdRefundProvinceService;
import io.renren.modules.zd.service.ZdRefundSupplierService;
import io.renren.modules.zd.service.ZdStockService;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.RefundOrgCountVO;
import io.renren.modules.zd.vo.RefundResourceVo;
import io.renren.modules.zd.vo.RefundSupplierResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 退货主单据
 *
 * @author hestersmile
 * @date 2018-04-30 14:14:33
 */
@RestController("zdRefundProvinceControllerV2")
@Api("省级退货单接口")
@RequestMapping("/v2/zd/zdRefundForProvince")
public class ZdRefundProvinceController extends AbstractController {
    @Autowired
    private ZdRefundProvinceService zdRefundProvinceService;
    @Autowired
    private ZdStockService zdStockService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private ZdRefundCountryService zdRefundCountryService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Autowired
    private ZdRefundSupplierService zdRefundSupplierService;
    @Autowired
    private RedisUtils redisUtils;



    /**
     * 退货审核通过
     * @param
     * @return
     */
    @PostMapping("/auditPass")
    @SysLog("省级退货单审核通过")
    public R auditPass(@RequestBody ZdRefundAuditForm zdRefundAuditForm){
      zdRefundProvinceService.auditPass(zdRefundAuditForm.getIds(),zdRefundAuditForm);
      return R.ok("审核通过成功");

    }
    /**
     * 退货审核不通过
     * @param
     * @return
     */
    @PostMapping("/auditFail")
    @SysLog("省级退货单审核不通过")
    public R auditFail(@RequestBody UpdateForm updateForm){
        zdRefundProvinceService.auditFail(updateForm.getIds());
        return R.ok("审核不通过成功");

    }

    /**
     * 修改退貨單據審批狀態
     * @param refundlist
     * @return
     */
    @SysLog("省级修改退货单审核状态")
    @PostMapping("/updateCheckStatusAndNum")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("修改审核状态")
    public R list(@RequestBody List<ZdRefundCheckEntity> refundlist){
        ValidatorUtils.validateEntity(refundlist);
        int res = zdRefundProvinceService.updateCheckStatus(refundlist);
        return R.ok().put("updateStatus", res);
    }

    @PostMapping("/completeRefund")
    @SysLog("省级退货单确认收货")
    public R completeRefund(@RequestBody CompleteOrgRefundForm completeOrgRefundForm){
        ValidatorUtils.validateEntity(completeOrgRefundForm);
        zdRefundProvinceService.completeRefund(completeOrgRefundForm,ShiroUtils.getUserEntity());
        return R.ok("确认成功");

    }
    @PostMapping("/syncRefund")
    @SysLog("省级同步单据")
    public R syncRefund(@RequestParam("refundId") String refundId){
        ZdRefundEntity zdRefundEntity=zdRefundProvinceService.selectById(refundId);
        if("1".equals(zdRefundEntity.getIsSync()))
        {
            return R.error("状态不允许同步");
        }
        zdRefundProvinceService.syncRefund(refundId,ShiroUtils.getUserEntity());
        /*String key="REFUND_"+refundId;
        boolean lock = false;
        lock = redisUtils.setIfAbsent(key,"stuSeatId");
        try {
            if (lock) {


            }
        } finally {
            if (lock) {
//                    redisUtils.delete(key);
                logger.info("任务结束，释放锁!");
            } else {
                logger.info("没有获取到锁，无需释放锁!");
            }
        }*/
        return R.ok("确认成功");

    }


    /**
     * 保存退货单
     */
    @SysLog("省级保存退货单")
    @PostMapping("/saveRefund")
    //@RequiresPermissions("zd:zdpublish:save")
    @ApiOperation("保存退货单")
    public R save(@RequestBody ZdRefundForm zdRefundForm){
        ValidatorUtils.validateEntity(zdRefundForm);
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        zdRefundForm.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdRefundProvinceService.saveRefund(zdRefundForm);
        return R.ok();
    }
    @SysLog("省级代退")
    @ApiOperation("省级代退")
    @PostMapping("/new-refund-order")
    public R saveRefundOrder(@RequestBody ZdRefundCreateOrderFrom req) {
        ValidatorUtils.validateEntity(req);
        req.setToOrgCode(ShiroUtils.getUserEntity().getOrgCode());
        req.setStatus(Constant.REFUND_STATUS.COMPLETE);
        int res = zdRefundCountryService.insertRefundOrder(req);
        if (res == 1) {
            return R.ok("生成退货单据成功。");
        } else {
            return R.error("生成退货单据失败。");
        }
    }
   /* @GetMapping("/getRefundLimitInfo")
    @ApiOperation("获取退货上限信息")
    public R getRefundLimitInfo(String semesterCode){
        if(StringUtils.isBlank(semesterCode))
        {
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }

//        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        RefundLimitInfoVO refundLimitInfoVo=zdRefundProvinceService.getRefundLimitInfo(sysUserEntity.getOrgCode());

        return R.ok().put("data",refundLimitInfoVo);
    }*/
    @GetMapping("/getRefundLimitInfo2")
    @ApiOperation("获取退货上限信息")
    public R getRefundLimitInfo2(String semesterCode,String supplierId){
        if(StringUtils.isBlank(semesterCode)||StringUtils.isBlank(supplierId)){
            return R.error("退货季或者供应商不能为空");
        }
        /*if(StringUtils.isBlank(semesterCode))
        {
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }*/

//        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        RefundLimitInfoVO refundLimitInfoVo=zdRefundProvinceService.getRefundLimitInfo2(semesterCode,sysUserEntity.getOrgCode(),supplierId);

        return R.ok().put("data",refundLimitInfoVo);
    }
    /**
     * 查询用户所在单位管辖范围内的所有的退货单据
     * "orglist:"、"orgcode:"、"semester_code:"、"createtime:"、"reund_code"
     * @return
     */
    @PostMapping("/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获取列表")
    public R list(@RequestBody Map<String, Object> params){
        params.put("toOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        List<String> statusList=new ArrayList<>();
        statusList.add(Constant.REFUND_ORG_STATUS.CONFIRM);
        statusList.add(Constant.REFUND_ORG_STATUS.COMPLETE);
        statusList.add(Constant.REFUND_ORG_STATUS.FINISH);
        statusList.add(Constant.REFUND_ORG_STATUS.SHIP);
        statusList.add(Constant.REFUND_ORG_STATUS.AUDIT_PASS);
        params.put("statusList",statusList);
        PageUtils resPage = zdRefundCountryService.queryRefundOrderList(params);
        if(resPage.getList().size() >0){
            return R.ok().put("page", resPage);
        }else{
            return R.ok("未找到退货单据").put("page", resPage);
        }
    }
    @PostMapping("/auditList")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获取列表")
    public R auditList(@RequestParam Map<String, Object> params){
        params.put("toOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        params.put("status",Constant.REFUND_STATUS.COMPLETE);
        PageUtils resPage = zdRefundCountryService.queryRefundOrderList(params);
        if(resPage.getList().size() >0){
            return R.ok().put("page", resPage);
        }else{
            return R.ok("未找到退货单据").put("page", resPage);
        }
    }
    /**
     * 省级获取当前退货单据
     */
    @GetMapping("/refund-order")
    public R listRefundOrderList(@RequestParam  Map<String,Object> params){
        params.put("fromOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils resPage = zdRefundSupplierService.queryRefundOrderList(params);
        if(resPage.getList().size() >0){
            return R.ok().put("page", resPage);
        }else{
            return R.ok("未找到退货单据").put("page", resPage);
        }
    }

    @SysLog("修改县级详情单")
    @PostMapping("/saveCountryRefundResource")
    @ApiOperation("修改退货详情单")
    public R saveCountryRefundResource(@RequestBody ZdRefundForm zdRefundForm){
        ZdRefundEntity zdRefundEntity=zdRefundCountryService.selectById(zdRefundForm.getId());
        if(zdRefundEntity==null){
            return R.error("未找到退货单");
        }
        zdRefundForm.setOrgCode(zdRefundEntity.getOrgCode());
        int res = zdRefundCountryService.saveRefundResource(zdRefundForm);
        if(res > 0){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }

    @SysLog("修改省级退货详情单")
    @PostMapping("/saveRefundResource")
    @ApiOperation("修改退货详情单")
    public R saveRefundResource(@RequestBody ZdRefundForm zdRefundForm){
        int res = zdRefundProvinceService.saveRefundResource(zdRefundForm);
        if(res > 0){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }
    @SysLog("省级删除县级退货详情单")
    @PostMapping("/deleteRefundResource")
    @ApiOperation("删除县级退货详情单")
    public R deleteRefundResource(String id){
        if(StringUtils.isBlank(id)){
            return R.error("无法找到订单");
        }
        int res = zdRefundCountryService.deleteRefundResource(id);
        if(res > 0){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }
    @SysLog("省级删除县级退货单")
    @PostMapping("/deleteRefund")
    @ApiOperation("省级删除县级退货单")
    public R deleteRefund(@RequestBody DeleteForm deleteForm){
        int res = zdRefundCountryService.deleteRefund(deleteForm.getIds());
        if(res > 0){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }
     @SysLog("省级删除供应商退货单")
    @PostMapping("/deleteRefundSuppiler")
    @ApiOperation("省级删除供应商退货单")
    public R deleteRefundSuppiler(@RequestBody DeleteForm deleteForm){
        int count=zdRefundSupplierService.selectCount(new EntityWrapper<ZdRefundSupplierEntity>().in("status","3,4").in("id_",deleteForm.getIds()));
        if(count>0){
            return R.error("状态不允许筛选");
        }
        boolean res = zdRefundSupplierService.deleteRefundSuppiler(deleteForm.getIds());
        if(res){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }


    /**
     * 查看县级上报的退货详情单
     * @param params
     * @return
     */

    @GetMapping ("/getRefundAuditForList")
    //@RequiresPermissions("zd:zdrefund:info")
    @ApiOperation("获取教材详情列表")
    public R getRefundAuditForList(@RequestParam Map<String, Object> params){
        ZdRefundEntity zdRefundEntity=zdRefundCountryService.selectById(MapUtils.getString(params,"refundid"));
        PageUtils refundOrgInfo = zdRefundProvinceService.getRefundResourceinfo(params);
        return R.ok().put("page", refundOrgInfo).put("refund",zdRefundEntity);
    }
    /**
     * 查看省级退货详情单
     * @param params
     * @return
     */

    @GetMapping ("/getRefundResourceinfoForList")
    //@RequiresPermissions("zd:zdrefund:info")
    @ApiOperation("获取教材详情列表")
    public R info1(@RequestParam Map<String, Object> params){

        PageUtils refundOrgInfo = zdRefundSupplierService.getRefundResourceinfo(params);

        return R.ok().put("page", refundOrgInfo);
    }



    @PostMapping("/returnRefund")
    @SysLog("省级退货弃审")
    public R returnRefund(@RequestBody UpdateForm updateForm){
        zdRefundProvinceService.returnRefund(updateForm.getIds());
        return R.ok("弃审成功");

    }
    @PostMapping("/syncRefundSupplier")
    @SysLog("省级同步单据")
    public R syncRefundSupplier(@RequestParam("refundId") String refundId){
        ZdRefundSupplierEntity zdRefundEntity=zdRefundSupplierService.selectById(refundId);
        if("1".equals(zdRefundEntity.getIsSync()))
        {
            return R.error("状态不允许同步");
        }
        zdRefundProvinceService.syncRefundSupplier(refundId,ShiroUtils.getUserEntity());
        return R.ok("确认成功");

    }


    @SysLog("省级退货单导出")
    //县级保存报订(根据教材)
    @GetMapping("/exportRefund")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("省级退货单导出")
    public void exportOrder(@RequestParam(name = "ids",required = false) String refundId, HttpServletResponse response){
        // 当前日期，用于导出文件名称
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String dateStr = sdf.format(new Date());
        // 指定下载的文件名--设置响应头
        Map<String,Object> params=new HashMap<>();
        String fileName="";
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        if(StringUtils.isNotBlank(refundId)){
            params.put("refundIds", Arrays.asList(refundId.split(",")));
        }else{
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        }
        fileName+=sysOrgEntity.getOrgName()+System.currentTimeMillis();
        params.put("fromOrgCode",sysUserEntity.getOrgCode());
        List<RefundSupplierResourceVO> list = zdRefundSupplierService.getRefundResourceinfoAll(params);
        FileUtil.exportExcel(list,fileName,fileName,RefundSupplierResourceVO.class,fileName+".xls",response);

    }

    @SysLog("省级删除供应商退货详情单")
    @PostMapping("/deleteSupplierResource")
    @ApiOperation("删除县级退货详情单")
    public R deleteSupplierResource(@RequestBody DeleteForm deleteForm){
        int res = zdRefundSupplierService.deleteRefundResource(deleteForm.getIds());
        if(res > 0){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    /**
     * 含有退货申请的单位信息
     */
    @GetMapping("/getRefundOrginfo")
    //@RequiresPermissions("zd:zdrefund:info")
    @ApiOperation("获取退货申请的单位详情")
    public R info(@RequestParam Map<String,Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("orgType","3");
        params.put("parentCode",sysUserEntity.getOrgCode());
        List<RefundOrgCountVO> list=sysOrgService.listAll(params);
        return R.ok().put("refundOrgInfo", list);
    }



    /**
     *
     * @param mparams
     * @return
     */
    @GetMapping("/getRefundResourceOrderinfo")
    //@RequiresPermissions("zd:zdrefund:info")
    @ApiOperation("获取报订退货资源列表 界面")
    public R refunListInfo(@RequestParam Map<String, Object> mparams){

        PageUtils refundOrderInfo = zdRefundProvinceService.getRefundResourceOrderinfo(mparams);

        return R.ok().put("page", refundOrderInfo);
    }


    @PostMapping ("/listResource")
    //@RequiresPermissions("zd:zdrefund:info")
    @ApiOperation("省级获取可退教材列表")
    public R listResource(@RequestBody Map<String, Object> params){
        if(params.containsKey("semesterCode")){
            return R.error("请选择报订季");
        }
        /*try {
//            List<String> semesterCodeList= (List<String>) params.get("semesterCodeList");


            if(CollectionUtils.isEmpty(semesterCodeList))
            {
                semesterCodeList=new ArrayList<>();
                List<SysSemesterEntity> sysSemesterEntities=sysSemesterService.listRefund();
                for(SysSemesterEntity sysSemesterEntity:sysSemesterEntities){
                    semesterCodeList.add(sysSemesterEntity.getSemesterCode());
                }
                params.put("semesterCodeList",semesterCodeList);
            }
        } catch (Exception e) {
            return R.error(e.getMessage());
        }*/
        params.put("toOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils refundOrgInfo = zdStockService.queryResourcePage(params);
        return R.ok().put("page", refundOrgInfo);
    }



    @GetMapping("/refund-order-org")
    public R listRefundOrderListOrg(@RequestParam  Map<String,Object> params){
       // req.setFromOrgCode(ShiroUtils.getUserEntity().getOrgCode());
        if(params.get("fromOrgCode")==null)
        {
            return R.error("请选择单位");
        }
        params.put("toOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        params.put("status",Constant.REFUND_STATUS.COMPLETE);
        PageUtils resPage = zdRefundCountryService.queryRefundOrderList(params);
        if(resPage.getList().size() >0){
            return R.ok().put("page", resPage);
        }else{
            return R.ok("未找到退货单据").put("page", resPage);
        }
    }

    @GetMapping("/refundable-resources")
    @ApiOperation("查询可退货教材列表")
    public R listResourcesRefundable(@RequestParam Map<String, Object> params) {
        String orgCode=MapUtils.getString(params,"orgCode");

        if(StringUtils.isBlank(orgCode))
        {
            return R.error("请选择单位!");
        }
//        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(orgCode);
        List<SysSemesterEntity> semesterList=sysSemesterService.listRefund();
//        params.put("userId", sysUserEntity.getUserId().toString());
        params.put("semesterList",semesterList);
        params.put("orgCode",sysOrgSettingEntity.getOrgCode());
        params.put("toOrgCode",sysOrgSettingEntity.getToOrgCode());
        PageUtils page = zdRefundCountryService.listResourcesRefundable(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/{refundId}/report")
    public R reportRefundOrder(@PathVariable("refundId") String refundId){
        int res = zdRefundSupplierService.reportRefundOrder(refundId);
        if(res > 0){
            return R.ok("上报成功");
        }else{
            return R.ok("上报成功");
        }
    }

    @SysLog("省级更新物流信息")
    @PostMapping("/addAddress")
    @ApiOperation("更新物流信息")
    public R update(@RequestBody ZdRefundForm zdRefundForm){
        int res = zdRefundCountryService.updateAddress(zdRefundForm);
        if(res > 0){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }
    @SysLog("省级退货审核不通过")
    @PostMapping("/auditRefundFail")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("审核不通过")
    public R auditRefundFail(@RequestBody ZdRefundForm from){
        int res = zdRefundProvinceService.auditRefund(from,Constant.REFUND_STATUS.AUDIT_FAIL);
        return R.ok().put("updateStatus", res);
    }
    @GetMapping("/listRefundDetail")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获取详情单")
    public R listRefundDetail(@RequestParam Map<String, Object> params ){
        String refundId=(String)params.get("refundId");
        if(StringUtils.isBlank(refundId))
        {
            return R.error("请选择退货单");
        }
        params.put("refundid",refundId);
        ZdRefundEntity zdRefundEntity=zdRefundProvinceService.selectById(refundId);
        List<RefundResourceVo> list = zdRefundProvinceService.listRefundDetail(params);
        return R.ok().put("list", list).put("refund",zdRefundEntity);
    }
    @SysLog("省级修改退货单折扣")
    @PostMapping("/modifyRefundResource")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("修改折扣")
    public R modifyRefundResource(@RequestBody ZdRefundForm zdRefundForm){

         zdRefundProvinceService.modifyRefundResource(zdRefundForm);

        return R.ok();
    }



}
