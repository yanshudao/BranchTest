package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.subject.entity.SubjectCourseEntity;
import io.renren.modules.subject.entity.SubjectMajorEntity;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.service.SubjectCourseService;
import io.renren.modules.subject.service.SubjectMajorService;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.ZdMajorCourseResourceDao;
import io.renren.modules.zd.dao.ZdZMCROrderLogDao;
import io.renren.modules.zd.dao.ZdZMCRSourceLogDao;
import io.renren.modules.zd.entity.ZdMajorCourseResourceEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.form.UpdateResourceVersionForm;
import io.renren.modules.zd.service.*;
import io.renren.modules.zd.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */
@Service("zdMajorCourseResourceService")
public class ZdMajorCourseResourceServiceImpl extends ServiceImpl<ZdMajorCourseResourceDao, ZdMajorCourseResourceEntity> implements ZdMajorCourseResourceService {

    @Autowired
    private SubjectMajorService subjectMajorService;
    @Autowired
    private SubjectCourseService subjectCourseService;
    @Autowired
    private SubjectResourceService subjectResourceService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdMajorCourseResourceVO> page=new Query<ZdMajorCourseResourceVO>(params).getPage();
        page.setRecords(baseMapper.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    public List queryOrgZdGroup(Map<String, Object> params) {
        List<OrgZdVO> list=new ArrayList<>();
        List<OrgZdVO> orgTypeList=baseMapper.selectOrgZdGroup(params);
        for(OrgZdVO orgZdVO:orgTypeList)
        {
            params.put("majorType",orgZdVO.getMajorType());
            params.put("studentType",orgZdVO.getStudentType());
            List<OrgZdMajorVO> majorVOS=baseMapper.selectOrgZdMajorMap(params);
            if(majorVOS.size()>0){
                orgZdVO.setList(majorVOS);
                list.add(orgZdVO);
            }else{
                orgTypeList.remove(orgZdVO);
            }
//            orgZdVO.setMajorType(majorType);

        }
        return list;
    }

    @Override
    public PageUtils queryCourseByOrg(Map<String, Object> params) {
        Page<MajorCourseOrgVO> page=new Query<MajorCourseOrgVO>(params).getPage();
        page.setRecords(baseMapper.selectCourseByOrg(page,params));
        return new PageUtils(page);

    }

    @Override
    public PageUtils queryResourceByOrg(Map<String, Object> params) {
        Page<OrderResourceVO> page=new Query<OrderResourceVO>(params).getPage();
        page.setRecords(baseMapper.selectResourceByOrg(page,params));
        return new PageUtils(page);
    }

    @Override
    public int countByMap(Map<String, Object> map) {
        return baseMapper.countByMap(map);
    }

    @Override
    @Transactional
    public List<ZdMajorCourseResourceVO> importZMCR(List<ZdMajorCourseResourceVO> list, SysUserEntity user,String semesterCode) {
        SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(user.getOrgCode());
        List<ZdMajorCourseResourceEntity> batchSave=new ArrayList<>();
        Map<String,Object> ukMap=new HashMap<>();
        for(ZdMajorCourseResourceVO zdMajorCourseResourceVO:list) {
            String uk=StringUtils.deleteWhitespace(zdMajorCourseResourceVO.getMajorCode()+"-"+zdMajorCourseResourceVO.getMajorType()+"-"+zdMajorCourseResourceVO.getStudentType()+"-"+
                    zdMajorCourseResourceVO.getSubjectType()+"-"+zdMajorCourseResourceVO.getCourseCode()+"-"+
                    zdMajorCourseResourceVO.getResourceCode()+"-"+user.getOrgCode()+"-"+semesterCode);
            //强制转大写
            uk=StringUtils.upperCase(uk);
           if(ukMap.get(uk)!=null){
               zdMajorCourseResourceVO.setResult("表中已存在此关系数据！");
               continue;
           }
            ukMap.put(uk,1);
            if(StringUtils.isBlank(zdMajorCourseResourceVO.getMajorCode())){
                zdMajorCourseResourceVO.setResult("专业代码不能为空！");
                continue;
            }
            if(StringUtils.isBlank(zdMajorCourseResourceVO.getStudentType())){
                zdMajorCourseResourceVO.setResult("学生类型不能为空！");
                continue;
            }
            if(StringUtils.isBlank(zdMajorCourseResourceVO.getMajorType())){
                zdMajorCourseResourceVO.setResult("专业层次不能为空！");
                continue;
            }
            if(StringUtils.isBlank(zdMajorCourseResourceVO.getCourseCode())){
                zdMajorCourseResourceVO.setResult("课程代码不能为空！");
                continue;
            }
            if(StringUtils.isBlank(zdMajorCourseResourceVO.getResourceCode())){
                zdMajorCourseResourceVO.setResult("教材代码不能为空！");
                continue;
            }
            if("ZYDD".equals(user.getOrgCode())){
                zdMajorCourseResourceVO.setSubjectType("1");
            }else{
                zdMajorCourseResourceVO.setSubjectType("2");
            }
            /*if(!"1".equals(zdMajorCourseResourceVO.getSubjectType())&&!"2".equals(zdMajorCourseResourceVO.getSubjectType())){
                zdMajorCourseResourceVO.setResult("考试单位不规范！请输入 中央或者省");
                continue;
            }*/
           SubjectMajorEntity subjectMajorEntity=subjectMajorService.selectByCode(zdMajorCourseResourceVO.getMajorCode(),
                   zdMajorCourseResourceVO.getStudentType(),
                   zdMajorCourseResourceVO.getMajorType(),
                   sysOrgEntity.getOrgCode(),
                   sysOrgEntity.getParentCodes()
                   );
           if(subjectMajorEntity==null) {
               /*subjectMajorEntity=new SubjectMajorEntity();
               subjectMajorEntity.setMajorCode(zdMajorCourseResourceVO.getMajorCode());
               subjectMajorEntity.setMajorType(zdMajorCourseResourceVO.getMajorType());
               subjectMajorEntity.setStudentType(zdMajorCourseResourceVO.getStudentType());
               subjectMajorEntity.setMajorName(zdMajorCourseResourceVO.getMajorName());
               subjectMajorEntity.setOrgCode(user.getOrgCode());
               subjectMajorService.insert(subjectMajorEntity);*/
               zdMajorCourseResourceVO.setResult("专业未找到！");
               continue;
           }
           SubjectCourseEntity subjectCourseEntity=subjectCourseService.selectByCode(zdMajorCourseResourceVO.getCourseCode());
           if(subjectCourseEntity==null)
           {
               zdMajorCourseResourceVO.setResult("课程未找到！");
               continue;
           }
            SubjectResourceEntity subjectResourceEntity=subjectResourceService.selectByCode(zdMajorCourseResourceVO.getResourceCode());
            if(subjectResourceEntity==null)
            {
                zdMajorCourseResourceVO.setResult("教材未找到！");
                continue;
            }
            if(!"0".equals(user.getOrgType())){
                int count=baseMapper.selectCount(new EntityWrapper<ZdMajorCourseResourceEntity>().eq("semester_code",semesterCode).eq("org_code","ZYDD")
                .eq("major_code",zdMajorCourseResourceVO.getMajorCode()).eq("major_type",zdMajorCourseResourceVO.getMajorType()).eq("student_type",zdMajorCourseResourceVO.getStudentType()).eq("course_code",zdMajorCourseResourceVO.getCourseCode())
                .eq("resource_code",zdMajorCourseResourceVO.getResourceCode()));
                if(count>0){
                    zdMajorCourseResourceVO.setResult("规则已在中央维护！");
                    continue;
                }
            }
            ZdMajorCourseResourceEntity zdMajorCourseResourceEntity=baseMapper.selectByUk(zdMajorCourseResourceVO.getMajorCode(),zdMajorCourseResourceVO.getMajorType(),zdMajorCourseResourceVO.getStudentType(),
            zdMajorCourseResourceVO.getSubjectType(),zdMajorCourseResourceVO.getCourseCode(),
            zdMajorCourseResourceVO.getResourceCode(),user.getOrgCode(),semesterCode);
//            zdMajorCourseResourceVO.setOrgCode(user.getOrgCode());
//            zdMajorCourseResourceVO.setSemesterCode(semesterCode);

        if(zdMajorCourseResourceEntity!=null)
            {
                zdMajorCourseResourceVO.setResult("关系已存在");
            }else
            {
                zdMajorCourseResourceEntity=new ZdMajorCourseResourceEntity();
                zdMajorCourseResourceEntity.setMajorCode(zdMajorCourseResourceVO.getMajorCode());
                zdMajorCourseResourceEntity.setMajorType(zdMajorCourseResourceVO.getMajorType());
                zdMajorCourseResourceEntity.setStudentType(zdMajorCourseResourceVO.getStudentType());
                zdMajorCourseResourceEntity.setCourseCode(zdMajorCourseResourceVO.getCourseCode());
                zdMajorCourseResourceEntity.setResourceCode(zdMajorCourseResourceVO.getResourceCode());
                zdMajorCourseResourceEntity.setSemesterCode(semesterCode);
                zdMajorCourseResourceEntity.setOrgCode(user.getOrgCode());
                zdMajorCourseResourceEntity.setSubjectType(zdMajorCourseResourceVO.getSubjectType());
                zdMajorCourseResourceEntity.setRuleNumber(zdMajorCourseResourceVO.getRuleNumber());
//                baseMapper.insert(zdMajorCourseResourceEntity);
                batchSave.add(zdMajorCourseResourceEntity);
                zdMajorCourseResourceVO.setResult("成功");
            }


        }
        if(batchSave.size()>0){
            insertBatch(batchSave,100);
        }
        return list;
    }

    @Override
    public List<ZdMajorCourseResourceVO> queryAll(Map<String, Object> params) {
        return baseMapper.selectListPage(params);
    }

    @Override
    public List selectOrgZdMajorMap(Map<String, Object> params) {
        return baseMapper.selectOrgZdMajorMap(params);
    }
    @Autowired
    private ZdCartService zdCartService;
    @Autowired
    private ZdSourceService zdSourceService;
    @Autowired
    private ZdOrderResourceService zdOrderResourceService;
    @Autowired
    private ZdZMCRSourceLogDao zdZMCRSourceLogDao;
    @Autowired
    private ZdZMCROrderLogDao zdZMCROrderLogDao;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Override
    @Transactional
    public boolean updateVersion(UpdateResourceVersionForm updateResourceVersionForm) {
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        if(updateResourceVersionForm.getOrgList()==null&&updateResourceVersionForm.getOrgList().size()<=0){
            throw new RRException("未指定单位替换！");
        }
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SearchForm searchForm=new SearchForm();
        BeanUtils.copyProperties(updateResourceVersionForm,searchForm);
        List<ZdMajorCourseResourceEntity> updateList=new ArrayList<>();
        List<ZdMajorCourseResourceEntity> newList=new ArrayList<>();
        updateResourceVersionForm.getZmcrList().stream().forEach(item->{
            ZdMajorCourseResourceEntity zdMajorCourseResourceEntity=selectById(item);
            ZdMajorCourseResourceEntity newZmcr=
                    selectByUk(zdMajorCourseResourceEntity.getMajorCode(),zdMajorCourseResourceEntity.getMajorType(),zdMajorCourseResourceEntity.getStudentType(),zdMajorCourseResourceEntity.getSubjectType(),zdMajorCourseResourceEntity.getCourseCode(),updateResourceVersionForm.getResourceCode(),zdMajorCourseResourceEntity.getOrgCode(),
                            zdMajorCourseResourceEntity.getSemesterCode());
            if(newZmcr==null){
                newZmcr=new ZdMajorCourseResourceEntity();
                BeanUtils.copyProperties(zdMajorCourseResourceEntity,newZmcr);
                newZmcr.setId(IdWorker.get32UUID());
                newZmcr.setResourceCode(updateResourceVersionForm.getResourceCode());
                newList.add(newZmcr);
            }else{
                newZmcr.setIsArchives(Constant.ZMCR_ARCHIVES.NOT_ARCHIVES);
                updateList.add(newZmcr);
            }
            zdMajorCourseResourceEntity.setIsArchives(Constant.ZMCR_ARCHIVES.ARCHIVES);
            updateList.add(zdMajorCourseResourceEntity);
            zdZMCROrderLogDao.selectInsert(sysSemesterEntity.getSemesterCode(),updateResourceVersionForm.getOrgList(),zdMajorCourseResourceEntity.getId(),newZmcr.getId(),sysUserEntity.getUserId());
            zdZMCRSourceLogDao.selectInsert(sysSemesterEntity.getSemesterCode(),updateResourceVersionForm.getOrgList(),zdMajorCourseResourceEntity.getResourceCode(),updateResourceVersionForm.getResourceCode(),sysUserEntity.getUserId());
            zdCartService.updateVersion(zdMajorCourseResourceEntity.getId(),newZmcr.getId(),updateResourceVersionForm.getOrgList(),sysSemesterEntity.getSemesterCode());
            zdSourceService.updateVersion(zdMajorCourseResourceEntity.getResourceCode(),newZmcr.getResourceCode(),updateResourceVersionForm.getOrgList(),sysSemesterEntity.getSemesterCode(),zdMajorCourseResourceEntity.getId());
            zdOrderResourceService.updateVersion(zdMajorCourseResourceEntity.getId(),newZmcr.getId(),updateResourceVersionForm.getOrgList(),sysSemesterEntity.getSemesterCode());
            SubjectResourceEntity subjectResourceEntity=subjectResourceService.selectByCode(newZmcr.getResourceCode());
            SubjectResourceEntity update=new SubjectResourceEntity();
            update.setId(subjectResourceEntity.getId());
            update.setIsShow(Constant.RESOURCE_SHOW.SHOW);
            subjectResourceService.updateById(update);
        });

        if(CollectionUtils.isNotEmpty(newList)){
            this.insertBatch(newList);
        }
        return this.updateBatchById(updateList);
    }


    @Override
    public ZdMajorCourseResourceEntity selectByUk(String majorCode, String majorType, String studentType, String subjectType, String courseCode, String resourceCode, String orgCode, String semesterCode) {
        return baseMapper.selectByUk(majorCode,majorType,studentType,subjectType,courseCode,resourceCode,orgCode,semesterCode);
    }
    @Autowired
    private SysOrgService sysOrgService;

    @Override
    public int syncCourse(List ids) {
        List<SysOrgEntity> list=sysOrgService.listProvince();
        int count= list.stream().mapToInt(item -> baseMapper.selectInsert(item.getOrgCode(),ids)).sum();
        return count;
    }

}
