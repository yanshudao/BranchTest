package io.renren.modules.zd.service.impl;

import io.renren.modules.zd.vo.CourseResourceOrgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.zd.dao.ZdOrgCourseResourceDao;
import io.renren.modules.zd.entity.ZdOrgCourseResourceEntity;
import io.renren.modules.zd.service.ZdOrgCourseResourceService;


@Service("zdOrgCourseResourceService")
public class ZdOrgCourseResourceServiceImpl extends ServiceImpl<ZdOrgCourseResourceDao, ZdOrgCourseResourceEntity> implements ZdOrgCourseResourceService {

    @Autowired
    private ZdOrgCourseResourceDao zdOrgCourseResourceDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CourseResourceOrgVO> page=new Query<CourseResourceOrgVO>(params).getPage();
        page.setRecords(zdOrgCourseResourceDao.selectByList(page,params));
        return new PageUtils(page);

    }

    @Override
    public PageUtils queryByOrg(Map<String, Object> params) {
        Page<CourseResourceOrgVO> page=new Query<CourseResourceOrgVO>(params).getPage();
        page.setRecords(zdOrgCourseResourceDao.selectResourceByZdOrg(page,params));
        return new PageUtils(page);
    }

    /**
     * 获得未开设教材
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryNotHaveByOrg(Map<String, Object> params) {
        Page<CourseResourceOrgVO> page=new Query<CourseResourceOrgVO>(params).getPage();
        page.setRecords(zdOrgCourseResourceDao.selectNotHaveByOrg(page,params));
        return new PageUtils(page);
    }

    /**
     * 获得已开设教材
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryHaveByOrg(Map<String, Object> params) {
        Page<CourseResourceOrgVO> page=new Query<CourseResourceOrgVO>(params).getPage();
        page.setRecords(zdOrgCourseResourceDao.selectHaveByOrg(page,params));
        return new PageUtils(page);
    }

    /**
     * 获取单位屏蔽的教材
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryDisableResourceByOrg(Map<String, Object> params) {
        Page<CourseResourceOrgVO> page=new Query<CourseResourceOrgVO>(params).getPage();
        page.setRecords(zdOrgCourseResourceDao.selectDisableResourceByOrg(page,params));
        return new PageUtils(page);
    }

}
