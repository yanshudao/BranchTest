package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysOrgAreaDao;
import io.renren.modules.sys.entity.SysOrgAreaEntity;
import io.renren.modules.sys.service.SysOrgAreaService;


@Service("sysOrgAreaService")
public class SysOrgAreaServiceImpl extends ServiceImpl<SysOrgAreaDao, SysOrgAreaEntity> implements SysOrgAreaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysOrgAreaEntity> page=new Query<SysOrgAreaEntity>(params).getPage();
        page.setRecords(baseMapper.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    public List<SysOrgAreaEntity> queryAll(Map<String, Object> params) {
        return baseMapper.selectListPage(params);
    }

    @Override
    public SysOrgAreaEntity selectByCode(String areaCode) {
        return baseMapper.selectByCode(areaCode);
    }

}
