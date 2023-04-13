package io.renren.modules.search.controller;

import io.renren.common.utils.FileUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.search.vo.StatisticsResourceDetailVO;
import io.renren.modules.search.vo.StatisticsResourceVO;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.service.SysOrgAreaService;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@Api("省级查询服务")
@RequestMapping("province/search")
public class ProvinceSearchController extends AbstractController {
    @Autowired
    private ZdOrderService zdOrderService;
    @Autowired
    private ZdSourceService zdSourceService;
    @Autowired
    private ZdPublishService zdPublishService;
    @Autowired
    private ZdRefundProvinceService zdRefundProvinceService;
    @Autowired
    private ZdRefundSupplierService zdRefundSupplierService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysOrgAreaService sysOrgAreaService;
    /**
     * 列表
     */
    @GetMapping("/listOrderResource")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("查询报订服务 orgCode")
    public R list(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
        if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }
        params.put("orgType","1");
        params.put("toOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils page = zdOrderService.queryStatisticsByMap(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/exportOrderResource")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("导出报订服务 orgCode")
    public void exportOrderResource(@RequestParam Map<String, Object> params, HttpServletResponse response){
        String semesterCode= params.get("semesterCode")==null?"":params.get("semesterCode").toString();
        String fromOrgCode= params.get("fromOrgCode")==null?"":params.get("fromOrgCode").toString();
        String areaCode= params.get("areaCode")==null?"":params.get("areaCode").toString();
        String status= params.get("status")==null?"":params.get("status").toString();
        String submitTimeStartStr= params.get("submitTimeStartStr")==null?"":params.get("submitTimeStartStr").toString();
        String submitTimeEndStr= params.get("submitTimeEndStr")==null?"":params.get("submitTimeEndStr").toString();
        String fileName="";
        if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }

        SysSemesterEntity sysSemesterEntity=sysSemesterService.getSemesterByCode(String.valueOf(params.get("semesterCode")));
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
        fileName=sysOrgEntity.getOrgName()+sysSemesterEntity.getSemesterCode()+"";
        /* if(StringUtils.isNotBlank(areaCode)){
            SysOrgAreaEntity sysOrgAreaEntity=sysOrgAreaService.selectByCode(areaCode);
            fileName=sysOrgAreaEntity.getAreaName()+sysSemesterEntity.getSemesterCode()+"";
        }else if(StringUtils.isNotBlank(fromOrgCode))
        {
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(fromOrgCode);
            fileName=sysOrgEntity.getOrgName()+sysSemesterEntity.getSemesterCode()+"";
        }else
        {

        }*/
        params.put("orgType","1");
        params.put("submitTimeStartStr",submitTimeStartStr);
        params.put("submitTimeEndStr",submitTimeEndStr);
        params.put("areaCode",areaCode);
        params.put("status",status);
        params.put("orgCode",fromOrgCode);
        params.put("toOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        List<StatisticsResourceVO> list= zdOrderService.queryStatisticsAllByMap(params);

        FileUtil.exportExcel(list,fileName,fileName,StatisticsResourceVO.class,fileName+".xls",response);

    }
    @GetMapping("/exportOrderResourceDetail")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("导出报订服务 orgCode")
    public void exportOrderResourceDetail(@RequestParam Map<String, Object> params, HttpServletResponse response){
        String semesterCode= params.get("semesterCode")==null?"":params.get("semesterCode").toString();
        String fromOrgCode= params.get("fromOrgCode")==null?"":params.get("fromOrgCode").toString();
        String areaCode= params.get("areaCode")==null?"":params.get("areaCode").toString();
        String status= params.get("status")==null?"":params.get("status").toString();
        String submitTimeStartStr= params.get("submitTimeStartStr")==null?"":params.get("submitTimeStartStr").toString();
        String submitTimeEndStr= params.get("submitTimeEndStr")==null?"":params.get("submitTimeEndStr").toString();
        String fileName="";
        if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }

        SysSemesterEntity sysSemesterEntity=sysSemesterService.getSemesterByCode(String.valueOf(params.get("semesterCode")));
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
        fileName=sysOrgEntity.getOrgName()+sysSemesterEntity.getSemesterCode()+"";
        /* if(StringUtils.isNotBlank(areaCode)){
            SysOrgAreaEntity sysOrgAreaEntity=sysOrgAreaService.selectByCode(areaCode);
            fileName=sysOrgAreaEntity.getAreaName()+sysSemesterEntity.getSemesterCode()+"";
        }else if(StringUtils.isNotBlank(fromOrgCode))
        {
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(fromOrgCode);
            fileName=sysOrgEntity.getOrgName()+sysSemesterEntity.getSemesterCode()+"";
        }else
        {

        }*/
        params.put("orgType","1");
        params.put("submitTimeStartStr",submitTimeStartStr);
        params.put("submitTimeEndStr",submitTimeEndStr);
        params.put("areaCode",areaCode);
        params.put("status",status);
        params.put("orgCode",fromOrgCode);
        params.put("toOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        List<StatisticsResourceDetailVO> list= zdOrderService.queryStatisticsDetailByMap(params);

        FileUtil.exportExcel(list,fileName,fileName,StatisticsResourceDetailVO.class,fileName+".xls",response);

    }

    @GetMapping("/listSourceResource")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("查询采购服务 orgCode")
    public R listSourceResource(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
        if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }
        PageUtils page = zdSourceService.queryStatisticsByMap(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/listPublishResource")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("查询发行服务 orgCode")
    public R listPublishResource(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
        if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }
        params.put("fromOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils page = zdPublishService.queryStatisticsByMap(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/listRefundResource")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("查询县级向本级退货服务 orgCode")
    public R listRefundResource(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
        if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }
        params.put("toOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils page = zdRefundProvinceService.queryStatisticsByMap(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/listRefundSupplierResource")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("查询向供应商退货")
    public R listRefundSupplierResource(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
        if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }
        params.put("orgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils page = zdRefundSupplierService.queryStatisticsByMap(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/listRefundZyddResource")
    //@RequiresPermissions("subject:subjectcourse:list")
    @ApiOperation("查询向中央电大退货服务 orgCode")
    public R listRefundZyddResource(@RequestParam Map<String, Object> params){
        String semesterCode= String.valueOf(params.get("semesterCode"));
        if(StringUtils.isBlank(semesterCode))
        {
            params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        }
        params.put("fromOrgCode",ShiroUtils.getUserEntity().getOrgCode());
        PageUtils page = zdSourceService.queryStatisticsByMap(params);
        return R.ok().put("page", page);
    }
}
