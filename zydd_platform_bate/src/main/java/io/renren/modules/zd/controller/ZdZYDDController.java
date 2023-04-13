package io.renren.modules.zd.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.*;
import io.renren.modules.zd.form.ZdBalanceCompanyForm;
import io.renren.modules.zd.form.ZdIncomeForm;
import io.renren.modules.zd.form.ZdRefundForm;
import io.renren.modules.zd.service.*;
import io.renren.modules.zd.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@RestController
@RequestMapping("zydd")
@Api("中央电大相关接口")
public class ZdZYDDController extends AbstractController {
    @Resource
    private ZdRefundProvinceService zdRefundProvinceService;
    @Resource
    private ZdRefundSupplierService zdRefundSupplierService;
    @Resource
    private ZdStockIncomeService stockIncomeService;
    @Resource
    private ZdBalanceCompanyService zdBalanceCompanyService;
    @Resource
    private SysOrgService sysOrgService;
    @Resource
    private SysSemesterService sysSemesterService;
    @Resource
    private ZdStockIncomeResourceService zdStockIncomeResourceService;
    @Resource
    private ZdOrderResourceService zdOrderResourceService;
   /* @GetMapping("/refund/completeList")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获取省级上报的退货单")
    public R completeList(@RequestParam Map<String, Object> params){
        params.put("toOrgCode",getUser().getOrgCode());
        params.put("salesmancode",getUser().getUsername());
        params.put("status",Constant.REFUND_STATUS.COMPLETE);
        PageUtils page = zdRefundProvinceService.queryRefundOrderList(params);
        return R.ok().put("page", page);
    }*/
    @PostMapping("/refund/auditList")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获取省级上报的退货单")
    public R listConfirm(@RequestBody Map<String, Object> params){
        params.put("toOrgCode",getUser().getOrgCode());
//        params.put("salesmancode",getUser().getUsername());

        List<String> statusList=new ArrayList<>();
        statusList.add(Constant.REFUND_STATUS.CONFIRM);
        statusList.add(Constant.REFUND_STATUS.COMPLETE);
        statusList.add(Constant.REFUND_STATUS.FINISH);
        statusList.add(Constant.REFUND_STATUS.AUDIT_FAIL);
        params.put("statusList",statusList);
        PageUtils page = zdRefundProvinceService.queryRefundOrderList(params);
        return R.ok().put("page", page);
    }
    @PostMapping("/refund/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获取省级退货单")
    public R list(@RequestBody Map<String, Object> params){
        params.put("toOrgCode",getUser().getOrgCode());
//        params.put("salesmancode",getUser().getUsername());
//        params.put("status",Constant.REFUND_STATUS.COMPLETE);
        params.put("status",Constant.REFUND_STATUS.COMPLETE);
        PageUtils page = zdRefundProvinceService.queryRefundOrderList(params);
        return R.ok().put("page", page);
    }

