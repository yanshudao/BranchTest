package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.ZdMajorCourseOrgForm;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.ZdMajorCourseOrgDao;
import io.renren.modules.zd.entity.ZdMajorCourseOrg;
import io.renren.modules.zd.service.ZdMajorCourseOrgService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("ZdMajorCourseOrgService")
public class ZdMajorCourseOrgServiceImpl extends ServiceImpl<ZdMajorCourseOrgDao, ZdMajorCourseOrg> implements ZdMajorCourseOrgService {
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query(params).getPage();
        page.setRecords(baseMapper.selectPageList(page,params));
        return new PageUtils(page);
    }


    @Override
    @Transactional
    public R remove(List<ZdMajorCourseOrg> zdMajorCourseOrgList) {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        //县级取消开设为新增取消记录
        zdMajorCourseOrgList.forEach(item->{
            if(StringUtils.isBlank(item.getMajorCode())||StringUtils.isBlank(item.getCourseCode())||StringUtils.isBlank(item.getMajorType())||StringUtils.isBlank(item.getSubjectType())){
                throw new RRException("数据项为空！");
            }
            item.setId(null);
            item.setOrgCode(sysUserEntity.getOrgCode());
            item.setSemesterCode(sysSemesterEntity.getSemesterCode());
        });
        insertBatch(zdMajorCourseOrgList);

        return R.ok();
    }

    @Override
    @Transactional
    public R saveBatch(List<ZdMajorCourseOrg> zdMajorCourseOrgList) {
        for(ZdMajorCourseOrg zdMajorCourseOrg:zdMajorCourseOrgList){
            ZdMajorCourseOrg courseOrg=selectOne(new EntityWrapper<ZdMajorCourseOrg>().eq("course_code",zdMajorCourseOrg.getCourseCode())
                    .eq("major_code",zdMajorCourseOrg.getMajorCode()).eq("major_type",zdMajorCourseOrg.getMajorType())
                    .eq("semester_code",zdMajorCourseOrg.getSemesterCode()).eq("student_type",zdMajorCourseOrg.getStudentType())
                    .eq("org_code",zdMajorCourseOrg.getOrgCode()).eq("to_org_code",zdMajorCourseOrg.getToOrgCode()));
            if(courseOrg!=null){
                return R.error("存在重复取消开设！");
            }
            zdMajorCourseOrg.setId(null);
        }
        insertBatch(zdMajorCourseOrgList);
        return R.ok();
    }

    @Override
    public PageUtils queryOpenCountryPage(Map<String, Object> params) {
        Page page=new Query(params).getPage();
        page.setRecords(baseMapper.selectOpenCountryList(page,params));
        return new PageUtils(page);
    }

    @Override
    public int selectInsert(Map<String, Object> insertMap) {
        return baseMapper.selectInsert(insertMap);
    }
    @Override
    @Transactional
    public R saveBatchZMO(List<ZdMajorCourseOrg> zdMajorCourseOrgList,SysOrgEntity sysOrgEntity) {
        for(ZdMajorCourseOrg zdMajorCourseOrg:zdMajorCourseOrgList){
            ZdMajorCourseOrg courseOrg=selectOne(new EntityWrapper<ZdMajorCourseOrg>().eq("course_code",zdMajorCourseOrg.getCourseCode())
                    .eq("major_code",zdMajorCourseOrg.getMajorCode()).eq("major_type",zdMajorCourseOrg.getMajorType())
                    .eq("semester_code",zdMajorCourseOrg.getSemesterCode()).eq("student_type",zdMajorCourseOrg.getStudentType()).eq("org_code",zdMajorCourseOrg.getOrgCode()));
            if(courseOrg!=null){
                return R.error("存在重复开设！");
            }
            zdMajorCourseOrg.setId(null);
        }
        insertBatch(zdMajorCourseOrgList);
        return R.ok();
    }

    @Override
    public R removeZMO(ZdMajorCourseOrgForm zdMajorCourseOrgForm) {
        String orgCode=zdMajorCourseOrgForm.getOrgCode();
        SysOrgEntity orgEntity=sysOrgService.selectByOrgCode(orgCode);
        zdMajorCourseOrgForm.getList().forEach(item->{
            if(StringUtils.isBlank(item.getMajorCode())||StringUtils.isBlank(item.getCourseCode())||StringUtils.isBlank(item.getMajorType())||StringUtils.isBlank(item.getSubjectType())){
                throw new RRException("数据项为空！");
            }
            item.setId(null);
            item.setOrgCode(orgCode);
            item.setOptType("del");
//                item.setSemesterCode(sysSemesterEntity.getSemesterCode());
        });
        insertBatch(zdMajorCourseOrgForm.getList());

        return R.ok();
    }

    @Override
    public PageUtils queryDisablePage(Map<String, Object> params) {
        Page page=new Query(params).getPage();
        page.setRecords(baseMapper.selectDisableList(page,params));
        return new PageUtils(page);
    }

}
