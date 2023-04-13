package io.renren.modules.zd.service.impl;

import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.sys.dao.SysCompanyDao;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.sys.vo.SysOrgVO;
import io.renren.modules.sys.vo.SysOrgVO1;
import io.renren.modules.zd.dao.*;
import io.renren.modules.zd.entity.*;
import io.renren.modules.zd.form.ZdBalanceCompanyForm;
import io.renren.modules.zd.vo.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.modules.zd.service.ZdBalanceCompanyService;
import org.springframework.transaction.annotation.Transactional;


@Service("zdBalanceCompanyService")
public class ZdBalanceCompanyServiceImpl extends ServiceImpl<ZdBalanceCompanyDao, ZdBalanceCompanyEntity> implements ZdBalanceCompanyService {

    @Autowired
    private ZdBalanceCompanyDao zdBalanceCompanyDao;
    @Autowired
    private ZdBalanceCompanyPayDao zdBalanceCompanyPayDao;

    @Autowired
    private ZdBalanceCompanyIncomeDao zdBalanceCompanyIncomeDao;
    @Autowired
    private ZdBalanceCompanyRefundDao zdBalanceCompanyRefundDao;
    @Autowired
    private ZdRefundSupplierDao zdRefundSupplierDao;
    @Autowired
    private SysSemesterService sysSemesterService;
    @Autowired
    private ZdStockIncomeDao zdStockIncomeDao;
    @Autowired
    private SysCompanyDao sysCompanyDao;
    @Autowired
    private SysOrgDao sysOrgDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdBalanceCompanyEntity> page = this.selectPage(
                new Query<ZdBalanceCompanyEntity>(params).getPage(),
                new EntityWrapper<ZdBalanceCompanyEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    @Transactional
    public int saveBalanceOrder(ZdBalanceCompanyForm req) {
        SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
        String timeInSecond= DateUtils.format(new Date(), DateUtils.DATE_TIME_IN_SECOND);
        SysSemesterEntity sysSemesterEntity=sysSemesterService.getCurrentSemester();
        ZdBalanceCompanyEntity zdBalanceEntity=new ZdBalanceCompanyEntity();
        zdBalanceEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdBalanceEntity.setDiscountRate(req.getDiscountRate());
        zdBalanceEntity.setBalanceCompanyNo("JS" +sysUserEntity.getOrgCode()+ timeInSecond);
        zdBalanceEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdBalanceEntity.setCompanyId(req.getSupplierId());
        zdBalanceEntity.setAuditStatus("0");
        zdBalanceEntity.setBalanceStatus("0");
        zdBalanceEntity.setToOrgCode(req.getToOrgCode());
        int result = zdBalanceCompanyDao.insert(zdBalanceEntity);
        List<ZdRefundSupplierEntity> refundList=new ArrayList<>();
        for(String refundId:req.getRefundList())
        {
            ZdRefundSupplierEntity zdRefundEntity=zdRefundSupplierDao.selectById(refundId);
            if(zdRefundEntity==null||Constant.PUBLISH_STATUS.FINISH.equals(zdRefundEntity.getStatus()))
            {
                throw new RRException("退货单据未查到或者退货单据状态不允许结算！");
            }
            ZdBalanceCompanyRefundEntity zdBalanceRefundEntity=new ZdBalanceCompanyRefundEntity();
            zdBalanceRefundEntity.setBalanceCompanyId(zdBalanceEntity.getId());
            zdBalanceRefundEntity.setRefundId(refundId);
            zdBalanceCompanyRefundDao.insert(zdBalanceRefundEntity);
            zdRefundEntity.setStatus(Constant.REFUND_STATUS.FINISH);
            zdRefundSupplierDao.updateById(zdRefundEntity);
            refundList.add(zdRefundEntity);
        }
        List<ZdStockIncomeEntity> incomeList=new ArrayList<>();
        for(String publishId:req.getIncomeList())
        {
            ZdStockIncomeEntity zdStockIncomeEntity=zdStockIncomeDao.selectById(publishId);
            if(zdStockIncomeEntity==null||Constant.STOCK_STATUS.FINISH.equals(zdStockIncomeEntity.getStatus()))
            {
                throw new RRException("发行单据未查到或者发行单据状态不允许结算！");
            }
            ZdBalanceCompanyIncomeEntity zdBalancePublishEntity=new ZdBalanceCompanyIncomeEntity();
            zdBalancePublishEntity.setBalanceCompanyId(zdBalanceEntity.getId());
            zdBalancePublishEntity.setIncomeId(publishId);
            zdBalanceCompanyIncomeDao.insert(zdBalancePublishEntity);
            zdStockIncomeEntity.setStatus(Constant.STOCK_STATUS.FINISH);
            zdStockIncomeDao.updateById(zdStockIncomeEntity);
            incomeList.add(zdStockIncomeEntity);
        }
        ZdBalancePriceVO incomeVO=null;
        ZdBalancePriceVO refundVO=null;
        //计算入库单总价
        if(refundList.size()>0)
        {
            refundVO=zdRefundSupplierDao.getRefundPrice(refundList);
        }
        if(incomeList.size()>0)
        {
            //计算退货单总价
            incomeVO=zdStockIncomeDao.getIncomePrice(incomeList);

        }
        if(incomeVO!=null)
        {
            zdBalanceEntity.setIncomePriceTotal(incomeVO.getMayang());
            zdBalanceEntity.setIncomePriceShiyang(incomeVO.getShiyang());
        }else
        {
            zdBalanceEntity.setIncomePriceTotal(new BigDecimal("0.00"));
            zdBalanceEntity.setIncomePriceShiyang(new BigDecimal("0.00"));
        }
        if(refundVO!=null)
        {
            zdBalanceEntity.setRefundPriceTotal(refundVO.getMayang());
            zdBalanceEntity.setRefundPriceShiyang(refundVO.getShiyang());
        }else
        {
            zdBalanceEntity.setRefundPriceTotal(new BigDecimal("0.00"));
            zdBalanceEntity.setRefundPriceShiyang(new BigDecimal("0.00"));
        }
        BigDecimal shiyang=zdBalanceEntity.getIncomePriceShiyang().subtract(zdBalanceEntity.getRefundPriceShiyang());
        zdBalanceEntity.setMayang(zdBalanceEntity.getIncomePriceTotal().subtract(zdBalanceEntity.getRefundPriceTotal()));
        zdBalanceEntity.setShiyang(DecimalUtils.shiyang(shiyang,BigDecimal.valueOf(zdBalanceEntity.getDiscountRate())));
        return  zdBalanceCompanyDao.updateById(zdBalanceEntity);
    }

    @Override
    public PageUtils queryList(Map<String, Object> params) {
        Page<ZdBalanceCompanyVO> page=new Query<ZdBalanceCompanyVO>(params).getPage();
        page.setRecords(zdBalanceCompanyDao.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void savePayBalance(ZdBalanceCompanyPayEntity zdBalanceCompanyPayEntity) {
        zdBalanceCompanyPayDao.insert(zdBalanceCompanyPayEntity);
    }

    @Override
    @Transactional
    public void finishBalance(String id) {
       ZdBalanceCompanyEntity zdBalanceCompanyEntity= zdBalanceCompanyDao.selectById(id);
       if(zdBalanceCompanyEntity==null)
       {
           throw new RRException("未找到此结算单！");
       }
        zdBalanceCompanyEntity.setBalanceStatus("1");
        zdBalanceCompanyDao.updateById(zdBalanceCompanyEntity);
    }

    @Override
    public List<ZdBalanceCompanyRefundVO> listRefundListByBalanceId(String balanceId) {
        return zdBalanceCompanyRefundDao.listRefundListByBalanceId(balanceId);
    }
    public List<Map<String,Object>> listRefundListByBalanceId(String balanceId,String name) {
        return zdBalanceCompanyRefundDao.listRefundListByBalanceId(balanceId,name);
    }
    @Override
    public List<ZdBalanceCompanyIncomeVO> listIncomeListByBalanceId(String balanceId) {
        return zdBalanceCompanyIncomeDao.listIncomeListByBalanceId(balanceId);
    }
    public List<Map<String,Object>> listIncomeListByBalanceId(String balanceId,String name) {
        return zdBalanceCompanyIncomeDao.listIncomeListByBalanceId(balanceId,name);
    }
    @Override
    public ReportBalanceCompanyVO generatorBalanceBill(String semesterCode,String orgCode)  {

            ReportBalanceCompanyVO reportBalanceVO=new ReportBalanceCompanyVO();
            reportBalanceVO.setCurrentDate(DateUtils.format(new Date()));

            List<ZdBalanceCompanyVO>  refundVOS=baseMapper.listBalanceVO(semesterCode,orgCode);
        return reportBalanceVO;
    }



    @Override
    public ReportBalanceCompanyDetailVO generatorBalanceBillDetail(String semesterCode,String orgCode,String companyId,String balanceId) {

        ReportBalanceCompanyDetailVO reportBalanceVO=new ReportBalanceCompanyDetailVO();
        reportBalanceVO.setCurrentDate(DateUtils.format(new Date()));

         reportBalanceVO.setCompanyEntity(sysCompanyDao.selectById(companyId));
        reportBalanceVO.setOrgEntity(sysOrgDao.selectVOByOrgCode(orgCode));
//        reportBalanceVO.setZdBalanceCompanyVO(zdBalanceCompanyEntity);
        List<ZdBalanceRefundDetailVO>  refundVOS=zdRefundSupplierDao.queryBalanceRefundDetail(semesterCode,orgCode,companyId,balanceId);
        List<ZdBalanceIncomeDetailVO>  incomeVOS=zdStockIncomeDao.queryBalanceIncomeDetail(semesterCode,orgCode,companyId,balanceId);
        BigDecimal incomeMayang=new BigDecimal("0.00");
        BigDecimal incomeShiyang=new BigDecimal("0.00");
        BigDecimal refundMayang=new BigDecimal("0.00");
        BigDecimal refundshiyang=new BigDecimal("0.00");
        int incomeSum=0;
        int refundSum=0;
        for(ZdBalanceRefundDetailVO zdBalanceRefundDetailVO:refundVOS) {
            if(zdBalanceRefundDetailVO.getMayangTotal()!=null){
                refundMayang=refundMayang.add(zdBalanceRefundDetailVO.getMayangTotal());
            }
            if(zdBalanceRefundDetailVO.getShiyang()!=null){
                refundshiyang=refundshiyang.add(zdBalanceRefundDetailVO.getShiyang());
            }
            if(zdBalanceRefundDetailVO.getRefundTotal()!=null){
                 refundSum+=zdBalanceRefundDetailVO.getRefundTotal();
            }

        }

        for(ZdBalanceIncomeDetailVO zdBalanceIncomeDetailVO:incomeVOS)
        {
            incomeMayang=incomeMayang.add(zdBalanceIncomeDetailVO.getTotalMayang());
            incomeShiyang=incomeShiyang.add(zdBalanceIncomeDetailVO.getTotalShiyang());
            incomeSum+=zdBalanceIncomeDetailVO.getTotalNum();
        }
        reportBalanceVO.setIncomeMayang(incomeMayang);
        reportBalanceVO.setIncomeShiyang(incomeShiyang);
        reportBalanceVO.setIncomeSum(incomeSum);
        reportBalanceVO.setRefundMayang(refundMayang);
        reportBalanceVO.setRefundShiyang(refundshiyang);
        reportBalanceVO.setRefundSum(refundSum);
        BigDecimal shiyang=incomeShiyang.subtract(refundshiyang);
        BigDecimal mayang=incomeMayang.subtract(refundMayang);
        BigDecimal totalShiyang=incomeShiyang.add(refundshiyang);
        reportBalanceVO.setShiyang(shiyang);
        reportBalanceVO.setMayang(mayang);
        reportBalanceVO.setAvgPrice(totalShiyang.divide(new BigDecimal(incomeSum+refundSum),2));
        reportBalanceVO.setChineseTotal(ConvertMoney.toChinese(shiyang.doubleValue()));
        reportBalanceVO.setIncomeVOS(incomeVOS);
        reportBalanceVO.setRefundVOS(refundVOS);
        return reportBalanceVO;
    }

}
