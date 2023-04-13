package io.renren.modules.subject.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.subject.dao.SubjectResourceCatalogDao;
import io.renren.modules.subject.entity.SubjectResourceCatalogEntity;
import io.renren.modules.subject.service.SubjectResourceCatalogService;


@Service("subjectResourceCatalogService")
public class SubjectResourceCatalogServiceImpl extends ServiceImpl<SubjectResourceCatalogDao, SubjectResourceCatalogEntity> implements SubjectResourceCatalogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SubjectResourceCatalogEntity> page = this.selectPage(
                new Query<SubjectResourceCatalogEntity>(params).getPage(),
                new EntityWrapper<SubjectResourceCatalogEntity>()
        );

        return new PageUtils(page);
    }

}
