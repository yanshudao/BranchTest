package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.sys.dao.SysConfigDao;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.dao.SysOrgSettingDao;
import io.renren.modules.sys.entity.*;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.ZdPublishDao;
import io.renren.modules.zd.dao.ZdRefundDao;
import io.renren.modules.zd.dao.ZdRefundResourceDao;
import io.renren.modules.zd.dao.ZdRefundSupplierDao;
import io.renren.modules.zd.entity.*;
import io.renren.modules.zd.form.CountyRefundSubmitFrom;
import io.renren.modules.zd.form.ZdRefundCreateOrderFrom;
import io.renren.modules.zd.form.ZdRefundForm;
import io.renren.modules.zd.form.ZdRefundResourceForm;
import io.renren.modules.zd.service.ZdRefundCountryService;
import io.renren.modules.zd.service.ZdStockService;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.ZdCountyRefundResourceVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service("zdRefundCountryService")
public class ZdRefundCountryServiceImpl extends ServiceImpl<ZdRefundDao, ZdRefundEntity> implements ZdRefundCountryService  {

    @Resource
    private ZdRefundDao zdRefundDao;
    @Resource
    private SysOrgDao sysOrgDao;
    @Resource
    private ZdStockService zdStockService;
    @Resource
    private ZdRefundResourceDao zdRefundResourceDao;

    @Resource
    private SysSemesterService sysSemesterService;
    @Resource
    private SysOrgSettingService sysOrgSettingService;

    @Resource
    private SubjectResourceDao subjectResourceDao;
    @Resource
    private SysOrgSettingDao sysOrgSettingDao;
    @Resource
    private ZdPublishDao zdPublishDao;
    @Resource
    private ZdRefundSupplierDao zdRefundSupplierDao;
    @Resource
    private SysConfigDao sysConfigDao;


    @Override
    public PageUtils listResourcesRefundable(Map<String, Object> params) {

        Page<ZdRefundableResourceEntity> page=new Query<ZdRefundableResourceEntity>(params).getPage();
        List<ZdRefundableResourceEntity> list = zdRefundDao.queryRefundableResource(page,params);

//        for (ZdRefundableResourceEntity item : list) {
       /* for (int i = 0; i<list.size(); i++) {
            ZdRefundableResourceEntity item = list.get(i);
            String resId = item.getResourceId();
            if(!StringUtils.isEmpty(resId)) resId = resId.trim();
            //计算待审退货数
            Integer approvingNum = zdRefundDao.queryApprovingNumByResId(resId);
            approvingNum = approvingNum==null?0:approvingNum;
            item.setApprovingNum(approvingNum);
            //已退数
            Integer refundedNum = zdRefundDao.queryRefundedNumByResId(resId);
            refundedNum = refundedNum==null?0:refundedNum;
            item.setRefundedNum(refundedNum);
            //可退发行
            Integer refundablePublishedNum = zdRefundDao.queryRefundablePublishedNumByResId(resId);
            refundablePublishedNum = refundablePublishedNum==null?0:refundablePublishedNum;
            item.setRefundableNum(refundablePublishedNum);
            //计算可退数 = 可退发行数-已退数   可退数小于0时
            item.setRefundableNum(refundablePublishedNum - refundedNum);
            if( item.getRefundableNum() <= 0){
                list.remove(item);
                i--;
            }
//            if(params.get("isRefundable") != null && params.get("isRefundable").equals("1") ){
//
//            }
            //季发数
            item.setSemePublishedNum(zdRefundDao.querySemePublishedNumByResId(resId, sysSemesterEntity.getSemesterCode()));
            //计算总发数
            item.setTotalNum(zdRefundDao.queryTotalNumByResId(resId));
        }*/
        page.setRecords(list);
        return new PageUtils(page);
    }

