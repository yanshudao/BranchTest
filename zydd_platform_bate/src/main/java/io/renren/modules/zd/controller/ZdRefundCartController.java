package io.renren.modules.zd.controller;

import com.google.common.collect.Lists;
import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdRefundCartEntity;
import io.renren.modules.zd.entity.ZdRefundSupplierEntity;
import io.renren.modules.zd.form.RefundCartFrom;
import io.renren.modules.zd.form.SubmitZdRefundCartFrom;
import io.renren.modules.zd.form.ZdRefundCartFrom;
import io.renren.modules.zd.service.ZdRefundCartService;
import io.renren.modules.zd.service.ZdRefundCountryService;
import io.renren.modules.zd.service.ZdRefundProvinceService;
import io.renren.modules.zd.service.ZdSemesterRegService;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.ZdRefundCartVO;
import io.renren.modules.zd.vo.ZdSemesterRegVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-20 15:56:23
 */
@RestController
@Api("县级退货车")
@RequestMapping("zd/refundCart")
public class ZdRefundCartController extends AbstractController{
    @Autowired
    private ZdRefundCartService zdRefundCartService;
    @Autowired
    private ZdSemesterRegService zdSemesterRegService;
    @Autowired
    private ZdRefundCountryService zdRefundCountryService;
    @Autowired
    private ZdRefundProvinceService zdRefundProvinceService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private SubjectResourceService subjectResourceService;
    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("sys:zdrefundcart:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        params.put("createBy",getUserId());
        List<ZdRefundCartVO> list = zdRefundCartService.selectList(params);
        if(list.size()>0){
            Map<String,List<ZdRefundCartVO>> map=list.stream().filter(item->StringUtils.isNotBlank(item.getRefundSemesterCode())).collect(Collectors.groupingBy(ZdRefundCartVO::getRefundSemesterCode));
            Map<String,String> semesterMap=list.stream().collect(Collectors.toMap(ZdRefundCartVO::getRefundSemesterCode, ZdRefundCartVO::getRefundSemesterName,(entity1, entity2) -> entity1));
            Map<String,ZdSemesterRegVO> rateMap=new HashMap<>();
            map.keySet().stream().forEach(item->{
                params.put("semesterCode",item);
                params.put("orgCode",getUser().getOrgCode());
                ZdSemesterRegVO zdSemesterRegVO=zdSemesterRegService.selectCurrentRate(params);
                rateMap.put(item,zdSemesterRegVO);
            });
            return R.ok().put("data", map).put("semesterMap", semesterMap).put("semesterList",map.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())).put("rateMap",rateMap);

        }else{
            return R.ok();
        }
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("sys:zdrefundcart:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			ZdRefundCartEntity zdRefundCart = zdRefundCartService.selectById(id);

        return R.ok().put("zdRefundCart", zdRefundCart);
    }

