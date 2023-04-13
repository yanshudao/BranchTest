package io.renren.modules.zd.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.*;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.entity.ZdRefundResourceDetailEntity;
import io.renren.modules.zd.entity.ZdSemesterRegEntity;
import io.renren.modules.zd.service.ZdSemesterRegService;
import io.renren.modules.zd.vo.CartResourceVO;
import io.renren.modules.zd.vo.ZdSemesterRegVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Api("注册人数管理")
@RequestMapping("zd/semesterReg")
public class ZdSemesterRegController extends AbstractController {

    @Autowired
    private ZdSemesterRegService zdSemesterRegService;
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysSemesterService sysSemesterService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params) {
        String orgCode = MapUtils.getString(params, "orgCode");
        if (StringUtils.isBlank(orgCode)) {
            orgCode = ShiroUtils.getUserEntity().getOrgCode();
        }
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester(orgCode);
        if(sysSemesterEntity!=null){
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        }
        SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingService.selectByOrg(orgCode);
        if (sysOrgSettingEntity != null && StringUtils.isNotBlank(sysOrgSettingEntity.getCustCode())) {
            params.put("custCode", sysOrgSettingEntity.getCustCode());
            PageUtils page = zdSemesterRegService.selectListPage(params);
            return R.ok().put("page", page);
        }else{
            Page page=new Query(params).getPage();
            return R.ok().put("page", page);
        }

    }

    @GetMapping("/rate")
    @ApiOperation("获得配置率")
    public R reate(@RequestParam Map<String, Object> params) {
        String orgCode = ShiroUtils.getUserEntity().getOrgCode();
//        SysOrgEntity sysOrgEntity = sysOrgService.selectByOrgCode(orgCode);
        SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingService.selectByOrg(orgCode);
       /* if (sysOrgSettingEntity != null && StringUtils.isNotBlank(sysOrgSettingEntity.getCustCode())) {
            params.put("custCode", sysOrgSettingEntity.getCustCode());
        } else {
            return R.ok();
        }*/
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        params.put("orgCode",orgCode);
        ZdSemesterRegVO zdSemesterRegVO=zdSemesterRegService.selectCurrentRate(params);
        return R.ok().put("rate", zdSemesterRegVO).put("orgSetting",sysOrgSettingEntity);
    }
    @GetMapping("/org/rate")
    @ApiOperation("获得单位当前配置率")
    public R orgRate(@RequestParam Map<String, Object> params) {
        String orgCode = MapUtils.getString(params,"orgCode","");
        if(StringUtils.isBlank(orgCode)){
            return R.error("未选择单位!");
        }
        SysOrgSettingEntity sysOrgSettingEntity = sysOrgSettingService.selectByOrg(orgCode);
//        SysOrgEntity sysOrgEntity = sysOrgService.selectByOrgCode(orgCode);
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester(orgCode);
        params.put("semesterCode", sysSemesterEntity.getSemesterCode());
        params.put("orgCode", orgCode);
        ZdSemesterRegVO zdSemesterRegVO=zdSemesterRegService.selectCurrentRate(params);
        return R.ok().put("rate", zdSemesterRegVO).put("orgSetting",sysOrgSettingEntity).put("semester",sysSemesterEntity);
    }
    @Value("${fxfw.filePath}")
    private String filePath;
    @SysLog("导入注册人数")
    //县级保存报订(根据教材)
    @PostMapping("/importReg")
    @ApiOperation("导入注册人数")
    public R importCart(@RequestParam("excelFile") MultipartFile file, HttpServletResponse response){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        String fileName= null;
        String date= DateUtils.format(new Date(),"/yyyy/MM/dd/");
        try {
//            ExcelDataHandle excelDataHandle=new ExcelDataHandle();
//            excelDataHandle.setNeedHandlerFields(new String[] { "学生类型" });
//            List<ZdSemesterRegEntity> list= FileUtil.importExcel(file,1,1, ZdSemesterRegEntity.class,excelDataHandle);
            List<ZdSemesterRegEntity> list= FileUtil.importExcel(file,0,1, ZdSemesterRegEntity.class,new ExcelDictHandlerImpl());
            String time= DateUtils.format(new Date(),"yyyyMMddHHmmssSSS");
            list=zdSemesterRegService.importReg(list,getUser(),sysSemesterEntity.getSemesterCode());
            fileName = "";
            date="semesterReg"+date;
            fileName=time+"注册人数导入结果.xls";

            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(fileName,fileName),
                    ZdSemesterRegEntity.class, list);
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

    @GetMapping("/exportReg")
    public void exportReg(@RequestParam Map<String,Object> params,HttpServletResponse response){
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        String fileName="";
        params.put("semesterCode",sysSemesterEntity.getSemesterCode());
        List<ZdSemesterRegEntity> list = zdSemesterRegService.selectAll(params);
        fileName="注册人数导出"+System.currentTimeMillis();
        FileUtil.exportExcel(list,fileName,fileName,ZdSemesterRegEntity.class,fileName+".xls",response);

    }
    @PostMapping("/delete")
    @SysLog("删除注册人数")
    public R deleteReg(@RequestBody ZdSemesterRegVO zdSemesterRegVO){
        if(StringUtils.isBlank(zdSemesterRegVO.getOrgCode())){
            return R.error("未指定单位");
        }
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(zdSemesterRegVO.getOrgCode());
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        zdSemesterRegService.remove(sysOrgSettingEntity.getCustCode(),sysSemesterEntity.getSemesterCode());

        return R.ok();
    }
    @PostMapping("/deleteAll")
    @SysLog("清空注册人数")
    public R deleteAll(@RequestBody ZdSemesterRegVO zdSemesterRegVO){
        if(StringUtils.isBlank(zdSemesterRegVO.getOrgCode())){
            return R.error("未指定单位");
        }
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        zdSemesterRegService.delete(new EntityWrapper<ZdSemesterRegEntity>().eq("semester_code",sysSemesterEntity.getSemesterCode()));
        return R.ok();
    }
}
