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
import io.renren.modules.subject.entity.SubjectResourceChangeOrgEntity;
import io.renren.modules.subject.service.SubjectResourceChangeOrgService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 换版教材记录
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@RestController
@Api("换版教材记录接口")
@RequestMapping("subject/subjectresourcechangeorg")
public class SubjectResourceChangeOrgController {
    @Autowired
    private SubjectResourceChangeOrgService subjectResourceChangeOrgService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("subject:subjectresourcechangeorg:list")
    @ApiOperation("获取列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = subjectResourceChangeOrgService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("subject:subjectresourcechangeorg:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
			SubjectResourceChangeOrgEntity subjectResourceChangeOrg = subjectResourceChangeOrgService.selectById(id);

        return R.ok().put("subjectResourceChangeOrg", subjectResourceChangeOrg);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("subject:subjectresourcechangeorg:save")
    @ApiOperation("保存")
    public R save(@RequestBody SubjectResourceChangeOrgEntity subjectResourceChangeOrg){
			subjectResourceChangeOrgService.insert(subjectResourceChangeOrg);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("subject:subjectresourcechangeorg:update")
    public R update(@RequestBody SubjectResourceChangeOrgEntity subjectResourceChangeOrg){
			subjectResourceChangeOrgService.updateById(subjectResourceChangeOrg);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("subject:subjectresourcechangeorg:delete")
    public R delete(@RequestBody String[] ids){
			subjectResourceChangeOrgService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