    @PostMapping("/income/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("获取省级入库单")
    public R incomeList(@RequestBody Map<String, Object> params){
        if(null==params.get("orgCode"))
        {
            return R.error("请选择要查询的单位");
        }
        params.put("supplierId","f8ad206116ec46dfa394ba5a45f39455");
//        params.put("salesmancode",getUser().getUsername());
        params.put("status",Constant.STOCK_STATUS.SURE);
        PageUtils page = stockIncomeService.queryByOrg(params);
        return R.ok().put("page", page);
    }

    @SysLog("中央电大保存结算单")
    @PostMapping("/saveBalance")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("中央电大保存结算单")
    public R incomeList(@RequestBody ZdBalanceCompanyForm req){
        if(req.getDiscountRate()==null||req.getDiscountRate()>100)
        {
            req.setDiscountRate(100);
        }
        req.setSupplierId("f8ad206116ec46dfa394ba5a45f39455");
        int result = zdBalanceCompanyService.saveBalanceOrder(req);
        if(result == 1){
            return R.ok("生成结算单据成功");
        }else {
            return R.error("生成结算单据失败");
        }
    }

    @GetMapping("/balance/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("中央电大结算单列表")
    public R balanceList(@RequestParam Map<String, Object> params){
        params.put("createBy",getUser().getUserId());
        PageUtils page = zdBalanceCompanyService.queryList(params);
         return R.ok().put("page",page);
    }
    @SysLog("中央电大添加付款记录")
    @PostMapping("/balance/pay")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("中央电大保存付款单")
    public R balancePay(@RequestBody ZdBalanceCompanyPayEntity zdBalanceCompanyPayEntity){
         zdBalanceCompanyService.savePayBalance(zdBalanceCompanyPayEntity);
        return R.ok();
    }
    @SysLog("中央电大结清此单据")
    @PostMapping("/balance/finish")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("中央电大结清此单据")
    public R finishBalance(@RequestBody ZdBalanceCompanyPayEntity zdBalanceCompanyPayEntity){
        zdBalanceCompanyService.finishBalance(zdBalanceCompanyPayEntity.getId());
        return R.ok();
    }

    @SysLog("同步退货单")
    @PostMapping("/syncOrder")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("同步退货单")
    public R syncOrder(String id){
        // ValidatorUtils.validateEntity(id);
        //   SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        String msg=zdRefundProvinceService.syncOrder(id);
        return R.ok(msg);
    }


    @GetMapping("/listOrg")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("获取当前用户所管理的省级单位")
    public R listOrg(@RequestParam Map<String, Object> params){
        // ValidatorUtils.validateEntity(id);
        //   SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
//        params.put("salesmancode",getUser().getUsername());
        params.put("orgType","1");
        params.put("parentCode",ShiroUtils.getUserEntity().getOrgCode());
        List<RefundOrgCountVO> list=sysOrgService.listAll(params);
        return R.ok().put("list",list);
    }
    @GetMapping ("/listRefundDetail")
    //@RequiresPermissions("zd:zdrefund:info")
    @ApiOperation("获取退货详情列表")
    public R info1(@RequestParam Map<String, Object> params){
        String id=(String)params.get("refundId");
        if(StringUtils.isBlank(id))
        {
            return R.error("请选择退货单");
        }
        params.put("refundid",id);
        params.put("toOrgCode",getUser().getOrgCode());
        List<RefundResourceVo> list = zdRefundProvinceService.listRefundDetail(params);
        return R.ok().put("list", list);
    }
    @SysLog("中央审核通过退货单")
    @PostMapping("/auditRefundPass")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("审核通过")
    public R auditRefundPass(@RequestBody ZdRefundForm zdRefundForm){

        int res = zdRefundSupplierService.auditRefund(zdRefundForm,Constant.REFUND_STATUS.COMPLETE);

        return R.ok().put("updateStatus", res);
    }
    @SysLog("中央审核不通过退货单")
    @PostMapping("/auditRefundFail")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("审核不通过")
    public R auditRefundFail(@RequestBody ZdRefundForm from){
        int res = zdRefundSupplierService.auditRefund(from,Constant.REFUND_STATUS.AUDIT_FAIL);
        return R.ok().put("updateStatus", res);
    }

    @GetMapping("/semester/list")
    //@RequiresPermissions("sys:syssemester:list")
    @ApiOperation("获得所有报订季节列表")
    public R semesterList(@RequestParam Map<String, Object> params){
        List<SysSemesterEntity> list = sysSemesterService.listAllByZydd(params);
        return R.ok().put("data", list);
    }
    @GetMapping("/balance/{balanceId}")
    //@RequiresPermissions("sys:syssemester:list")
    @ApiOperation("获取结算详情单")
    public R balanceDetail(@PathVariable("balanceId") String balanceId){
        List<ZdBalanceCompanyRefundVO> refundList=zdBalanceCompanyService.listRefundListByBalanceId(balanceId);
        List<ZdBalanceCompanyIncomeVO> incomeList=zdBalanceCompanyService.listIncomeListByBalanceId(balanceId);
        return R.ok().put("refundList", refundList).put("incomeList",incomeList);
    }

    @SysLog("中央电大修改退货详情折扣")
    @PostMapping("/modifyRefundResource")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("修改退货折扣")
    public R modifyRefundResource(@RequestBody ZdRefundForm zdRefundForm){
        zdRefundProvinceService.modifyRefundResource(zdRefundForm);
        return R.ok();
    }

    @SysLog("中央电大修改入库详情折扣")
    @PostMapping("/modifyIncomeResource")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("修改入库折扣")
    public R modifyIncomeResource(@RequestBody ZdIncomeForm zdIncomeForm){
        zdRefundProvinceService.modifyIncomeResource(zdIncomeForm);
        return R.ok();
    }
    @GetMapping("/listIncomeDetail")
    //@RequiresPermissions("zd:zdstockincome:list")
    @ApiOperation("获取入库单据详情")
    public R listIncomeDetail(@RequestParam String incomeId){

        List<ZdStockIncomeResourceVO> data = zdStockIncomeResourceService.selectByIncomeId(incomeId);
        return R.ok().put("data", data);
    }
    @Resource
    private ResourceLoader resourceLoader;

    @GetMapping("/exportBalanceDetail/{balanceId}")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("中央电大结算单导出")
    public void exportBalance(String semesterCode,String orgCode,String companyId,HttpServletResponse response, HttpServletRequest request) throws IOException, TemplateException {
        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:template/结算单模板_map.xlsx");


        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        response.setCharacterEncoding("utf-8");
        Template temp = cfg.getTemplate("balance.html");
//        ReportBalanceCompanyDetailVO reportBalanceVO=zdBalanceCompanyService.generatorBalanceBillDetail(semesterCode,orgCode,companyId);
//        temp.process(reportBalanceVO,response.getWriter());


        //        File savefile = new File("D:\\home\\lemur");
//        if (!savefile.exists()) {
//            savefile.mkdirs();
//        }
//        FileOutputStream fos = new FileOutputStream("D:\\home\\lemur\\htmlToExcelByIs.xlsx");
//        workbook.write(fos);
//        fos.close();
//        workbook = ExcelXorHtmlUtil.htmlToExcel(getClass().getResourceAsStream("/html/sample.html"), ExcelType.HSSF);
//        fos = new FileOutputStream("D:\\home\\lemur\\htmlToExcelByIs.xls");
//        workbook.write(fos);
//        fos.close();

      /*  Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2014-12-25");
        map.put("money", 2000000.00);
        map.put("upperMoney", "贰佰万");
        map.put("company", "执笔潜行科技有限公司");
        map.put("bureau", "财政局");
        map.put("person", "JueYue");
        map.put("phone", "1879740****");
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<String, String>();
            lm.put("id", i + 1 + "");
            lm.put("zijin", i * 10000 + "");
            lm.put("bianma", "A001");
            lm.put("mingcheng", "设计");
            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
            lm.put("quancheng", "开源项目");
            lm.put("sqje", i * 10000 + "");
            lm.put("hdje", i * 10000 + "");

            listMap.add(lm);
        }
        map.put("maplist", listMap);
*/


    }

        @GetMapping("/exportBalanceList")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("中央电大结算单导出")
    public void exportBalanceList(String semesterCode,String orgCode,
                                  HttpServletResponse response, HttpServletRequest request) throws IOException, TemplateException {


        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        response.setCharacterEncoding("utf-8");
        Template temp = cfg.getTemplate("balance.html");
        ReportBalanceCompanyVO reportBalanceVO=zdBalanceCompanyService.generatorBalanceBill(semesterCode,orgCode);
        PrintWriter p=response.getWriter();
        temp.process(reportBalanceVO,response.getWriter());


    }
    @Resource
    Configuration cfg;
    @GetMapping("/listOrderDetail")
    //@RequiresPermissions("zd:zdstockincome:list")
    @ApiOperation("根据报订规则查看报订情况")
    public R listOrderDetail(@RequestParam Map<String, Object> params)
    {
        String zmcrId=params.get("zmcrId")==null?"":params.get("zmcrId").toString();
        if(StringUtils.isBlank(zmcrId))
        {
            return R.error("请选择报订规则！");
        }
        List<OrderResourceVO>  orderResourceVOS=zdOrderResourceService.queryAllOrderList(params);
        return R.ok().put("list",orderResourceVOS);
    }

}
