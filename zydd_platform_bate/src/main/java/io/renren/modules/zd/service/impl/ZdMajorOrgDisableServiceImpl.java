package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.dao.ZdMajorOrgDisableDao;
import io.renren.modules.zd.entity.ZdMajorOrg;
import io.renren.modules.zd.entity.ZdMajorOrgDisable;
import io.renren.modules.zd.service.ZdMajorOrgDisableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("ZdMajorOrgDisableService")
public class ZdMajorOrgDisableServiceImpl extends ServiceImpl<ZdMajorOrgDisableDao, ZdMajorOrgDisable> implements ZdMajorOrgDisableService {


    @Override
    public R insertIgnoreByBatch(List<ZdMajorOrg> zdMajorOrgs, String orgCode) {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        baseMapper.insertIgnoreByBatch(zdMajorOrgs,orgCode,sysUserEntity.getUserId());
        return R.ok();
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query(params).getPage();
        page.setRecords(baseMapper.selectPageList(page,params));
        return new PageUtils(page);
    }
    @Override
    public PageUtils queryProvincePage(Map<String, Object> params) {
        Page page=new Query(params).getPage();
        page.setRecords(baseMapper.selectProvincePageList(page,params));
        return new PageUtils(page);
    }

    @Override
    public int selectInsert(Map<String, Object> insertMap) {
        return baseMapper.selectInsert(insertMap);
    }
}
