package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.sys.dao.SysSemesterDao;
import io.renren.modules.sys.dao.SysSemesterOrgDao;
import io.renren.modules.sys.entity.*;
import io.renren.modules.sys.service.SysOrgRateService;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.service.ZdSemesterRegService;
import io.renren.modules.zd.vo.ZdSemesterRegVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("sysSemesterService")
public class SysSemesterServiceImpl extends ServiceImpl<SysSemesterDao, SysSemesterEntity> implements SysSemesterService {
    @Autowired
    private SysSemesterDao sysSemesterDao;
    @Autowired
    private SysSemesterOrgDao sysSemesterOrgDao;
    @Autowired
    private SysOrgService sysOrgDao;
    @Autowired
    private SysOrgSettingService sysOrgSettingService;
    @Autowired
    private ZdSemesterRegService zdSemesterRegService;
    @Autowired
    private SysOrgRateService sysOrgRateService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysSemesterEntity> page=new Query<SysSemesterEntity>(params).getPage();
        page.setRecords(sysSemesterDao.selectListZYDD(page,params));
        return new PageUtils(page);
    /*    Page<SysSemesterEntity> page = this.selectPage(
                new Query<SysSemesterEntity>(params).getPage(),
                new EntityWrapper<SysSemesterEntity>()
        );
        return new PageUtils(page);*/
    }

    @Override
    @Transactional
    @CacheEvict(value = Constant.CACHE_NAMESPACE + "currentSemester")
    public SysSemesterEntity updateCurrentSemester(String orgCode, SysSemesterEntity updateForm) {
        SysSemesterEntity sysSemesterEntity=sysSemesterDao.selectById(updateForm.getId());
        if("ZYDD".equals(orgCode))
        {
            sysSemesterDao.updateStatusNotInId(updateForm.getId(),"0");
            sysSemesterEntity.setStatus("1");
            sysSemesterDao.updateById(sysSemesterEntity);
        }else
        {
            if(sysSemesterEntity!=null)
            {
                sysSemesterOrgDao.updateStatus(orgCode, Constant.COMMON_STATUS.CLOSE);
                SysSemesterOrgEntity sysSemester=sysSemesterOrgDao.selectByOrgAndSemester(orgCode,sysSemesterEntity.getSemesterCode());
                if(sysSemester!=null)
                {
                    sysSemester.setSemesterStartTime(DateUtils.stringToDate(updateForm.getSemesterStartTimeStr(),DateUtils.DATE_PATTERN));
                    sysSemester.setSemesterEndTime(DateUtils.stringToDate(updateForm.getSemesterEndTimeStr(),DateUtils.DATE_PATTERN));
                    sysSemester.setStatus(Constant.COMMON_STATUS.OPEN);
                    sysSemesterOrgDao.updateById(sysSemester);
                }
                else
                {
                    SysSemesterOrgEntity sysSemesterOrgEntity=new SysSemesterOrgEntity();
                    sysSemesterOrgEntity.setSemesterStartTime(DateUtils.stringToDate(updateForm.getSemesterStartTimeStr(),DateUtils.DATE_PATTERN));
                    sysSemesterOrgEntity.setSemesterEndTime(DateUtils.stringToDate(updateForm.getSemesterEndTimeStr(),DateUtils.DATE_PATTERN));
                    sysSemesterOrgEntity.setStatus(Constant.COMMON_STATUS.OPEN);
                    sysSemesterOrgEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
                    sysSemesterOrgEntity.setOrgCode(orgCode);
                    sysSemesterOrgDao.insert(sysSemesterOrgEntity);
                }


            }
        }

        return sysSemesterEntity;
        // SysSemesterEntity sysSemester=this.selectById(id);
       // sysSemester.setStatus(Constant.COMMON_STATUS.OPEN);
        //sysSemesterDao.updateById(sysSemester);
        //return sysSemester;
    }
    @Override
   public SysSemesterEntity getCurrentSemester() {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity= sysOrgDao.selectByOrgCode(sysUserEntity.getOrgCode());
        if("ZYDD".equals(sysOrgEntity.getOrgCode()))
        {
            return getCurrentSemester(sysUserEntity.getOrgCode());
        }else
        {
            return getCurrentSemester(sysUserEntity.getOrgCode(),sysOrgEntity.getParentCodes());
        }

    }
    @Cacheable(value = Constant.CACHE_NAMESPACE + "currentSemester",key = "'CURRENT_SEMESTER:'+#p0" )
    @Override
    public SysSemesterEntity getCurrentSemester(String orgCode,String parentCodes) {
        return  sysSemesterDao.selectCurrentSemester(orgCode,parentCodes);
    }
    @Cacheable(value = Constant.CACHE_NAMESPACE + "currentSemester",key = "'CURRENT_SEMESTER:'+#p0" )
    @Override
    public SysSemesterEntity getCurrentSemester(String orgCode) {
        return  sysSemesterDao.selectZyddSemester(orgCode);
    }

    /**
     * 获得省级列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryListPage(Map<String, Object> params) {
        Page<SysSemesterEntity> page=new Query<SysSemesterEntity>(params).getPage();
        page.setRecords(sysSemesterDao.selectListPage(page,params));
        return new PageUtils(page);

    }

    @Override
    public List listAll(Map<String, Object> params) {
        return sysSemesterDao.selectListPage(params);
    }

    @Override
    public List listRefund() {
        Map map=new HashMap();
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        if("3".equals(sysUserEntity.getOrgType())){
            SysOrgEntity sysOrgEntity= sysOrgDao.selectParentByOrgCode(sysUserEntity.getOrgCode());
            map.put("orgCode",sysOrgEntity.getOrgCode());
        }else{
            map.put("orgCode",sysUserEntity.getOrgCode());
        }

        map.put("isRefund","1");
        return sysSemesterDao.selectListPage(map);
    }

    @Override
    public List<SysSemesterEntity> listAllByZydd(Map<String, Object> params) {
        return sysSemesterDao.listAllByZydd(params);
    }

    @Override
    public SysSemesterEntity getSemesterByCode(String semesterCode) {
        return sysSemesterDao.selectByCode(semesterCode);
    }

    @Override
    @CachePut(value = Constant.CACHE_NAMESPACE + "currentSemester",key = "'CURRENT_SEMESTER:'+#p0" )
    @Transactional
    public SysSemesterEntity updateSemester(String orgCode, String semesterCode) {
        sysSemesterOrgDao.updateStatus(orgCode, Constant.COMMON_STATUS.CLOSE);
        SysSemesterOrgEntity sysSemesterOrgEntity=sysSemesterOrgDao.selectByOrgAndSemester(orgCode,semesterCode);
        if(sysSemesterOrgEntity==null){
             sysSemesterOrgEntity=new SysSemesterOrgEntity();

        }
        sysSemesterOrgEntity.setStatus(Constant.COMMON_STATUS.OPEN);
        sysSemesterOrgEntity.setOrgCode(orgCode);
        sysSemesterOrgEntity.setSemesterCode(semesterCode);
        if(StringUtils.isNotBlank(sysSemesterOrgEntity.getId())){
            sysSemesterOrgDao.updateById(sysSemesterOrgEntity);
        }else{
            sysSemesterOrgDao.insert(sysSemesterOrgEntity);
        }
        sysSemesterOrgEntity.setStatus(Constant.COMMON_STATUS.OPEN);
        SysSemesterEntity sysSemesterEntity=baseMapper.selectByCode(semesterCode);
        if(sysSemesterEntity==null){
            throw new RRException("更新数据异常");
        }
        sysSemesterEntity.setSemesterStartTime(sysSemesterOrgEntity.getSemesterStartTime());
        sysSemesterEntity.setSemesterEndTime(sysSemesterOrgEntity.getSemesterEndTime());
        return sysSemesterEntity;
    }

    @Override
    @CacheEvict(value = Constant.CACHE_NAMESPACE + "currentSemester",allEntries = true)
    @Transactional
    public void updateDefaultSemester(String semesterCode) {
        List<SysOrgEntity> list=sysOrgDao.listProvince();
        sysSemesterOrgDao.updateAllStatus(Constant.COMMON_STATUS.CLOSE);
        for(SysOrgEntity sysOrgEntity:list){
            if("ZYDD".equals(sysOrgEntity.getOrgCode())){
                SysSemesterEntity sysSemesterEntity=sysSemesterDao.selectByCode(semesterCode);
                sysSemesterEntity.setStatus(Constant.COMMON_STATUS.OPEN);
                sysSemesterDao.updateById(sysSemesterEntity);
                sysSemesterDao.updateStatusNotInId(sysSemesterEntity.getId(),Constant.COMMON_STATUS.CLOSE);
            }else{
                SysSemesterOrgEntity sysSemesterOrgEntity=sysSemesterOrgDao.selectByOrgAndSemester(sysOrgEntity.getOrgCode(),semesterCode);
                if(sysSemesterOrgEntity==null){
                    sysSemesterOrgEntity=new SysSemesterOrgEntity();
                    sysSemesterOrgEntity.setOrgCode(sysOrgEntity.getOrgCode());
                    sysSemesterOrgEntity.setSemesterCode(semesterCode);
                }
                sysSemesterOrgEntity.setStatus(Constant.COMMON_STATUS.OPEN);

                if(StringUtils.isNotBlank(sysSemesterOrgEntity.getId())){
                    sysSemesterOrgDao.updateById(sysSemesterOrgEntity);
                }else{
                    sysSemesterOrgDao.insert(sysSemesterOrgEntity);
                }

            }
        }
    }

    @Autowired
    private RedisUtils redisUtils;
    @Override
    @Transactional
    public void closeRate(String semesterCode) {

        String lock=redisUtils.get(Constant.RATE_LOCK);
        if(lock!=null){
            throw new RRException("正在归档数据，请稍后尝试");
        }
        redisUtils.set(Constant.RATE_LOCK,"1",600);
        List<SysOrgSettingEntity> list=sysOrgSettingService
                .selectList(new EntityWrapper<SysOrgSettingEntity>()
                        .eq("is_config_rate1",1)
                .or().eq("is_config_rate2",1)
                .or().eq("is_config_rate3",1));
        Map<String,Object> params=new HashMap<>();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        for(SysOrgSettingEntity sysOrgSettingEntity:list){
//            SysOrgEntity sysOrgEntity = sysOrgDao.selectByOrgCode(sysOrgSettingEntity.getOrgCode());
            params.put("semesterCode", semesterCode);
            params.put("orgCode", sysOrgSettingEntity.getOrgCode());
            params.put("isClose",1);
//            ZdSemesterRegVO zdSemesterRegVO = zdSemesterRegService.selectRate(params);
            SysOrgRateEntity sysOrgRateEntity=new SysOrgRateEntity();
            ZdSemesterRegVO zdSemesterRegVO =zdSemesterRegService.selectCurrentRate(params);
            if(zdSemesterRegVO!=null){
                sysOrgRateEntity.setRate1(zdSemesterRegVO.getRate1());
                sysOrgRateEntity.setRate2(zdSemesterRegVO.getRate2());
                sysOrgRateEntity.setRate3(zdSemesterRegVO.getRate3());
            }

            /*
            if (sysOrgSettingEntity.getIsConfigRate1() == 1) {
                if (zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate1(new BigDecimal("0"));
                } else {
                    Integer total=zdSemesterRegVO.getOrderTotal()+zdSemesterRegVO.getSourceTotal();
                    zdSemesterRegVO.setRate1(new BigDecimal((float) total/ zdSemesterRegVO.getStuTotal())
                            .setScale(2, BigDecimal.ROUND_DOWN));
                    zdSemesterRegVO.setRate1(zdSemesterRegVO.getRate1().multiply(new BigDecimal("100")));
                }
                sysOrgRateEntity.setRate1(zdSemesterRegVO.getRate1());
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
                sysOrgRateEntity.setRate2(zdSemesterRegVO.getRate2());

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
                sysOrgRateEntity.setRate3(zdSemesterRegVO.getRate3());
            }*/
         /*   if (sysOrgSettingEntity.getIsConfigRate1() == 1) {
                if (zdSemesterRegVO.getOrderTotal() == 0 || zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate1(new BigDecimal("0"));
                } else {
                    Integer total=zdSemesterRegVO.getOrderTotal()+zdSemesterRegVO.getSourceTotal();
                    zdSemesterRegVO.setRate1(new BigDecimal((float) total/ zdSemesterRegVO.getStuTotal())
                            .setScale(2, BigDecimal.ROUND_DOWN));
                    zdSemesterRegVO.setRate1(zdSemesterRegVO.getRate1().multiply(new BigDecimal("100")));
                }
                sysOrgRateEntity.setRate1(zdSemesterRegVO.getRate1());
            }
            if (sysOrgSettingEntity.getIsConfigRate2() == 1) {
                if (zdSemesterRegVO.getSourceTotal() == 0 || zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate2(new BigDecimal("0"));
                } else {
                    Integer left=zdSemesterRegVO.getStockIncomeTotal()-zdSemesterRegVO.getRefundTotal()-zdSemesterRegVO.getRefundSupplierTotal();
                    zdSemesterRegVO.setRate2(new BigDecimal((float) left / zdSemesterRegVO.getStuTotal())
                            .setScale(2, BigDecimal.ROUND_DOWN));
                    zdSemesterRegVO.setRate2(zdSemesterRegVO.getRate2().multiply(new BigDecimal("100")));
                }

                sysOrgRateEntity.setRate2(zdSemesterRegVO.getRate2());
            }
            if (sysOrgSettingEntity.getIsConfigRate3() == 1) {
                if (zdSemesterRegVO.getSourceTotal() == 0 || zdSemesterRegVO.getStuTotal() == 0) {
                    zdSemesterRegVO.setRate3(new BigDecimal("0"));
                } else {
                    zdSemesterRegVO.setRate3(new BigDecimal((float) zdSemesterRegVO.getStockIncomeTotal() / zdSemesterRegVO.getStuTotal())
                            .setScale(2, BigDecimal.ROUND_DOWN));
                    zdSemesterRegVO.setRate3(zdSemesterRegVO.getRate3().multiply(new BigDecimal("100")));
                }
                sysOrgRateEntity.setRate3(zdSemesterRegVO.getRate3());
            }*/
            sysOrgRateEntity.setOrgCode(sysOrgSettingEntity.getOrgCode());
            sysOrgRateEntity.setSemesterCode(semesterCode);
            sysOrgRateEntity.setCreateBy(sysUserEntity.getUsername());
            sysOrgRateEntity.setCreateTime(new Date());
            sysOrgRateService.replaceRate(sysOrgRateEntity);
        }
        redisUtils.delete(Constant.RATE_LOCK);
    }


}
