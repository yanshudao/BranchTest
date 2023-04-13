package io.renren.modules.subject.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.subject.dao.SubjectResourceChangeOrgDao;
import io.renren.modules.subject.entity.SubjectResourceChangeOrgEntity;
import io.renren.modules.subject.service.SubjectResourceChangeOrgService;


@Service("subjectResourceChangeOrgService")
public class SubjectResourceChangeOrgServiceImpl extends ServiceImpl<SubjectResourceChangeOrgDao, SubjectResourceChangeOrgEntity> implements SubjectResourceChangeOrgService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SubjectResourceChangeOrgEntity> page = this.selectPage(
                new Query<SubjectResourceChangeOrgEntity>(params).getPage(),
                new EntityWrapper<SubjectResourceChangeOrgEntity>()
        );

        return new PageUtils(page);
    }

}
