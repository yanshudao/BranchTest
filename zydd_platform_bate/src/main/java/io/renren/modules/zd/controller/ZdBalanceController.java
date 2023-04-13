package io.renren.modules.zd.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.ZdPublishDao;
import io.renren.modules.zd.dao.ZdRefundDao;
import io.renren.modules.zd.entity.ZdBalanceEntity;
import io.renren.modules.zd.entity.ZdBalancePayEntity;
import io.renren.modules.zd.form.ZdBalanceForm;
import io.renren.modules.zd.service.ZdBalanceCompanyService;
import io.renren.modules.zd.service.ZdBalanceService;
import io.renren.modules.zd.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("zd/zdbalance")
@Api("结算单主单据查询")
public class ZdBalanceController extends AbstractController{

    @Resource
    private ZdBalanceService zdBalanceService;
    @Resource
    private ZdBalanceCompanyService zdBalanceCompanyService;

    @Resource
    private SysSemesterService sysSemesterService;
    @Resource
    private SysOrgService sysOrgService;

    @Resource
    private ZdRefundDao zdRefundDao;

    @Resource

    private ZdPublishDao zdPublishDao;
    @Value("${fxfw.filePath}")
    private String filePath;
    @GetMapping("/zdbalance-list")
    public R listRefundResourceByOrgCode(@RequestParam Map<String, Object> params){
    	SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
    	params.put("userId", sysUserEntity.getUserId().toString());
    	params.put("orgCode", sysUserEntity.getOrgCode());
    	PageUtils resPage = zdBalanceService.queryBalancePage(params);
        if(resPage.getList().size() >0){
            return R.ok().put("page", resPage);
        }else{
            return R.ok("未找到结算单").put("page", resPage);
        }
    }
    @GetMapping("/zdbalance-list/export")
    public void exportList(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        params.put("userId", sysUserEntity.getUserId().toString());
        params.put("orgCode", sysUserEntity.getOrgCode());
        String semesterCode=params.get("semesterCode")==null?"":params.get("semesterCode").toString();
        if(StringUtils.isBlank(semesterCode)){
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            params.put("semesterCode", sysSemesterEntity.getSemesterCode());
        }
        List<BalanceExportVO> exportVOS=zdBalanceService.queryBalanceMergeList(params);
        try {
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
            String fileName="";
            String date= DateUtils.format(new Date(),"/yyyy/MM/dd");
            date="balance"+date+File.separator;
            String time= DateUtils.format(new Date(),"yyyyMMddHHmmssSSS");
            fileName=sysOrgEntity.getOrgName()+time+"结算单导出.xls";
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(fileName,fileName),
                    BalanceExportVO.class, exportVOS);
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

    }
    /**
     * 生成结算
     *
     * @param req
     * @return
     */
    @SysLog("生成结算单")
    @PostMapping("/new-balance-order")
    public R saveBalanceOrder(@RequestBody ZdBalanceForm req) {
        if(0>req.getDiscountRate()||req.getDiscountRate()>100)
        {
            return R.error("不符合规范");
        }
    	int result = zdBalanceService.saveBalanceOrder(req);
    	if(result == 1){
    		return R.ok("生成结算单据成功");
    	}else {
    		return R.error("生成结算单据失败");
		}
    }
    
    /**
     * 保存 单据详情List
     * @param zdbrList
     * @return
     */
  /*  @PostMapping("/saveList")
    @ApiOperation("存结算单据列表")
    public R saveList(@RequestBody List<ZdBalancePublish> zdbrList){
  	
    	int result = zdBalanceService.inserBalancetList(zdbrList);
    	if(result == 1){
    		return R.ok("存结算单据列表成功");
    	}else {
    		return R.error("存结算单据列表失败");
		}
    }*/
    /*孔 begin*/
    /**
     * 付款动作
     * @param payDebt
     * @return
     */
    @SysLog("添加付款记录")
    @PostMapping("/insertPayForAccount")
    @ApiOperation("付款动作")
    public R insertPayForAccount (@RequestBody ZdBalancePayEntity payDebt){
        int result = zdBalanceService.insertPayForAccount(payDebt);
        if(result == 1){
            return R.ok("付款动作成功");
        }else if(result == 2){
            return R.error("付款流水记录成功，但结算单结算状态更新失败");
        }else {
            return R.error("付款动作失败");
        }
    }
    /**
     * 付款流水详情
     * @param params
     * @return
     */
    @GetMapping("/selectPayDetailForAccount")
    @ApiOperation("付款流水详情")
    public R selectPayDetailForAccount (@RequestParam Map<String, Object> params){

        List<ZdBalancePayEntity> result = zdBalanceService.selectPayDetailForAccount(params);
        if(result != null){
            return R.ok("付款流水详情查询成功").put("accountDetail", result);
        }else {
            return R.error("付款流水详情查询失败");
        }
    }
    /*孔 end*/

