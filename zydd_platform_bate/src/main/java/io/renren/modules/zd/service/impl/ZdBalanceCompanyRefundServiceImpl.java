package io.renren.modules.zd.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.zd.dao.ZdBalanceCompanyRefundDao;
import io.renren.modules.zd.entity.ZdBalanceCompanyRefundEntity;
import io.renren.modules.zd.service.ZdBalanceCompanyRefundService;


@Service("zdBalanceCompanyRefundService")
public class ZdBalanceCompanyRefundServiceImpl extends ServiceImpl<ZdBalanceCompanyRefundDao, ZdBalanceCompanyRefundEntity> implements ZdBalanceCompanyRefundService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdBalanceCompanyRefundEntity> page = this.selectPage(
                new Query<ZdBalanceCompanyRefundEntity>(params).getPage(),
                new EntityWrapper<ZdBalanceCompanyRefundEntity>()
        );

        return new PageUtils(page);
    }

}
