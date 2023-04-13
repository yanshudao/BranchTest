package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysSemesterOrgDao;
import io.renren.modules.sys.entity.SysSemesterOrgEntity;
import io.renren.modules.sys.service.SysSemesterOrgService;


@Service("sysSemesterOrgService")
public class SysSemesterOrgServiceImpl extends ServiceImpl<SysSemesterOrgDao, SysSemesterOrgEntity> implements SysSemesterOrgService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysSemesterOrgEntity> page = this.selectPage(
                new Query<SysSemesterOrgEntity>(params).getPage(),
                new EntityWrapper<SysSemesterOrgEntity>()
        );

        return new PageUtils(page);
    }

}
