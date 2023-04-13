package io.renren.modules.zd.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdStockIncomeEntity;
import io.renren.modules.zd.service.ZdOrderService;
import io.renren.modules.zd.service.ZdRefundProvinceService;
import io.renren.modules.zd.service.ZdStockIncomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("zd/workbench")
@Api("工作台")
public class ZdWorkBenchController extends AbstractController{
    @Resource
    private ZdOrderService zdOrderService;
    @Resource
    private ZdRefundProvinceService zdRefundProvinceService;
    @Resource
    private ZdStockIncomeService zdStockIncomeService;
    @Resource
    private SysSemesterService sysSemesterService;
    @GetMapping("/county/orderSubmitCount")
    @ApiOperation("获取县级待上报报订单")
    public R orderSubmitCount()
    {
        return
                R.ok(0+"");
    }
    @GetMapping("/county/refundSubmitCount")
    @ApiOperation("获取县级待上报退货单单")
    public R refundSubmitCount()
    {
        return R.ok(0+"");
    }

    @GetMapping("/province/orderSubmitCount")
    @ApiOperation("获取省级待审核报订单")
    public R orderAuditCount(@RequestParam Map<String,Object> params)
    {
        params.put("toOrgCode",getUser().getOrgCode());
        params.put("status", Constant.ORDER_STATUS.CONFIRM);
        params.put("semesterCode", sysSemesterService.getCurrentSemester().getSemesterCode());
        Integer count=zdOrderService.selectCountByMap(params);
        return R.ok().put("count",count);
    }
    @GetMapping("/province/refundSubmitCount")
    @ApiOperation("获取省级待审核退货单")
    public R refundAuditCount(@RequestParam Map<String,Object> params)
    {
        params.put("toOrgCode",getUser().getOrgCode());
        params.put("status", Constant.REFUND_STATUS.CONFIRM);
        params.put("semesterCode", sysSemesterService.getCurrentSemester().getSemesterCode());
        Integer count=zdRefundProvinceService.selectCountByMap(params);
        return R.ok().put("count",count);
    }
    @GetMapping("/province/incomeAuditCount")
    @ApiOperation("获取省级待单确认的入库单")
    public R incomeAuditCount()
    {
        Integer count=zdStockIncomeService
                .selectCount(new EntityWrapper<ZdStockIncomeEntity>()
                        .eq("org_code",getUser().getOrgCode())
                        .eq("status",1).eq("semester_code",sysSemesterService.getCurrentSemester().getSemesterCode()));
        return R.ok().put("count",count);
    }


    @GetMapping("/zydd/refundAuditCount")
    @ApiOperation("获取中央电大待审核退货单")
    public R zyddAuditCount(@RequestParam Map<String,Object> params)
    {
        params.put("status",Constant.REFUND_STATUS.CONFIRM);
        params.put("toOrgCode",getUser().getOrgCode());
        params.put("salesmancode",getUser().getUsername());
        params.put("semesterCode", sysSemesterService.getCurrentSemester().getSemesterCode());
        Integer count=zdRefundProvinceService.selectZyddCountByMap(params);
        return R.ok().put("count",count);
    }



}
