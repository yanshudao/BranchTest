package io.renren.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysNoticeEntity;
import io.renren.modules.sys.entity.SysNoticeOrgEntity;
import io.renren.modules.sys.entity.SysNoticeRecordEntity;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.service.SysNoticeOrgService;
import io.renren.modules.sys.service.SysNoticeRecordService;
import io.renren.modules.sys.service.SysNoticeService;
import io.renren.modules.sys.service.SysOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-22 15:57:47
 */
@RestController
@Api("公告接口")
@RequestMapping("sys/notice")
public class SysNoticeController extends AbstractController{
    @Autowired
    private SysNoticeService sysOrgNoticeService;
    @Autowired
    private SysNoticeRecordService sysNoticeRecordService;
    @Autowired
    private SysNoticeOrgService sysOrgNoticeOrgService;
    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("sys:sysorgnotice:list")
    @ApiOperation("查询上级发布的公告")
    public R list(@RequestParam Map<String, Object> params){
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
        params.put("orgId",sysOrgEntity.getId());
        params.put("orgCode",sysOrgEntity.getOrgCode());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("isShow",1);
        params.put("show",1);
        PageUtils page = sysOrgNoticeService.queryPage(params);
        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @PostMapping("/getNoRead")
//    @RequiresPermissions("sys:sysorgnotice:list")
    @ApiOperation("查询未读消息")
    public R getNoRead(@RequestBody Map<String, Object> params){
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
        params.put("orgId",sysOrgEntity.getId());
        params.put("orgCode",sysOrgEntity.getOrgCode());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("isShow",1);
        params.put("isRead",1);
        params.put("userId",getUserId());
        params.put("show",1);
        SysNoticeEntity sysOrgNotice=sysOrgNoticeService.getNoRead(params);
        if(sysOrgNotice==null){
            return R.error("沒有更多未读消息");
        }
        SysNoticeRecordEntity sysNoticeRecordEntity=new SysNoticeRecordEntity();
        sysNoticeRecordEntity.setNoticeId(sysOrgNotice.getId());
        sysNoticeRecordEntity.setUserId(getUserId()+"");
        sysNoticeRecordEntity.setCreateTime(new Date());
        sysNoticeRecordService.insert(sysNoticeRecordEntity);
        return R.ok().put("sysOrgNotice", sysOrgNotice);
    }
    @PostMapping("/countNoRead")
//    @RequiresPermissions("sys:sysorgnotice:list")
    @ApiOperation("查询未读消息数量")
    public R countNoRead(@RequestBody Map<String, Object> params){
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
        params.put("orgId",sysOrgEntity.getId());
        params.put("orgCode",sysOrgEntity.getOrgCode());
        params.put("parentOrgCodes",sysOrgEntity.getParentCodes());
        params.put("isShow",1);
        params.put("show",1);
        params.put("isRead",1);
        params.put("userId",getUserId());
        int count=sysOrgNoticeService.countNoRead(params);
        return R.ok().put("count", count);
    }

    /**
     * 列表
     */
    @GetMapping("/listNotice")
//    @RequiresPermissions("sys:sysorgnotice:list")
    @ApiOperation("查询本级发布的公告")
    public R listNotice(@RequestParam Map<String, Object> params){
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(getUser().getOrgCode());
        params.put("createOrgCode",sysOrgEntity.getOrgCode());
        PageUtils page = sysOrgNoticeService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("sys:sysorgnotice:info")
    @ApiOperation("获取详情")
    public R info(@PathVariable("id") String id){
        SysNoticeEntity sysOrgNotice = sysOrgNoticeService.selectById(id);
        List<SysNoticeOrgEntity> list=sysOrgNoticeOrgService.selectList(new EntityWrapper<SysNoticeOrgEntity>().eq("notice_id",id));
        List<String> orgList=list.stream().map(SysNoticeOrgEntity::getOrgId).collect(Collectors.toList());
        sysOrgNotice.setOrgList(orgList);
        return R.ok().put("sysOrgNotice", sysOrgNotice);
    }

    /**
     * 保存
     */
    @SysLog("保存单位公告")
    @PostMapping("/save")
//    @RequiresPermissions("sys:sysorgnotice:save")
    @ApiOperation("保存")
    public R save(@RequestBody SysNoticeEntity sysOrgNotice){
        sysOrgNotice.setOrgCode(getUser().getOrgCode());
        if(sysOrgNotice.getShowScope()==1){
          if(CollectionUtils.isEmpty(sysOrgNotice.getOrgList())){
              return R.error("请选择展示范围！");
          }
        }
		sysOrgNoticeService.saveOrUpdate(sysOrgNotice);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("更新单位公告")
    @PostMapping("/update")
//    @RequiresPermissions("sys:sysorgnotice:update")
    @ApiOperation("更新")
    public R update(@RequestBody SysNoticeEntity sysOrgNotice){
        if(sysOrgNotice.getShowScope()==1){
            if(CollectionUtils.isEmpty(sysOrgNotice.getOrgList())){
                return R.error("请选择展示范围！");
            }
        }
        sysOrgNoticeService.saveOrUpdate(sysOrgNotice);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除单位公告")
    @PostMapping("/delete")
//    @RequiresPermissions("sys:sysorgnotice:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody DeleteForm deleteForm){
			sysOrgNoticeService.deleteBatchIds(deleteForm.getIds());

        return R.ok();
    }



}
