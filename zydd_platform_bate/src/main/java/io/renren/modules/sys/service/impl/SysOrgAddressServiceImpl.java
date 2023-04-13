package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysOrgAddressDao;
import io.renren.modules.sys.entity.SysOrgAddressEntity;
import io.renren.modules.sys.service.SysOrgAddressService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysOrgAddressService")
public class SysOrgAddressServiceImpl extends ServiceImpl<SysOrgAddressDao, SysOrgAddressEntity> implements SysOrgAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysOrgAddressEntity> page = this.selectPage(
                new Query<SysOrgAddressEntity>(params).getPage(),
                new EntityWrapper<SysOrgAddressEntity>()
        );

        return new PageUtils(page);
    }


}
