package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.zd.dao.ZdMajorOrgDao;
import io.renren.modules.zd.entity.ZdMajorOrg;
import io.renren.modules.zd.service.ZdMajorOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("ZdMajorOrgService")
public class ZdMajorOrgServiceImpl extends ServiceImpl<ZdMajorOrgDao, ZdMajorOrg> implements ZdMajorOrgService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query(params).getPage();
        page.setRecords(baseMapper.selectPageList(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryZMCRPage(Map<String, Object> params) {
        Page page=new Query(params).getPage();
        page.setRecords(baseMapper.selectZMCRList(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryOpenCountryPage(Map<String, Object> params) {
        Page page=new Query(params).getPage();
        page.setRecords(baseMapper.selectOpenList(page,params));
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public R saveBatch(List<ZdMajorOrg> zdMajorOrgs) {
        for(ZdMajorOrg zdMajorCourseOrg:zdMajorOrgs){
            ZdMajorOrg courseOrg=selectOne(new EntityWrapper<ZdMajorOrg>()
                    .eq("major_code",zdMajorCourseOrg.getMajorCode()).eq("major_type",zdMajorCourseOrg.getMajorType())
                    .eq("semester_code",zdMajorCourseOrg.getSemesterCode()).eq("student_type",zdMajorCourseOrg.getStudentType())
//                    .eq("subject_type",zdMajorCourseOrg.getSubjectType())
                    .eq("org_code",zdMajorCourseOrg.getOrgCode()).eq("to_org_code",zdMajorCourseOrg.getToOrgCode()));
            if(courseOrg!=null){
                return R.error("存在重复开设！");
            }
            zdMajorCourseOrg.setId(null);
        }
        insertBatch(zdMajorOrgs);
        return R.ok();
    }

    @Override
    public int selectInsert(Map<String, Object> insertMap) {
        return baseMapper.selectInsert(insertMap);
    }
}
