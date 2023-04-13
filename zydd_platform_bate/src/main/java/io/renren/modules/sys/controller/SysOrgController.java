package io.renren.modules.sys.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.form.OrgCodeUpdateForm;
import io.renren.modules.sys.form.OrgForm;
import io.renren.modules.sys.form.SyncCourseForm;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 部门
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:15:51
 */
@RestController
@Api("单位接口")
@RequestMapping("sys/sysorg")
public class SysOrgController extends AbstractController{
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Autowired
    private SysSemesterOrgService sysSemesterOrgService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        params.put("orgCode",ShiroUtils.getUserEntity().getOrgCode());
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(ShiroUtils.getUserEntity().getOrgCode());
        List list = sysOrgService.selectChildren(params);
        list.add(sysOrgEntity);
        return R.ok().put("data", list);
    }
    @GetMapping("/listOrg")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("获取选中的单位及下级单位")
    public R listOrg(@RequestParam Map<String, String> params){
        String id= params.getOrDefault("id","");
        if(StringUtils.isBlank(id)){
            return R.error("请选择单位");
        }
        SysOrgEntity sysOrgEntity=sysOrgService.selectById(id);
        params.put("orgCode",sysOrgEntity.getOrgCode());
        List list = sysOrgService.selectChildren(params);
        list.add(sysOrgEntity);
        return R.ok().put("data", list);
    }
    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("sys:sysorg:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
        SysOrgEntity sysOrg = sysOrgService.selectById(id);
        if(StringUtils.isNotBlank(sysOrg.getParentId()))
        {
            SysOrgEntity sysOrgEntity=sysOrgService.selectById(sysOrg.getParentId());
            sysOrg.setParentOrg(sysOrgEntity);
        }
        SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingService.selectByOrg(sysOrg.getOrgCode());
        return R.ok().put("sysOrg", sysOrg).put("sysOrgSetting", sysOrgSettingEntity);
    }

    /**
     * 保存
     */
    @SysLog("保存单位")
    @PostMapping("/save")
    //@RequiresPermissions("sys:sysorg:save")
    @ApiOperation("保存")
    public R save(@RequestBody OrgForm sysOrg){
        int count=sysOrgService.selectCount(new EntityWrapper<SysOrgEntity>().eq("org_code",sysOrg.getOrgCode()));
        if(count>0)
        {
            return R.error("单位代码重复");
        }
		sysOrgService.saveOrg(sysOrg);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改单位")
    @PostMapping("/update")
    //@RequiresPermissions("sys:sysorg:update")
    @ApiOperation("更新")
    public R update(@RequestBody OrgForm sysOrg){
        if(sysOrg.getParentId().equals(sysOrg.getId())){
            return R.error("父节点不能是自己");
        }
        if(StringUtils.isBlank(sysOrg.getParentId())){
            return R.error("上级单位不能为空！");
        }
        if("1".equals(sysOrg.getId())){
            return R.error("中央电大不可修改");
        }
		sysOrgService.updateOrg(sysOrg);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除单位")
    @PostMapping("/delete")
    //@RequiresPermissions("sys:sysorg:delete")
    public R delete(@RequestBody DeleteForm form){
        sysOrgService.deleteOrg(form.getIds());
        return R.ok();
    }
    @GetMapping("/listChildren")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("获取下属单位列表")
    public R listChildren(@RequestParam(value = "parentId",required = false) String parentId,
                          @RequestParam(value = "orgType",required = false)String orgType){
         Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("orgCode",ShiroUtils.getUserEntity().getOrgCode());
        queryMap.put("parentId",parentId);
        queryMap.put("orgType",orgType);
        List List = sysOrgService.selectChildren(queryMap);
        return R.ok().put("data", List);
    }
    @GetMapping("/listParent")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("获取上级单位列表")
    public R listParent(String orgCode){
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("orgCode",orgCode);
        List list = sysOrgService.selectParent(queryMap);
      /*  if(list!=null)
        {
            list.add(sysOrgService.selectByOrgCode(orgCode));
        }*/
        return R.ok().put("data", list);
    }
    /**
     * 列表
     */
    @GetMapping("/listByType")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("根据类型获取列表")
    public R list(String orgType){
        if("ZYDD".equals(getUser().getOrgCode())){
            List list = sysOrgService.selectByType(orgType);
            return R.ok().put("data", list);
        }else{
            List list=Arrays.asList(sysOrgService.selectByOrgCode(getUser().getOrgCode()));
            return R.ok().put("data", list);
        }

    }
    @GetMapping("/listProvince")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("获得省级树形列表")
    public R listProvince(){
        if("ZYDD".equals(getUser().getOrgCode())){
            List list = sysOrgService.listProvince();
            return R.ok().put("data", list);
        }else{
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
            return R.ok().put("data", Arrays.asList(sysOrgEntity));
        }

    }
    @PostMapping("/syncProvinceCourse")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("同步省级开设信息")
    @SysLog("同步省级开设信息")
    public R syncSemesterCourse(@RequestBody SyncCourseForm syncCourseForm){
        int count=sysOrgService.syncSemesterCourse(syncCourseForm.getSemesterCode(),syncCourseForm.getOrgCode());
        return R.ok("同步成功！");
    }
    @PostMapping("/syncCourse")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("一键同步开设信息")
    @SysLog("一键同步开设信息")
    public R syncProvinceSemesterCourse(@RequestBody SyncCourseForm syncCourseForm){
        List<SysOrgEntity> list = sysOrgService.listProvince();
        int count= list.stream().filter(o->!"ZYDD".equals(o.getOrgCode())).mapToInt(item -> sysOrgService.syncSemesterCourse(syncCourseForm.getSemesterCode(), item.getOrgCode())).sum();
        return R.ok("同步成功！");
    }

    @PostMapping("/updateOrgCode")
    @ApiOperation("更新单位编码")
    @SysLog("更新单位编码")
    public R updateOrgCode(@RequestBody OrgCodeUpdateForm sysOrg){
        ValidatorUtils.validateEntity(sysOrg);
        return sysOrgService.updateOrgCode(sysOrg);
    }
    @GetMapping("/treeList")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("异步返回单位列表")
    public R treeList(String pid){
        String orgCode=getUser().getOrgCode();
        String selectPid=pid;
        if("ZYDD".equals(getUser().getOrgCode())){
            if(StringUtils.isBlank(pid)){
                selectPid="1";
            }
        }
        if(StringUtils.isBlank(pid)){
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(orgCode);
            selectPid=sysOrgEntity.getId();
            List list = sysOrgService.treeList(selectPid);
            list.add(sysOrgService.selectChildrenById(selectPid));
            return R.ok().put("data", list);
        }else{
            List list = sysOrgService.treeList(selectPid);
            return R.ok().put("data", list);
        }

    }
    @PostMapping("/updateRateConfig")
    //@RequiresPermissions("sys:sysorg:list")
    @ApiOperation("开启/关闭配置率计算规则")
    @SysLog("开启/关闭配置率计算规则")
    public R updateRateConfig(@RequestBody Map<String,Object> params){
        String status= MapUtils.getString(params,"status");
        String rateConfig= MapUtils.getString(params,"rateConfig");
        if("1".equals(rateConfig)){
            sysOrgSettingService.updateRateConfig(status,rateConfig);
        }else if("2".equals(rateConfig)){
            sysOrgSettingService.updateRateConfig(status,rateConfig);
        }else if("3".equals(rateConfig)){
            sysOrgSettingService.updateRateConfig(status,rateConfig);
        }else{
            return R.error("参数错误！");
        }
        return R.ok();

    }
}
