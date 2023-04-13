package io.renren.modules.zd.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.service.SubjectResourceService;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.zd.dao.ZdCartDao;
import io.renren.modules.zd.dao.ZdSemesterRegDao;
import io.renren.modules.zd.entity.ZdCartEntity;
import io.renren.modules.zd.entity.ZdMajorCourseResourceEntity;
import io.renren.modules.zd.entity.ZdSemesterRegEntity;
import io.renren.modules.zd.form.SearchForm;
import io.renren.modules.zd.form.ZdCatFrom;
import io.renren.modules.zd.form.ZdCatResourceFrom;
import io.renren.modules.zd.service.ZdCartService;
import io.renren.modules.zd.service.ZdMajorCourseResourceService;
import io.renren.modules.zd.service.ZdOrderService;
import io.renren.modules.zd.service.ZdSemesterRegService;
import io.renren.modules.zd.vo.CartResourceVO;
import io.renren.modules.zd.vo.ZdCatVO;
import io.renren.modules.zd.vo.ZdOrderCartVO;
import io.renren.modules.zd.vo.ZdSemesterRegVO;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


@Service("zdSemesterRegService")
public class ZdSemesterRegServiceImpl extends ServiceImpl<ZdSemesterRegDao, ZdSemesterRegEntity> implements ZdSemesterRegService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Override
    public PageUtils selectListPage(Map<String, Object> params) {
        Page page=new Query(params).getPage();
        page.setRecords(baseMapper.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    public ZdSemesterRegVO selectRate(Map<String, Object> params) {
        return baseMapper.selectRate(params);
    }

    @Override
    @Transactional
    public List<ZdSemesterRegEntity> importReg(List<ZdSemesterRegEntity> list, SysUserEntity user, String semesterCode) {
        List<ZdSemesterRegEntity> bathSave=new ArrayList<>();
        for(ZdSemesterRegEntity zdSemesterRegEntity:list){
             if(StringUtils.isBlank(zdSemesterRegEntity.getSemesterCode())){
               zdSemesterRegEntity.setResult("报订季节不能为空");
               continue;
             }
             if(StringUtils.isBlank(zdSemesterRegEntity.getStudentType())){
                 zdSemesterRegEntity.setResult("学生类型不能为空");
                 continue;
             }
             if(StringUtils.isBlank(zdSemesterRegEntity.getMajorType())){
                 zdSemesterRegEntity.setResult("专业层次不能为空");
                 continue;
             }
             if(StringUtils.isBlank(zdSemesterRegEntity.getCourseCode())){
                 zdSemesterRegEntity.setResult("课程代码不能为空");
                 continue;
             }
             if(StringUtils.isBlank(zdSemesterRegEntity.getCustCode())){
                 zdSemesterRegEntity.setResult("客商代码不能为空");
                 continue;
             }
            ZdSemesterRegEntity entity=baseMapper.selectUnique(zdSemesterRegEntity.getSemesterCode(),
                    zdSemesterRegEntity.getStudentType(),zdSemesterRegEntity.getMajorType(),
                    zdSemesterRegEntity.getMajorCode(),zdSemesterRegEntity.getCourseCode(),zdSemesterRegEntity.getCustCode());
            if(entity==null){
                entity=new ZdSemesterRegEntity();
                BeanUtils.copyProperties(zdSemesterRegEntity,entity);
            }
            entity.setCourseName(zdSemesterRegEntity.getCourseName());
            entity.setMajorName(zdSemesterRegEntity.getMajorName());
            entity.setCustName(zdSemesterRegEntity.getCustName());
            entity.setGroupName(zdSemesterRegEntity.getGroupName());
            entity.setGroupCode(zdSemesterRegEntity.getGroupCode());
            entity.setSchoolCode(zdSemesterRegEntity.getSchoolCode());
            entity.setSchoolName(zdSemesterRegEntity.getSchoolName());
            entity.setTeachCode(zdSemesterRegEntity.getTeachCode());
            entity.setTeachName(zdSemesterRegEntity.getTeachName());
            bathSave.add(entity);
            zdSemesterRegEntity.setResult("成功");
         }
        if(bathSave.size()>0){
            insertOrUpdateBatch(bathSave,1000);
        }
        return list;
    }


    @Override
    public List<ZdSemesterRegEntity> selectAll(Map<String, Object> params) {
        return baseMapper.selectAll(params);
    }

    @Override
    @Transactional
    public Integer remove(String custCode, String semesterCode) {
        return baseMapper.delete(new EntityWrapper<ZdSemesterRegEntity>().eq("cust_code",custCode).eq("semester_code",semesterCode));
    }

    @Override
    public ZdSemesterRegVO selectRateSum(Map<String, Object> params) {
        return baseMapper.selectRateSum(params);
    }

    @Override
    public ZdSemesterRegVO selectCurrentRate(Map<String, Object> params) {
        String orgCode= MapUtils.getString(params,"orgCode");

        SysOrgEntity sysOrgEntity = sysOrgService.selectByOrgCode(orgCode);
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(orgCode);
        if(sysOrgSettingEntity==null){
            return null;
        }
        if(StringUtils.isBlank(sysOrgSettingEntity.getCustCode())){
            return null;
        }
//        Map<String,Object> params=new HashMap<>();
        params.put("custCode", sysOrgSettingEntity.getCustCode());
//        params.put("semesterCode", semesterCode);
        params.put("orgCode", orgCode);
        ZdSemesterRegVO zdSemesterRegVO =selectRate(params);
        if(zdSemesterRegVO!=null){
            zdSemesterRegVO.setOrgType(sysOrgEntity.getOrgType());
            if (sysOrgSettingEntity.getIsConfigRate1() == 1) {
                if (zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate1(new BigDecimal("0"));
                } else {
                    Integer total=zdSemesterRegVO.getOrderCartTotal()+zdSemesterRegVO.getSourceCartTotal()+zdSemesterRegVO.getOrderTotal()+zdSemesterRegVO.getSourceTotal();
                    //专业配置率不算采购单采购车数量
                    /*if(ObjectUtils.isNotEmpty(params.get("majorCode"))){
                        total=zdSemesterRegVO.getOrderCartTotal()+zdSemesterRegVO.getOrderTotal();
                    }else{
                        total=zdSemesterRegVO.getOrderCartTotal()+zdSemesterRegVO.getSourceCartTotal()+zdSemesterRegVO.getOrderTotal()+zdSemesterRegVO.getSourceTotal();
                    }*/
                    zdSemesterRegVO.setRate1(new BigDecimal((float) total/ zdSemesterRegVO.getStuTotal())
                            .setScale(2, BigDecimal.ROUND_DOWN));
                    zdSemesterRegVO.setRate1(zdSemesterRegVO.getRate1().multiply(new BigDecimal("100")));
                }
            }
            if (sysOrgSettingEntity.getIsConfigRate2() == 1) {
                if (zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate2(new BigDecimal("0"));
                } else {
                    if(Constant.ORG_TYPE.SHENG.equals(sysOrgEntity.getOrgType())||Constant.ORG_TYPE.SHI.equals(sysOrgEntity.getOrgType())){
                        Integer left=zdSemesterRegVO.getStockIncomeTotal()-zdSemesterRegVO.getRefundTotal()-zdSemesterRegVO.getRefundSupplierTotal();
                        zdSemesterRegVO.setRate2(new BigDecimal((float) left / zdSemesterRegVO.getStuTotal())
                                .setScale(2, BigDecimal.ROUND_DOWN));
                        zdSemesterRegVO.setRate2(zdSemesterRegVO.getRate2().multiply(new BigDecimal("100")));
                    }else if(Constant.ORG_TYPE.XIAN.equals(sysOrgEntity.getOrgType())){
                        Integer left=zdSemesterRegVO.getPublishTotal()-zdSemesterRegVO.getRefundTotal()-zdSemesterRegVO.getRefundSupplierTotal();
                        zdSemesterRegVO.setRate2(new BigDecimal((float) left / zdSemesterRegVO.getStuTotal())
                                .setScale(2, BigDecimal.ROUND_DOWN));
                        zdSemesterRegVO.setRate2(zdSemesterRegVO.getRate2().multiply(new BigDecimal("100")));
                    }

                }
            }
            if (sysOrgSettingEntity.getIsConfigRate3() == 1) {
                if (zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate3(new BigDecimal("0"));
                } else {
                    if(Constant.ORG_TYPE.SHENG.equals(sysOrgEntity.getOrgType())||Constant.ORG_TYPE.SHI.equals(sysOrgEntity.getOrgType())){
                        zdSemesterRegVO.setRate3(new BigDecimal((float) zdSemesterRegVO.getStockIncomeTotal() / zdSemesterRegVO.getStuTotal())
                                .setScale(2, BigDecimal.ROUND_DOWN));
                        zdSemesterRegVO.setRate3(zdSemesterRegVO.getRate3().multiply(new BigDecimal("100")));
                    }else if(Constant.ORG_TYPE.XIAN.equals(sysOrgEntity.getOrgType())){
                        zdSemesterRegVO.setRate3(new BigDecimal((float) zdSemesterRegVO.getPublishTotal() / zdSemesterRegVO.getStuTotal())
                                .setScale(2, BigDecimal.ROUND_DOWN));
                        zdSemesterRegVO.setRate3(zdSemesterRegVO.getRate3().multiply(new BigDecimal("100")));
                    }

                }
            }
            BigDecimal comp=new BigDecimal("100");
            if(zdSemesterRegVO.getRate1()!=null&&comp.compareTo(zdSemesterRegVO.getRate1()) < 0){
                zdSemesterRegVO.setRate1(comp);
            }
            if(zdSemesterRegVO.getRate2()!=null&&comp.compareTo(zdSemesterRegVO.getRate2()) < 0){
                zdSemesterRegVO.setRate2(comp);
            }
            if(zdSemesterRegVO.getRate3()!=null&&comp.compareTo(zdSemesterRegVO.getRate3()) < 0){
                zdSemesterRegVO.setRate3(comp);
            }
            return zdSemesterRegVO;
        }else{
            return null;
        }
    }


}
