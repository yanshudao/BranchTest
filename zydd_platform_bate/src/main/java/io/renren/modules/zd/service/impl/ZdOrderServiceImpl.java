package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.search.vo.StatisticsResourceDetailVO;
import io.renren.modules.search.vo.StatisticsResourceVO;
import io.renren.modules.subject.dao.SubjectCourseDao;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.subject.entity.SubjectCourseEntity;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.dao.SysOrgSettingDao;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.ZdMajorCourseResourceDao;
import io.renren.modules.zd.dao.ZdOrderDao;
import io.renren.modules.zd.dao.ZdOrderResourceDao;
import io.renren.modules.zd.dao.ZdOrgCourseResourceDao;
import io.renren.modules.zd.entity.ZdMajorCourseResourceEntity;
import io.renren.modules.zd.entity.ZdOrderEntity;
import io.renren.modules.zd.entity.ZdOrderResourceEntity;
import io.renren.modules.zd.form.*;
import io.renren.modules.zd.service.ZdOrderService;
import io.renren.modules.zd.vo.CourseResourceOrgVO;
import io.renren.modules.zd.vo.ZdOrderCartVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */
@Service("zdOrderService")
public class ZdOrderServiceImpl extends ServiceImpl<ZdOrderDao, ZdOrderEntity> implements ZdOrderService {

