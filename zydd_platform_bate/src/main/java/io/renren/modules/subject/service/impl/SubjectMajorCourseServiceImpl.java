package io.renren.modules.subject.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.subject.dao.SubjectMajorCourseDao;
import io.renren.modules.subject.entity.SubjectMajorCourseEntity;
import io.renren.modules.subject.service.SubjectMajorCourseService;


@Service("subjectMajorCourseService")
public class SubjectMajorCourseServiceImpl extends ServiceImpl<SubjectMajorCourseDao, SubjectMajorCourseEntity> implements SubjectMajorCourseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SubjectMajorCourseEntity> page = this.selectPage(
                new Query<SubjectMajorCourseEntity>(params).getPage(),
                new EntityWrapper<SubjectMajorCourseEntity>()
        );

        return new PageUtils(page);
    }

}
