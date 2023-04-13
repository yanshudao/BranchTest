package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysOrgAreaEntity;
import io.renren.modules.sys.entity.SysOrgRateEntity;
import io.renren.modules.sys.service.SysOrgRateService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.vo.ZdSemesterRegVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Api("单位配置率管理")
@RequestMapping("sys/sysOrgRate")
public class SysOrgRateController  extends AbstractController{

    @Resource
    private SysOrgRateService sysOrgRateService;
    @Resource
    private SysSemesterService sysSemesterService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("sys:sysorgarea:list")
    @ApiOperation("获得列表")
    public R list(@RequestParam Map<String, Object> params){
        List<SysOrgRateEntity> list = sysOrgRateService.queryPage(params);
        return R.ok().put("list", list);
    }
  /**
     * 列表
     */
    @GetMapping("/sum")
    //@RequiresPermissions("sys:sysorgarea:list")
    @ApiOperation("汇总查询")
    public R sum(@RequestParam Map<String, Object> params){
        String semesterCode= MapUtils.getString(params,"semesterCode");
        String orgCode= MapUtils.getString(params,"orgCode");
        if(StringUtils.isBlank(semesterCode)){
            semesterCode=sysSemesterService.getCurrentSemester().getSemesterCode();
        }
        ZdSemesterRegVO entity = sysOrgRateService.querySumPage(orgCode,semesterCode);
        return R.ok().put("entity", entity);
    }


}
