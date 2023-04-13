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
import io.renren.modules.subject.entity.SubjectResourceChangeEntity;
import io.renren.modules.subject.service.SubjectResourceChangeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 换版教材
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@RestController
@Api("换版教材接口")
@RequestMapping("subject/subjectresourcechange")
public class SubjectResourceChangeController {
    @Autowired
    private SubjectResourceChangeService subjectResourceChangeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("subject:subjectresourcechange:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = subjectResourceChangeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("subject:subjectresourcechange:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			SubjectResourceChangeEntity subjectResourceChange = subjectResourceChangeService.selectById(id);

        return R.ok().put("subjectResourceChange", subjectResourceChange);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("subject:subjectresourcechange:save")
    @ApiOperation("保存")
    public R save(@RequestBody SubjectResourceChangeEntity subjectResourceChange){
			subjectResourceChangeService.insert(subjectResourceChange);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("subject:subjectresourcechange:update")
    @ApiOperation("更新")
    public R update(@RequestBody SubjectResourceChangeEntity subjectResourceChange){
			subjectResourceChangeService.updateById(subjectResourceChange);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("subject:subjectresourcechange:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody String[] ids){
			subjectResourceChangeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
