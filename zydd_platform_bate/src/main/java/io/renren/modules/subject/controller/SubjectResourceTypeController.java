package io.renren.modules.subject.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.subject.entity.SubjectResourceTypeEntity;
import io.renren.modules.subject.service.SubjectResourceTypeService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api("教材类型接口")
@RequestMapping("subject/resourceType")
public class SubjectResourceTypeController extends AbstractController {
    @Autowired
    private SubjectResourceTypeService subjectResourceTypeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("subject:subjectresourcecatalog:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = subjectResourceTypeService.queryPage(params);

        return R.ok().put("page", page);
    }
    @SysLog("删除教材类型")
    @PostMapping("/delete")
    public R delete(@RequestBody DeleteForm deleteForm){
        if(CollectionUtils.isEmpty(deleteForm.getIds())){
            return R.error("请选择要删除的数据！");
        }
        subjectResourceTypeService.deleteBatchIds(deleteForm.getIds());
        return R.ok();
    }
    @PostMapping("/save")
    @ApiOperation("添加教材类型")
    public R save(@RequestBody SubjectResourceTypeEntity subjectResourceTypeEntity){
        if(StringUtils.isEmpty(subjectResourceTypeEntity.getName())){
            return R.error("请输入类型名称");
        }
        subjectResourceTypeService.insert(subjectResourceTypeEntity);
        return R.ok();
    }
}