    /**
     * 县级保存退货单
     * @param req
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRefundOrder(ZdRefundCreateOrderFrom req) {
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(req.getFromOrgCode());
        SysOrgEntity sheng=sysOrgDao.selectByOrgCode(sysOrgSettingEntity.getToOrgCode());
//        Map<String,Object> params=new HashMap<>();
//        params.put("isRefund","1");
//        params.put("orgCode",sheng.getOrgCode());
//        params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
//        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();
        RefundLimitInfoVO refundLimitInfoVo=getRefundLimitInfo2(req.getFromOrgCode(),req.getRefundSemesterCode());
        BigDecimal totalRefund=new BigDecimal("0.00");
//        totalRefund=totalRefund.add(refundLimitInfoVo.getAuditRefundMayang());
     //   req.getRefundOrder().setSemesterCode(sysSemesterEntity.getSemesterCode());
        String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        String refundCode = "TH" + req.getFromOrgCode() + timeInSecond;
        ZdRefundEntity refundOrder = new ZdRefundEntity();
        refundOrder.setRefundSemesterCode(req.getRefundSemesterCode());
        refundOrder.setRefundCode(refundCode);
        refundOrder.setSemesterCode(sysSemesterEntity.getSemesterCode());
        refundOrder.setFromOrgCode(sysOrgSettingEntity.getOrgCode());
        refundOrder.setToOrgCode(sysOrgSettingEntity.getToOrgCode());
        refundOrder.setLogisticAddress(req.getLogisticAddress());
        refundOrder.setLogisticBag(req.getLogisticBag());
        refundOrder.setLogisticCompany(req.getLogisticCompany());
        refundOrder.setLogisticNo(req.getLogisticNo());
        refundOrder.setLogisticType(req.getLogisticType());
        if(StringUtils.isBlank(req.getStatus()))
        {
            refundOrder.setStatus(Constant.REFUND_STATUS.NEW);
        }else
        {
            refundOrder.setStatus(req.getStatus());
        }
        int res = zdRefundDao.insert(refundOrder);
        if (res == 1) {
           // long pkId = zdRefundCountryService.selectLastInsertPkId(refundCode);
         //   zdRefundDao.updateOrgCode(req.getRefundOrder().getOrgCode(), pkId);
            List<ZdRefundResourceEntity> list = req.getResourceList();
            for (ZdRefundResourceEntity item : list) {
                SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(item.getResourceId());
                item.setRefundId(refundOrder.getId());
                if(item.getRefundNum()<1)
                {
                    throw new RRException("退货数量不符合规范");
                }

                item.setRealNum(item.getRefundNum());
                item.setNitemdiscountrate(DecimalUtils.DEFAULT_DISCOUNT);
                item.setMayang(DecimalUtils.mayang(subjectResourceEntity.getPrice(),item.getRefundNum()));
                totalRefund=totalRefund.add(item.getMayang());
                if(totalRefund.compareTo(refundLimitInfoVo.getAllowPublishMayang())>0)
                {
                    throw new RRException("超出可退码洋");
                }
                item.setShiyang(DecimalUtils.shiyang(item.getMayang(),DecimalUtils.DEFAULT_DISCOUNT));
                item.setRefundPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
           /*     item.setNitemdiscountrate(new BigDecimal(100.00));
                item.setMayang(BigDecimal.valueOf(subjectResourceEntity.getPrice()).multiply(BigDecimal.valueOf(item.getRefundNum())));
                item.setShiyang(BigDecimal.valueOf(subjectResourceEntity.getPrice()).multiply(BigDecimal.valueOf(item.getRefundNum())));
       */
//                item.setResourceId(item.getResourceId());
                zdRefundResourceDao.insert(item);
                if(Constant.REFUND_STATUS.COMPLETE.equals(refundOrder.getStatus()))
                {
                    ZdStockEntity zdStockEntity=new ZdStockEntity();
                    zdStockEntity.setResourceId(item.getResourceId());
                    zdStockEntity.setOrgCode(sheng.getOrgCode());
                    zdStockEntity.setTotalAmount(item.getRefundNum());
                    zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.REFUND_ORG);
                    //zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                    //  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
                    zdStockService.saveStock(zdStockEntity,ShiroUtils.getUserEntity().getOrgCode());

                }

            }
            return 1;
        } else {
            return 0;
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int submitRefundOrder(ZdRefundCreateOrderFrom req) {
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(req.getFromOrgCode());
        SysOrgEntity sheng=sysOrgDao.selectByOrgCode(sysOrgSettingEntity.getToOrgCode());
//        Map<String,Object> params=new HashMap<>();
//        params.put("isRefund","1");
//        params.put("orgCode",sheng.getOrgCode());
//        params.put("semesterCode",sysSemesterService.getCurrentSemester().getSemesterCode());
//        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();
        RefundLimitInfoVO refundLimitInfoVo=getRefundLimitInfo2(req.getFromOrgCode(),req.getRefundSemesterCode());
        if(BigDecimal.ZERO.compareTo(refundLimitInfoVo.getAllowPublishMayang())>0){
            throw new RRException("超出可退码洋");
        }

        BigDecimal totalRefund=new BigDecimal("0.00");
//        totalRefund=totalRefund.add(refundLimitInfoVo.getAuditRefundMayang());
        //   req.getRefundOrder().setSemesterCode(sysSemesterEntity.getSemesterCode());
        String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        String refundCode = "TH" + req.getFromOrgCode() + timeInSecond;
        ZdRefundEntity refundOrder = new ZdRefundEntity();
        refundOrder.setRefundSemesterCode(req.getRefundSemesterCode());
        refundOrder.setRefundCode(refundCode);
        refundOrder.setSemesterCode(sysSemesterEntity.getSemesterCode());
        refundOrder.setFromOrgCode(sysOrgSettingEntity.getOrgCode());
        refundOrder.setToOrgCode(sysOrgSettingEntity.getToOrgCode());
        refundOrder.setLogisticAddress(req.getLogisticAddress());
        refundOrder.setLogisticBag(req.getLogisticBag());
        refundOrder.setLogisticCompany(req.getLogisticCompany());
        refundOrder.setLogisticNo(req.getLogisticNo());
        refundOrder.setLogisticType(req.getLogisticType());
        if(StringUtils.isBlank(req.getStatus()))
        {
            refundOrder.setStatus(Constant.REFUND_STATUS.NEW);
        }else
        {
            refundOrder.setStatus(req.getStatus());
        }
        int res = zdRefundDao.insert(refundOrder);
        if (res == 1) {
            // long pkId = zdRefundCountryService.selectLastInsertPkId(refundCode);
            //   zdRefundDao.updateOrgCode(req.getRefundOrder().getOrgCode(), pkId);
            List<ZdRefundResourceEntity> list = req.getResourceList();
            for (ZdRefundResourceEntity item : list) {
                SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(item.getResourceId());
                item.setRefundId(refundOrder.getId());
                if(item.getRefundNum()<1)
                {
                    throw new RRException("退货数量不符合规范");
                }

                item.setRealNum(item.getRefundNum());
                item.setNitemdiscountrate(DecimalUtils.DEFAULT_DISCOUNT);
                item.setMayang(DecimalUtils.mayang(subjectResourceEntity.getPrice(),item.getRefundNum()));
                /*totalRefund=totalRefund.add(item.getMayang());
                if(totalRefund.compareTo(refundLimitInfoVo.getAllowPublishMayang())>0)
                {
                    throw new RRException("超出可退码洋");
                }*/
                item.setShiyang(DecimalUtils.shiyang(item.getMayang(),DecimalUtils.DEFAULT_DISCOUNT));
                item.setRefundPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
           /*     item.setNitemdiscountrate(new BigDecimal(100.00));
                item.setMayang(BigDecimal.valueOf(subjectResourceEntity.getPrice()).multiply(BigDecimal.valueOf(item.getRefundNum())));
                item.setShiyang(BigDecimal.valueOf(subjectResourceEntity.getPrice()).multiply(BigDecimal.valueOf(item.getRefundNum())));
       */
//                item.setResourceId(item.getResourceId());
                zdRefundResourceDao.insert(item);
                if(Constant.REFUND_STATUS.COMPLETE.equals(refundOrder.getStatus()))
                {
                    ZdStockEntity zdStockEntity=new ZdStockEntity();
                    zdStockEntity.setResourceId(item.getResourceId());
                    zdStockEntity.setOrgCode(sheng.getOrgCode());
                    zdStockEntity.setTotalAmount(item.getRefundNum());
                    zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.REFUND_ORG);
                    //zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                    //  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
                    zdStockService.saveStock(zdStockEntity,ShiroUtils.getUserEntity().getOrgCode());

                }

            }
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public PageUtils queryRefundOrderList(Map<String,Object> params) {
    /*    Map<String, Object> pageParam = new HashMap<>();
        pageParam.put("page",req.getPage());
   */
     Page<ZdRefundOrderListEntity> page = new Query<ZdRefundOrderListEntity>(params).getPage();
        List<ZdRefundOrderListEntity> resList = zdRefundDao.queryRefundOrderList(page ,params);
       /* for (ZdRefundOrderListEntity item : resList) {
            item.setKinds(zdRefundDao.countResourceKinds(item.getRefundCode()));
            item.setRefundTotal(zdRefundDao.sumRefundNum(item.getRefundCode()));
            item.setMayangTotal(zdRefundDao.sumRefundMaYang(item.getRefundCode()));
        }*/
        page.setRecords(resList);
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryRefundResourcesList(Map<String,Object> params) {
        Page<ZdRefundResourceDetailEntity> page = new Query<ZdRefundResourceDetailEntity>(params).getPage();
        List<ZdRefundResourceDetailEntity> resList = zdRefundDao.queryRefundResourcesList(page ,params);
          page.setRecords(resList);
        return new PageUtils(page);
    }




    @Override
    public String getUserIdByOrgCode(String orgCode) {
        return zdRefundDao.getUserIdByOrgCode(orgCode);
    }

    @Override
    public List<ZdRefundResourceEntity> listRefundResourcesByRefundId(String refundId) {
        return zdRefundResourceDao.listRefundResourcesByRefundId(refundId);
    }

    @Override
    public List<ZdCountyRefundResourceVO> listResourceByRefundId(String refundId) {
        return zdRefundResourceDao.listResourceByRefundId(refundId);
    }

    @Override
    @Transactional
    public int reportRefundOrder(String refundId) {
        return zdRefundDao.reportRefundOrderById(refundId);
    }

    @Override
    @Transactional
    public int saveRefundResource(ZdRefundForm zdRefundForm) {
//        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        int i=0;
        SysOrgSettingEntity sysOrgSettingEntity= sysOrgSettingService.selectByOrg(zdRefundForm.getOrgCode());
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();

        for(ZdRefundResourceForm zdRefundResourceForm:zdRefundForm.getResourceForm())
       {
           ZdRefundResourceEntity zdRefundResourceEntity=zdRefundResourceDao.selectById(zdRefundResourceForm.getId());
            if(zdRefundResourceEntity==null)
            {
                throw new RRException("详情单未找到");
            }
            if(Constant.REFUND_ORG_STATUS.COMPLETE.equals(zdRefundResourceEntity.getStatus())||Constant.REFUND_ORG_STATUS.FINISH.equals(zdRefundResourceEntity.getStatus()))
            {
                throw new RRException("状态不允许修改");
            }
           int allowRefundNum=getRefundResourceLimit(zdRefundForm.getOrgCode(),
                   semesterEntityList,zdRefundResourceEntity.getResourceId(),sysSemesterEntity.getSemesterCode(),sysOrgSettingEntity.getToOrgCode());
           if(allowRefundNum<0||(allowRefundNum+zdRefundResourceEntity.getRefundNum()-zdRefundResourceForm.getRefundNum()<0))
           {
               throw new RRException("超出可退数量！");
           }
           zdRefundResourceEntity.setRefundNum(zdRefundResourceForm.getRefundNum());
           zdRefundResourceEntity.setRealNum(zdRefundResourceForm.getRefundNum());
           zdRefundResourceEntity.setMayang(DecimalUtils.mayang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum()));
           zdRefundResourceEntity.setShiyang(DecimalUtils.shiyang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum(),zdRefundResourceEntity.getNitemdiscountrate()));
           i+=zdRefundResourceDao.updateById(zdRefundResourceEntity);
       }
        return i;
    }

    @Override
    @Transactional
    public int deleteRefundResource(String id) {
        ZdRefundResourceEntity zdRefundResourceEntity=zdRefundResourceDao.selectById(id);
        ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(zdRefundResourceEntity.getRefundId());
        if("3".equals(zdRefundResourceEntity.getStatus())||Constant.REFUND_STATUS.COMPLETE.equals(zdRefundEntity.getStatus())||Constant.REFUND_STATUS.FINISH.equals(zdRefundEntity.getStatus()))
        {
            throw new RRException("状态不允许删除");
        }
        return  zdRefundResourceDao.deleteById(id);
    }

    @Override
    @Transactional
    public int updateAddress(ZdRefundForm zdRefundForm) {
        ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(zdRefundForm.getId());
        if(zdRefundEntity==null)
        {
            throw new RRException("详情单未找到！");
        }
        zdRefundEntity.setShippingAddress(zdRefundEntity.getShippingAddress());
        zdRefundEntity.setShippingPerson(zdRefundEntity.getShippingPerson());
        zdRefundEntity.setShippingPhone(zdRefundEntity.getShippingPhone());

        return zdRefundDao.updateById(zdRefundEntity);
    }

    @Override
    public RefundLimitInfoVO getRefundLimitInfo(String orgCode,List<SysSemesterEntity> sysSemesterEntityList) {
//        SysSemesterEntity sysSemesterEntity=sysSemesterService.getSemesterByCode(semesterCode);
        SysConfigEntity sysConfigEntity=sysConfigDao.queryByKey(Constant.ALLOW_REFUND_KEY);
        RefundLimitInfoVO refundLimitInfoVO=zdPublishDao.getRefundLimitInfo(orgCode,sysSemesterEntityList);
        BigDecimal defaultAllow=null;
        if(sysConfigEntity==null) {
            defaultAllow=Constant.DEFAULT_REFUND;
        }else{
            String d=sysConfigEntity.getValue();
            defaultAllow=new BigDecimal(d);
        }
        BigDecimal d=new BigDecimal("100.00");

        if(d.compareTo(defaultAllow)<0)
        {
            defaultAllow=Constant.DEFAULT_REFUND;
        }
        d=defaultAllow.divide(d,BigDecimal.ROUND_HALF_EVEN);
        refundLimitInfoVO.setAllowPublishMayang(refundLimitInfoVO.getTotalPublishMayang().multiply(d).subtract(refundLimitInfoVO.getAuditRefundMayang()));
        if(refundLimitInfoVO.getTotalPublishMayang().compareTo(BigDecimal.ZERO)<0){
            throw new RRException("已超出最大退货金额！");
        }
        return refundLimitInfoVO;
    }

    @Override
    public RefundLimitInfoVO getRefundLimitInfo2(String orgCode, String semesterCode) {
        SysConfigEntity sysConfigEntity=sysConfigDao.queryByKey(Constant.ALLOW_REFUND_KEY);
        RefundLimitInfoVO refundLimitInfoVO=zdPublishDao.getRefundLimitInfo2(orgCode,semesterCode);
        BigDecimal defaultAllow=null;
        if(sysConfigEntity==null) {
            defaultAllow=Constant.DEFAULT_REFUND;
        }else{
            String d=sysConfigEntity.getValue();
            defaultAllow=new BigDecimal(d);
        }
        BigDecimal d=new BigDecimal("100.00");

        if(d.compareTo(defaultAllow)<0)
        {
            defaultAllow=Constant.DEFAULT_REFUND;
        }
        d=defaultAllow.divide(d,BigDecimal.ROUND_HALF_EVEN);
        refundLimitInfoVO.setAllowRefundTotal(refundLimitInfoVO.getTotalPublishMayang().multiply(d));
        refundLimitInfoVO.setAllowPublishMayang(refundLimitInfoVO.getTotalPublishMayang().multiply(d).subtract(refundLimitInfoVO.getAuditRefundMayang()).subtract(refundLimitInfoVO.getRefundTotal()));
        if(refundLimitInfoVO.getAllowPublishMayang().compareTo(BigDecimal.ZERO)<0){
            throw new RRException("已超出最大退货金额！");
        }
        return refundLimitInfoVO;
    }


    @Override
    public List<ZdRefundResourceDetailEntity> queryAllRefundResourcesList(Map<String,Object> params) {
        return zdRefundDao.queryRefundResourcesList(params);
    }

    //////////////////////////////////////////////////////////////////
    ////此后为V2增加
    @Override
    @Transactional
    public void auditPass(List<String> ids) {
        List<ZdRefundEntity> zdRefundEntities=selectBatchIds(ids);
    }

    @Override
    public void confirmRefund(CountyRefundSubmitFrom countyRefundSubmitFrom, SysUserEntity sysUserEntity) {

        List<ZdRefundEntity> zdRefundEntities=selectBatchIds(countyRefundSubmitFrom.getIds());
        for(ZdRefundEntity zdRefundEntity:zdRefundEntities)
        {
            if(!Constant.REFUND_ORG_STATUS.NEW.equalsIgnoreCase(zdRefundEntity.getStatus())&&!Constant.REFUND_ORG_STATUS.AUDIT_FAIL.equalsIgnoreCase(zdRefundEntity.getStatus()))
            {
                throw new RRException("状态无法上报");
            }
            zdRefundEntity.setStatus(Constant.REFUND_ORG_STATUS.CONFIRM);
            zdRefundEntity.setConfirmBy(sysUserEntity.getUserId()+"");
            zdRefundEntity.setConfirmTime(new Date());
            zdRefundEntity.setLogisticAddress(countyRefundSubmitFrom.getLogisticAddress());
            zdRefundEntity.setLogisticBag(countyRefundSubmitFrom.getLogisticBag());
            zdRefundEntity.setLogisticCompany(countyRefundSubmitFrom.getLogisticCompany());
            zdRefundEntity.setLogisticNo(countyRefundSubmitFrom.getLogisticNo());
            zdRefundEntity.setLogisticPerson(countyRefundSubmitFrom.getLogisticPerson());
            zdRefundEntity.setLogisticTelphone(countyRefundSubmitFrom.getLogisticTelphone());
            baseMapper.updateById(zdRefundEntity);

        }
    }

    @Override
    public void confirmAddress(ZdRefundEntity zdRefundEntity) {
        ZdRefundEntity updateEntity=baseMapper.selectById(zdRefundEntity.getId());
        if(updateEntity==null){
            throw new RRException("无法获取退货单详情");
        }
        if(!Constant.REFUND_ORG_STATUS.AUDIT_PASS.equalsIgnoreCase(updateEntity.getStatus()))
        {
            throw new RRException("状态不允许更改");
        }
        updateEntity.setLogisticType(zdRefundEntity.getLogisticType());
        updateEntity.setLogisticNo(zdRefundEntity.getLogisticNo());
        updateEntity.setLogisticCompany(zdRefundEntity.getLogisticCompany());
        updateEntity.setLogisticBag(zdRefundEntity.getLogisticBag());
        updateEntity.setLogisticAddress(zdRefundEntity.getLogisticAddress());
        updateEntity.setStatus(Constant.REFUND_ORG_STATUS.SHIP);
        baseMapper.updateById(updateEntity);
    }

    @Override
    public int getRefundResourceLimit(String orgCode, List<SysSemesterEntity> semesterEntityList,String resourceId,String semesterCode,String toOrgCode) {
        return baseMapper.selectRefundResourceLimit(orgCode,semesterEntityList,resourceId,semesterCode,toOrgCode);
     }

    @Override
    public int getRefundResourceLimit2(String refundSemesterCode, String orgCode, String toOrgCode, String resourceId) {
        return baseMapper.selectRefundResourceLimit2(refundSemesterCode,orgCode,toOrgCode,resourceId);

    }

    @Override
    @Transactional
    public int deleteRefund(List ids) {
        List<ZdRefundEntity> zdRefundEntities=baseMapper.selectBatchIds(ids);
        for(ZdRefundEntity zdRefundEntity:zdRefundEntities)
        {
            if(Constant.REFUND_ORG_STATUS.FINISH.equals(zdRefundEntity.getStatus())||"1".equals(zdRefundEntity.getIsSync()))
            {
                throw new RRException("状态不允许删除");
            }
            zdRefundResourceDao.deleteByRefundId(zdRefundEntity.getId());
            return baseMapper.deleteById(zdRefundEntity.getId());
           /* if(Constant.REFUND_ORG_STATUS.NEW.equals(zdRefundEntity.getStatus())||Constant.REFUND_ORG_STATUS.AUDIT_FAIL.equals(zdRefundEntity.getStatus()))
            {
                if("0".equals(zdRefundEntity.getIsSync()))
                {
                    baseMapper.deleteById(zdRefundEntity.getId());
                    zdRefundResourceDao.deleteByRefundId(zdRefundEntity.getId());
                }else
                {
                    throw new RRException("状态不允许删除");
                }

            }else
            {
                throw new RRException("状态不允许删除");
            }*/
        }
        return 0;
    }

    @Override
    public PageUtils listRefundResource(Map<String, Object> params) {
        Page<ZdCountyRefundResourceVO> page = new Query<ZdCountyRefundResourceVO>(params).getPage();
        List<ZdCountyRefundResourceVO> resList = zdRefundDao.listRefundResource(page ,params);
        page.setRecords(resList);
        return new PageUtils(page);
    }


}
