package io.renren.modules.subject.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.subject.dao.SubjectResourceScopeDao;
import io.renren.modules.subject.entity.SubjectResourceScopeEntity;
import io.renren.modules.subject.service.SubjectResourceScopeService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("subjectResourceScopeService")
public class SubjectResourceScopeServiceImpl extends ServiceImpl<SubjectResourceScopeDao, SubjectResourceScopeEntity> implements SubjectResourceScopeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SubjectResourceScopeEntity> page = this.selectPage(
                new Query<SubjectResourceScopeEntity>(params).getPage(),
                new EntityWrapper<SubjectResourceScopeEntity>()
        );

        return new PageUtils(page);
    }

}
