package io.renren.modules.subject.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.subject.dao.SubjectMajorBaseDao;
import io.renren.modules.subject.entity.SubjectMajorBaseEntity;
import io.renren.modules.subject.service.SubjectMajorBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 */
@Service("subjectMajorBaseService")
@Transactional
public class SubjectMajorBaseServiceImpl extends ServiceImpl<SubjectMajorBaseDao, SubjectMajorBaseEntity> implements SubjectMajorBaseService {


}
