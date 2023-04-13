package io.renren.modules.subject.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.subject.dao.SubjectResourceChangeDao;
import io.renren.modules.subject.entity.SubjectResourceChangeEntity;
import io.renren.modules.subject.service.SubjectResourceChangeService;


@Service("subjectResourceChangeService")
public class SubjectResourceChangeServiceImpl extends ServiceImpl<SubjectResourceChangeDao, SubjectResourceChangeEntity> implements SubjectResourceChangeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SubjectResourceChangeEntity> page = this.selectPage(
                new Query<SubjectResourceChangeEntity>(params).getPage(),
                new EntityWrapper<SubjectResourceChangeEntity>()
        );

        return new PageUtils(page);
    }

}
