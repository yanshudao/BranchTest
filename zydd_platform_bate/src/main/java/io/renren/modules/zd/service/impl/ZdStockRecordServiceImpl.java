package io.renren.modules.zd.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.zd.dao.ZdStockRecordDao;
import io.renren.modules.zd.entity.ZdStockRecordEntity;
import io.renren.modules.zd.service.ZdStockRecordService;


@Service("zdStockRecordService")
public class ZdStockRecordServiceImpl extends ServiceImpl<ZdStockRecordDao, ZdStockRecordEntity> implements ZdStockRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdStockRecordEntity> page = this.selectPage(
                new Query<ZdStockRecordEntity>(params).getPage(),
                new EntityWrapper<ZdStockRecordEntity>()
        );

        return new PageUtils(page);
    }

}
