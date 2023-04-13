package io.renren.modules.search.controller;

import io.renren.common.utils.DateUtils;
import io.renren.common.utils.FileUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.vo.StatisticProvinceBalanceVO;
import io.renren.modules.zd.vo.StatisticProvinceTOPVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Api("省级统计分析接口")
@RequestMapping("province/statistical")
public class ProvinceStatisticalController extends AbstractController {
    @Resource
    private SysSemesterService sysSemesterService;
    @Resource
    private SysOrgService sysOrgService;
    @GetMapping("/top10")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("报订TOP10")
    public R list(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
       /* if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }*/
        params.put("top","10");
        params.put("toOrgCode",getUser().getOrgCode());
        params.put("orgType","3");
//        params.put("toOrgCode", ShiroUtils.getUserEntity().getOrgCode());
        List<StatisticProvinceTOPVO> list = sysOrgService.queryStatisticsCountryTOPByMap(params);
        return R.ok().put("data", list);
    }
    @GetMapping("/listOrg")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("分页获取报订统计数据")
    public R listOrg(@RequestParam Map<String, Object> params){
       /* if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }*/
//        params.put("orgType","1");
//        params.put("toOrgCode", ShiroUtils.getUserEntity().getOrgCode());
        params.put("toOrgCode",getUser().getOrgCode());
        params.put("orgType","3");
        PageUtils page = sysOrgService.queryStatisticsCountryPage(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/balance/list")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("结算支付概览")
    public R balanceList(@RequestParam Map<String, Object> params){
        /*if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }*/
        params.put("highLevelOrg",getUser().getOrgCode());
//        params.put("toOrgCode", ShiroUtils.getUserEntity().getOrgCode());
        List<StatisticProvinceBalanceVO> list = sysOrgService.queryStatisticsBalanceCountryByMap(params);
        return R.ok().put("data", list);
    }

    @GetMapping("/exportStatistical")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("导出报订统计数据")
    public void exportStatistical(@RequestParam Map<String, Object> params, HttpServletResponse response){
        String semesterCode= params.get("semesterCode")==null?"":params.get("semesterCode").toString();
        if(StringUtils.isBlank(semesterCode))
        {
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }
//        params.put("orgType","1");
//        params.put("toOrgCode", ShiroUtils.getUserEntity().getOrgCode());
        String fileName= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        params.put("toOrgCode",getUser().getOrgCode());
        params.put("orgType","3");
        params.put("semesterCode",semesterCode);
        List<StatisticProvinceTOPVO> list = sysOrgService.queryAllStatisticsCountry(params);
        FileUtil.exportExcel(list,fileName,fileName,StatisticProvinceTOPVO.class,fileName+".xls",response);
    }
}
