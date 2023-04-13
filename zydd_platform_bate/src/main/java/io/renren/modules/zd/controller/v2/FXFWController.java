package io.renren.modules.zd.controller.v2;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.zd.service.ZdRefundSupplierService;
import io.renren.modules.zd.service.ZdSourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("webFxfw")
public class FXFWController {
    @Resource
    private ZdSourceService zdSourceService;
    @Resource
    private ZdRefundSupplierService zdRefundSupplierService;
    /**
     * 列表
     */
    @GetMapping("/source/list")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("获取采购单据")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdSourceService.queryListPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/source/listDetail")
    //@RequiresPermissions("zd:zdsource:list")
    @ApiOperation("获取采购单据详情列表 传参sourceId")
    public R listDetail(@RequestParam Map<String, Object> params){
        PageUtils page = zdSourceService.queryResourceListPage(params);
        return R.ok().put("page", page);
    }
    /**
     * 省级获取当前退货单据
     */
    @GetMapping("/refund/list")
    public R listRefundOrderList(@RequestParam  Map<String,Object> params){
//        params.put("fromOrgCode", ShiroUtils.getUserEntity().getOrgCode());
        PageUtils resPage = zdRefundSupplierService.queryRefundOrderList(params);
        if(resPage.getList().size() >0){
            return R.ok().put("page", resPage);
        }else{
            return R.ok("未找到退货单据").put("page", resPage);
        }
    }

    @GetMapping ("/refund/listDetail")
    //@RequiresPermissions("zd:zdrefund:info")
    @ApiOperation("获取教材详情列表")
    public R info1(@RequestParam Map<String, Object> params){

        PageUtils refundOrgInfo = zdRefundSupplierService.getRefundResourceinfo(params);

        return R.ok().put("page", refundOrgInfo);
    }

}
