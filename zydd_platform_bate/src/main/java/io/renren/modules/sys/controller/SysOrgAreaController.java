package io.renren.modules.sys.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import io.renren.modules.sys.entity.SysOrgAreaEntity;
import io.renren.modules.sys.service.SysOrgAreaService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-22 15:57:47
 */
@RestController
@Api("单位区域接口")
@RequestMapping("sys/sysorgarea")
public class SysOrgAreaController extends AbstractController{
    @Autowired
    private SysOrgAreaService sysOrgAreaService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("sys:sysorgarea:list")
    @ApiOperation("获取列表不分页")
    public R list(@RequestParam Map<String, Object> params){
        params.put("orgCode",getUser().getOrgCode());
        List<SysOrgAreaEntity> list = sysOrgAreaService.queryAll(params);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("sys:sysorgarea:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			SysOrgAreaEntity sysOrgArea = sysOrgAreaService.selectById(id);

        return R.ok().put("sysOrgArea", sysOrgArea);
    }

    /**
     * 保存
     */
    @SysLog("保存单位区域")
    @PostMapping("/save")
    //@RequiresPermissions("sys:sysorgarea:save")
    @ApiOperation("保存")
    public R save(@RequestBody SysOrgAreaEntity sysOrgArea){
        sysOrgArea.setOrgCode(getUser().getOrgCode());
			sysOrgAreaService.insert(sysOrgArea);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("更新单位区域")
    @PostMapping("/update")
    //@RequiresPermissions("sys:sysorgarea:update")
    @ApiOperation("更新")
    public R update(@RequestBody SysOrgAreaEntity sysOrgArea){
        sysOrgArea.setOrgCode(getUser().getOrgCode());
        sysOrgAreaService.updateById(sysOrgArea);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除单位区域")
    @PostMapping("/delete")
   // @RequiresPermissions("sys:sysorgarea:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm){
			sysOrgAreaService.deleteBatchIds(deleteForm.getIds());

        return R.ok();
    }

}
