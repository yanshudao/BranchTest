package io.renren.modules.search.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
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
import java.util.List;
import java.util.Map;

@RestController
@Api("中央电大统计分析接口")
@RequestMapping("zydd/statistical")
public class ZYDDStatisticalController extends AbstractController{
    @Resource
    private SysSemesterService sysSemesterService;
    @Resource
    private SysOrgService sysOrgService;
    @GetMapping("/top10")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("报订TOP10")
    public R list(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
        /*if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }*/
        params.put("top","10");
//        params.put("toOrgCode", ShiroUtils.getUserEntity().getOrgCode());
        List<StatisticProvinceTOPVO> list = sysOrgService.queryStatisticsTOPByMap(params);
        return R.ok().put("data", list);
    }
    @GetMapping("/listOrg")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("分页获取报订统计数据")
    public R listOrg(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
       /* if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }*/
//        params.put("orgType","1");
//        params.put("toOrgCode", ShiroUtils.getUserEntity().getOrgCode());
        PageUtils page = sysOrgService.queryStatisticsPage(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/balance/list")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("结算支付概览")
    public R balanceList(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
        /*if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }*/
//        params.put("orgType","1");
//        params.put("toOrgCode", ShiroUtils.getUserEntity().getOrgCode());
        List<StatisticProvinceBalanceVO> list = sysOrgService.queryStatisticsBalanceByMap(params);
        return R.ok().put("data", list);
    }
}
