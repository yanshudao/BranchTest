package io.renren.modules.subject.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.subject.dao.SubjectCourseResourceDao;
import io.renren.modules.subject.entity.SubjectCourseResourceEntity;
import io.renren.modules.subject.service.SubjectCourseResourceService;


@Service("subjectCourseResourceService")
public class SubjectCourseResourceServiceImpl extends ServiceImpl<SubjectCourseResourceDao, SubjectCourseResourceEntity> implements SubjectCourseResourceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SubjectCourseResourceEntity> page = this.selectPage(
                new Query<SubjectCourseResourceEntity>(params).getPage(),
                new EntityWrapper<SubjectCourseResourceEntity>()
        );

        return new PageUtils(page);
    }

}
