package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.zd.dao.ZdRefundResourceDao;
import io.renren.modules.zd.entity.ZdRefundResourceEntity;
import io.renren.modules.zd.service.ZdRefundResourceService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("zdRefundResourceService")
public class ZdRefundResourceServiceImpl extends ServiceImpl<ZdRefundResourceDao, ZdRefundResourceEntity> implements ZdRefundResourceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdRefundResourceEntity> page = this.selectPage(
                new Query<ZdRefundResourceEntity>(params).getPage(),
                new EntityWrapper<ZdRefundResourceEntity>()
        );

        return new PageUtils(page);
    }

}
