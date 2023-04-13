package io.renren.modules.zd.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.*;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdCartEntity;
import io.renren.modules.zd.form.ZdCatFrom;
import io.renren.modules.zd.service.ZdCartService;
import io.renren.modules.zd.vo.CartResourceVO;
import io.renren.modules.zd.vo.ZdOrderCartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
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
@RequestMapping("zd/cart")
public class ZdOrderCartController extends AbstractController{
    /*@Autowired
    private ZdOrderCartService zdOrderCartService;

    *//**
     * 列表
     *//*
    @GetMapping("/list")
//    @RequiresPermissions("sys:zdordercart:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        params.put("createBy",getUserId());
        PageUtils page = zdOrderCartService.queryListPage(params);
        return R.ok().put("page", page);
    }
    *//**
     * 信息
     *//*
    @GetMapping("/info/{id}")
//    @RequiresPermissions("sys:zdordercart:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
        ZdOrderCartEntity zdOrderCart = zdOrderCartService.selectById(id);

        return R.ok().put("zdOrderCart", zdOrderCart);
    }

    *//**
     * 保存
     *//*
    @PostMapping("/save")
//    @RequiresPermissions("sys:zdordercart:save")
    @ApiOperation("保存")
    public R save(@RequestBody ZdCatFrom zdCatFrom){
        zdCatFrom.setSysUserEntity(getUser());
        zdOrderCartService.saveCatForm(zdCatFrom);
        return R.ok();
    }
*//**
     * 保存
     *//*
    @PostMapping("/submitOrder")
//    @RequiresPermissions("sys:zdordercart:save")
    @ApiOperation("保存")
    public R submitOrder(@RequestBody DeleteForm deleteForm){
        zdOrderCartService.submitOrder(deleteForm.getIds(),getUser(), Constant.ORDER_STATUS.NEW,deleteForm.getNote(),deleteForm.getRemark());
        return R.ok();
    }
    *//**
     * 保存
     *//*
    @PostMapping("/submitConfirmOrder")
//    @RequiresPermissions("sys:zdordercart:save")
    @ApiOperation("保存")
    public R submitConfirmOrder(@RequestBody DeleteForm deleteForm){
        zdOrderCartService.submitOrder(deleteForm.getIds(),getUser(), Constant.ORDER_STATUS.CONFIRM,deleteForm.getNote(),deleteForm.getRemark());
        return R.ok();
    }
    *//**
     * 修改
     *//*
    @PostMapping("/update")
//    @RequiresPermissions("sys:zdordercart:update")
    @ApiOperation("更新")
    public R update(@RequestBody ZdCatFrom zdCatFrom){
        zdCatFrom.setSysUserEntity(getUser());
			zdOrderCartService.updateCatForm(zdCatFrom);

        return R.ok();
    }

    *//**
     * 删除
     *//*
    @PostMapping("/delete")
//    @RequiresPermissions("sys:zdordercart:delete")
    @ApiOperation("删除")
    @SysLog("删除购物车")
    public R delete(@RequestBody DeleteForm deleteForm){
        logger.debug("废弃！删除购物车:{},操作人:{}", JSON.toJSONString(deleteForm.getIds()),getUser().getUsername());
        zdOrderCartService.deleteBatchIds(deleteForm.getIds());
        return R.ok();
    }
*/

    @Autowired
    private ZdCartService zdCartService;

    /**
     * 列表
     */
    @GetMapping("/v2/list")
//    @RequiresPermissions("sys:zdordercart:list")
    @ApiOperation("获取列表")
    public R v2List(@RequestParam Map<String, Object> params){
        params.put("createBy",getUserId());
        PageUtils page = zdCartService.queryListPage(params);
        return R.ok().put("page", page);
    }

    @PostMapping("/v2/clear")
//    @RequiresPermissions("sys:zdordercart:list")
    @ApiOperation("清空购物车")
    public R v2Clear(@RequestParam Map<String, Object> params){
        params.put("createBy",getUserId());
        zdCartService.delete(new EntityWrapper<ZdCartEntity>().eq("create_by",getUserId()));
        return R.ok();
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
    @SysLog("报订车生产订单")
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
    @SysLog("报订车生产订单并提交")
    public R v2SubmitConfirmOrder(@RequestBody DeleteForm deleteForm){
        zdCartService.submitOrder(deleteForm.getIds(),getUser(), Constant.ORDER_STATUS.CONFIRM,deleteForm.getNote(),deleteForm.getRemark());
        return R.ok();
    }
    @PostMapping("/v2/delete")
//    @RequiresPermissions("sys:zdordercart:delete")
    @ApiOperation("删除")
    @SysLog("删除购物车")
    public R v2Delete(@RequestBody DeleteForm deleteForm){
        List<ZdOrderCartVO> cartEntityList=zdCartService.selectVOByIds(deleteForm.getIds());
        logger.debug("删除购物车:{},操作人:{}",JSON.toJSONString(cartEntityList),getUser().getUsername());
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
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private SysOrgService sysOrgService;
    @Value("${fxfw.filePath}")
    private String filePath;
    @SysLog("导入购物车")
    //县级保存报订(根据教材)
    @PostMapping("/importCart")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("导入购物车")
    public R importCart(@RequestParam("excelFile") MultipartFile file, HttpServletResponse response){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        String fileName= null;
        String date= DateUtils.format(new Date(),"/yyyy/MM/dd/");
        try {
            List<CartResourceVO> list= FileUtil.importExcel(file,1,1, CartResourceVO.class);
            String time= DateUtils.format(new Date(),"yyyyMMddHHmmssSSS");
            list=zdCartService.importCart(list,getUser(),sysSemesterEntity.getSemesterCode());
            fileName = "";
            date="cart"+date;
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
            fileName=sysOrgEntity.getOrgName()+time+"购物车导入结果.xls";
            //FileUtil.exportExcel(list,filePath+fileName,"报订规则导入结果",ZdMajorCourseResourceVO.class,fileName+".xls",response);
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(fileName,fileName),
                    CartResourceVO.class, list);
            File savefile = new File(filePath+date);
            if (!savefile.exists()) {
                savefile.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(filePath+date+fileName);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("filePath",date+fileName);
    }

}
