package io.renren.modules.zd.controller.v2;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.zd.entity.ZdRefundSupplierEntity;
import io.renren.modules.zd.service.ZdRefundSupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 退货主单据
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-26 18:46:17
 */
@RestController
@Api("退货供应商主单据接口")
@RequestMapping("/v2/zd/refundSupplier")
public class ZdRefundSupplierController {
    @Autowired
    private ZdRefundSupplierService zdRefundSupplierService;

    /**
     * 列表
     */
    @PostMapping("/list")
//    @RequiresPermissions("sys:zdrefundsupplier:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdRefundSupplierService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("sys:zdrefundsupplier:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			ZdRefundSupplierEntity zdRefundSupplier = zdRefundSupplierService.selectById(id);

        return R.ok().put("zdRefundSupplier", zdRefundSupplier);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("sys:zdrefundsupplier:save")
    @ApiOperation("保存")
    public R save(@RequestBody ZdRefundSupplierEntity zdRefundSupplier){
			zdRefundSupplierService.insert(zdRefundSupplier);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("sys:zdrefundsupplier:update")
    @ApiOperation("更新")
    public R update(@RequestBody ZdRefundSupplierEntity zdRefundSupplier){
			zdRefundSupplierService.updateById(zdRefundSupplier);

        return R.ok();
    }



}
