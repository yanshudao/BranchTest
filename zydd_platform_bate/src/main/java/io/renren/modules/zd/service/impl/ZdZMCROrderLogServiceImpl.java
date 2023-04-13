package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.zd.dao.ZdZMCROrderLogDao;
import io.renren.modules.zd.entity.ZdZMCROrderLog;
import io.renren.modules.zd.service.ZdZMCROrderLogService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("zdZMCROrderLogService")
public class ZdZMCROrderLogServiceImpl extends ServiceImpl<ZdZMCROrderLogDao, ZdZMCROrderLog> implements ZdZMCROrderLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdZMCROrderLog> page = this.selectPage(
                new Query<ZdZMCROrderLog>(params).getPage(),
                new EntityWrapper<ZdZMCROrderLog>()
        );

        return new PageUtils(page);
    }

}
