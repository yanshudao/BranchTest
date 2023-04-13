package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.thoughtworks.xstream.XStream;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.search.vo.StatisticsRefundResourceVO;
import io.renren.modules.subject.cxf.SyncNCService;
import io.renren.modules.subject.dao.SubjectResourceDao;
import io.renren.modules.subject.entity.SubjectResourceEntity;
import io.renren.modules.subject.entity.request.Backbill;
import io.renren.modules.subject.entity.request.BackbillDataInfo;
import io.renren.modules.subject.entity.request.BackbillList;
import io.renren.modules.subject.util.XStreamUtil;
import io.renren.modules.sys.dao.SysConfigDao;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.dao.SysOrgSettingDao;
import io.renren.modules.sys.entity.*;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.zd.dao.*;
import io.renren.modules.zd.entity.*;
import io.renren.modules.zd.form.*;
import io.renren.modules.zd.service.ZdRefundProvinceService;
import io.renren.modules.zd.service.ZdStockService;
import io.renren.modules.zd.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Service("ZdRefundProvinceService")
public class ZdRefundProvinceServiceImpl extends ServiceImpl<ZdRefundDao, ZdRefundEntity> implements ZdRefundProvinceService {

    @Autowired
    private SubjectResourceDao subjectResourceDao;
    @Autowired
    private SysOrgDao sysOrgDao;
//    @Autowired
//    private ZdStockService zdStockService;
    @Autowired
    private ZdOrderResourceDao zdOrderResourceDao;
    @Autowired
    private SysOrgSettingDao sysOrgSettingDao;
    @Autowired
    private ZdStockIncomeDao zdStockIncomeDao;
    @Autowired
    private ZdStockIncomeResourceDao zdStockIncomeResourceDao;
    @Autowired
    private ZdStockService zdStockService;
    @Autowired
    private SyncNCService syncNCService;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private ZdRefundSupplierResourceDao zdRefundSupplierResourceDao;
    @Autowired
    private ZdRefundSupplierDao zdRefundSupplierDao;
    @Autowired
    private ZdRefundDao zdRefundDao;
    @Autowired
    private ZdRefundResourceDao zdRefundResourceDao;
    @Autowired
    private SysConfigDao sysConfigDao;
    /**
     * 根据省级用户的id获取到它可以审核的县级单位
     * @param userid
     * @return
     */
    @Override
    public List<Map<String, String>> getRefundOrgInfotByUserId(String userid) {
        /*List<String> orglist = null;
        List<String> preorglist = null;
        List<Map<String,String>> orgname = new ArrayList<>();
        List<SysOrgEntity> orgInfoList =null;
        HashMap<String,String> orginfo =new HashMap<>();
        //首先根據用戶的id獲取到對應的单位id 并在单位详情表中获取到对应的子单位信息 并过滤掉市级信息只保留县级数据()
        preorglist = sysOrgDao.getRefundOrgInfotWithoutFilter(userid);
        System.out.println("獲取到對應的单位管辖idorglist:===="+preorglist);
        //然后获取退货单据中的所有单位类别
        orglist  = zdRefundSupplierService.getRefundOrgInfo();
        System.out.println("退货单据中的所有单位orglist:===="+orglist);
        orglist.retainAll(preorglist);//获取两个集合的交集
        System.out.println("orglist:===="+orglist);
        //最后根据单位code得到对应的名称
        orgInfoList = sysOrgDao.getAllOrgInfo();

         for(SysOrgEntity orgcode : orgInfoList ) {
//             System.out.println("orgcode:===="+orgcode);
           if (orgcode == null || orgcode.getOrgCode() == null){continue;}
             orginfo.put(orgcode.getOrgCode(),orgcode.getOrgName());
        }
        for(String orgcode : orglist ) {
           String name = orginfo.get(orgcode);
           Map<String,String> orgmap = new HashMap<>() ;
            orgmap.put("orgcode",orgcode);
            orgmap.put("name",name);
           orgname.add(orgmap);

        }
*/
        return null ;

    }


    /**
     * 修改对应单号的状态以及资源数量以及库存数量
     * @param refundlist
     * @return
     */

