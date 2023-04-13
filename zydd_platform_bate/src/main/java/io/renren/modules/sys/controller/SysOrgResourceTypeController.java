package io.renren.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.R;
import io.renren.modules.subject.entity.SubjectResourceTypeEntity;
import io.renren.modules.subject.service.SubjectResourceTypeService;
import io.renren.modules.sys.form.SysOrgResourceTypeForm;
import io.renren.modules.sys.service.SysOrgResourceTypeService;
import io.renren.modules.sys.vo.SysOrgResourceTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api("教材类型")
@RequestMapping("sys/sysorg/resourceType")
public class SysOrgResourceTypeController {
    @Autowired
    private SysOrgResourceTypeService sysOrgResourceTypeService;

    @Autowired
    private SubjectResourceTypeService subjectResourceTypeService;
    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("获得已保存的教材类型")
    public R list(@RequestParam String orgCode){
        Map<String,Object> queryParams=new HashMap<>();
        queryParams.put("orgCode",orgCode);
        List<SysOrgResourceTypeVO> resourceTypeVOList=sysOrgResourceTypeService.selectAll(queryParams);
        return R.ok().put("data", resourceTypeVOList);
    }
    @GetMapping("/listAll")
    @ApiOperation("获得所有教材类型")
    public R listAll(@RequestParam(required = false) String name){
        List<SubjectResourceTypeEntity> list=subjectResourceTypeService.selectList(new EntityWrapper<SubjectResourceTypeEntity>().like(StringUtils.isNotBlank(name),"name",name));
        return R.ok().put("data", list);
    }
    @PostMapping("/delete")
    @ApiOperation("删除设置的教材类型")
    public R delete(@RequestBody DeleteForm deleteForm){
        if(CollectionUtils.isEmpty(deleteForm.getIds())){
            return R.error("请选择要删除的数据！");
        }
        sysOrgResourceTypeService.deleteBatchIds(deleteForm.getIds());
        return R.ok();
    }
    @PostMapping("/save")
    @ApiOperation("保存设置的教材类型")
    public R save(@RequestBody SysOrgResourceTypeForm resourceTypeForm){
        if(CollectionUtils.isEmpty(resourceTypeForm.getIdList())){
            return R.error("请选择类型！");
        }
        if(StringUtils.isBlank(resourceTypeForm.getOrgCode())){
            return R.error("请选择一个单位！");
        }
        sysOrgResourceTypeService.save(resourceTypeForm);
        return R.ok();
    }

}
