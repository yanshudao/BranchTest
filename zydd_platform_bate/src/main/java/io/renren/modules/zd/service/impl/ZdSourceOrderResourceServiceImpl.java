package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.zd.dao.ZdSourceOrderResourceDao;
import io.renren.modules.zd.entity.ZdSourceOrderResourceEntity;
import io.renren.modules.zd.service.ZdSourceOrderResourceService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("zdSourceOrderResourceService")
public class ZdSourceOrderResourceServiceImpl extends ServiceImpl<ZdSourceOrderResourceDao, ZdSourceOrderResourceEntity> implements ZdSourceOrderResourceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdSourceOrderResourceEntity> page = this.selectPage(
                new Query<ZdSourceOrderResourceEntity>(params).getPage(),
                new EntityWrapper<ZdSourceOrderResourceEntity>()
        );

        return new PageUtils(page);
    }

}
