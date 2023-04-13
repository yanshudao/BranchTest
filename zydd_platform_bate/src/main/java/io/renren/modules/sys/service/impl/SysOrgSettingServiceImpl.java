package io.renren.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysOrgSettingDao;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.service.SysOrgSettingService;
import org.springframework.transaction.annotation.Transactional;


@Service("sysOrgSettingService")
public class SysOrgSettingServiceImpl extends ServiceImpl<SysOrgSettingDao, SysOrgSettingEntity> implements SysOrgSettingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysOrgSettingEntity> page = this.selectPage(
                new Query<SysOrgSettingEntity>(params).getPage(),
                new EntityWrapper<SysOrgSettingEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public SysOrgSettingEntity selectByOrg(String orgCode) {
        return baseMapper.selectByOrg(orgCode);
    }

    @Override
    @Transactional
    public int updateRateConfig(String status, String rateConfig) {
        return baseMapper.updateRateConfig(status,rateConfig);
    }

}
