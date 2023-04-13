package io.renren.modules.subject.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.subject.dao.SubjectResourceTypeDao;
import io.renren.modules.subject.entity.SubjectResourceTypeEntity;
import io.renren.modules.subject.service.SubjectResourceTypeService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("subjectResourceTypeService")
public class SubjectResourceTypeServiceImpl extends ServiceImpl<SubjectResourceTypeDao, SubjectResourceTypeEntity> implements SubjectResourceTypeService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SubjectResourceTypeEntity> page = this.selectPage(
                new Query<SubjectResourceTypeEntity>(params).getPage(),
                new EntityWrapper<SubjectResourceTypeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public SubjectResourceTypeEntity selectByName(String invclassname) {
        return baseMapper.selectByName(invclassname);
    }


}
