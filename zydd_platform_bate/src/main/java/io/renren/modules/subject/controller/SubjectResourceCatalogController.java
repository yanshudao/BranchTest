package io.renren.modules.subject.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import io.renren.modules.subject.entity.SubjectResourceCatalogEntity;
import io.renren.modules.subject.service.SubjectResourceCatalogService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 教材目录
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@RestController
@Api("教材目录接口")
@RequestMapping("subject/subjectresourcecatalog")
public class SubjectResourceCatalogController {
    @Autowired
    private SubjectResourceCatalogService subjectResourceCatalogService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("subject:subjectresourcecatalog:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = subjectResourceCatalogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("subject:subjectresourcecatalog:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			SubjectResourceCatalogEntity subjectResourceCatalog = subjectResourceCatalogService.selectById(id);

        return R.ok().put("subjectResourceCatalog", subjectResourceCatalog);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("subject:subjectresourcecatalog:save")
    @ApiOperation("保存")
    public R save(@RequestBody SubjectResourceCatalogEntity subjectResourceCatalog){
			subjectResourceCatalogService.insert(subjectResourceCatalog);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("subject:subjectresourcecatalog:update")
    @ApiOperation("更新")
    public R update(@RequestBody SubjectResourceCatalogEntity subjectResourceCatalog){
			subjectResourceCatalogService.updateById(subjectResourceCatalog);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("subject:subjectresourcecatalog:delete")
    @ApiOperation("更新")
    public R delete(@RequestBody String[] ids){
			subjectResourceCatalogService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
