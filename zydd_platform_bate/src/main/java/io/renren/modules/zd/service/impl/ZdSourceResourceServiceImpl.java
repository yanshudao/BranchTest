package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.zd.dao.ZdSourceResourceDao;
import io.renren.modules.zd.entity.ZdSourceResourceEntity;
import io.renren.modules.zd.service.ZdSourceResourceService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("zdSourceResourceService")
public class ZdSourceResourceServiceImpl extends ServiceImpl<ZdSourceResourceDao, ZdSourceResourceEntity> implements ZdSourceResourceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdSourceResourceEntity> page = this.selectPage(
                new Query<ZdSourceResourceEntity>(params).getPage(),
                new EntityWrapper<ZdSourceResourceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int selectLeftCount(String sourceId) {
        return baseMapper.selectLeftCount(sourceId);
    }


}
