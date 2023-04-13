package io.renren.modules.zd.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdRefundCheckEntity;
import io.renren.modules.zd.entity.ZdRefundEntity;
import io.renren.modules.zd.form.ZdRefundCreateOrderFrom;
import io.renren.modules.zd.form.ZdRefundForm;
import io.renren.modules.zd.service.ZdRefundCountryService;
import io.renren.modules.zd.service.ZdRefundProvinceService;
import io.renren.modules.zd.service.ZdStockService;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.RefundOrgCountVO;
import io.renren.modules.zd.vo.RefundResourceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 退货主单据
 *
 * @author hestersmile
 * @date 2018-04-30 14:14:33
 */
@RestController
@Api("省级退货单接口")
@RequestMapping("zd/zdRefundForProvince")
public class ZdRefundProvinceController {
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
        statusList.add(Constant.REFUND_STATUS.CONFIRM);
        statusList.add(Constant.REFUND_STATUS.COMPLETE);
        statusList.add(Constant.REFUND_STATUS.FINISH);
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

    /**
     *
     * @param params
     * @return
     */

    @GetMapping ("/getRefundResourceinfoForList")
    //@RequiresPermissions("zd:zdrefund:info")
    @ApiOperation("获取教材详情列表")
    public R info1(@RequestParam Map<String, Object> params){

        PageUtils refundOrgInfo = zdRefundProvinceService.getRefundResourceinfo(params);

        return R.ok().put("page", refundOrgInfo);
    }
    @PostMapping ("/listResource")
    //@RequiresPermissions("zd:zdrefund:info")
    @ApiOperation("省级获取可退教材列表")
    public R listResource(@RequestBody Map<String, Object> params){
        try {
            List<String> semesterCodeList= (List<String>) params.get("semesterCodeList");

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
        }
        params.put("toOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils refundOrgInfo = zdStockService.queryResourcePage(params);
        return R.ok().put("page", refundOrgInfo);
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

     /**
     * 省级获取当前退货单据
     */
    @GetMapping("/refund-order")
    public R listRefundOrderList(@RequestParam  Map<String,Object> params){
        params.put("fromOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils resPage = zdRefundCountryService.queryRefundOrderList(params);
        if(resPage.getList().size() >0){
            return R.ok().put("page", resPage);
        }else{
            return R.ok("未找到退货单据").put("page", resPage);
        }
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
        if(params.get("orgCode")==null)
        {
            return R.error("请选择单位!");
        }
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        params.put("userId", sysUserEntity.getUserId().toString());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdRefundCountryService.listResourcesRefundable(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/{refundId}/report")
    public R reportRefundOrder(@PathVariable("refundId") String refundId){
        int res = zdRefundCountryService.reportRefundOrder(refundId);
        if(res > 0){
            return R.ok("上报成功");
        }else{
            return R.ok("上报成功");
        }
    }
    @SysLog("省级修改退货详情单")
    @PostMapping("/saveRefundResource")
    @ApiOperation("修改退货详情单")
    public R saveRefundResource(@RequestBody ZdRefundForm zdRefundForm){
        int res = zdRefundCountryService.saveRefundResource(zdRefundForm);
        if(res > 0){
            return R.ok("修改成功");
        }else{
            return R.ok("修改失败");
        }
    }
    @SysLog("省级删除退货详情单")
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

    @GetMapping("/getRefundLimitInfo")
    @ApiOperation("获取退货信息")
    public R getRefundLimitInfo(String semesterCode){
        if(StringUtils.isBlank(semesterCode))
        {
            return R.error("请选择退货季节！");
        }

//        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        RefundLimitInfoVO refundLimitInfoVo=zdRefundProvinceService.getRefundLimitInfo(sysUserEntity.getOrgCode());

        return R.ok().put("data",refundLimitInfoVo);
    }

}
