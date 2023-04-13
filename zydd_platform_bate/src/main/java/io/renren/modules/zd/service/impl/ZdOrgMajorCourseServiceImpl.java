package io.renren.modules.zd.service.impl;

import io.renren.common.utils.ShiroUtils;
import io.renren.modules.subject.vo.SubjectMajorVO;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.dao.SysSemesterDao;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.vo.MajorCourseOrgVO;
import io.renren.modules.zd.vo.OrderResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.zd.dao.ZdOrgMajorCourseDao;
import io.renren.modules.zd.entity.ZdOrgMajorCourseEntity;
import io.renren.modules.zd.service.ZdOrgMajorCourseService;
import org.springframework.transaction.annotation.Transactional;


@Service("zdOrgMajorCourseService")
public class ZdOrgMajorCourseServiceImpl extends ServiceImpl<ZdOrgMajorCourseDao, ZdOrgMajorCourseEntity> implements ZdOrgMajorCourseService {

    @Autowired
    private ZdOrgMajorCourseDao zdOrgMajorCourseDao;
    @Autowired
    private SysOrgDao sysOrgDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MajorCourseOrgVO> page=new Query<MajorCourseOrgVO>(params).getPage();
        page.setRecords(zdOrgMajorCourseDao.selectByList(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryByOrg(Map<String, Object> params) {
        Page<MajorCourseOrgVO> page=new Query<MajorCourseOrgVO>(params).getPage();
        page.setRecords(zdOrgMajorCourseDao.selectListPage(page,params));
        return new PageUtils(page);

    }
    @Override
    public List queryAllByOrg(Map<String, Object> params) {
        return zdOrgMajorCourseDao.selectListAll(params);
    }

    /**
     * 获得未开设的课程列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryNotHaveByOrg(Map<String, Object> params) {
        Page<MajorCourseOrgVO> page=new Query<MajorCourseOrgVO>(params).getPage();
        page.setRecords(zdOrgMajorCourseDao.selectNotHaveByOrg(page,params));
        return new PageUtils(page);
    }

    /**
     * 获得已开设的课程列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryHaveByOrg(Map<String, Object> params) {
        Page<MajorCourseOrgVO> page=new Query<MajorCourseOrgVO>(params).getPage();
        page.setRecords(zdOrgMajorCourseDao.selectHaveByOrg(page,params));
        return new PageUtils(page);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<String> ids, String orgCode,String semesterCode) {
        this.deleteBatchIds(ids);
      /* SysOrgEntity sysOrgEntity= sysOrgDao.selectByOrgCode(orgCode);
       if(sysOrgEntity!=null)
       {
           List<String> sysOrgEntities=sysOrgDao.selectCodeByParentId(sysOrgEntity.getId());
           if(sysOrgEntities!=null&&sysOrgEntities.size()>0)
           {
               //删除 下级单位的开设信息
               for(String id:ids)
               {
                   ZdOrgMajorCourseEntity zdOrgMajorCourseEntity=this.selectById(id);
                   Map<String,Object> map=new HashMap<>();
                   map.put("majorId",zdOrgMajorCourseEntity.getMajorId());
                   map.put("courseId",zdOrgMajorCourseEntity.getCourseId());
                   map.put("semesterCode",semesterCode);
                   map.put("orgCodes",sysOrgEntities);
                   zdOrgMajorCourseDao.deleteByMap(map);
               }
               this.deleteBatchIds(ids);

           }else
           {
               this.deleteBatchIds(ids);
           }

       }*/
    }
    /**
     * 获取本单位已经屏蔽的课程
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryDisableCourseByOrg(Map<String, Object> params) {
        Page<MajorCourseOrgVO> page=new Query<MajorCourseOrgVO>(params).getPage();
        page.setRecords(zdOrgMajorCourseDao.selectDisableCourseByOrg(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryByOrgCourseResource(Map<String, Object> params) {
        Page<OrderResourceVO> page=new Query<OrderResourceVO>(params).getPage();
        page.setRecords(zdOrgMajorCourseDao.selectResourceByCourseId(page,params));
        return new PageUtils(page);
    }


}