    @Override
    @Transactional
    public int updateCheckStatus(List<ZdRefundCheckEntity> refundlist) {
        ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(refundlist.get(0).getRefundId());
        if(zdRefundEntity==null)
        {
           throw new RRException("退货单未找到");
        }
        if(Constant.REFUND_ORG_STATUS.COMPLETE.equals(zdRefundEntity.getStatus())||Constant.REFUND_ORG_STATUS.FINISH.equals(zdRefundEntity.getStatus()))
        {
            throw new RRException("状态不允许此操作");
        }
        zdRefundDao.updateStatus(refundlist.get(0).getRefundId());//修改退货单状态
        //修改退货单中教材资源具体数量
        for(ZdRefundCheckEntity zdRefundCheckEntity : refundlist ) {
            ZdRefundResourceEntity zdRefundResourceEntity=zdRefundResourceDao.selectById(zdRefundCheckEntity.getId());
            if(zdRefundResourceEntity==null)
            {
                throw new RRException("退货详情未找到");
            }
            zdRefundResourceEntity.setRealNum(zdRefundCheckEntity.getRealNum());
            zdRefundResourceEntity.setNitemdiscountrate(DecimalUtils.DEFAULT_DISCOUNT);
            zdRefundResourceEntity.setMayang(DecimalUtils.mayang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum()));
            zdRefundResourceEntity.setShiyang(DecimalUtils.shiyang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum(),DecimalUtils.DEFAULT_DISCOUNT));

//            Map<String,Object> refundMap =new HashMap<String, Object>() ;
//            refundMap.put("resourceid",orgcode.getResourceId());
//            refundMap.put("realnum",orgcode.getRealNum());
//            int temp = zdRefundResourceDao.updateRefundRealNum(refundMap);
            zdRefundResourceDao.updateById(zdRefundResourceEntity);
            ZdStockEntity zdStockEntity=new ZdStockEntity();
            zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.REFUND_ORG);
            zdStockEntity.setTotalAmount(zdRefundCheckEntity.getRealNum());
            zdStockEntity.setResourceId(zdRefundResourceEntity.getResourceId());
           zdStockService.saveStock(zdStockEntity,ShiroUtils.getUserEntity().getOrgCode());

        }
        return 1;
    }

    /**
     * 獲取退貨訂單中的資源詳情
     * @param params
     * @return
     */
    @Override
    public PageUtils getRefundResourceinfo(Map<String, Object> params) {
        Page<RefundResourceVo> page=new Query<RefundResourceVo>(params).getPage();
        page.setRecords(zdRefundResourceDao.queryRefundResourceInfoPage(page,params));
        return new PageUtils(page);

    }

    /**
     * 獲取退貨訂單中的資源以及报订詳情界面
     * @param mparams
     * @return
     */
    @Override
    public PageUtils getRefundResourceOrderinfo(Map<String, Object> mparams) {
        System.out.println("params:"+mparams);
        Page<RefundResourceInfoVo> page=new Query<RefundResourceInfoVo>(mparams).getPage();
        List<RefundResourceInfoVo> mInfoList = zdRefundResourceDao.getRefundResourceinfoForList(page,mparams);
        List<RefundResourceInfoVo> resInfoList =new ArrayList<>();
        System.out.println("退貨資源詳情：=============="+mInfoList);
        for(RefundResourceInfoVo refundInfo : mInfoList ) {
            Map<String,Object> refundMap =new HashMap<String, Object>() ;
                refundMap.put("resourceid",refundInfo.getResourceId());
                refundMap.put("semester_code",refundInfo.getSemesterCode());
                System.out.println("refundMap:==============="+refundMap);
                RefundOrderVO num = zdOrderResourceDao.getRefundOrderInfo(refundMap);
                System.out.println("RefundOrderVO:=========" +
                        "======"+num);
                refundInfo.setOrderNum(num.getOrdernum());
                refundInfo.setTotalNum(num.getTotatlnum());
                resInfoList.add(refundInfo);
            }
        page.setRecords(resInfoList);
        return new PageUtils(page);
    }


    /**
     * 省级保存退货单（向供应商）
     * @param zdRefundForm
     */
    @Override
    @Transactional
    public void saveRefund(ZdRefundForm zdRefundForm) {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(sysUserEntity.getOrgCode());
        //SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(zdRefundForm.getOrgCode());
//        RefundLimitInfoVO refundLimitInfoVo=getRefundLimitInfo(sysUserEntity.getOrgCode());
       /* if(refundLimitInfoVo.getAuditRefundMayang().compareTo(refundLimitInfoVo.getAllowPublishMayang())>-1){
            throw new RRException("已超出可退金额!");
        }*/
       /*if(refundLimitInfoVo.getAllowRefundTotal().compareTo(new BigDecimal("0")) < 0){
           throw new RRException("超出可退码洋");
       }*/
       /* BigDecimal totalRefund=new BigDecimal("0.00");
//        totalRefund=totalRefund.add(refundLimitInfoVo.getAuditRefundMayang());
        if(Constant.ZYDD_SUPPILER_ID.equals(zdRefundForm.getSupplierId())){
            for(ZdRefundResourceForm zdRefundResourceForm:zdRefundForm.getResourceForm()) {
                totalRefund=totalRefund.add(zdRefundResourceForm.getPrice().multiply(new BigDecimal(zdRefundResourceForm.getRefundNum())));
                if(totalRefund.compareTo(refundLimitInfoVo.getAllowPublishMayang())>0)
                {
                    throw new RRException("超出可退码洋");
                }
            }
        }*/
        BigDecimal totalRefund=new BigDecimal("0.00");
        ZdRefundSupplierEntity zdRefundEntity=new ZdRefundSupplierEntity();
        zdRefundEntity.setRefundSemesterCode(zdRefundForm.getRefundSemesterCode());
        zdRefundEntity.setFromOrgCode(sysUserEntity.getOrgCode());
        zdRefundEntity.setOrgCode(sysUserEntity.getOrgCode());
        zdRefundEntity.setLogisticPerson(zdRefundForm.getLogisticPerson());
        zdRefundEntity.setLogisticTelphone(zdRefundForm.getLogisticTelphone());
        if(sysOrgSettingEntity!=null&&StringUtils.isNotBlank(sysOrgSettingEntity.getToOrgCode()))
        {
            zdRefundEntity.setToOrgCode(sysOrgSettingEntity.getToOrgCode());
        }else
        {
            zdRefundEntity.setToOrgCode("ZYDD");
        }
        String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
        if(Constant.ZYDD_SUPPILER_ID.equals(zdRefundForm.getSupplierId()))
        {
            zdRefundEntity.setRefundCode("TH"+zdRefundEntity.getFromOrgCode()+timeInSecond);
            zdRefundEntity.setStatus(Constant.REFUND_STATUS.NEW);
            zdRefundEntity.setSemesterCode(zdRefundForm.getSemesterCode());
            zdRefundEntity.setLogisticBag(zdRefundForm.getLogisticBag());
            zdRefundEntity.setLogisticCompany(zdRefundForm.getLogisticCompany());
            zdRefundEntity.setLogisticNo(zdRefundForm.getLogisticNo());
            zdRefundEntity.setLogisticType(zdRefundForm.getLogisticType());
            zdRefundEntity.setLogisticAddress(zdRefundForm.getLogisticAddress());
            zdRefundEntity.setSupplierId(zdRefundForm.getSupplierId());
            zdRefundEntity.setRefundName(sysOrgEntity.getOrgName()+timeInSecond+"退货单");
            zdRefundSupplierDao.insert(zdRefundEntity);
            //List<ZdPublishResourceEntity> list=new ArrayList<>();
            for(ZdRefundResourceForm zdRefundResourceForm:zdRefundForm.getResourceForm())
            {
                //String courseId=zdCourseDetailForm.getCourseId();

                SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(zdRefundResourceForm.getResourceId());
                if(subjectResourceEntity==null)
                {
                    throw new RRException("教材未找到，请仔细查询教材信息!");
                }
                //	SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(courseResourceOrgVO.getId());
                ZdRefundSupplierResourceEntity zdRefundResourceEntity=new ZdRefundSupplierResourceEntity();
                zdRefundResourceEntity.setRefundSupplierId(zdRefundEntity.getId());
                zdRefundResourceEntity.setResourceId(subjectResourceEntity.getId());
                zdRefundResourceEntity.setRefundNum(zdRefundResourceForm.getRefundNum());
                zdRefundResourceEntity.setRefundPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdRefundResourceEntity.setRealNum(zdRefundResourceForm.getRefundNum());
                zdRefundResourceEntity.setNitemdiscountrate(DecimalUtils.DEFAULT_DISCOUNT);
                zdRefundResourceEntity.setMayang(DecimalUtils.mayang(subjectResourceEntity.getPrice(),zdRefundResourceForm.getRefundNum()));
               /* totalRefund=totalRefund.add(zdRefundResourceEntity.getMayang());
                if(totalRefund.compareTo(refundLimitInfoVo.getAllowPublishMayang())>0)
                {
                    throw new RRException("超出可退码洋");
                }*/
                zdRefundResourceEntity.setShiyang(DecimalUtils.shiyang(subjectResourceEntity.getPrice(),zdRefundResourceForm.getRefundNum(),DecimalUtils.DEFAULT_DISCOUNT));
                zdRefundSupplierResourceDao.insert(zdRefundResourceEntity);
                // ZdStockEntity zdStockEntity=new ZdStockEntity();
                // zdStockEntity.setResourceId(zdRefundResourceEntity.getResourceId());
                // zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                // zdStockEntity.setTotalAmount(-zdRefundResourceForm.getRefundNum());
                //  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.REFUND_SUPPIER);
                //zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                //  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
                //zdStockService.saveStock(zdStockEntity,sysUserEntity.getOrgCode());
                //list.add(zdPublishResourceEntity);
            }
        }else {
            zdRefundEntity.setRefundCode("TH" + zdRefundEntity.getFromOrgCode() + timeInSecond);
            zdRefundEntity.setStatus(Constant.REFUND_STATUS.COMPLETE);
            zdRefundEntity.setSemesterCode(zdRefundForm.getSemesterCode());
            zdRefundEntity.setLogisticBag(zdRefundForm.getLogisticBag());
            zdRefundEntity.setLogisticCompany(zdRefundForm.getLogisticCompany());
            zdRefundEntity.setLogisticNo(zdRefundForm.getLogisticNo());
            zdRefundEntity.setLogisticType(zdRefundForm.getLogisticType());
            zdRefundEntity.setLogisticAddress(zdRefundForm.getLogisticAddress());
            zdRefundEntity.setSupplierId(zdRefundForm.getSupplierId());
            zdRefundEntity.setRefundName(sysOrgEntity.getOrgName() + timeInSecond + "退货单");
            zdRefundSupplierDao.insert(zdRefundEntity);
            //List<ZdPublishResourceEntity> list=new ArrayList<>();
            for (ZdRefundResourceForm zdRefundResourceForm : zdRefundForm.getResourceForm()) {
                //String courseId=zdCourseDetailForm.getCourseId();
                SubjectResourceEntity subjectResourceEntity = subjectResourceDao.selectById(zdRefundResourceForm.getResourceId());
                if (subjectResourceEntity == null) {
                    throw new RRException("教材未找到，请仔细查询教材信息!");
                }
                //	SubjectResourceEntity subjectResourceEntity=subjectResourceDao.selectById(courseResourceOrgVO.getId());
                ZdRefundSupplierResourceEntity zdRefundResourceEntity = new ZdRefundSupplierResourceEntity();
                zdRefundResourceEntity.setRefundSupplierId(zdRefundEntity.getId());
                zdRefundResourceEntity.setResourceId(subjectResourceEntity.getId());
                zdRefundResourceEntity.setRefundNum(zdRefundResourceForm.getRefundNum());
                zdRefundResourceEntity.setRefundPrice(BigDecimal.valueOf(subjectResourceEntity.getPrice()));
                zdRefundResourceEntity.setRealNum(zdRefundResourceForm.getRefundNum());
                zdRefundResourceEntity.setNitemdiscountrate(DecimalUtils.DEFAULT_DISCOUNT);
                zdRefundResourceEntity.setStatus(Constant.REFUND_STATUS.COMPLETE);
                zdRefundResourceEntity.setMayang(DecimalUtils.mayang(subjectResourceEntity.getPrice(), zdRefundResourceForm.getRefundNum()));
                zdRefundResourceEntity.setShiyang(DecimalUtils.shiyang(subjectResourceEntity.getPrice(), zdRefundResourceForm.getRefundNum(), DecimalUtils.DEFAULT_DISCOUNT));
                zdRefundSupplierResourceDao.insert(zdRefundResourceEntity);
                ZdStockEntity zdStockEntity = new ZdStockEntity();
                zdStockEntity.setResourceId(zdRefundResourceEntity.getResourceId());
                zdStockEntity.setOrgCode(sysUserEntity.getOrgCode());
                zdStockEntity.setTotalAmount(-zdRefundResourceForm.getRefundNum());
                zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.REFUND_SUPPIER);
                //zdStockEntity.setCost(zdStockIncomeResourceEntity.getIncomePrice());
                //  zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.ADD);
                zdStockService.saveStock(zdStockEntity, sysUserEntity.getOrgCode());
                //list.add(zdPublishResourceEntity);
            }
        }

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String syncOrder(String ids)
    {
        StringBuffer stringBuffer=new StringBuffer();
        String xml=syncNCService.refundBook(ids);
        if(xml==null)
        {
            ZdRefundSupplierEntity zdRefundEntity=zdRefundSupplierDao.selectById(ids);
//            TShopOrderEntity tShopOrderEntity=get(TShopOrderEntity.class,id);
            zdRefundEntity.setResult("失败");
            zdRefundEntity.setReason("服务器异常");
            zdRefundEntity.setIsSync("2");
            zdRefundSupplierDao.updateById(zdRefundEntity);
            stringBuffer.append("订单号："+ids+",结果："+zdRefundEntity.getResult()+",原因："+zdRefundEntity.getReason()+"<br>");

        }else
        {
            XStream stream= XStreamUtil.getInstance();
            stream.alias("datainfos", BackbillDataInfo.class);

            BackbillDataInfo backbillDataInfo= (BackbillDataInfo)stream.fromXML(xml);
            BackbillList backbillList=backbillDataInfo.getDatainfo();
            for(Backbill backbill:backbillList.getBackbill())
            {

                ZdRefundSupplierEntity zdRefundEntity=zdRefundSupplierDao.selectById(ids);
                if(zdRefundEntity!=null)
                {
                    if(StringUtils.isNotBlank(backbill.getErpcode()))
                    {
                        zdRefundEntity.setIsSync("1");
                    }
                    zdRefundEntity.setErpcode(backbill.getErpcode());
                    zdRefundEntity.setResult(backbill.getResult());
                    zdRefundEntity.setReason(backbill.getReason());
                    zdRefundSupplierDao.updateById(zdRefundEntity);
                }

            }
        }

        return stringBuffer.toString();

    }

    @Override
    public List<ZdRefundResourceEntity> selectByRefundId(String id) {
        return zdRefundResourceDao.selectByRefundId(id);
    }

    @Override
    public ZdRefundEntity selectByCode(String id) {
        return zdRefundDao.selectByRefundCode(id);
    }

    @Override
    public PageUtils queryStatisticsByMap(Map<String, Object> params) {
        Page<StatisticsRefundResourceVO> page=new Query<StatisticsRefundResourceVO>(params).getPage();
        page.setRecords(zdRefundDao.selectStatisticsByMap(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryRefundOrderList(Map<String, Object> params) {
        Page<ZdRefundOrderListEntity> page = new Query<ZdRefundOrderListEntity>(params).getPage();
        List<ZdRefundOrderListEntity> resList = zdRefundSupplierDao.queryRefundOrderList(page ,params);
        page.setRecords(resList);
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public int auditRefund(ZdRefundForm zdRefundForm,String status) {
        ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(zdRefundForm.getId());
        if(Constant.REFUND_STATUS.COMPLETE.equals(zdRefundEntity.getStatus())||Constant.REFUND_STATUS.FINISH.equals(zdRefundEntity.getStatus()))
        {
            throw new RRException("不能重复审核退货单");
        }
        if(Constant.REFUND_STATUS.COMPLETE.equals(status))
        {
           List<ZdRefundResourceEntity> refundResourceEntities=zdRefundResourceDao.selectByRefundId(zdRefundForm.getId());
            //修改退货单中教材资源具体数量
          for(ZdRefundResourceEntity zdRefundResourceEntity:refundResourceEntities)
          {
              zdRefundResourceEntity.setRealNum(zdRefundResourceEntity.getRefundNum());

              if(zdRefundForm.getResourceForm()!=null)
              {
                  for(ZdRefundResourceForm zdRefundResourceForm : zdRefundForm.getResourceForm() ) {
                      if(zdRefundResourceEntity.getId().equals(zdRefundResourceForm.getId()))
                      {
                          if(zdRefundResourceForm.getRealNum()<=0)
                          {
                              throw new RRException("审核数量不能小于等于0");
                          }else
                          {
                              zdRefundResourceEntity.setRealNum(zdRefundResourceForm.getRealNum());
                              break;
                          }

                      }
                  }
              }
              zdRefundResourceEntity.setMayang(DecimalUtils.mayang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum()));
              zdRefundResourceEntity.setShiyang(DecimalUtils.shiyang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum(),zdRefundResourceEntity.getNitemdiscountrate()));
              zdRefundResourceDao.updateById(zdRefundResourceEntity);
              ZdStockEntity zdStockEntity=new ZdStockEntity();
              zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.REFUND_SUPPIER);
              zdStockEntity.setTotalAmount(-zdRefundResourceEntity.getRealNum());
              zdStockEntity.setResourceId(zdRefundResourceEntity.getResourceId());
              zdStockService.saveStock(zdStockEntity,zdRefundEntity.getFromOrgCode());
          }
            zdRefundEntity.setStatus(Constant.REFUND_STATUS.COMPLETE);
            zdRefundDao.updateById(zdRefundEntity);

        }
        if(Constant.REFUND_STATUS.AUDIT_FAIL.equals(status))
        {
            zdRefundEntity.setRemark(zdRefundForm.getRemark());
            zdRefundEntity.setStatus(Constant.REFUND_STATUS.AUDIT_FAIL);
            return zdRefundDao.updateById(zdRefundEntity);
        }
       return 0;
    }


    @Override
    public List<RefundOrgCountVO> queryRefundCount(Map<String, Object> params) {
        return zdRefundDao.selectRefundCount(params);
    }

    @Override
    public Integer selectCountByMap(Map<String, Object> params) {
        return zdRefundDao.selectCountByMap(params);
    }

    @Override
    public Integer selectZyddCountByMap(Map<String, Object> params) {
        return zdRefundDao.selectZyddCountByMap(params);
    }

    @Override
    public List<RefundResourceVo> listRefundDetail(Map<String, Object> params) {
        return zdRefundResourceDao.queryRefundResourceInfoPage(params);
    }

    @Override
    @Transactional
    public void modifyRefundResource(ZdRefundForm zdRefundForm) {
        ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(zdRefundForm.getId());
        if(Constant.REFUND_STATUS.FINISH.equals(zdRefundEntity.getStatus()))
        {
            throw new RRException("已结算的单据无法修改折扣");
        }

        for(ZdRefundResourceForm zdRefundResourceForm : zdRefundForm.getResourceForm() ) {
            ZdRefundResourceEntity zdRefundResourceEntity=zdRefundResourceDao.selectById(zdRefundResourceForm.getId());
            if(!zdRefundResourceEntity.getRefundId().equals(zdRefundEntity.getId()))
            {
                throw new RRException("操作异常！");
            }
            if(zdRefundResourceForm.getRealNum()==null)
            {
                zdRefundResourceForm.setRealNum(zdRefundResourceEntity.getRealNum());
            }
            if(zdRefundResourceForm.getNitemdiscountrate()==null)
            {
                zdRefundResourceEntity.setNitemdiscountrate(new BigDecimal(100.00));
            }else
            {
                zdRefundResourceEntity.setNitemdiscountrate(zdRefundResourceForm.getNitemdiscountrate());
            }
            zdRefundResourceEntity.setShiyang(DecimalUtils.shiyang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceForm.getRealNum(),zdRefundResourceEntity.getNitemdiscountrate()));
            //TODO计算实洋
//            zdRefundResourceEntity.getNitemdiscountrate()
//            BigDecimal bigDecimal = discount.setScale(2,BigDecimal.ROUND_HALF_DOWN);
       //     zdRefundResourceEntity.setShiyang(zdRefundResourceEntity.getRefundPrice().multiply(BigDecimal.valueOf(zdRefundResourceEntity.getRefundNum()).multiply()));
            zdRefundResourceDao.updateById(zdRefundResourceEntity);

        }

    }

    @Override
    @Transactional
    public void modifyIncomeResource(ZdIncomeForm zdIncomeForm) {
       ZdStockIncomeEntity zdStockIncomeEntity=zdStockIncomeDao.selectById(zdIncomeForm.getIncomeId());
       if(Constant.STOCK_STATUS.FINISH.equals(zdStockIncomeEntity.getStatus()))
       {
        throw new RRException("已结算的单据不允许修改折扣！");
       }

      List<ZdStockIncomeResourceEntity> list= zdStockIncomeResourceDao.selectByIncomeId(zdStockIncomeEntity.getId());
       for(ZdSourceIncomeResourceForm zdSourceIncomeResourceForm:zdIncomeForm.getList())
       {
           for(ZdStockIncomeResourceEntity zdStockIncomeResourceEntity:list)
           {
               if(zdStockIncomeResourceEntity.getId().equals(zdSourceIncomeResourceForm.getId()))
               {

                   zdStockIncomeResourceEntity.setNitemdiscountrate(zdSourceIncomeResourceForm.getNitemdiscountrate());
                   zdStockIncomeResourceEntity.setShiyang(DecimalUtils.shiyang(zdStockIncomeResourceEntity.getIncomePrice(),zdStockIncomeResourceEntity.getRealNum(),zdStockIncomeResourceEntity.getNitemdiscountrate()));
                   zdStockIncomeResourceDao.updateById(zdStockIncomeResourceEntity);
                   break;
               }
           }
       }
    }

    @Override
    public List<StatisticsRefundResourceVO> queryStatisticsAllByMap(Map<String, Object> params) {
        return zdRefundDao.selectStatisticsByMap(params);
    }

    @Override
    public List<RefundResourceVo> getRefundResourceinfoAll(Map<String, Object> params) {
        return zdRefundResourceDao.queryRefundResourceInfoPage(params);
    }

    @Override
    public RefundLimitInfoVO getRefundLimitInfo(String orgCode) {
        SysConfigEntity sysConfigEntity=sysConfigDao.queryByKey(Constant.ALLOW_REFUND_KEY);
        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();
        RefundLimitInfoVO refundLimitInfoVO=zdStockIncomeDao.getRefundLimitInfo(orgCode,semesterEntityList);
        BigDecimal defaultAllow=null;
        if(sysConfigEntity==null) {
            defaultAllow=Constant.DEFAULT_REFUND;
        }else{
            String d=sysConfigEntity.getValue();
            defaultAllow=new BigDecimal(d);
        }
        BigDecimal d=new BigDecimal("100.00");
        d=defaultAllow.divide(d,2, RoundingMode.HALF_UP);
        refundLimitInfoVO.setAllowPublishMayang(refundLimitInfoVO.getTotalPublishMayang().multiply(d).subtract(refundLimitInfoVO.getAuditRefundMayang()).subtract(refundLimitInfoVO.getRefundTotal()));
        if(refundLimitInfoVO.getAllowPublishMayang().compareTo(BigDecimal.ZERO)<0){
            throw new RRException("已超出最大退货金额！");
        }
        return refundLimitInfoVO;
    }

    ////////////////////////////////////////////////////
    ///此后为新增及改动 v2版本 ---author TomLee
    @Override
    @Transactional
    public void auditPass(List<String> ids,ZdRefundAuditForm updateForm) {
        ZdRefundEntity updateFormRefundForm=updateForm.getRefundForm();
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingDao.selectByOrg(sysUserEntity.getOrgCode());
        List<ZdRefundEntity> zdRefundEntities=selectBatchIds(ids);
        for(ZdRefundEntity zdRefundEntity:zdRefundEntities)
        {
            if(!Constant.REFUND_ORG_STATUS.CONFIRM.equalsIgnoreCase(zdRefundEntity.getStatus()))
            {
                throw new RRException("状态不允许审核通过！");
            }
            if(updateFormRefundForm!=null&&updateFormRefundForm.getShippingAddress()!=null)
            {
                zdRefundEntity.setShippingAddress(updateFormRefundForm.getShippingAddress());
                zdRefundEntity.setShippingPerson(updateFormRefundForm.getShippingPerson());
                zdRefundEntity.setShippingPhone(updateFormRefundForm.getShippingPhone());
            }else
            {
                zdRefundEntity.setShippingAddress(sysOrgSettingEntity.getAddress());
                zdRefundEntity.setShippingPhone(sysOrgSettingEntity.getTelPhone());
                zdRefundEntity.setShippingPerson(sysOrgSettingEntity.getPerson());
            }
            zdRefundEntity.setStatus(Constant.REFUND_ORG_STATUS.AUDIT_PASS);
            zdRefundEntity.setAuditBy(sysUserEntity.getUserId()+"");
            zdRefundEntity.setAuditTime(new Date());
            baseMapper.updateById(zdRefundEntity);
            /*List<ZdRefundResourceEntity> list=zdRefundResourceDao.selectByRefundId(zdRefundEntity.getId());
            //修改退货单中教材资源具体数量
            for(ZdRefundResourceEntity zdRefundResourceEntity:list)
            {
                zdRefundResourceEntity.setRefundNum(zdRefundResourceEntity.getRefundNum());
                zdRefundResourceEntity.setRealNum(zdRefundResourceEntity.getRefundNum());
                if(updateForm.getRefundForm()!=null)
                {
                    for(ZdRefundResourceForm zdRefundResourceForm : updateForm.getResourceForm() ) {
                        if(zdRefundResourceEntity.getId().equals(zdRefundResourceForm.getId()))
                        {
                            if(zdRefundResourceForm.getRealNum()<=0)
                            {
                                throw new RRException("审核数量不能小于等于0");
                            }else
                            {
                                zdRefundResourceEntity.setRealNum(zdRefundResourceForm.getRealNum());
                                break;
                            }

                        }
                    }
                }
                zdRefundResourceEntity.setMayang(DecimalUtils.mayang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum()));
                zdRefundResourceEntity.setShiyang(DecimalUtils.shiyang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum(),zdRefundResourceEntity.getNitemdiscountrate()));
                zdRefundResourceDao.updateById(zdRefundResourceEntity);
            }*/
        }
    }

    @Override
    @Transactional
    public void auditFail(List<String> ids) {
        SysUserEntity sysUserEntity=ShiroUtils.getUserEntity();
        List<ZdRefundEntity> zdRefundEntities=selectBatchIds(ids);
        for(ZdRefundEntity zdRefundEntity:zdRefundEntities)
        {
            if(!Constant.REFUND_ORG_STATUS.CONFIRM.equalsIgnoreCase(zdRefundEntity.getStatus()))
            {
                throw new RRException("状态不允许审核不通过！");
            }
            zdRefundEntity.setStatus(Constant.REFUND_ORG_STATUS.AUDIT_FAIL);
            zdRefundEntity.setAuditBy(sysUserEntity.getUserId()+"");
            zdRefundEntity.setAuditTime(new Date());
            baseMapper.updateById(zdRefundEntity);

        }
    }

    @Override
    @Transactional
    public void completeRefund(CompleteOrgRefundForm completeOrgRefundForm, SysUserEntity userEntity) {
        ZdRefundEntity zdRefundEntity = baseMapper.selectById(completeOrgRefundForm.getRefundId());
        if (zdRefundEntity == null) {
            throw new RRException("确认失败：无法获取订单！");
        }
        if (Constant.REFUND_ORG_STATUS.SHIP.equalsIgnoreCase(zdRefundEntity.getStatus())
                ||Constant.REFUND_ORG_STATUS.CONFIRM.equalsIgnoreCase(zdRefundEntity.getStatus())) {

            zdRefundEntity.setStatus(Constant.REFUND_ORG_STATUS.COMPLETE);
            zdRefundEntity.setCompleteTime(new Date());
            zdRefundEntity.setCompleteBy(userEntity.getUserId() + "");
            baseMapper.updateById(zdRefundEntity);
            List<ZdRefundResourceEntity> refundResourceEntities=zdRefundResourceDao.selectByRefundId(completeOrgRefundForm.getRefundId());
            //修改退货单中教材资源具体数量
            for(ZdRefundResourceEntity zdRefundResourceEntity:refundResourceEntities)
            {
                zdRefundResourceEntity.setRealNum(zdRefundResourceEntity.getRefundNum());

                if(completeOrgRefundForm.getResourceForms()!=null)
                {
                    for(CompleteOrgRefundResourceForm zdRefundResourceForm : completeOrgRefundForm.getResourceForms() ) {
                        if(zdRefundResourceEntity.getId().equals(zdRefundResourceForm.getId()))
                        {
                            if(zdRefundResourceForm.getRealNum()<=0)
                            {
                                throw new RRException("审核数量不能小于等于0");
                            }else
                            {
                                zdRefundResourceEntity.setRealNum(zdRefundResourceForm.getRealNum());
                                break;
                            }

                        }
                    }
                }
                zdRefundResourceEntity.setMayang(DecimalUtils.mayang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum()));
                zdRefundResourceEntity.setShiyang(DecimalUtils.shiyang(zdRefundResourceEntity.getRefundPrice(),zdRefundResourceEntity.getRealNum(),zdRefundResourceEntity.getNitemdiscountrate()));
                zdRefundResourceDao.updateById(zdRefundResourceEntity);
                ZdStockEntity zdStockEntity=new ZdStockEntity();
                zdStockEntity.setRecordType(Constant.STOCK_RECORD_TYPE.REFUND_SUPPIER);
                zdStockEntity.setTotalAmount(zdRefundResourceEntity.getRealNum());
                zdStockEntity.setResourceId(zdRefundResourceEntity.getResourceId());
                zdStockService.saveStock(zdStockEntity,zdRefundEntity.getFromOrgCode());
            }

        }else
        {
            throw new RRException("确认失败：状态不允许确认！");
        }


    }

    @Override
    @Transactional
    public void syncRefund(String refundId, SysUserEntity userEntity) {
        ZdRefundEntity zdRefundEntity=baseMapper.selectById(refundId);
        SysOrgEntity shengOrg=sysOrgDao.selectByOrgCode(zdRefundEntity.getToOrgCode());

        if(zdRefundEntity==null)
        {
            throw new RRException("退货单未找到");
        }
        if("0".equals(zdRefundEntity.getIsSync())) {

//            baseMapper.updateById(zdRefundEntity);
            String timeInSecond= DateUtils.format(new Date(),DateUtils.DATE_TIME_IN_SECOND);
            List<ZdRefundResourceGroup> zdRefundResourceGroups = zdRefundResourceDao.selectGroupByRefundId(refundId);
            for(ZdRefundResourceGroup zdRefundResourceGroup:zdRefundResourceGroups)
            {
                ZdRefundSupplierEntity zdRefundSupplierEntity=zdRefundSupplierDao.selectByRefundId(zdRefundEntity.getId());
                if(zdRefundSupplierEntity==null)
                {
                    zdRefundSupplierEntity=new ZdRefundSupplierEntity();
                }
                zdRefundSupplierEntity.setLogisticAddress(zdRefundEntity.getLogisticAddress());
                zdRefundSupplierEntity.setLogisticBag(zdRefundEntity.getLogisticBag());
                zdRefundSupplierEntity.setLogisticCompany(zdRefundEntity.getLogisticCompany());
                zdRefundSupplierEntity.setLogisticNo(zdRefundEntity.getLogisticNo());
                zdRefundSupplierEntity.setLogisticPerson(zdRefundEntity.getLogisticPerson());
                zdRefundSupplierEntity.setLogisticTelphone(zdRefundEntity.getLogisticTelphone());
                zdRefundSupplierEntity.setLogisticType(zdRefundEntity.getLogisticType());
                zdRefundSupplierEntity.setStatus(Constant.REFUND_ORG_STATUS.COMPLETE);

                zdRefundSupplierEntity.setRefundName(shengOrg.getOrgName() + timeInSecond + "退货单");
                zdRefundSupplierEntity.setSemesterCode(zdRefundEntity.getSemesterCode());
                zdRefundSupplierEntity.setForeignRefundId(zdRefundEntity.getId());
                zdRefundSupplierEntity.setAvgdiscount(new BigDecimal("100.00"));
                zdRefundSupplierEntity.setFromOrgCode(userEntity.getOrgCode());
                zdRefundSupplierEntity.setCountyOrgCode(zdRefundEntity.getFromOrgCode());
                zdRefundSupplierEntity.setRefundCode("TH" + zdRefundEntity.getFromOrgCode() + timeInSecond);
                zdRefundSupplierEntity.setOrgCode(userEntity.getOrgCode());
                zdRefundSupplierEntity.setSupplierId(zdRefundResourceGroup.getSupplierId());
                zdRefundSupplierEntity.setCreateBy(userEntity.getUserId()+"");
                zdRefundSupplierEntity.setIsSync("1");
                zdRefundSupplierEntity.setNcStatus("1");
                zdRefundSupplierEntity.setCreateTime(new Date());
                zdRefundSupplierEntity.setConfirmBy(userEntity.getUserId()+"");
                zdRefundSupplierEntity.setConfirmTime(new Date());
                zdRefundSupplierEntity.setCompleteTime(new Date());
                zdRefundSupplierEntity.setCompleteBy(userEntity.getUserId()+"");
                zdRefundSupplierEntity.setToOrgCode("ZYDD");
                zdRefundSupplierDao.insert(zdRefundSupplierEntity);
                for(ZdRefundResourceEntity zdRefundResourceEntity:zdRefundResourceGroup.getList())
                {
                    ZdRefundSupplierResourceEntity zdRefundSupplierResourceEntity=zdRefundSupplierResourceDao.selectByFRefundId(zdRefundResourceEntity.getId());
                    if(zdRefundSupplierResourceEntity==null)
                    {
                        zdRefundSupplierResourceEntity=new ZdRefundSupplierResourceEntity();
                    }
                    zdRefundSupplierResourceEntity.setRefundSupplierId(zdRefundSupplierEntity.getId());
                    zdRefundSupplierResourceEntity.setResourceId(zdRefundResourceEntity.getResourceId());
                    zdRefundSupplierResourceEntity.setRefundNum(zdRefundResourceEntity.getRefundNum());
                    zdRefundSupplierResourceEntity.setRealNum(zdRefundResourceEntity.getRealNum());
                    zdRefundSupplierResourceEntity.setRefundPrice(zdRefundResourceEntity.getRefundPrice());
                    zdRefundSupplierResourceEntity.setNitemdiscountrate(zdRefundResourceEntity.getNitemdiscountrate());
                    zdRefundSupplierResourceEntity.setMayang(zdRefundResourceEntity.getMayang());
                    zdRefundSupplierResourceEntity.setShiyang(zdRefundResourceEntity.getShiyang());
                    zdRefundSupplierResourceEntity.setStatus(Constant.NC_REFUND_RESOURCE_STATUS.NEW);
                    zdRefundSupplierResourceDao.insert(zdRefundSupplierResourceEntity);
                }
               String xml= syncNCService.refundBook(zdRefundSupplierEntity.getId());
                if(xml==null)
                {
//                    ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(ids);
//            TShopOrderEntity tShopOrderEntity=get(TShopOrderEntity.class,id);
                    zdRefundEntity.setResult("失败");
                    zdRefundEntity.setReason("服务器异常");
                    zdRefundEntity.setIsSync("2");
                    zdRefundDao.updateById(zdRefundEntity);
//                    stringBuffer.append("订单号："+ids+",结果："+zdRefundEntity.getResult()+",原因："+zdRefundEntity.getReason()+"<br>");

                }else
                {
                    XStream stream= XStreamUtil.getInstance();
                    stream.alias("datainfos", BackbillDataInfo.class);

                    BackbillDataInfo backbillDataInfo= (BackbillDataInfo)stream.fromXML(xml);
                    BackbillList backbillList=backbillDataInfo.getDatainfo();
                    for(Backbill backbill:backbillList.getBackbill())
                    {

//                        ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(ids);

                            if(StringUtils.isNotBlank(backbill.getErpcode()))
                            {
                                zdRefundEntity.setIsSync("1");
                                zdRefundSupplierEntity.setIsSync("1");
                                zdRefundSupplierEntity.setStatus(Constant.REFUND_STATUS.COMPLETE);
                            }
                            zdRefundEntity.setSupplierRefundId(zdRefundSupplierEntity.getId());
                            zdRefundEntity.setStatus(Constant.REFUND_ORG_STATUS.COMPLETE);
                            zdRefundEntity.setCompleteBy(userEntity.getUserId()+"");
                            zdRefundEntity.setCompleteTime(new Date());
                            zdRefundEntity.setErpcode(backbill.getErpcode());
                            zdRefundEntity.setResult(backbill.getResult());
                            zdRefundEntity.setReason(backbill.getReason());
                            zdRefundDao.updateById(zdRefundEntity);
                            zdRefundSupplierEntity.setErpcode(backbill.getErpcode());
                            zdRefundSupplierEntity.setResult(backbill.getResult());
                            zdRefundSupplierEntity.setReason(backbill.getReason());
                           zdRefundSupplierDao.updateById(zdRefundSupplierEntity);
                    }
                }

            }

        }else
        {
            throw new RRException("状态不允许同步");
        }
    }

    @Override
    @Transactional
    public void returnRefund(List<String> ids) {
        List<ZdRefundEntity> list=selectBatchIds(ids);
        for(ZdRefundEntity zdRefundEntity:list)
        {
            if(!Constant.REFUND_ORG_STATUS.COMPLETE.equals(zdRefundEntity.getStatus())&&!Constant.REFUND_ORG_STATUS.FINISH.equals(zdRefundEntity.getStatus())){
                zdRefundEntity.setStatus(Constant.REFUND_ORG_STATUS.CONFIRM);
                baseMapper.updateById(zdRefundEntity);
            }else
            {
                throw new RRException("状态不允许弃审");
            }
        }
    }

    @Override
    public void syncRefundSupplier(String refundId, SysUserEntity userEntity) {
        ZdRefundSupplierEntity zdRefundSupplierEntity=zdRefundSupplierDao.selectByRefundId(refundId);
        ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(zdRefundSupplierEntity.getForeignRefundId());
        String xml= syncNCService.refundBook(refundId);
        if(xml==null)
        {
            zdRefundSupplierEntity.setResult("失败");
            zdRefundSupplierEntity.setReason("服务器异常");
            zdRefundSupplierEntity.setIsSync("2");
            zdRefundSupplierDao.updateById(zdRefundSupplierEntity);
            zdRefundEntity.setResult("失败");
            zdRefundEntity.setReason("服务器异常");
            zdRefundEntity.setIsSync("2");
            zdRefundDao.updateById(zdRefundEntity);
        }else
        {
            XStream stream= XStreamUtil.getInstance();
            stream.alias("datainfos", BackbillDataInfo.class);
            BackbillDataInfo backbillDataInfo= (BackbillDataInfo)stream.fromXML(xml);
            BackbillList backbillList=backbillDataInfo.getDatainfo();
            for(Backbill backbill:backbillList.getBackbill())
            {

//                        ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(ids);

                if(StringUtils.isNotBlank(backbill.getErpcode()))
                {
                    zdRefundEntity.setIsSync("1");
                }
                zdRefundEntity.setSupplierRefundId(zdRefundSupplierEntity.getId());
                zdRefundEntity.setStatus(Constant.REFUND_ORG_STATUS.COMPLETE);
                zdRefundEntity.setCompleteBy(userEntity.getUserId()+"");
                zdRefundEntity.setCompleteTime(new Date());
                zdRefundEntity.setErpcode(backbill.getErpcode());
                zdRefundEntity.setResult(backbill.getResult());
                zdRefundEntity.setReason(backbill.getReason());
                zdRefundDao.updateById(zdRefundEntity);
                zdRefundSupplierEntity.setErpcode(backbill.getErpcode());
                zdRefundSupplierEntity.setResult(backbill.getResult());
                zdRefundSupplierEntity.setReason(backbill.getReason());
                zdRefundSupplierDao.updateById(zdRefundSupplierEntity);
            }
        }
    }

    @Override
    /**
     * 修改退货详情单
     */
    public int saveRefundResource(ZdRefundForm zdRefundForm) {
        int i=0;
        for(ZdRefundResourceForm zdRefundResourceForm:zdRefundForm.getResourceForm()){
            ZdRefundSupplierResourceEntity zdRefundSupplierResourceEntity=zdRefundSupplierResourceDao.selectById(zdRefundResourceForm.getId());
            zdRefundSupplierResourceEntity.setRefundNum(zdRefundResourceForm.getRefundNum());
            i+=zdRefundSupplierResourceDao.updateById(zdRefundSupplierResourceEntity);
        }
        return i;
    }

    @Override
    public Integer getRefundResourceLimit(String orgCode, List<SysSemesterEntity> semesterEntityList, String resourceId) {
        return zdRefundSupplierResourceDao.selectRefundResourceLimit(orgCode,semesterEntityList,resourceId);
    } @Override
    public Integer getRefundResourceLimit2(String orgCode, String semesterCode, String resourceId) {
        return zdRefundSupplierResourceDao.selectRefundResourceLimit2(orgCode,semesterCode,resourceId);
    }

    @Override
    public RefundLimitInfoVO getRefundLimitInfo2(String semesterCode, String orgCode, String supplierId) {
        SysConfigEntity sysConfigEntity=sysConfigDao.queryByKey(Constant.ALLOW_REFUND_KEY);
//        List<SysSemesterEntity> semesterEntityList=sysSemesterService.listRefund();
        RefundLimitInfoVO refundLimitInfoVO=zdStockIncomeDao.getRefundLimitInfo2(semesterCode,orgCode,supplierId);
        BigDecimal defaultAllow=null;
        if(sysConfigEntity==null) {
            defaultAllow=Constant.DEFAULT_REFUND;
        }else{
            String d=sysConfigEntity.getValue();
            defaultAllow=new BigDecimal(d);
        }
        BigDecimal d=new BigDecimal("100.00");
        d=defaultAllow.divide(d,2, RoundingMode.HALF_UP);
        refundLimitInfoVO.setAllowPublishMayang(refundLimitInfoVO.getTotalPublishMayang().multiply(d).subtract(refundLimitInfoVO.getAuditRefundMayang()).subtract(refundLimitInfoVO.getRefundTotal()));
        if(refundLimitInfoVO.getAllowPublishMayang().compareTo(BigDecimal.ZERO)<0){
            throw new RRException("已超出最大退货金额！");
        }
        return refundLimitInfoVO;
    }


}
