package io.renren.modules.zd.controller.v2;

import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.zd.form.ZdCatFrom;
import io.renren.modules.zd.service.ZdCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-09-07 16:30:50
 */
@RestController
@Api("购物车接口")
@RequestMapping("zd/sheng/cart")
public class ZdShengOrderCartController extends AbstractController{
//    @Autowired
//    private ZdOrderCartService zdOrderCartService;
    @Autowired
    private ZdCartService zdCartService;

    @GetMapping("/v2/listOrg")
//    @RequiresPermissions("sys:zdordercart:list")
    @ApiOperation("获取列表")
    public R v2ListOrg(@RequestParam Map<String, Object> params){
        params.put("createBy",getUserId());
        List list = zdCartService.queryOrgList(params);
        return R.ok().put("list", list);
    }

    /**
     * 列表
     */
    @GetMapping("/v2/list")
//    @RequiresPermissions("sys:zdordercart:list")
    @ApiOperation("获取列表")
    public R v2List(@RequestParam Map<String, Object> params,String orgCode){
        params.put("createBy",getUserId());
        PageUtils page = zdCartService.queryListPage(params);
        return R.ok().put("page", page);
    }




    /**
     * 保存
     */
    @PostMapping("/v2/save")
//    @RequiresPermissions("sys:zdordercart:save")
    @ApiOperation("保存")
    public R v2Save(@RequestBody ZdCatFrom zdCatFrom){
        zdCatFrom.setSysUserEntity(getUser());
        zdCartService.saveCatFormByRelation(zdCatFrom);
        return R.ok();
    }
    /**
     * 保存
     */
    @PostMapping("/v2/submitOrder")
//    @RequiresPermissions("sys:zdordercart:save")
    @ApiOperation("保存")
    public R v2SubmitOrder(@RequestBody DeleteForm deleteForm){
        zdCartService.submitOrder(deleteForm.getIds(),getUser(), Constant.ORDER_STATUS.NEW,deleteForm.getNote(),deleteForm.getRemark());
        return R.ok();
    }
    /**
     * 保存
     */
    @PostMapping("/v2/submitConfirmOrder")
//    @RequiresPermissions("sys:zdordercart:save")
    @ApiOperation("保存")
    public R v2SubmitConfirmOrder(@RequestBody DeleteForm deleteForm){
        zdCartService.submitOrder(deleteForm.getIds(),getUser(), Constant.ORDER_STATUS.CONFIRM,deleteForm.getNote(),deleteForm.getRemark());
        return R.ok();
    }
    @PostMapping("/v2/delete")
//    @RequiresPermissions("sys:zdordercart:delete")
    @ApiOperation("删除")
    @SysLog("删除购物车")
    public R v2Delete(@RequestBody DeleteForm deleteForm){
        zdCartService.deleteBatchIds(deleteForm.getIds());
        return R.ok();
    }
    @PostMapping("/v2/update")
//    @RequiresPermissions("sys:zdordercart:update")
    @ApiOperation("更新")
    public R v2Update(@RequestBody ZdCatFrom zdCatFrom){
        zdCatFrom.setSysUserEntity(getUser());
        zdCartService.updateCatForm(zdCatFrom);

        return R.ok();
    }


}
