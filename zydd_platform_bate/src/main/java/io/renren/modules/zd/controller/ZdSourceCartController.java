package io.renren.modules.zd.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.FileUtil;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdSourceCartEntity;
import io.renren.modules.zd.form.ZdSourceCatFrom;
import io.renren.modules.zd.form.ZdSourceForm;
import io.renren.modules.zd.form.ZdSourceResourceForm;
import io.renren.modules.zd.service.ZdSourceCartService;
import io.renren.modules.zd.service.ZdSourceService;
import io.renren.modules.zd.vo.ZdSourceCartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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
import java.util.stream.Collectors;

@RestController
@Api("采购车接口")
@RequestMapping("zd/source/cart")
public class ZdSourceCartController extends AbstractController {
    @Autowired
    private ZdSourceCartService zdSourceCartService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private ZdSourceService zdSourceService;
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("sys:zdordercart:list")
    @ApiOperation("获得购物车列表")
    public R list(@RequestParam Map<String, Object> params){
        params.put("createBy",getUserId());
        params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
        List<ZdSourceCartVO> page = zdSourceCartService.queryList(params);
        return R.ok().put("list", page);
    }
    @PostMapping("/clear")
//    @RequiresPermissions("sys:zdordercart:list")
    @ApiOperation("清空采购车")
    public R clear(@RequestParam Map<String, Object> params){
        zdSourceCartService.delete(new EntityWrapper<ZdSourceCartEntity>().eq("create_by",getUserId()).eq("semester_code",sysSemesterService.getCurrentSemester().getSemesterCode()));
        return R.ok();
    }
    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    public R save(@RequestBody ZdSourceCatFrom zdCatFrom){
        zdCatFrom.setSysUserEntity(getUser());
        zdCatFrom.setSemesterCode(sysSemesterService.getCurrentSemester().getSemesterCode());
        zdSourceCartService.saveCatForm(zdCatFrom);
        return R.ok();
    }
    /**
     * 更新采购车
     */
    @PostMapping("/update")
    @ApiOperation("更新采购车")
    public R update(@RequestBody ZdSourceCartEntity zdSourceCartEntity){
        if(zdSourceCartEntity.getNum()<=0){
            return R.error("数量不能为0");
        }
        if(StringUtils.isBlank(zdSourceCartEntity.getId())){
            return R.error("参数异常");
        }
        ZdSourceCartEntity update=new ZdSourceCartEntity();
        update.setId(zdSourceCartEntity.getId());
        update.setNum(zdSourceCartEntity.getNum());
        zdSourceCartService.updateById(update);
        return R.ok();
    }
    /**
     * 更新采购车
     */
    /*@PostMapping("/update")
    @ApiOperation("更新采购车")
    public R update(@RequestBody ZdSourceCatFrom zdCatFrom){
        zdCatFrom.setSysUserEntity(getUser());
        zdSourceCartService.updateCatForm(zdCatFrom);
        return R.ok();
    }*/
    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("sys:zdordercart:delete")
    @ApiOperation("删除采购车")
    @SysLog("删除采购车")
    public R delete(@RequestBody DeleteForm deleteForm){
        zdSourceCartService.delete(new EntityWrapper<ZdSourceCartEntity>().eq("create_by",getUserId()).in("id",deleteForm.getIds()));
        return R.ok();
    }
    /**
     * 保存
     */
    @PostMapping("/submitOrder")
//    @RequiresPermissions("sys:zdordercart:save")
    @ApiOperation("提交采购车")
    @SysLog("提交采购车")
    public R submitOrder(@RequestBody DeleteForm deleteForm){
        SysUserEntity user=getUser();
        ZdSourceForm zdSourceForm=new ZdSourceForm();
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(user.getOrgCode());
        if(Constant.ORG_TYPE.SHENG.equals(sysOrgEntity.getOrgType())){
             zdSourceForm.setOrgCode(user.getOrgCode());
             zdSourceForm.setStatus(Constant.SOURCE_STATUS.SUBMIT);
        }else{
            SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(user.getOrgCode());
            if(sysOrgSettingEntity==null|| StringUtils.isBlank(sysOrgSettingEntity.getToOrgCode())){
                return R.error("提交失败，未查到单位设置的 上报单位。请联系管理员处理");
            }
            zdSourceForm.setOrgCode(sysOrgSettingEntity.getToOrgCode());
            zdSourceForm.setToOrgCode(user.getOrgCode());
            zdSourceForm.setStatus(Constant.SOURCE_STATUS.NEW);
        }
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        List<ZdSourceCartEntity> zdSourceCartEntities=zdSourceCartService.selectBatchIds(deleteForm.getIds());
        if(zdSourceCartEntities.size()>0){
            zdSourceForm.setNote(deleteForm.getNote());
            zdSourceForm.setRemark(deleteForm.getRemark());
            zdSourceForm.setSourceType(Constant.SOURCE_TYPE.RC);
            zdSourceForm.setSemesterCode(sysSemesterEntity.getSemesterCode());
            List<ZdSourceResourceForm> zdSourceResourceEntities=zdSourceCartEntities.stream().map(zdSourceCartEntity -> {
                if(zdSourceCartEntity.getStatus()==1){
                    throw new RRException("存在未确认的图书！");
                }
                ZdSourceResourceForm zdSourceResourceForm=new ZdSourceResourceForm();
                zdSourceResourceForm.setResourceId(zdSourceCartEntity.getResourceId());
                zdSourceResourceForm.setNum(zdSourceCartEntity.getNum());
                return zdSourceResourceForm;
            }).collect(Collectors.toList());
            zdSourceForm.setResourceForm(zdSourceResourceEntities);
            zdSourceService.saveSource(zdSourceForm);
            zdSourceCartService.deleteBatchIds(deleteForm.getIds());
        }

        return R.ok();
    }
    @Value("${fxfw.filePath}")
    private String filePath;
    @SysLog("导入采购车")
    //县级保存报订(根据教材)
    @PostMapping("/importCart")
    //@RequiresPermissions("zd:zdorder:save")
    @ApiOperation("导入采购车")
    public R importCart(@RequestParam("excelFile") MultipartFile file, HttpServletResponse response){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        String fileName= null;
        String date= DateUtils.format(new Date(),"/yyyy/MM/dd/");
        try {
            List<ZdSourceCartVO> list= FileUtil.importExcel(file,1,1, ZdSourceCartVO.class);
            String time= DateUtils.format(new Date(),"yyyyMMddHHmmssSSS");
            list=zdSourceCartService.importCart(list,getUser(),sysSemesterEntity.getSemesterCode());
            fileName = "";
            date="source"+date;
            SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
            fileName=sysOrgEntity.getOrgName()+time+"采购车导入结果.xls";
            //FileUtil.exportExcel(list,filePath+fileName,"报订规则导入结果",ZdMajorCourseResourceVO.class,fileName+".xls",response);
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(fileName,fileName),
                    ZdSourceCartVO.class, list);
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
