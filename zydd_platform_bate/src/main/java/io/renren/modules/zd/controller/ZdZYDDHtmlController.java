package io.renren.modules.zd.controller;

import cn.afterturn.easypoi.excel.ExcelXorHtmlUtil;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.service.ZdBalanceCompanyService;
import io.renren.modules.zd.service.ZdBalanceService;
import io.renren.modules.zd.vo.ReportBalanceCompanyDetailVO;
import io.renren.modules.zd.vo.ReportBalanceDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("zydd/html")
@Api("供应商结算单导出")
public class ZdZYDDHtmlController extends AbstractController {
    @Resource
    private ZdBalanceCompanyService zdBalanceCompanyService;
    @Resource
    private ZdBalanceService zdBalanceService;
    @Resource
    private SysSemesterService sysSemesterService;
    @RequestMapping("/exportBalanceDetail/detail")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("供应商结算单静态页")
    public String exportBalanceHtml(String semesterCode, String companyId,String toOrgCode,
                                    String balanceId,HttpServletResponse response, HttpServletRequest request) throws IOException, TemplateException {

        // 告诉浏览器用什么软件可以打开此文件
//        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
//        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        response.setCharacterEncoding("utf-8");
//        Template temp = cfg.getTemplate("balance.html");
        if(StringUtils.isBlank(semesterCode))
        {
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }
        ReportBalanceCompanyDetailVO reportBalanceVO = zdBalanceCompanyService.generatorBalanceBillDetail(semesterCode,
                toOrgCode, companyId,balanceId);
//        temp.process(reportBalanceVO, response.getWriter());
        request.setAttribute("reportBalanceVO",reportBalanceVO);
        return "balanceCompany";

    }

    @RequestMapping("/exportBalanceDetail/export")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("供应商结算单导出")
    public void exportBalanceHtmlExport(String semesterCode, String companyId,String toOrgCode,
                                    String balanceId,HttpServletResponse response, HttpServletRequest request) throws IOException, TemplateException {

        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        response.setCharacterEncoding("utf-8");
       Template temp = cfg.getTemplate("balanceCompany.html");
        if(StringUtils.isBlank(semesterCode))
        {
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }
        Map dataModel = new HashMap<>();
        ReportBalanceCompanyDetailVO reportBalanceVO = zdBalanceCompanyService.generatorBalanceBillDetail(semesterCode,
                toOrgCode, companyId,balanceId);
        dataModel.put("reportBalanceVO",reportBalanceVO);
        temp.process(dataModel,response.getWriter());
    }

    @RequestMapping("/exportBalanceOrgDetail/detail")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("下级单位结算单静态页")
    public String exportBalanceOrgDetail(String semesterCode,String toOrgCode,
                                    String balanceId,HttpServletResponse response, HttpServletRequest request) throws IOException, TemplateException {

        // 告诉浏览器用什么软件可以打开此文件
//        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
//        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        response.setCharacterEncoding("utf-8");
//        Template temp = cfg.getTemplate("balance.html");
        if(StringUtils.isBlank(semesterCode))
        {
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }
        ReportBalanceDetailVO reportBalanceVO = zdBalanceService.generatorBalanceBillDetail(semesterCode,
                toOrgCode,balanceId);
//        temp.process(reportBalanceVO, response.getWriter());
        request.setAttribute("reportBalanceVO",reportBalanceVO);
        return "balanceOrg";

    }
    @RequestMapping("/exportBalanceOrgDetail/export")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("导出单位结算详情单")
    public void exportBalanceOrgExport(String semesterCode,String toOrgCode,
                                    String balanceId,HttpServletResponse response, HttpServletRequest request) throws IOException, TemplateException {

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        response.setCharacterEncoding("utf-8");
       Template temp = cfg.getTemplate("balanceOrg.html");
        if(StringUtils.isBlank(semesterCode))
        {
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }
        Map dataModel = new HashMap<>();
        ReportBalanceDetailVO reportBalanceVO = zdBalanceService.generatorBalanceBillDetail(semesterCode,
                toOrgCode,balanceId);
        dataModel.put("reportBalanceVO",reportBalanceVO);
        temp.process(dataModel,response.getWriter());
//        Workbook workbook= ExcelXorHtmlUtil.htmlToExcel(out.toString(), ExcelType.HSSF);
//        workbook.write(response.getOutputStream());
    }
    @Resource
    Configuration cfg;
}
