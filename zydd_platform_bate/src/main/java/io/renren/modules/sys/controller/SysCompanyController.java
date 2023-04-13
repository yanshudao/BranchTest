package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.ShiroUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.zd.service.ZdSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.sys.service.SysCompanyService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 供应商/出版社
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-29 22:09:37
 */
@Api("供应商/出版社接口")
@RestController
@RequestMapping("sys/syscompany")
public class SysCompanyController extends AbstractController{
    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private SysOrgService  sysOrgService;
    @Autowired
    private ZdSourceService zdSourceService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("sys:syscompany:list")
    @ApiOperation("查询本单位的供应商列表")
    public R list(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("parentCodes",sysOrgEntity.getParentCodes());
        params.put("orgCode",sysOrgEntity.getOrgCode());
        PageUtils page = sysCompanyService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("sys:syscompany:info")
    @ApiOperation("详情")
    public R info(@PathVariable("id") String id){
	  SysCompanyEntity sysCompany = sysCompanyService.selectById(id);

        return R.ok().put("sysCompany", sysCompany);
    }

    /**
     * 保存
     */
    @SysLog("保存供应商")
    @PostMapping("/save")
    //@RequiresPermissions("sys:syscompany:save")
    @ApiOperation("保存")
    public R save(@RequestBody SysCompanyEntity sysCompany){
			sysCompanyService.insert(sysCompany);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("更新供应商")
    @PostMapping("/update")
    //@RequiresPermissions("sys:syscompany:update")
    @ApiOperation("更新")
    public R update(@RequestBody SysCompanyEntity sysCompany){
			sysCompanyService.updateById(sysCompany);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除供应商")
    @PostMapping("/delete")
    //@RequiresPermissions("sys:syscompany:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm ids){
        ValidatorUtils.validateEntity(ids);
        sysCompanyService.deleteBatchIds(ids.getIds());
        return R.ok();
    }
    @GetMapping("/listAll")
    //@RequiresPermissions("sys:syscompany:list")
    @ApiOperation("获取上级与本级所有供应商")
    public R listAll(@RequestParam Map<String, Object> params){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(sysUserEntity.getOrgCode());
        params.put("parentCodes",sysOrgEntity.getParentCodes());
        params.put("orgCode",sysOrgEntity.getOrgCode());
        List<SysCompanyEntity> sysCompanyEntityList = sysCompanyService.queryAllByMap(params);
        return R.ok().put("data", sysCompanyEntityList);
    }

}
