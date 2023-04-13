package io.renren.modules.zd.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.zd.dao.ZdRefundSupplierResourceDao;
import io.renren.modules.zd.entity.ZdRefundSupplierResourceEntity;
import io.renren.modules.zd.service.ZdRefundSupplierResourceService;


@Service("zdRefundSupplierResourceService")
public class ZdRefundSupplierResourceServiceImpl extends ServiceImpl<ZdRefundSupplierResourceDao, ZdRefundSupplierResourceEntity> implements ZdRefundSupplierResourceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdRefundSupplierResourceEntity> page = this.selectPage(
                new Query<ZdRefundSupplierResourceEntity>(params).getPage(),
                new EntityWrapper<ZdRefundSupplierResourceEntity>()
        );

        return new PageUtils(page);
    }

}
