package io.renren.modules.zd.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.*;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.search.vo.StatisticsPublishResourceVO;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdPublishEntity;
import io.renren.modules.zd.form.PublishConfirmForm;
import io.renren.modules.zd.form.ZdPublishForm;
import io.renren.modules.zd.service.ZdPublishService;
import io.renren.modules.zd.vo.PublishResourceVO;
import io.renren.modules.zd.vo.ZdStockResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@RestController
@Api("发行接口")
@RequestMapping("zd/zdpublish")
public class ZdPublishController extends AbstractController {
    @Autowired
    private ZdPublishService zdPublishService;
    

    @Autowired 
    private SysSemesterService sysSemesterService;
    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 列表
     */
    @PostMapping("/list")
    //@RequiresPermissions("zd:zdpublish:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdPublishService.queryPage(params);
        
        return R.ok().put("page", page);
    }
    
    /**
     * 列表
     */
    @PostMapping("/list1")
    //@RequiresPermissions("zd:zdpublish:list")
    @ApiOperation("获取列表")
    public R list1(@RequestParam Map<String, Object> params){
    	String currPage = (String) params.get("currPage");
        PageUtils page = zdPublishService.queryByLimit(params);
        
    	System.out.println("访问list接口");
    	System.out.println("当前页面为 :" + currPage);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("zd:zdpublish:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			ZdPublishEntity zdPublish = zdPublishService.selectById(id);

        return R.ok().put("zdPublish", zdPublish);
    }

    /**
     * 保存
     */
    @SysLog("保存发行单")
    @PostMapping("/save")
    //@RequiresPermissions("zd:zdpublish:save")
    @ApiOperation("保存")
    public R save(@RequestBody ZdPublishForm zdPublishForm){
        ValidatorUtils.validateEntity(zdPublishForm);
    	SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        zdPublishForm.setSemesterCode(sysSemesterEntity.getSemesterCode());
//			zdPublishService.insert(zdPublish);
			zdPublishService.savePublish(zdPublishForm);
//        String id=	zdPublishService.insertSelective(zdPublish);
		//	String result = zdPublish.getId();

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("更新发行单")
    @PostMapping("/update")
    //@RequiresPermissions("zd:zdpublish:update")
    @ApiOperation("更新")
    public R update(@RequestBody ZdPublishEntity zdPublish){
			zdPublishService.updateById(zdPublish);

        return R.ok();
    }
    
    /**
     * 删除
     */
    @SysLog("废弃 删除发行单")
    @PostMapping("/delete")
    //@RequiresPermissions("zd:zdpublish:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody String[] ids){
	zdPublishService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    
    
  /*  *
     * 查询发行单位
     * 参数为：orgType ,根据这个查询单位机构
     */
    @PostMapping("/queryOrg")
    //@RequiresPermissions("zd:zdpublish:update")
    @ApiOperation("查询发行单位")
    @Deprecated
    public R queryOrg(@RequestParam Map<String, Object> params){
        PageUtils page = zdPublishService.queryOrg(params);
        return R.ok().put("page", page);

    }
   /**
    * 报订发行获取教材列表
    * @param params :
    *  orgCode
    *  toOrgCode
    *  resourceName
    *  page
    *  limit
    * @return
    *//*
    @GetMapping("/subListByOrder")
    @ApiOperation("报订发行获取教材列表")
    public R subListByOrder (@RequestParam Map<String, Object> params
            ){

    	PublicshCourseOrderResultVO result =  zdPublishService.queryByOrder(params);

    	return R.ok().put("data", result);
    }*/


    
    
    /**
     * 查询教材
     * 参数为orgCode
     * 参数为resourceName (可不填)
     * 第几页：pageNo (必填,从1开始)
     * 每页大小：pageSize (必填)
     */
   /* @GetMapping("/querySubject")
    //@RequiresPermissions("zd:zdpublish:update")
    @ApiOperation("查询教材")
    public R querySubject(@RequestParam Map<String, Object> params){
        PageUtils page = zdPublishService.querySubject(params);
        return R.ok().put("page", page);

    }*/
    
    /**
     * 查询发行单据列表
     * 查询参数：semesterName
     * 查询参数：semesterCode
     * 查询参数：orgName 
     * 模糊查询参数：id
     * 开始时间：startTime
     * 结束时间：endTime
     * 第几页：pageNo (从第一页开始，必填)
     * 每页大小：pageSize(每页大小，必填)
     */
    @GetMapping("/queryPublishList")
    //@RequiresPermissions("zd:zdpublish:update")
    @ApiOperation("县级查询发行单据列表")
    public R queryPublishList(@RequestParam Map<String, Object> params){
        params.put("toOrgCode",getUser().getOrgCode());
        PageUtils page = zdPublishService.queryPublishList(params);
        return R.ok().put("page", page);

    }
    @GetMapping("/queryProvincePublishList")
    //@RequiresPermissions("zd:zdpublish:update")
    @ApiOperation("省级查询发行单据列表")
    public R queryProvincePublishList(@RequestParam Map<String, Object> params){
        params.put("fromOrgCode",getUser().getOrgCode());
        PageUtils page = zdPublishService.queryPublishList(params);
        return R.ok().put("page", page);

    }
    /**
     * 在使用
     * 查询发行单据详情
     * 参数：id
     */
    @PostMapping("/queryPublishListDetail")
    //@RequiresPermissions("zd:zdpublish:update")
    @ApiOperation("查询发行单据详情")
    public R queryPublishListDetail(@RequestParam Map<String, Object> params){
        if(params.get("id")==null)
        {
            return R.error("参数不能为空");
        }
        String id=(String)params.get("id");
        ZdPublishEntity zdPublishEntity=zdPublishService.selectById(id);
        List<PublishResourceVO> page = zdPublishService.queryPublishListDetail(params);
        return R.ok().put("list", page).put("publish",zdPublishEntity);

    }
    
  /*  *
     * 查询所以报季
     * 
     */
    @SysLog("废弃 查询所有报订季")
    @PostMapping("/querySemester")
    //@RequiresPermissions("zd:zdpublish:update")
    @ApiOperation("查询所以报季")
    public R querySemester(@RequestParam Map<String, Object> params){
        PageUtils page = zdPublishService.querySemester(params);
        return R.ok().put("page", page);

    }

    /**
     * 列表
     */
    @GetMapping("/listOrderResource")
    //@RequiresPermissions("zd:zdpublish:list")
    @ApiOperation("获取列表")
    public R listOrderResource(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity=getUser();

        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        params.put("toOrgCode",sysUserEntity.getOrgCode());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        List<ZdStockResourceVO> list = zdPublishService.queryOrderResourceAll(params);
        return R.ok().put("data", list);
    }
    /**
     * 列表
     */
    @GetMapping("/listResource")
    //@RequiresPermissions("zd:zdpublish:list")
    @ApiOperation("获取列表")
    public R listResource(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity=getUser();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        params.put("toOrgCode",sysUserEntity.getOrgCode());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdPublishService.queryResourcePage(params);
        return R.ok().put("page", page);
    }

    @SysLog("本季发行单导出")
    //县级保存报订(根据教材)
    @GetMapping("/exportPublish")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("本季发行单导出 publishId 单个订单导出")
    public void exportOrder(@RequestParam(value = "ids",required = false)String publishId,HttpServletResponse response){

        Map<String,Object> params=new HashMap<>();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
//        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();

        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        String fileName="";
        if(StringUtils.isNotBlank(publishId)){
            params.put("publishIds",Arrays.asList(publishId.split(",")));
        }else{
            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        }
        if(Constant.ORG_TYPE.SHENG.equals(sysOrgEntity.getOrgType()))
        {
//            ZdPublishEntity zdPublishEntity=zdPublishService.selectById(publishId);
            SysOrgEntity toOrg=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
            fileName+=toOrg.getOrgName()+System.currentTimeMillis();
//            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
//            params.put("status",Constant.ORDER_STATUS.COMPLETE);
            params.put("fromOrgCode",sysUserEntity.getOrgCode());

        }else
        {
            fileName+=sysOrgEntity.getOrgName()+System.currentTimeMillis()+"";
//            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
//            params.put("status",Constant.ORDER_STATUS.COMPLETE);
              params.put("toOrgCode",sysUserEntity.getOrgCode());
//            params.put("publishIds",publishId);
//            PageUtils page = zdSourceService.queryStatisticsByMap(params);

        }
        List<StatisticsPublishResourceVO> list = zdPublishService.queryAllStatisticsByMap(params);
        FileUtil.exportExcel(list,fileName,fileName,StatisticsPublishResourceVO.class,fileName+".xls",response);

    }
    @SysLog("省级修改发行折扣")
    @PostMapping("/modifyPublishResource")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("修改折扣")
    public R modifyPublishResource(@RequestBody ZdPublishForm zdPublishForm){
        zdPublishService.modifyPublishResource(zdPublishForm);
        return R.ok();
    }

    @SysLog("省级退回发行单")
    @PostMapping("/rejectPublish")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("修改折扣")
    public R rejectPublish(@RequestBody ZdPublishForm zdPublishForm){
        zdPublishService.rejectPublish(zdPublishForm);
        return R.ok();
    }
    @SysLog("省级删除发行单")
    @PostMapping("/deletePublish")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("修改折扣")
    public R deletePublish(@RequestBody ZdPublishForm zdPublishForm){
        zdPublishService.deletePublish(zdPublishForm);
        return R.ok();
    }

    /**
     * 列表
     */
   /* @GetMapping("/v2/listResource")
    //@RequiresPermissions("zd:zdpublish:list")
    @ApiOperation("获取列表")
    public R v2ListResource(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity=getUser();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        params.put("toOrgCode",sysUserEntity.getOrgCode());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdPublishService.queryResourcePage1(params);
        return R.ok().put("page", page);
    }*/

    @GetMapping("/v2/listOrderResource")
    //@RequiresPermissions("zd:zdpublish:list")
    @ApiOperation("获取列表")
    public R v2ListOrderResource(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity=getUser();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        params.put("toOrgCode",sysUserEntity.getOrgCode());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        List<ZdStockResourceVO> list = zdPublishService.queryOrderResourceAll1(params);
        return R.ok().put("data", list);
    }

    /**
     * 县级确认收货
     * @param publishConfirmForm
     * @return
     */
    @PostMapping("/v2/confirmPublish")
    //@RequiresPermissions("zd:zdpublish:list")
    public R v2confirmPublish(@RequestBody PublishConfirmForm publishConfirmForm){
        if(CollectionUtils.isEmpty(publishConfirmForm.getResourceList())){
            return R.error("参数错误");
        }

        zdPublishService.confirmPublish(publishConfirmForm);
        return R.ok();
    }

    @PostMapping("/v2/audit")
    //@RequiresPermissions("zd:zdpublish:list")
    public R v2Audit(@RequestBody DeleteForm deleteForm){
        if(CollectionUtils.isEmpty(deleteForm.getIds())){
            return R.error("参数错误");
        }
        zdPublishService.audit(deleteForm.getIds());
        return R.ok();
    }
    @PostMapping("/v2/reject")
    //@RequiresPermissions("zd:zdpublish:list")
    public R v2Reject(@RequestBody DeleteForm deleteForm){
        if(CollectionUtils.isEmpty(deleteForm.getIds())){
            return R.error("参数错误");
        }
        zdPublishService.reject(deleteForm.getIds());
        return R.ok();
    }
}
