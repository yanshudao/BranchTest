package io.renren.modules.zd.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.*;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.search.vo.StatisticsStockIncomeResourceDetailVO;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdStockIncomeEntity;
import io.renren.modules.zd.form.ZdIncomeForm;
import io.renren.modules.zd.form.ZdSourceIncomeForm;
import io.renren.modules.zd.form.ZdSourceIncomeResourceForm;
import io.renren.modules.zd.service.ZdStockIncomeResourceService;
import io.renren.modules.zd.service.ZdStockIncomeService;
import io.renren.modules.zd.vo.ZdStockIncomeResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 入库
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@RestController
@Api("入库接口")
@RequestMapping("zd/zdstockincome")
public class ZdStockIncomeController extends AbstractController{
    @Autowired
    private ZdStockIncomeService zdStockIncomeService;
    @Autowired
    private ZdStockIncomeResourceService zdStockIncomeResourceService;
    @Autowired
    private SysSemesterService sysSemesterService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("zd:zdstockincome:list")
    @ApiOperation("入库单据列表")
    public R list(@RequestParam Map<String, Object> params){
        params.put("orgCode",getUser().getOrgCode());
        PageUtils page = zdStockIncomeService.queryByOrg(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/listDetail")
    //@RequiresPermissions("zd:zdstockincome:list")
    @ApiOperation("获取单据详情")
    public R listDetail(@RequestParam String incomeId){

       List<ZdStockIncomeResourceVO> data = zdStockIncomeResourceService.selectByIncomeId(incomeId);
        return R.ok().put("data", data);
    }
    @SysLog("修改详情单")
    @PostMapping("/saveIncomeResource")
    //@RequiresPermissions("zd:zdstockincome:list")
    @ApiOperation("修改详情单")
    public R saveIncomeResource(@RequestBody List<ZdSourceIncomeResourceForm> list){
       zdStockIncomeResourceService.saveIncomeResource(list);
        return R.ok();
    }
    @SysLog("删除详情单")
    @PostMapping("/deleteIncomeResource")
    //@RequiresPermissions("zd:zdstockincome:list")
    @ApiOperation("删除详情单")
    public R saveIncomeResource(@RequestBody DeleteForm list){
        ValidatorUtils.validateEntity(list);
        Map map=new HashMap();
        map.put("ids",list.getIds());
        map.put("status",1);
        int count=zdStockIncomeResourceService.countByMap(map);
        if(count>0)
        {
            return R.error("已入库的单据不允许删除");
        }
        zdStockIncomeResourceService.deleteBatchIds(list.getIds());
        return R.ok();
    }
    @SysLog("入库确认")
    @PostMapping("/confirmIncomeResource")
    //@RequiresPermissions("zd:zdstockincome:save")
    @ApiOperation("入库确认")
    public R saveIncomeResource(@RequestBody ZdIncomeForm zdIncomeForm){
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        zdStockIncomeService.saveIncomeResource(zdIncomeForm,sysUserEntity);
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("zd:zdstockincome:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			ZdStockIncomeEntity zdStockIncome = zdStockIncomeService.selectById(id);

        return R.ok().put("zdStockIncome", zdStockIncome);
    }

    /**
     * 保存
     */
    @SysLog("保存入库单")
    @PostMapping("/saveIncome")
    //@RequiresPermissions("zd:zdstockincome:save")
    @ApiOperation("保存")
    public R saveIncome(@RequestBody ZdSourceIncomeForm zdSourceIncomeForm){
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        zdSourceIncomeForm.setSemesterCode(sysSemesterEntity.getSemesterCode());
	   zdStockIncomeService.saveIncome(zdSourceIncomeForm,sysUserEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    /*@PostMapping("/update")
    //@RequiresPermissions("zd:zdstockincome:update")
    @ApiOperation("更新")
    public R update(@RequestBody ZdStockIncomeEntity zdStockIncome){
			zdStockIncomeService.updateById(zdStockIncome);

        return R.ok();
    }
*/
    /**
     * 删除
     */
    @SysLog("删除入库单")
    @PostMapping("/delete")
    //@RequiresPermissions("zd:zdstockincome:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm){
        ValidatorUtils.validateEntity(deleteForm);
        Map map=new HashMap();
        map.put("ids",deleteForm.getIds());
        map.put("status", Constant.STOCK_STATUS.SURE);
        int count=zdStockIncomeService.countByMap(map);
        if(count>0)
        {
            return R.error("已入库的单据不允许删除");
        }
        zdStockIncomeService.deleteBatchIds(deleteForm.getIds());
        return R.ok();
    }

    @SysLog("延期确认入库单")
    @PostMapping("/delayIncome")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("延期确认入库单")
    public R delayIncome(@RequestBody ZdIncomeForm  zdIncomeForm){
        zdStockIncomeService.delayIncome(zdIncomeForm);
        return R.ok();
    }
    @SysLog("入库转发行")
    @GetMapping("/transforPublish")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("入库转发行")
    public R transforPublish(@RequestParam("id") String id){
        zdStockIncomeService.transforPublish(id);
        return R.ok();
    }

   // @SysLog("入库转发行")
    @GetMapping("/transforPublishList")
    //@RequiresPermissions("zd:zdrefund:list")
    @ApiOperation("入库转发行批量")
    public R transforPublishList(String semesterCode,String orgCode){
        List<ZdStockIncomeEntity> zdPublishEntityList=
                zdStockIncomeService.selectList(new EntityWrapper<ZdStockIncomeEntity>().eq("org_code",orgCode).eq("semester_code",semesterCode).eq("status","0"));

        for(ZdStockIncomeEntity zdStockIncomeEntity:zdPublishEntityList)
        {
            zdStockIncomeService.transforPublish(zdStockIncomeEntity.getId());
        }
        return R.ok();
    }

    @Autowired
    private SysOrgService sysOrgService;
    @SysLog("本季采购单导出")
    //县级保存报订(根据教材)
    @GetMapping("/exportStockIncome")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("本季采购单导出")
    public void exportSourceDetail(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response) {
        // 指定下载的文件名--设置响应头
        Map<String, Object> params = new HashMap<>();
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();

        SysOrgEntity sysOrgEntity = sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        String fileName = sysOrgEntity.getOrgName() + System.currentTimeMillis();
        if (StringUtils.isNotBlank(ids)) {
            params.put("ids", Arrays.asList(ids.split(",")));

        } else {
            if (Constant.ORG_TYPE.SHENG.equals(sysUserEntity.getOrgType())) {
                params.put("orgCode", sysUserEntity.getOrgCode());
            } else {
                params.put("toOrgCode", sysUserEntity.getOrgCode());
            }
            SysSemesterEntity sysSemesterEntity = sysSemesterService.getCurrentSemester();
//            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("semesterCode", sysSemesterEntity.getSemesterCode());
        }
        List<StatisticsStockIncomeResourceDetailVO> list = zdStockIncomeResourceService.queryStatisticsDetailByMap(params);
        FileUtil.exportExcel(list, fileName, fileName, StatisticsStockIncomeResourceDetailVO.class, fileName + ".xls", response);

    }

}