    @Autowired
    private ZdOrderDao zdOrderDao;
    @Autowired
    private ZdOrderResourceDao zdOrderResourceDao;
    @Autowired
    private SysOrgSettingDao sysOrgSettingDao;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private ZdOrgCourseResourceDao zdOrgCourseResourceDao;
    @Autowired
    private SysOrgDao sysOrgDao;
    @Autowired
    private SubjectResourceDao subjectResourceDao;
    @Autowired
    private SubjectCourseDao subjectCourseDao;
    @Autowired
    private ZdMajorCourseResourceDao zdMajorCourseResourceDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdOrderEntity> page=new Query<ZdOrderEntity>(params).getPage();
        page.setRecords(zdOrderDao.selectListPage(page,params));
        return new PageUtils(page);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(ZdCourseForm zdCourseForm) {
        SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(zdCourseForm.getOrgCode());
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(zdCourseForm.getOrgCode());
        ZdOrderEntity zdOrderEntity=new ZdOrderEntity();
        zdOrderEntity.setFromOrgCode(zdCourseForm.getOrgCode());
        zdOrderEntity.setToOrgCode(sysOrgSettingEntity.getToOrgCode());
        String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        zdOrderEntity.setOrderNo("ORDER"+timeInSecond);
        if(Constant.ORDER_STATUS.CONFIRM.equals(zdCourseForm.getStatus()))
        {
            zdOrderEntity.setConfirmBy(String.valueOf(sysUserEntity.getUserId()));
            zdOrderEntity.setConfirmTime(new Date());
            zdOrderEntity.setStatus(Constant.ORDER_STATUS.CONFIRM);
        }else
        {
            zdOrderEntity.setStatus(Constant.ORDER_STATUS.NEW);
        }

        zdOrderEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdOrderEntity.setOrderName(sysOrgEntity.getOrgName()+timeInSecond+"报订");
        zdOrderDao.insert(zdOrderEntity);
        for(ZdCourseDetailForm zdCourseDetailForm:zdCourseForm.getCourseForm())
        {
            String courseId=zdCourseDetailForm.getCourseId();
            SubjectCourseEntity subjectCourseEntity=subjectCourseDao.selectById(courseId);
            if(subjectCourseEntity==null)
            {

                throw new RRException("无法查找到此课程");

            }
            Map<String,Object> params=new HashMap<>();
            params.put("courseId",courseId);
            params.put("resourceType","1");
            params.put("parentCodes",sysOrgEntity.getParentCodes());
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
            params.put("orgCode",sysOrgEntity.getOrgCode());
            List<CourseResourceOrgVO> subjectResourceEntityList=zdOrgCourseResourceDao.selectResourceByZd(params);
            if(subjectResourceEntityList.size()<=0)
            {
                throw new RRException("课程代码："+subjectCourseEntity.getCourseCode()+"<br>课程名称："+subjectCourseEntity.getCourseName()+"<br>该课程下没有教材！");
            }
            for(CourseResourceOrgVO courseResourceOrgVO:subjectResourceEntityList)
            {
                SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(courseResourceOrgVO.getId());
                if(subjectResourceEntity==null)
                {
                    throw new RRException("课程代码："+subjectCourseEntity.getCourseCode()+"<br>课程名称："+subjectCourseEntity.getCourseName()+"<br>教材没有查询到！");
                }
                ZdOrderResourceEntity zdOrderResourceEntity=new ZdOrderResourceEntity();
                zdOrderResourceEntity.setMajorId(zdCourseDetailForm.getMajorId());
                zdOrderResourceEntity.setCourseId(courseResourceOrgVO.getCourseId());
                zdOrderResourceEntity.setResourceId(courseResourceOrgVO.getId());
                zdOrderResourceEntity.setOrderNum(zdCourseDetailForm.getNum());
                zdOrderResourceEntity.setOrderId(zdOrderEntity.getId());
                zdOrderResourceEntity.setOrderResourceNo("ORDERDETAIL"+timeInSecond);
               if(subjectResourceEntity.getPrice()==null||subjectResourceEntity.getPrice().compareTo(new Double("0"))==0)
                {
                    throw new RRException("课程代码："+subjectCourseEntity.getCourseCode()+"<br>课程名称："+subjectCourseEntity.getCourseName()+"<br>教材代码："+subjectResourceEntity.getResourceCode()+"没有价格!");
                }
                zdOrderResourceEntity.setItemPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdOrderResourceDao.insert(zdOrderResourceEntity);
            }


        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmOder(List<String> orderIds) {
        int count=zdOrderDao.countByIdsAndStatus(orderIds,Constant.ORDER_STATUS.COMPLETE);
        if(count>0)
        {
            throw new RRException("已审核的单据不可再次上报！");
        }
        for(String orderId:orderIds)
        {
            ZdOrderEntity orderEntity=zdOrderDao.selectById(orderId);
            if(orderEntity!=null)
            {
               SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
                orderEntity.setStatus(Constant.ORDER_STATUS.CONFIRM);
                orderEntity.setConfirmBy(String.valueOf(sysUserEntity.getUserId()));
                orderEntity.setConfirmTime(new Date());
                zdOrderDao.updateById(orderEntity);
            }

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeOrder(List<String> orderIds) {

        for(String orderId:orderIds)
        {
            ZdOrderEntity orderEntity=zdOrderDao.selectById(orderId);
            if(orderEntity!=null)
            { SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
                orderEntity.setCompleteBy(String.valueOf(sysUserEntity.getUserId()));
                orderEntity.setCompleteTime(new Date());
                orderEntity.setStatus(Constant.ORDER_STATUS.COMPLETE);
                zdOrderDao.updateById(orderEntity);
            }

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrderResource(ZdOrgResourceForm zdOrgResourceForm) {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        for(ZdOrderResourceEntity entity:zdOrgResourceForm.getList())
        {
            if(entity!=null&&StringUtils.isNotBlank(entity.getId()))
            {
                zdOrderResourceDao.updateById(entity);
            }

        }
        ZdOrderEntity zdOrderEntity=zdOrderDao.selectById(zdOrgResourceForm.getOrderId());
        if(Constant.ORDER_STATUS.CONFIRM.equals(zdOrgResourceForm.getStatus()))
        {
            zdOrderEntity.setStatus(Constant.ORDER_STATUS.CONFIRM);
            zdOrderEntity.setConfirmBy(String.valueOf(sysUserEntity.getUserId()));
            zdOrderEntity.setConfirmTime(new Date());
        }
        if(Constant.ORDER_STATUS.COMPLETE.equals(zdOrgResourceForm.getStatus()))
        {
            zdOrderEntity.setCompleteBy(String.valueOf(sysUserEntity.getUserId()));
            zdOrderEntity.setCompleteTime(new Date());
            zdOrderEntity.setStatus(Constant.ORDER_STATUS.COMPLETE);
        }
        zdOrderEntity.setRemark(zdOrgResourceForm.getRemark());
        zdOrderEntity.setNote(zdOrgResourceForm.getNote());
        zdOrderDao.updateById(zdOrderEntity);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrderResource(List<String> id) {
        zdOrderResourceDao.deleteBatchIds(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(List<String> ids) {
        zdOrderDao.deleteBatchIds(ids);
        zdOrderResourceDao.deleteByOrderId(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrderByResource(ZdResourceForm zdResourceForm) {
        SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(zdResourceForm.getOrgCode());
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(zdResourceForm.getOrgCode());
        ZdOrderEntity zdOrderEntity=new ZdOrderEntity();
        zdOrderEntity.setFromOrgCode(zdResourceForm.getOrgCode());
        zdOrderEntity.setToOrgCode(sysOrgSettingEntity.getToOrgCode());
        String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        zdOrderEntity.setOrderNo("ORDER"+timeInSecond);
        if(Constant.ORDER_STATUS.CONFIRM.equals(zdResourceForm.getStatus()))
        {
            zdOrderEntity.setConfirmBy(String.valueOf(sysUserEntity.getUserId()));
            zdOrderEntity.setConfirmTime(new Date());
            zdOrderEntity.setStatus(Constant.ORDER_STATUS.CONFIRM);
        }else
        {
            zdOrderEntity.setStatus(Constant.ORDER_STATUS.NEW);
        }
        zdOrderEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdOrderEntity.setOrderName(sysOrgEntity.getOrgName()+timeInSecond+"报订");
        zdOrderDao.insert(zdOrderEntity);
        for(ZdResourceDetailForm zdResourceDetailForm:zdResourceForm.getResourceForm())
        {

                SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(zdResourceDetailForm.getResourceId());
                if(subjectResourceEntity!=null)
                {
                    ZdOrderResourceEntity zdOrderResourceEntity=new ZdOrderResourceEntity();
                    zdOrderResourceEntity.setCourseId(zdResourceDetailForm.getCourseId());
                    zdOrderResourceEntity.setResourceId(zdResourceDetailForm.getResourceId());
                    zdOrderResourceEntity.setOrderNum(zdResourceDetailForm.getNum());
                    zdOrderResourceEntity.setOrderId(zdOrderEntity.getId());
                    zdOrderResourceEntity.setOrderResourceNo("ORDERDETAIL"+timeInSecond);
                    zdOrderResourceEntity.setItemPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                    zdOrderResourceDao.insert(zdOrderResourceEntity);

                }



        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectOrder(List<String> ids) {
        for(String orderId:ids)
        {
            ZdOrderEntity orderEntity=zdOrderDao.selectById(orderId);
            if(orderEntity!=null)
            { SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
                orderEntity.setCompleteBy(String.valueOf(sysUserEntity.getUserId()));
                orderEntity.setCompleteTime(new Date());
                orderEntity.setStatus(Constant.ORDER_STATUS.NEW);
                zdOrderDao.updateById(orderEntity);
            }

        }
    }

    @Override
    public PageUtils queryStatisticsByMap(Map<String, Object> params) {
        Page<StatisticsResourceVO> page=new Query<StatisticsResourceVO>(params).getPage();
        page.setRecords(zdOrderDao.selectStatisticsByMap(page,params));
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrderFromProvince(ZdCourseForm zdCourseForm) {
        SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(zdCourseForm.getOrgCode());
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(zdCourseForm.getOrgCode());
        ZdOrderEntity zdOrderEntity=new ZdOrderEntity();
        zdOrderEntity.setFromOrgCode(zdCourseForm.getOrgCode());
        zdOrderEntity.setToOrgCode(sysOrgSettingEntity.getToOrgCode());
        String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        zdOrderEntity.setOrderNo("ORDER"+timeInSecond);
        zdOrderEntity.setCompleteTime(new Date());
        zdOrderEntity.setConfirmTime(new Date());
        zdOrderEntity.setConfirmBy(sysUserEntity.getUserId()+"");
        zdOrderEntity.setCompleteBy(sysUserEntity.getUserId()+"");
        zdOrderEntity.setStatus(Constant.ORDER_STATUS.COMPLETE);
        zdOrderEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdOrderEntity.setOrderName(sysOrgEntity.getOrgName()+timeInSecond+"报订");
        zdOrderDao.insert(zdOrderEntity);
        for(ZdCourseDetailForm zdCourseDetailForm:zdCourseForm.getCourseForm())
        {
            String courseId=zdCourseDetailForm.getCourseId();
            Map<String,Object> params=new HashMap<>();
            params.put("courseId",courseId);
           // params.put("resourceType","1");
            params.put("parentCodes",sysOrgEntity.getParentCodes());
            params.put("semesterCode",sysSemesterEntity.getSemesterCode());
            params.put("orgCode",sysOrgEntity.getOrgCode());
            List<CourseResourceOrgVO> subjectResourceEntityList=zdOrgCourseResourceDao.selectResourceByZd(params);
            for(CourseResourceOrgVO courseResourceOrgVO:subjectResourceEntityList)
            {
                SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(courseResourceOrgVO.getId());
                ZdOrderResourceEntity zdOrderResourceEntity=new ZdOrderResourceEntity();
                zdOrderResourceEntity.setMajorId(zdCourseDetailForm.getMajorId());
                zdOrderResourceEntity.setCourseId(courseResourceOrgVO.getCourseId());
                zdOrderResourceEntity.setResourceId(courseResourceOrgVO.getId());
                zdOrderResourceEntity.setOrderNum(zdCourseDetailForm.getNum());
                zdOrderResourceEntity.setOrderId(zdOrderEntity.getId());
                zdOrderResourceEntity.setOrderResourceNo("ORDERDETAIL"+timeInSecond);
                zdOrderResourceEntity.setItemPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdOrderResourceDao.insert(zdOrderResourceEntity);
            }


        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrderByResourceFromProvince(ZdResourceForm zdResourceForm) {
        SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(zdResourceForm.getOrgCode());
       // SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(zdResourceForm.getOrgCode());
        ZdOrderEntity zdOrderEntity=new ZdOrderEntity();
        zdOrderEntity.setFromOrgCode(zdResourceForm.getOrgCode());
        zdOrderEntity.setToOrgCode(sysOrgSettingEntity.getToOrgCode());
        String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        zdOrderEntity.setOrderNo("ORDER"+timeInSecond);
        zdOrderEntity.setStatus(Constant.ORDER_STATUS.COMPLETE);
        zdOrderEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdOrderEntity.setOrderName(sysOrgEntity.getOrgName()+timeInSecond+"报订");
        zdOrderDao.insert(zdOrderEntity);
        for(ZdResourceDetailForm zdResourceDetailForm:zdResourceForm.getResourceForm())
        {

            SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(zdResourceDetailForm.getResourceId());
            if(subjectResourceEntity!=null)
            {
                ZdOrderResourceEntity zdOrderResourceEntity=new ZdOrderResourceEntity();
                zdOrderResourceEntity.setCourseId(zdResourceDetailForm.getCourseId());
                zdOrderResourceEntity.setResourceId(zdResourceDetailForm.getResourceId());
                zdOrderResourceEntity.setOrderNum(zdResourceDetailForm.getNum());
                zdOrderResourceEntity.setOrderId(zdOrderEntity.getId());
                zdOrderResourceEntity.setOrderResourceNo("ORDERDETAIL"+timeInSecond);
                zdOrderResourceEntity.setItemPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdOrderResourceDao.insert(zdOrderResourceEntity);

            }



        }
    }

    @Override
    public List<StatisticsResourceVO> queryStatisticsAllByMap(Map<String, Object> params) {
        return  zdOrderDao.selectStatisticsByMap(params);
    }

    @Override
    public Integer selectCountByMap(Map<String, Object> params) {
        return zdOrderDao.selectCountByMap(params);
    }

    @Override
    public List<StatisticsResourceDetailVO> queryStatisticsDetailByMap(Map<String, Object> params) {
        return zdOrderDao.selectStatisticsDetailByMap(params);
    }

    @Override
    @Transactional
    public void submitCatOrder(List<ZdOrderCartVO> cartEntityList, SysUserEntity sysUserEntity,String status,String note,String remark) {

        SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(sysUserEntity.getOrgCode());
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(sysUserEntity.getOrgCode());
        ZdOrderEntity zdOrderEntity=new ZdOrderEntity();
        zdOrderEntity.setNote(note);
        zdOrderEntity.setRemark(remark);
        zdOrderEntity.setFromOrgCode(sysUserEntity.getOrgCode());
        if(sysOrgSettingEntity==null||StringUtils.isBlank(sysOrgSettingEntity.getToOrgCode())){
             throw new RRException("未设置报订单据上报单位，请联系管理员！");
        }
        zdOrderEntity.setToOrgCode(sysOrgSettingEntity.getToOrgCode());
        String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        zdOrderEntity.setOrderNo("ORDER"+timeInSecond);
        if(Constant.ORDER_STATUS.CONFIRM.equals(status))
        {
            zdOrderEntity.setConfirmTime(new Date());
            zdOrderEntity.setConfirmBy(sysUserEntity.getUserId()+"");
        }
        zdOrderEntity.setStatus(status);
        zdOrderEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdOrderEntity.setOrderName(sysOrgEntity.getOrgName()+timeInSecond+"报订");
        zdOrderDao.insert(zdOrderEntity);
        for(ZdOrderCartVO zdOrderCartEntity:cartEntityList)
        {
            ZdMajorCourseResourceEntity zdMajorCourseResourceEntity=zdMajorCourseResourceDao.selectById(zdOrderCartEntity.getZmcrId());
            SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectByCode(zdMajorCourseResourceEntity.getResourceCode());
            if(subjectResourceEntity==null)
            {
                throw new RRException("课程代码："+zdOrderCartEntity.getCourseCode()+"<br>课程名称："+zdOrderCartEntity.getCourseName()+"<br>教材代码："+subjectResourceEntity.getResourceCode()+"没有价格!");

            }
             ZdOrderResourceEntity zdOrderResourceEntity=new ZdOrderResourceEntity();
            zdOrderResourceEntity.setMajorId(zdOrderCartEntity.getMajorId());
            zdOrderResourceEntity.setCourseId(zdOrderCartEntity.getCourseId());
            zdOrderResourceEntity.setResourceId(zdOrderCartEntity.getResourceId());
            zdOrderResourceEntity.setOrderNum(zdOrderCartEntity.getCatNum());
            zdOrderResourceEntity.setOrderId(zdOrderEntity.getId());
            zdOrderResourceEntity.setOrderResourceNo("ORDERDETAIL"+timeInSecond);

            zdOrderResourceEntity.setZmcrId(zdOrderCartEntity.getZmcrId());
            if(subjectResourceEntity.getPrice()==null||subjectResourceEntity.getPrice().compareTo(new Double("0"))==0)
            {
                throw new RRException("课程代码："+zdOrderCartEntity.getCourseCode()+"<br>课程名称："+zdOrderCartEntity.getCourseName()+"<br>教材代码："+subjectResourceEntity.getResourceCode()+"没有价格!");
            }
            zdOrderResourceEntity.setItemPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
            zdOrderResourceEntity.setMayang(zdOrderResourceEntity.getItemPrice().multiply(BigDecimal.valueOf(zdOrderResourceEntity.getOrderNum())));
            zdOrderResourceDao.insert(zdOrderResourceEntity);

        }
    }


}
