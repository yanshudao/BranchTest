package io.renren.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysSemesterOrgEntity;
import io.renren.modules.sys.service.SysSemesterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



/**
 * 征订季节设定
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-29 22:09:37
 */
@RestController
@RequestMapping("sys/syssemester")
@Api("报订季节设定")
public class SysSemesterController extends AbstractController {
    @Autowired
    private SysSemesterService sysSemesterService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("sys:syssemester:list")
    @ApiOperation("获得中央电大季节获取列表")
    public R list(@RequestParam Map<String, Object> params){
        String orgCode= MapUtils.getString(params,"orgCode");
        if(StringUtils.isBlank(orgCode)){
            return R.ok().put("page", new PageUtils(new Page()));
        }
        if("ZYDD".equals(orgCode)){
            PageUtils page = sysSemesterService.queryPage(params);
            return R.ok().put("page", page);
        }else{
            PageUtils page = sysSemesterService.queryListPage(params);
            return R.ok().put("page", page);
        }


    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("sys:syssemester:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
		SysSemesterEntity sysSemester = sysSemesterService.selectById(id);
        return R.ok().put("sysSemester", sysSemester);
    }

    /**
     * 保存
     */
    @SysLog("保存报订季节")
    @PostMapping("/save")
    //@RequiresPermissions("sys:syssemester:save")
    @ApiOperation("保存")
    public R save(@RequestBody SysSemesterEntity sysSemester){
			sysSemesterService.insert(sysSemester);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("更新报订季节")
    @PostMapping("/update")
    //@RequiresPermissions("sys:syssemester:update")
    @ApiOperation("更新")
    public R update(@RequestBody SysSemesterEntity sysSemester){
        sysSemesterService.updateById(sysSemester);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除报订季节")
    @PostMapping("/delete")
    //@RequiresPermissions("sys:syssemester:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm){
        if(CollectionUtils.isEmpty(deleteForm.getIds())){
            return R.error("请选择要删除的数据");
        }
        sysSemesterService.deleteBatchIds(deleteForm.getIds());
        return R.ok();
    }
    @SysLog("设置当前报订季节")
    @PostMapping("/setCurrent")
    @ApiOperation(value = "设定该季节为当前报订季节")
    //@RequiresPermissions("sys:syssemester:update")
    public R setCurrent(@RequestBody SysSemesterEntity sysSemester) {
        SysSemesterEntity entity= sysSemesterService.updateCurrentSemester(getUser().getOrgCode(),sysSemester);
        return R.ok().put("data", entity);
    }

    @GetMapping("/getCurrent")
    @ApiOperation(value = "获取当前报订季节")
    //@RequiresPermissions("sys:syssemester:list")
    public R getCurrent() {
        SysSemesterEntity entity=sysSemesterService.getCurrentSemester();
        return R.ok().put("data", entity).put("now",System.currentTimeMillis());
    }
    @GetMapping("/listOrg")
    //@RequiresPermissions("sys:syssemester:list")
    @ApiOperation("获得省级报订季节列表")
    public R listOrg(@RequestParam Map<String, Object> params){
        params.put("orgCode",getUser().getOrgCode());
        PageUtils page = sysSemesterService.queryListPage(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/listOrgRefund")
    //@RequiresPermissions("sys:syssemester:list")
    @ApiOperation("获得可退貨报订季节列表")
    public R listOrgRefund(@RequestParam Map<String, Object> params){
//        params.put("orgCode",getUser().getOrgCode());
//        params.put("isRefund","1");
        List list = sysSemesterService.listRefund();
        return R.ok().put("data", list);
    }

    @SysLog("设置单位报订季节")
    @PostMapping("/setOrgSemester")
    @ApiOperation(value = "设置单位报订季节")
    //@RequiresPermissions("sys:syssemester:update")
    public R setOrgSemester(@RequestBody SysSemesterOrgEntity sysSemester) {
        if("ZYDD".equals(sysSemester.getOrgCode())){
            sysSemesterService.updateDefaultSemester(sysSemester.getSemesterCode());
            return R.ok();
        }else{
            SysSemesterEntity entity= sysSemesterService.updateSemester(sysSemester.getOrgCode(),sysSemester.getSemesterCode());
            return R.ok().put("data", entity);
        }

    }
    @SysLog("设置单位报订季节")
    @PostMapping("/setDefaultSemester")
    @ApiOperation(value = "设置单位报订季节")
    //@RequiresPermissions("sys:syssemester:update")
    public R setDefaultSemester(@RequestBody SysSemesterOrgEntity sysSemester) {
        sysSemesterService.updateDefaultSemester(sysSemester.getSemesterCode());
        return R.ok();
    }
    /**
     * 列表
     */
    @GetMapping("/listAll")
    //@RequiresPermissions("sys:syssemester:list")
    @ApiOperation("获得所有报订季节")
    public R listAll(){
        List<SysSemesterEntity> list = sysSemesterService.selectList(new EntityWrapper<SysSemesterEntity>().orderBy("semester_code",false));
        return R.ok().put("data", list);
    }


    @PostMapping("/closeRate")
    @SysLog("归档报订季计算配置率")
    @ApiOperation("归档报订季计算配置率")
    public R closeRate(@RequestBody SysSemesterOrgEntity sysSemester){
        if(StringUtils.isBlank(sysSemester.getSemesterCode())){
            return R.error("参数错误");
        }
        sysSemesterService.closeRate(sysSemester.getSemesterCode());
        return R.ok();
    }
}
