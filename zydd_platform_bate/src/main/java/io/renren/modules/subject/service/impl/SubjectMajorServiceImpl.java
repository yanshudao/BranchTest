package io.renren.modules.subject.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.subject.dao.SubjectCourseDao;
import io.renren.modules.subject.dao.SubjectMajorCourseDao;
import io.renren.modules.subject.dao.SubjectMajorDao;
import io.renren.modules.subject.entity.SubjectCourseEntity;
import io.renren.modules.subject.entity.SubjectMajorCourseEntity;
import io.renren.modules.subject.entity.SubjectMajorEntity;
import io.renren.modules.subject.service.SubjectMajorService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.ZdOrgCourseResourceDao;
import io.renren.modules.zd.dao.ZdOrgMajorCourseDao;
import io.renren.modules.zd.dao.ZdOrgMajorDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 *
 */
@Service("subjectMajorService")
@Transactional
public class SubjectMajorServiceImpl extends ServiceImpl<SubjectMajorDao, SubjectMajorEntity> implements SubjectMajorService {

    @Autowired
    private SubjectMajorCourseDao subjectMajorCourseDao;
    @Autowired
    private SubjectMajorDao subjectMajorDao;
    @Autowired
    private SubjectCourseDao subjectCourseDao;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private ZdOrgMajorCourseDao zdOrgMajorCourseDao;
    @Autowired
    private ZdOrgMajorDao zdOrgMajorDao;
    @Autowired
    private ZdOrgCourseResourceDao zdOrgCourseResourceDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String majorName= (String) params.get("majorName");
        Page<SubjectMajorEntity> page=new Query<SubjectMajorEntity>(params).getPage();
        page.setRecords(subjectMajorDao.selectListPage(page,params));
        return new PageUtils(page);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMajorCourse(String majorId, List<String> courseIds,String semesterCode) {
        SubjectMajorEntity subjectMajorEntity=subjectMajorDao.selectById(majorId);
        for(String courseId:courseIds)
        {
            SubjectCourseEntity subjectCourseEntity=subjectCourseDao.selectById(courseId);
            SubjectMajorCourseEntity subjectMajorCourseEntity=new SubjectMajorCourseEntity();
            if(StringUtils.isBlank(subjectMajorEntity.getMajorType()))
            {
                throw new RRException("专业类型不能为空！请先完善专业信息");
            }
            if(StringUtils.isBlank(subjectMajorEntity.getStudentType()))
            {
                throw new RRException("学生类型不能为空！请先完善专业信息");
            }
            subjectMajorCourseEntity.setCourseId(courseId);
            subjectMajorCourseEntity.setSemesterCode(semesterCode);
            subjectMajorCourseEntity.setMajorId(subjectMajorEntity.getMajorCode());
            subjectMajorCourseEntity.setMajorCode(subjectMajorEntity.getMajorCode());
            subjectMajorCourseEntity.setStudentType(subjectMajorEntity.getStudentType());
            subjectMajorCourseEntity.setMajorType(subjectMajorEntity.getMajorType());
            subjectMajorCourseEntity.setCourseId(subjectCourseEntity.getCourseCode());
            subjectMajorCourseEntity.setCourseCode(subjectCourseEntity.getCourseCode());
            subjectMajorCourseDao.insert(subjectMajorCourseEntity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMajorCourse(List<String> ids) {
        subjectMajorCourseDao.deleteBatchIds(ids);


    }


    @Override
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        subjectMajorCourseDao.deleteByMajorIds(idList);
        zdOrgMajorDao.deleteByMajorIds(idList);
        zdOrgMajorCourseDao.deleteByMajorIds(idList);
        zdOrgCourseResourceDao.deleteByMajorIds(idList);
        super.deleteBatchIds(idList);
        return true;
    }
    @Override
    public SubjectMajorEntity selectByCode(String majorCode, String studentType, String majorType,String orgCode,String parentOrgCodes) {
        return baseMapper.selectByCode(majorCode,studentType,majorType,orgCode,parentOrgCodes);
    }

    /* @Override
    public PageUtils queryMajorByPage(Map<String, Object> params) {
        Page<SubjectCourseEntity> page=new Query<SubjectCourseEntity>(params).getPage();
        page.setRecords(subjectMajorDao.selectListPage(page,params));
        return new PageUtils(page);
    }*/

}
