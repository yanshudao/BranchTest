package io.renren.modules.subject.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.subject.dao.SubjectCourseDao;
import io.renren.modules.subject.dao.SubjectCourseResourceDao;
import io.renren.modules.subject.dao.SubjectMajorCourseDao;
import io.renren.modules.subject.entity.SubjectCourseEntity;
import io.renren.modules.subject.entity.SubjectCourseResourceEntity;
import io.renren.modules.subject.service.SubjectCourseService;
import io.renren.modules.zd.dao.ZdOrderResourceDao;
import io.renren.modules.zd.dao.ZdOrgCourseResourceDao;
import io.renren.modules.zd.dao.ZdOrgMajorCourseDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("subjectCourseService")
public class SubjectCourseServiceImpl extends ServiceImpl<SubjectCourseDao, SubjectCourseEntity> implements SubjectCourseService {

    @Autowired
    private SubjectCourseResourceDao subjectCourseResourceDao;
    @Autowired
    private SubjectCourseDao subjectCourseDao;
    @Autowired
    private ZdOrderResourceDao zdOrderResourceDao;
    @Autowired
    private SubjectMajorCourseDao subjectMajorCourseDao;
    @Autowired
    private ZdOrgMajorCourseDao zdOrgMajorCourseDao;
    @Autowired
    private ZdOrgCourseResourceDao zdOrgCourseResourceDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String courseName=(String)params.get("courseName");
        String orgCode=(String)params.get("orgCode");
        String parentOrgCode=(String)params.get("parentOrgCode");
        Page<SubjectCourseEntity> page = this.selectPage(
                new Query<SubjectCourseEntity>(params).getPage(),
                new EntityWrapper<SubjectCourseEntity>().like(StringUtils.isNotBlank(courseName),"course_name",courseName)
                        .and(StringUtils.isNotBlank(orgCode),"org_code",orgCode).or(StringUtils.isNotBlank(parentOrgCode),"org_code",parentOrgCode)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public void saveCourseResource(String courseId, List<String> resourceIds) {
        for(String resourceId:resourceIds)
        {
            SubjectCourseResourceEntity subjectCourseResourceEntity=new SubjectCourseResourceEntity();
            subjectCourseResourceEntity.setCourseId(courseId);
            subjectCourseResourceEntity.setResourceId(resourceId);
            subjectCourseResourceDao.insert(subjectCourseResourceEntity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public void deleteCourseResource(List<String> ids) {
        subjectCourseResourceDao.deleteBatchIds(ids);
    }

    @Override
    public PageUtils queryListPage(Map<String, Object> params) {
        Page<SubjectCourseEntity> page=new Query<SubjectCourseEntity>(params).getPage();
        page.setRecords(subjectCourseDao.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryListCoursePage(Map<String, Object> params) {
        Page<SubjectCourseEntity> page=new Query<SubjectCourseEntity>(params).getPage();
        page.setRecords(subjectCourseDao.selectListCoursePage(page,params));
        return new PageUtils(page);
    }

    @Override
    public int countByIds(List<String> courseIds) {
        return subjectCourseDao.countByIds(courseIds);
    }

    @Override
    @Transactional
//    @CacheEvict(value = Constant.CACHE_NAMESPACE + "SUBJECT_RESOURCE",allEntries = true)
    public void deleteCourse(List ids) {
        int count=zdOrderResourceDao.countByCourseIds(ids);
        if(count>0)
        {
            throw new RRException("有报订的课程不允许删除");

        }
        subjectCourseDao.deleteBatchIds(ids);
        subjectCourseResourceDao.deleteByCourseIds(ids);
        subjectMajorCourseDao.deleteByCourseIds(ids);
        zdOrgMajorCourseDao.deleteByCourseIds(ids);
        zdOrgCourseResourceDao.deleteByCourseIds(ids);
    }

    @Override
    public SubjectCourseEntity selectByCode(String courseCode) {
        return baseMapper.selectByCode(courseCode);
    }


}