    /**
     * 传入参数：balanceId
     * @param params
     * @return
     */
    @SysLog("废弃 结清结算单")
    @GetMapping("/auditBalance")
    public R auditBalance(@RequestParam Map<String, Object> params){
    	int result = zdBalanceService.auditBalance(params);
        if(result == 1){
            return R.ok("更新成功").put("result", result);
        }else{
            return R.ok("更新失败").put("result", result);
        }
    }

    @GetMapping("/balance-resource/{balanceId}")
    public R listBalanceResource(@PathVariable("balanceId") String balanceId){
        List<ZdBalancePublishVO> resList = zdBalanceService.listPublishByBalanceId(balanceId);
        List<ZdBalanceRefundVO> refundList = zdBalanceService.listRefundByBalanceId(balanceId);
        return R.ok().put("balancePublish",resList).put("balanceRefundList",refundList);
    }

    @SysLog("结清结算单")
    @PostMapping("/finisBalance")
    @ApiOperation("结清此单据")
    public R finisBalance (@RequestBody ZdBalanceEntity zdBalanceEntity){
        if(StringUtils.isBlank(zdBalanceEntity.getId())){
            return R.error("参数错误");
        }
        int result = zdBalanceService.finisBalance(zdBalanceEntity.getId());
         return R.ok();
    }
    @GetMapping("/balance/list")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("中央电大结算单列表")
    public R balanceList(@RequestParam Map<String, Object> params){
        params.put("toOrgCode",getUser().getOrgCode());
        PageUtils page = zdBalanceCompanyService.queryList(params);
        return R.ok().put("page",page);
    }
    @GetMapping("/balance/{balanceId}")
    //@RequiresPermissions("sys:syssemester:list")
    @ApiOperation("获取结算详情单")
    public R balanceDetail(@PathVariable("balanceId") String balanceId){
        List<ZdBalanceCompanyRefundVO> refundList=zdBalanceCompanyService.listRefundListByBalanceId(balanceId);
        List<ZdBalanceCompanyIncomeVO> incomeList=zdBalanceCompanyService.listIncomeListByBalanceId(balanceId);
        return R.ok().put("refundList", refundList).put("incomeList",incomeList);
    }
    @GetMapping("/listBalanceOrg")
    //@RequiresPermissions("sys:syssemester:list")
    @ApiOperation("获取结算单位列表")
    public R listBalanceOrg(@RequestParam Map<String,Object> params){
       String semesterCode= params.get("semesterCode")==null?"": params.get("semesterCode").toString();
      /* if(StringUtils.isBlank(semesterCode))
       {
           params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
       }*/
       SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
        params.put("orgCode",sysOrgEntity.getId());
       List<BalanceOrgVO> orgList=zdBalanceService.listBalanceOrg(params);
//        List<ZdBalanceCompanyIncomeVO> incomeList=zdBalanceCompanyService.listIncomeListByBalanceId(balanceId);
        return R.ok().put("orgList",orgList);
    }
    @GetMapping("/exportBalanceDetail")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("省电大结算单导出")
    public void exportBalance(String semesterCode,
                              String balanceId,
                               String orgCode,
                              HttpServletResponse response, HttpServletRequest request) throws IOException, TemplateException {

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        response.setCharacterEncoding("utf-8");
        Template temp = cfg.getTemplate("balance.html");
        ReportBalanceDetailVO reportBalanceVO=zdBalanceService.generatorBalanceBillDetail(semesterCode,orgCode,balanceId);
        temp.process(reportBalanceVO,response.getWriter());

    }
    @GetMapping("/exportBalanceList")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("省电大结算单导出")
    public void exportBalanceList( @PathVariable("semesterCode") String semesterCode,@PathVariable("orgCode") String orgCode,HttpServletResponse response, HttpServletRequest request) throws IOException, TemplateException {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        response.setCharacterEncoding("utf-8");
        Template temp = cfg.getTemplate("balance.html");
        ReportBalanceVO reportBalanceVO=zdBalanceService.generatorBalanceBill(semesterCode,orgCode);
        temp.process(reportBalanceVO,response.getWriter());


    }

    @GetMapping("/getBalanceInfo")
    public R getBalanceInfo(@RequestParam("balanceId")String balanceId){
        ZdBalanceEntity zdBalanceEntity=zdBalanceService.selectById(balanceId);
        return R.ok().put("data", zdBalanceEntity);

    }
    @PostMapping("/saveBalanceRemark")
    public R saveBalanceRemark(@RequestBody ZdBalanceEntity zdBalanceEntity){
        if(StringUtils.isBlank(zdBalanceEntity.getId()))
        {
            return R.error("id不能为空");
        }
        zdBalanceService.saveBalanceRemark(zdBalanceEntity);
        return R.ok().put("data", zdBalanceEntity);

    }
    @Resource
    Configuration cfg;
    @SysLog("重新计算结算单")
    @PostMapping("/refresh-balance-order")
    public R refreshBalanceOrder(@RequestBody DeleteForm deleteForm) {
       zdBalanceService.refreshBalanceOrder(deleteForm.getIds());
        return R.ok("重新计算结算单据成功");

    }
}
