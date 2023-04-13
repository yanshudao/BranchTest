package io.renren.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysOrgAddressEntity;
import io.renren.modules.sys.service.SysOrgAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api("单位收货地址管理")
@RequestMapping("sys/sysorg/address")
public class SysOrgAddressController extends AbstractController {

    @Autowired
    private SysOrgAddressService sysOrgAddressService;
    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("获取客商地址列表")
    public R list(@RequestParam Map<String, Object> params){
        Object orgCode=params.get("orgCode");
        if(orgCode==null){
            return R.ok().put("data",new ArrayList<>());
        }
        List<SysOrgAddressEntity> list = sysOrgAddressService.selectList(new EntityWrapper<SysOrgAddressEntity>().eq("org_code",orgCode));
        return R.ok().put("data", list);
    }

    /**
     * 保存
     */
    @SysLog("保存客商地址")
    @PostMapping("/save")
    @ApiOperation("保存客商地址")
    public R save(@RequestBody SysOrgAddressEntity orgAddressEntity){
        sysOrgAddressService.insert(orgAddressEntity);
        return R.ok();
    }

    /**
     * 保存
     */
    @SysLog("更新客商地址")
    @PostMapping("/update")
    @ApiOperation("更新客商地址")
    public R update(@RequestBody SysOrgAddressEntity orgAddressEntity){
        sysOrgAddressService.updateById(orgAddressEntity);
        return R.ok();
    }
    @GetMapping("/info")
    @ApiOperation("获取地址详情")
    public R info(@RequestParam String id){
        SysOrgAddressEntity orgAddressEntity=sysOrgAddressService.selectById(id);
        return R.ok().put("address",orgAddressEntity);
    }

    /**
     * 删除
     */
    @SysLog("删除客商地址")
    @PostMapping("/delete")
    public R delete(@RequestBody DeleteForm form){
        sysOrgAddressService.deleteBatchIds(form.getIds());
        return R.ok();
    }
    @GetMapping("/listAll")
    @ApiOperation("获得采购地址")
    public R listAll(@RequestParam Map<String, Object> params){
        String orgCode=params.get("orgCode")==null?"":params.get("orgCode").toString();
        if(StringUtils.isBlank(orgCode)){
            orgCode=getUser().getOrgCode();
        }
        List<SysOrgAddressEntity> list = sysOrgAddressService.selectList(new EntityWrapper<SysOrgAddressEntity>().eq("org_code",orgCode.toString()));
        return R.ok().put("data", list);
    }

}
