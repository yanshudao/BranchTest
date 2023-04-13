package io.renren.modules.zd.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.*;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.search.vo.StatisticsResourceVO;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdOrderEntity;
import io.renren.modules.zd.form.ZdCourseForm;
import io.renren.modules.zd.form.ZdOrgResourceForm;
import io.renren.modules.zd.form.ZdResourceForm;
import io.renren.modules.zd.service.ZdOrderResourceService;
import io.renren.modules.zd.service.ZdOrderService;
import io.renren.modules.zd.vo.OrderOrgCount;
import io.renren.modules.zd.vo.OrderResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 征订主表
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@RestController
@Api("征订主表接口")
@RequestMapping("zd/zdorder")
public class ZdOrderController {
    @Autowired
    private ZdOrderService zdOrderService;
    @Autowired
    private ZdOrderResourceService zdOrderResourceService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Autowired
    private SysSemesterService sysSemesterService;


    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("获取报订单据列表 orgType 省市县")
    public R list(@RequestParam Map<String, Object> params,String orgType){
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        String fromOrgCode=(String)params.get("orgCode");

        if("3".equals(orgType)) {
            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("orderBy"," o.status,o.create_time desc");
        }else if ("0".equals(orgType)) {
            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("usercode",sysUserEntity.getUsername());
        } else {
            if(StringUtils.isNotBlank(fromOrgCode))
            {
                params.put("fromOrgCode",fromOrgCode);
            }
//            SysOrgEntity sysOrgEntity= sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
//            params.put("toOrgCode",sysOrgEntity.getOrgCode());
            params.put("parentCode",sysUserEntity.getOrgCode());//20220331改为下级单位所有
            List<String> list=new ArrayList<>();
            list.add( Constant.ORDER_STATUS.CONFIRM);
            list.add( Constant.ORDER_STATUS.COMPLETE);
            params.put("statusList", list);
            params.put("orderBy","o.status,o.confirm_time desc");
        }
        PageUtils page = zdOrderService.queryPage(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/resource/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("省级/县级获取报订单据详情列表")
    public R resourceList(@RequestParam Map<String, Object> params,String orderId){
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("orderId",orderId);
        PageUtils page = zdOrderResourceService.queryListPage(params);
        return R.ok().put("page", page);
    }

    @SysLog("修改报订详情单")
    @PostMapping("/saveOrderResource")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("修改详情单据数量")
    public R saveOrderResource(@RequestBody ZdOrgResourceForm zdOrgResourceForm){
//        ValidatorUtils.validateEntity(deleteForm);
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        zdOrderService.saveOrderResource(zdOrgResourceForm);
        return R.ok();
    }
    @SysLog("删除报订详情单")
    @PostMapping("/deleteOrderResource")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("删除详情单据数量")
    public R deleteOrderResource(@RequestParam(value = "id") String id){
//        ValidatorUtils.validateEntity(deleteForm);
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        zdOrderResourceService.deleteById(id);
        return R.ok();
    }
    @SysLog("提交报订单")
    @PostMapping("/confirmOrder")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("县级提交单据")
    public R confirmOder(@RequestBody  DeleteForm deleteForm){
        ValidatorUtils.validateEntity(deleteForm);
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        int count=zdOrderService.selectCount(new EntityWrapper<ZdOrderEntity>().in("id_",deleteForm.getIds()).ne("status",1));
        if(count>0){
            return R.error("存在不允许提交的订单");
        }
        zdOrderService.confirmOder(deleteForm.getIds());
        return R.ok();
    }
    @SysLog("审核报订单")
    @PostMapping("/completeOrder")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("省级完成单据")
    public R completeOrder(@RequestBody  DeleteForm deleteForm){
        ValidatorUtils.validateEntity(deleteForm);
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        zdOrderService.completeOrder(deleteForm.getIds());
        return R.ok();
    }
    @SysLog("退回报订单")
    @PostMapping("/rejectOrder")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("省级退回单据")
    public R rejectOrder(@RequestBody  DeleteForm deleteForm){
        ValidatorUtils.validateEntity(deleteForm);
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        zdOrderService.rejectOrder(deleteForm.getIds());
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("zd:zdorder:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			ZdOrderEntity zdOrder = zdOrderService.selectById(id);

        return R.ok().put("zdOrder", zdOrder);
    }



    /**
     * 修改
     */
    @SysLog("修改报订单")
    @PostMapping("/update")
    //@RequiresPermissions("zd:zdorder:update")
    @ApiOperation("更新")
    public R update(@RequestBody ZdOrderEntity zdOrder){
		zdOrderService.updateById(zdOrder);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除报订单")
    @PostMapping("/delete")
    //@RequiresPermissions("zd:zdorder:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm){
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("ids",deleteForm.getIds());
        List list=new ArrayList();
        list.add(Constant.ORDER_STATUS.COMPLETE);
        list.add(Constant.ORDER_STATUS.CONFIRM);
        queryMap.put("idList",deleteForm.getIds());
        queryMap.put("statusList",list);
        int count=zdOrderService.selectCountByMap(queryMap);
        if(count>0)
        {
            return R.error("状态不允许删除！");
        }
	   zdOrderService.deleteOrder(deleteForm.getIds());
        return R.ok();
    }

    //县级保存报订（根据课程）
    /**
     * 保存
     */
    @SysLog("按课程报订")
    @PostMapping("/saveByCourse")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("保存报订（根据课程）")
    public R saveByCourse(@RequestBody ZdCourseForm zdCourseForm){
        ValidatorUtils.validateEntity(zdCourseForm);
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
       /* if(sysSemesterEntity==null||sysSemesterEntity.getSemesterEndTime()==null||sysSemesterEntity.getSemesterStartTime()==null)
        {
            return R.error("无法获取上级设置的报订季时间");
        }
        if(sysSemesterEntity.getSemesterStartTime().before(new Date())||sysSemesterEntity.getSemesterStartTime().after(new Date()))
        {
            return R.error("当前报订季已结束！请联系上级处理!");
        }*/
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        zdCourseForm.setOrgCode(sysUserEntity.getOrgCode());
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(zdCourseForm.getOrgCode());
        if(sysOrgSettingEntity==null)
        {
            return R.error("未设置上报单位");
        }
        zdOrderService.saveOrder(zdCourseForm);
        return R.ok();
    }
    @SysLog("按教材报订")
    //县级保存报订(根据教材)
    @PostMapping("/saveByResource")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("保存报订(根据教材)")
    public R saveByResource(@RequestBody ZdResourceForm zdResourceForm){
        ValidatorUtils.validateEntity(zdResourceForm);
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(sysUserEntity.getOrgCode());
        if(sysOrgSettingEntity==null)
        {
            return R.error("未设置上报单位");
        }
        zdOrderService.saveOrderByResource(zdResourceForm);
        return R.ok();
    }
    @SysLog("省级代报-课程报订")
    @PostMapping("/saveOrderByCourse")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("省级代报-保存报订（根据课程）")
    public R saveOrderByCourse(@RequestBody ZdCourseForm zdCourseForm){
        ValidatorUtils.validateEntity(zdCourseForm);
        if(StringUtils.isBlank(zdCourseForm.getOrgCode()))
        {
            return R.error("请选择报订单位");
        }
  //      SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        zdCourseForm.setOrgCode(zdCourseForm.getOrgCode());
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(zdCourseForm.getOrgCode());
        if(sysOrgSettingEntity==null)
        {
            return R.error("未设置上报单位");
        }
        zdOrderService.saveOrderFromProvince(zdCourseForm);
        return R.ok();
    }
    @SysLog("省级代报-按教材报订")
    //县级保存报订(根据教材)
    @PostMapping("/saveOrderByResource")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("省级代报-保存报订(根据教材)")
    public R saveOrderByResource(@RequestBody ZdResourceForm zdResourceForm){
       // SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(zdResourceForm.getOrgCode());
        if(sysOrgSettingEntity==null)
        {
            return R.error("未设置上报单位");
        }
        zdOrderService.saveOrderByResourceFromProvince(zdResourceForm);
        return R.ok();
    }

    @SysLog("本季报订单导出")
    //县级保存报订(根据教材)
    @GetMapping("/exportOrder")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("本季报订单导出")
    public void exportOrder(@RequestParam(value = "ids",required = false)String orderId,HttpServletResponse response){
        // 当前日期，用于导出文件名称
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String dateStr = sdf.format(new Date());
        // 指定下载的文件名--设置响应头
        Map<String,Object> params=new HashMap<>();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
//
        if(StringUtils.isNotBlank(orderId)){
           params.put("ids",Arrays.asList(orderId.split(",")));

        }else{
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        }
//        ZdOrderEntity zdOrderEntity=zdOrderService.selectById(orderId);
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        String fileName="";
        if(Constant.ORG_TYPE.SHENG.equals(sysUserEntity.getOrgType()))
        {
//            SysOrgEntity fromOrg=sysOrgService.selectByOrgCode(zdOrderEntity.getFromOrgCode());
            fileName+=sysOrgEntity.getOrgName()+System.currentTimeMillis();
//            params.put("status",Constant.ORDER_STATUS.COMPLETE);
            params.put("toOrgCode",sysUserEntity.getOrgCode());
//            params.put("orderId",orderId);
            params.put("orgType",sysOrgEntity.getOrgType());
        }else
        {
            fileName+=sysOrgEntity.getOrgName()+System.currentTimeMillis()+"";
//            params.put("status",Constant.ORDER_STATUS.COMPLETE);
            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("orgType",sysOrgEntity.getOrgType());
//            params.put("orderId",orderId);
//            PageUtils page = zdSourceService.queryStatisticsByMap(params);

        }
        params.put("modules","export");
        List<StatisticsResourceVO> list = zdOrderService.queryStatisticsAllByMap(params);
        FileUtil.exportExcel(list,fileName,fileName,StatisticsResourceVO.class,fileName+".xls",response);

    }

    /**
     * 列表
     */
    @GetMapping("/getOrderCount")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("获取已报定单")
    public R getOrderCount(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        if(sysSemesterEntity==null)
        {
            return R.error("无法获取当前报订季节");
        }
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        params.put("orgCode",sysUserEntity.getOrgCode());
        List<OrderOrgCount> orderList = sysOrgService.queryOrderList(params);
        List<OrderOrgCount> notOrderList = sysOrgService.queryNotOrderList(params);
        return R.ok().put("orderList", orderList).put("notOrderList",notOrderList);
    }

    @GetMapping("/v2/resource/list")
    //@RequiresPermissions("zd:zdorder:list")
    @ApiOperation("省级/县级获取报订单据详情列表")
    public R v2ResourceList(@RequestParam Map<String, Object> params,String orderId){
        ZdOrderEntity zdOrderEntity=zdOrderService.selectById(orderId);
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("orderId",orderId);
        PageUtils page = zdOrderResourceService.queryListPage1(params);
        return R.ok().put("page", page).put("order",zdOrderEntity);
    }

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
