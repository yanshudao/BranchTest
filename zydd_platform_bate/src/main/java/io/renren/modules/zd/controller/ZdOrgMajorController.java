package io.renren.modules.zd.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.ShiroUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import io.renren.modules.zd.entity.ZdOrgMajorEntity;
import io.renren.modules.zd.service.ZdOrgMajorService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 单位开设专业
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-04-01 15:58:41
 */
@RestController
@Api("省/市/县单位开设专业接口")
@RequestMapping("zd/zdorgmajor")
public class ZdOrgMajorController {
    @Autowired
    private ZdOrgMajorService zdOrgMajorService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("zd:zdorgmajor:list")
    @ApiOperation("获取本单位开设的专业列表")
    public R list(@RequestParam Map<String, Object> params){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        params.put("orgCode",sysUserEntity.getOrgCode());
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        PageUtils page = zdOrgMajorService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/listByOrg")
    //@RequiresPermissions("zd:zdorgmajor:list")
    @ApiOperation("省、市、县级获取可开设的专业列表")
    public R listByOrg(@RequestParam Map<String, Object> params,
                       @RequestParam(value = "orgType",required = true)@ApiParam(required = true) String orgType){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        if("1".equals(orgType))
        {
            //省级
            SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
            if(sysOrgEntity==null)
            {
                return R.error("上级单位无法获取");
            }
            params.put("parentOrgCode",sysOrgEntity.getOrgCode());
            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
            PageUtils page = zdOrgMajorService.queryBySheng(params);
            return R.ok().put("page", page);
        }else if("2".equals(orgType)||"3".equals(orgType))
        {
            //市级县级
            SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
            params.put("parentOrgCode",sysOrgEntity.getOrgCode());
            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
            PageUtils page = zdOrgMajorService.queryByOrg(params);
            return R.ok().put("page", page);
        }else
        {
            return R.error("参数不合法");
        }
    }

    @GetMapping("/listAllByOrg")
    //@RequiresPermissions("zd:zdorgmajor:list")
    @ApiOperation("省、市、县级获取可开设的专业列表")
    public R listAllByOrg(@RequestParam Map<String, Object> params,
                       @RequestParam(value = "orgType",required = true)@ApiParam(required = true) String orgType){
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        if("1".equals(orgType))
        {
            //省级
            SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
            if(sysOrgEntity==null)
            {
                return R.error("上级单位无法获取");
            }
            params.put("parentOrgCode",sysOrgEntity.getOrgCode());
            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
            PageUtils page = zdOrgMajorService.queryBySheng(params);
            return R.ok().put("page", page);
        }else if("2".equals(orgType)||"3".equals(orgType))
        {
            //市级县级
            SysOrgEntity sysOrgEntity=sysOrgService.selectParentByOrgCode(sysUserEntity.getOrgCode());
            params.put("parentOrgCode",sysOrgEntity.getOrgCode());
            params.put("orgCode",sysUserEntity.getOrgCode());
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
            PageUtils page = zdOrgMajorService.queryByOrg(params);
            return R.ok().put("page", page);
        }else
        {
            return R.error("参数不合法");
        }
    }
    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("zd:zdorgmajor:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			ZdOrgMajorEntity zdOrgMajor = zdOrgMajorService.selectById(id);

        return R.ok().put("zdOrgMajor", zdOrgMajor);
    }

    /**
     * 保存
     */
    @SysLog("保存开设专业")
    @PostMapping("/save")
    //@RequiresPermissions("zd:zdorgmajor:save")
    @ApiOperation("保存开设专业")
    public R save(@RequestBody ZdOrgMajorEntity zdOrgMajor){

     SysSemesterEntity sysSemesterEntity= sysSemesterService.getCurrentSemester();
     zdOrgMajor.setSemesterCode(sysSemesterEntity.getSemesterCode());
     zdOrgMajorService.insert(zdOrgMajor);
     return R.ok();
    }

    /**
     * 保存
     */
    @SysLog("批量保存开设专业")
    @PostMapping("/saveList")
    //@RequiresPermissions("zd:zdorgmajor:save")
    @ApiOperation("批量保存开设专业")
    public R saveList(@RequestBody List<ZdOrgMajorEntity> zdOrgMajor){
        SysSemesterEntity sysSemesterEntity= sysSemesterService.getCurrentSemester();
       for(ZdOrgMajorEntity zdOrgMajorEntity:zdOrgMajor)
        {
            zdOrgMajorEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());

        }
        zdOrgMajorService.insertBatch(zdOrgMajor);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除开设专业")
    @PostMapping("/delete")
    //@RequiresPermissions("zd:zdorgmajor:delete")
    @ApiOperation("省/市/县删除开设专业")
    public R delete(@RequestBody DeleteForm deleteForms){
        ValidatorUtils.validateEntity(deleteForms);
	   zdOrgMajorService.deleteBatchIds(deleteForms.getIds());

        return R.ok();
    }

}