    /**
     * 保存
     */
    /*@PostMapping("/save")
//    @RequiresPermissions("sys:zdrefundcart:save")
    @ApiOperation("分校-保存")
    @SysLog("分校-保存教材到退货车")
    public R save(@RequestBody ZdRefundCartFrom cartFrom){
		zdRefundCartService.saveCartForm(cartFrom);

        return R.ok();
    }*/
    @PostMapping("/v2/save")
//    @RequiresPermissions("sys:zdrefundcart:save")
    @ApiOperation("分校-保存")
    @SysLog("分校-保存教材到退货车")
    public R v2Save(@RequestBody RefundCartFrom  refundCartFroms){
		zdRefundCartService.saveRefundForm(refundCartFroms);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("sys:zdrefundcart:update")
    @ApiOperation("更新")
    @SysLog("更新单条退货车")
    public R update(@RequestBody ZdRefundCartEntity zdRefundCart){

        ZdRefundCartEntity zdRefundCartEntity=zdRefundCartService.selectById(zdRefundCart.getId());
        SysUserEntity sysUserEntity=getUser();
        if(zdRefundCart.getCatNum()<=0)
        {
            return  R.error("数量不能小于等于0");
        }
        if(zdRefundCart.getCatNum()<zdRefundCartEntity.getCatNum()) {
            zdRefundCartService.updateById(zdRefundCart);
        }else {
//            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
//            List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();

            RefundLimitInfoVO refundLimitInfoVo=zdRefundCountryService.getRefundLimitInfo2(sysUserEntity.getOrgCode(),zdRefundCartEntity.getRefundSemesterCode());
            Map<String, Object> map=new HashMap<>();
            map.put("notInIds", Lists.newArrayList(zdRefundCart.getId()));
            BigDecimal cartTotal=zdRefundCartService.selectCartTotal(map,zdRefundCartEntity.getRefundSemesterCode());
            if(cartTotal==null){
                cartTotal=new BigDecimal("0.00");
            }
            SubjectResourceEntity subjectResourceEntity=subjectResourceService.selectById(zdRefundCartEntity.getResourceId());
            cartTotal=cartTotal.add(new BigDecimal(subjectResourceEntity.getPrice()).multiply(new BigDecimal(zdRefundCart.getCatNum())));
            if(refundLimitInfoVo.getAllowRefundTotal().subtract(refundLimitInfoVo.getAuditRefundMayang()).subtract(cartTotal).compareTo(BigDecimal.ZERO) < 0){
                throw new RRException("超出可退码洋！");
            }
            zdRefundCartService.updateById(zdRefundCart);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("sys:zdrefundcart:delete")
    @ApiOperation("删除")
    @SysLog("删除退货车")
    public R delete(@RequestBody DeleteForm deleteForm){
			zdRefundCartService.deleteBatchIds(deleteForm.getIds());

        return R.ok();
    }
   /* @PostMapping("/submitOrder2")
//    @RequiresPermissions("sys:zdrefundcart:save")
    @ApiOperation("提交退货车订单")
    @SysLog("提交退货车订单")
    public R submitOrder(SubmitZdRefundCartFrom orderForm){
        zdRefundCartService.submitOrder2(orderForm);

        return R.ok();
    }*/
   @PostMapping("/submitOrder2")
//    @RequiresPermissions("sys:zdrefundcart:save")
    @ApiOperation("提交退货车订单")
    @SysLog("提交退货车订单")
    public R submitOrder(SubmitZdRefundCartFrom orderForm){
        zdRefundCartService.submitOrder2(orderForm);

        return R.ok();
    }

    /**
     * 保存
     */
    @PostMapping("/province/save")
//    @RequiresPermissions("sys:zdrefundcart:save")
    @ApiOperation("省级-保存")
    @SysLog("省级-保存教材到退货车")
    public R provinceSave(@RequestBody ZdRefundCartFrom cartFrom){
        if(StringUtils.isBlank(cartFrom.getRefundSemesterCode())){
            return R.error("请选择报订季");
        }
        zdRefundCartService.saveProvinceCartForm(cartFrom);
        return R.ok();
    }
    @PostMapping("/province/update")
//    @RequiresPermissions("sys:zdrefundcart:update")
    @ApiOperation("省级更新")
    @SysLog("省级更新单条退货车")
    public R provinceUpdate(@RequestBody ZdRefundCartEntity zdRefundCart){
        ZdRefundCartEntity zdRefundCartEntity=zdRefundCartService.selectById(zdRefundCart.getId());
        Assert.isNull(zdRefundCartEntity,"未找到详情单");
        Assert.isBlank(zdRefundCartEntity.getRefundSemesterCode(),"历史单据无法更新，请删除后重新添加");
        Assert.isBlank(zdRefundCartEntity.getSupplierId(),"历史单据无法更新，请删除后重新添加");
        Assert.isBlank(zdRefundCartEntity.getOrgCode(),"历史单据无法更新，请删除后重新添加");
        SysUserEntity sysUserEntity=getUser();
        if(zdRefundCart.getCatNum()<=0)
        {
            return  R.error("数量不能小于等于0");
        }
        if(zdRefundCart.getCatNum()<zdRefundCartEntity.getCatNum()) {
            zdRefundCartService.updateById(zdRefundCart);
        }else {
//            SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
//            List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();
            RefundLimitInfoVO refundLimitInfoVo=zdRefundProvinceService.getRefundLimitInfo2(zdRefundCartEntity.getRefundSemesterCode(),sysUserEntity.getOrgCode(),zdRefundCartEntity.getSupplierId());
            Map<String, Object> map=new HashMap<>();
            map.put("notInIds", Lists.newArrayList(zdRefundCart.getId()));
            map.put("orgCode",getUser().getOrgCode());
            BigDecimal cartTotal=zdRefundCartService.selectCartTotal(map,zdRefundCartEntity.getRefundSemesterCode());
            if(cartTotal==null){
                cartTotal=new BigDecimal("0.00");
            }
            SubjectResourceEntity subjectResourceEntity=subjectResourceService.selectById(zdRefundCartEntity.getResourceId());
            cartTotal=cartTotal.add(new BigDecimal(subjectResourceEntity.getPrice()).multiply(new BigDecimal(zdRefundCart.getCatNum())));
            if(refundLimitInfoVo.getAllowPublishMayang().subtract(refundLimitInfoVo.getAuditRefundMayang()).subtract(cartTotal).compareTo(BigDecimal.ZERO) < 0){
                throw new RRException("超出可退码洋！");
            }
            zdRefundCartService.updateById(zdRefundCart);
        }
        return R.ok();
    }
    @PostMapping("/province/submitOrder")
//    @RequiresPermissions("sys:zdrefundcart:save")
    @ApiOperation("提交退货车订单")
    @SysLog("提交退货车订单")
    public R provinceSubmitOrder(@RequestBody SubmitZdRefundCartFrom orderForm){
        if(StringUtils.isBlank(orderForm.getSupplierId())){
            return R.error("供应商不能为空");
        }
        zdRefundCartService.provinceSubmitOrder2(orderForm);
        return R.ok();
    }


    @GetMapping("/listSupplier")
//    @RequiresPermissions("sys:zdrefundcart:list")
    @ApiOperation("获得供应商列表")
    public R listSupplier(){
//        params.put("createBy",getUserId());
        List<SysCompanyEntity> list= zdRefundCartService.selectSuppliers(getUserId());


        return R.ok().put("data", list);
    }
}
