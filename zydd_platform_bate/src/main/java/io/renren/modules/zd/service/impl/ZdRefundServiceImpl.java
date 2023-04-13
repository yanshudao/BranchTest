package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.zd.dao.ZdRefundDao;
import io.renren.modules.zd.entity.ZdRefundEntity;
import io.renren.modules.zd.service.ZdRefundService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("zdRefundService")
public class ZdRefundServiceImpl extends ServiceImpl<ZdRefundDao, ZdRefundEntity> implements ZdRefundService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdRefundEntity> page = this.selectPage(
                new Query<ZdRefundEntity>(params).getPage(),
                new EntityWrapper<ZdRefundEntity>()
        );

        return new PageUtils(page);
    }



}
