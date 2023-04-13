package io.renren.modules.zd.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.dao.SysSemesterDao;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysSemesterService;
import io.renren.modules.sys.vo.SysOrgVO;
import io.renren.modules.zd.dao.*;
import io.renren.modules.zd.entity.*;
import io.renren.modules.zd.form.ZdBalanceForm;
import io.renren.modules.zd.service.ZdBalanceService;
import io.renren.modules.zd.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("zdBalanceService")
public class ZdBalanceServiceImpl extends ServiceImpl<ZdBalanceDao, ZdBalanceEntity> implements ZdBalanceService {

    @Autowired
    private SysOrgDao sysOrgDao;

    @Autowired
    private ZdBalanceDao adBalanceDao;

    @Autowired
    private SysSemesterDao sysSemesterDao;

    @Autowired
    private ZdBalancePublishDao zdBalancePublishDao;
    @Autowired
    private ZdBalanceRefundDao zdBalanceRefundDao;

    @Resource
    private SysSemesterService sysSemesterService;


    @Autowired
    private ZdBalancePayDao balancePayResourceDao;

    @Resource
    private ZdRefundDao zdRefundDao;

    @Resource
    private ZdPublishDao zdPublishDao;


    /*孔 begin*/
    @Override
    @Transactional
    @SysLog("结算付款")
    public int insertPayForAccount(ZdBalancePayEntity payDebt) {

        int i = balancePayResourceDao.insert(payDebt);
        return i;
/*
//		需要重新计算应付值以及欠款值，计算方式从balance表中获取到actual_total（总实洋）
// 		减去balance表中的sum值 等于应付值  应付值减去sum值等于欠款值
		BigDecimal actualTotal =  balancePayResourceDao.getActualToatal(payDebt.getBalanceId());

		if (actualTotal ==null ) return 0;
		//计算balance里所有的欠款debt值之和 用actualTotal减去debt之和
		BigDecimal sumAccount = balancePayResourceDao.getsumAccount(payDebt.getBalanceId());
		if(sumAccount == null){
		    sumAccount = new BigDecimal(0);
        }
		BigDecimal shouldpay = actualTotal.subtract(sumAccount);
		BigDecimal debt = shouldpay.subtract(payDebt.getSum());
		SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();
		//payDebt.setOperator(sysUserEntity.getUserId().toString());
		payDebt.setDebt(debt);
		payDebt.setShouldPay(shouldpay);
		int res = balancePayResourceDao.insert(payDebt);

		//balance更新状态
		int updateBalance = -1;
		boolean balanceFlag = false;
		//付款流水记录成功、且如果欠款<=0，则认为已经结清，更新结清状态
		if(res==1&&debt.compareTo(BigDecimal.ZERO)<=0) {
			balanceFlag = true;
			Map<String, Object> params = new HashMap<String, Object> ();
			params.put("balanceId", payDebt.getBalanceId());
			updateBalance = adBalanceDao.upBalanceStatus(params);
		}

		//付款流水记录成功
		if(res==1) {
			//如果需要更新结清状态
			if(balanceFlag) {
				//如果更新结清状态失败，则返回2
				if(updateBalance!=1) {
					res = 2;
				}
			}
		}
		return res;*/
    }

    @Override
    public List<ZdBalancePayEntity> selectPayDetailForAccount(Map<String, Object> params) {
        String balanceno = (String) params.get("balanceno");
        List<ZdBalancePayEntity> mlsit = balancePayResourceDao.selectPayDetailForAccount(balanceno);

        return mlsit;
    }
    /*孔 end*/

    @Override
    public PageUtils queryBalancePage(Map<String, Object> params) {
        Page page = new Query(params).getPage();
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        String orgType = sysOrgDao.selectByOrgCode(sysUserEntity.getOrgCode()).getOrgType();
        //如果为3，则为县级单位，否则为省级单位
        if ("3".equals(orgType)) {
            params.put("lowLevelOrg", sysUserEntity.getOrgCode());
        } else {
            params.put("highLevelOrg", sysUserEntity.getOrgCode());
        }
        page.setRecords(adBalanceDao.queryZdBalanceList(page, params));
        return new PageUtils(page);


/*
		String queryType = params.get("queryType").toString();//0 为列表查询,1为查汇总明细
		Page<ZdBalanceEntity> page= new Query<ZdBalanceEntity>(params).getPage();
		List<ZdBalanceEntity> list = null;
		String orgCode = params.get("orgCode").toString();

		//0 为列表查询
		if("0".equals(queryType)) {

			*//**
         * 遍历查询结果，补充返回的信息
         *//*
			for(ZdBalanceEntity balance: list) {
				//补充结算单位
				String low_level_org = balance.getLowLevelOrg();
				balance.setLowLevelOrgName(sysOrgDao.selectByOrgCode(low_level_org).getOrgName());
				//补充报订季
				String semesterCode = balance.getSemesterCode();
				//params.put("semester_code", semesterCode);
				//List <SysSemesterEntity> semester_list = sysSemesterDao.querySemester(params);
				//String semesterCodeName = semester_list.get(0).getName();
				balance.setSemesterCodeName(semesterCode);//报订季中文和报订季code保持一致，前端暂不用修改
				//获取折扣率
				int rate = balance.getDiscountRate();
				//根据发行码洋*折扣率/100，然后保留2位小数计算【发行实洋】
				BigDecimal publishShiyang = balance.getPublishPriceTotal().multiply(new BigDecimal(rate)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
				balance.setPublishShiyang(publishShiyang);
				//退货实洋 = 发行实洋 - 总实洋
				BigDecimal refuseShiyang = publishShiyang.subtract(balance.getActualTotal());
				balance.setRefundShiyang(refuseShiyang);
				//根据结算单据id实时查询付款详情
				String balanceid = balance.getBalanceNo();
				List<ZdBalancePayEntity> mlsit = balancePayResourceDao.selectPayDetailForAccount(balanceid);
				BigDecimal total_sum = new BigDecimal(0.00);
				//遍历获得所有付款记录，并统计已付款金额
				for(ZdBalancePayEntity pay : mlsit) {
					total_sum = total_sum.add(pay.getSum());
				}
				balance.setPaid(total_sum);
				balance.setNeedPaid(balance.getActualTotal().subtract(total_sum));
			}
			if(list==null) {
				list = new ArrayList<ZdBalanceEntity>();
			}

		}else {//查询【财务总额】
			params.put("highLevelOrg", orgCode);
			BigDecimal allBalanceMayang = new BigDecimal(0.00);//省级单位下所有结算单的【总码洋】之和
			BigDecimal allBalanceShiyang = new BigDecimal(0.00);//省级单位下所有结算单的【总实洋】之和
			BigDecimal allBalancePaid = new BigDecimal(0.00);//省级单位下所有结算单的【已付款】之和
			BigDecimal allBalanceNeedPaid = new BigDecimal(0.00);//省级单位下所有结算单的【欠款】之和
			List<ZdBalanceEntity> list_temp = null;
			Map <String, Object> map_temp = params;
			map_temp.put("lowLevelOrg", "");
			map_temp.put("semesterCode", params.get("semesterCode"));//统计【财务总额】需要根据报订季进行
			map_temp.put("startTime", "");
			map_temp.put("endTime", "");
			map_temp.put("balanceId", "");
			list_temp =  adBalanceDao.queryZdBalanceByHighLevelOrg(map_temp);

			for(ZdBalanceEntity zd_balance_temp : list_temp) {
				allBalanceMayang = allBalanceMayang.add(zd_balance_temp.getPriceTotal());
				allBalanceShiyang = allBalanceShiyang.add(zd_balance_temp.getActualTotal());

				String balanceid = zd_balance_temp.getBalanceNo();
				List<ZdBalancePayEntity> mlsit = balancePayResourceDao.selectPayDetailForAccount(balanceid);

				BigDecimal total_sum = new BigDecimal(0.00);
				//遍历获得所有付款记录，并统计已付款金额
				for(ZdBalancePayEntity pay : mlsit) {
					total_sum = total_sum.add(pay.getSum());
				}
				BigDecimal total_needpay_temp = new BigDecimal(0.00);
				total_needpay_temp = zd_balance_temp.getActualTotal().subtract(total_sum);

				allBalancePaid = allBalancePaid.add(total_sum);
				allBalanceNeedPaid = allBalanceNeedPaid.add(total_needpay_temp);
			}
			list = new ArrayList<ZdBalanceEntity>();
			ZdBalanceEntity zd = new ZdBalanceEntity();
			zd.setAllBalanceMayang(allBalanceMayang);
			zd.setAllBalanceShiyang(allBalanceShiyang);
			zd.setAllBalancePaid(allBalancePaid);
			zd.setAllBalanceNeedPaid(allBalanceNeedPaid);
			list.add(zd);
		}
		
		page.setRecords(list);
		return new PageUtils(page);*/
    }

    @Override
    @Transactional
    public int saveBalanceOrder(ZdBalanceForm req) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        String timeInSecond = DateUtils.format(new Date(), DateUtils.DATE_TIME_IN_SECOND);
        SysSemesterEntity sysSemesterEntity = sysSemesterService.getCurrentSemester();
        ZdBalanceEntity zdBalanceEntity = new ZdBalanceEntity();
        zdBalanceEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
        zdBalanceEntity.setDiscountRate(req.getDiscountRate());
        zdBalanceEntity.setBalanceNo("JS" + req.getLowLevelOrg() + timeInSecond);
        zdBalanceEntity.setLowLevelOrg(req.getLowLevelOrg());
        zdBalanceEntity.setHighLevelOrg(sysUserEntity.getOrgCode());
        zdBalanceEntity.setAuditStatus(0);
        zdBalanceEntity.setBalanceStatus(0);
        int result = adBalanceDao.insert(zdBalanceEntity);
        List<ZdRefundEntity> refundList = new ArrayList<>();
        for (String refundId : req.getRefundList()) {
            ZdRefundEntity zdRefundEntity = zdRefundDao.selectById(refundId);
            if (zdRefundEntity == null || Constant.REFUND_STATUS.FINISH.equals(zdRefundEntity.getStatus())) {
                throw new RRException("退货单据未查到或者退货单据状态不允许结算！");
            }
            ZdBalanceRefundEntity zdBalanceRefundEntity = new ZdBalanceRefundEntity();
            zdBalanceRefundEntity.setBalanceId(zdBalanceEntity.getId());
            zdBalanceRefundEntity.setRefundId(refundId);
            zdBalanceRefundDao.insert(zdBalanceRefundEntity);
            zdRefundEntity.setStatus(Constant.REFUND_STATUS.FINISH);
            zdRefundDao.updateById(zdRefundEntity);
            refundList.add(zdRefundEntity);
        }
        List<ZdPublishEntity> publishEntityList = new ArrayList<>();
        for (String publishId : req.getPublishList()) {
            ZdPublishEntity zdPublishEntity = zdPublishDao.selectById(publishId);
            if (zdPublishEntity == null || Constant.PUBLISH_STATUS.FINISH.equals(zdPublishEntity.getStatus())) {
                throw new RRException("发行单据未查到或者发行单据状态不允许结算！");
            }
            ZdBalancePublishEntity zdBalancePublishEntity = new ZdBalancePublishEntity();
            zdBalancePublishEntity.setBalanceId(zdBalanceEntity.getId());
            zdBalancePublishEntity.setPublishId(publishId);
            zdBalancePublishDao.insert(zdBalancePublishEntity);
            zdPublishEntity.setStatus(Constant.PUBLISH_STATUS.FINISH);
            zdPublishDao.updateById(zdPublishEntity);
            publishEntityList.add(zdPublishEntity);
        }
        ZdBalancePriceVO refundVO = null;
        ZdBalancePriceVO publishVO = null;
        //计算入库单总价
        if (refundList.size() > 0) {
            refundVO = zdRefundDao.getRefundPrice(refundList);

        }
        //计算发行单总价
        if (publishEntityList.size() > 0) {
            //计算退货单总价
            publishVO = zdPublishDao.getPublishPrice(publishEntityList);

        }
        if (publishVO != null) {
            zdBalanceEntity.setPublishPriceTotal(publishVO.getMayang());
            zdBalanceEntity.setPublishPriceShiyang(publishVO.getShiyang());
        } else {
            zdBalanceEntity.setPublishPriceTotal(new BigDecimal("0.00"));
            zdBalanceEntity.setPublishPriceShiyang(new BigDecimal("0.00"));
        }
        if (refundVO != null) {
            zdBalanceEntity.setRefundPriceTotal(refundVO.getMayang());
            zdBalanceEntity.setRefundPriceShiyang(refundVO.getShiyang());
        } else {
            zdBalanceEntity.setRefundPriceTotal(new BigDecimal("0.00"));
            zdBalanceEntity.setRefundPriceShiyang(new BigDecimal("0.00"));
        }
        zdBalanceEntity.setPriceTotal(zdBalanceEntity.getPublishPriceTotal().subtract(zdBalanceEntity.getRefundPriceTotal()));
        BigDecimal disRate=new BigDecimal(zdBalanceEntity.getDiscountRate());
//        BigDecimal rate=disRate.divide(new BigDecimal(100),2,BigDecimal.ROUND_DOWN);
        zdBalanceEntity.setActualTotal(DecimalUtils.shiyang(zdBalanceEntity.getPublishPriceShiyang().subtract(zdBalanceEntity.getRefundPriceShiyang()), disRate));
        adBalanceDao.updateById(zdBalanceEntity);
        return result;
    }


    /*@Override
    @Transactional(rollbackFor = Exception.class)
    public int inserBalancetList(List<ZdBalancePublishEntity> zdbrList) {
        try {
            for(int i = 0 ; i < zdbrList.size(); i++){
                ZdBalancePublish zdBalanceResource = zdbrList.get(i);
                int result = zdBalanceResourceDao.insertSelective(zdBalanceResource);
                if(result==1) {
                    if(zdBalanceResource.getType() == 0){
                        //更新发行单状态
                        zdPublishDao.updateByid(zdBalanceResource.getResourceNo());
                    }else{
                        //更新退货单状态
                        zdRefundDao.updateByRefundCode(zdBalanceResource.getResourceNo());
                    }
                }
            }
            return 1;
        } catch (Exception e) {
            // TODO: handle exception
            return 0;
        }

    }

*/
    @Override
    @Transactional
    public int auditBalance(Map<String, Object> params) {
        // TODO Auto-generated method stub
        int result = adBalanceDao.updaeAuditStatus(params);
        return result;
    }

    @Override
    public List<ZdBalancePublishVO> listPublishByBalanceId(String balanceId) {
        return adBalanceDao.listPublishByBalanceId(balanceId);
    }

    @Override
    public List<ZdBalanceRefundVO> listRefundByBalanceId(String balanceId) {
        return adBalanceDao.listRefundByBalanceId(balanceId);
    }

    @Override
    @Transactional
    @SysLog("结清此单据")
    public int finisBalance(String id) {
        ZdBalanceEntity zdBalanceEntity = adBalanceDao.selectById(id);
        zdBalanceEntity.setBalanceStatus(1);
        return adBalanceDao.updateById(zdBalanceEntity);


    }

    @Override
    public List<BalanceOrgVO> listBalanceOrg(Map map) {
        return adBalanceDao.listBalanceOrg(map);
    }

    @Override
    public ReportBalanceVO generatorBalanceBill(String semesterCode, String orgCode) {
        ReportBalanceVO reportBalanceVO = new ReportBalanceVO();
        reportBalanceVO.setCurrentDate(DateUtils.format(new Date()));
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        SysSemesterEntity sysSemesterEntity = sysSemesterService.getCurrentSemester();
//		List<ZdBalanceVO> zdBalanceVOS=baseMapper.queryBalancePublish(semesterCode,orgCode);
//		reportBalanceVO.setBalanceVOS(zdBalanceVOS);
        return reportBalanceVO;
    }

    @Override
    public ReportBalanceDetailVO generatorBalanceBillDetail(String semesterCode, String orgCode, String balanceId) {
        ReportBalanceDetailVO reportBalanceVO = new ReportBalanceDetailVO();
        if (StringUtils.isNotBlank(balanceId)) {
            ZdBalanceEntity zdBalanceEntity = baseMapper.selectById(balanceId);
            reportBalanceVO.setCurrentDate(DateUtils.format(zdBalanceEntity.getCreateTime()));
        } else {
            reportBalanceVO.setCurrentDate(DateUtils.format(new Date()));
        }

        SysOrgVO countyOrg = sysOrgDao.selectVOByOrgCode(orgCode);
        SysOrgVO provinceOrg = sysOrgDao.selectVOByOrgCode(countyOrg.getToOrgCode());
        reportBalanceVO.setCountyOrg(countyOrg);
        reportBalanceVO.setProvinceOrg(provinceOrg);
        List<ZdBalancePublishDetailVO> publishDetailVOS = zdBalancePublishDao.queryBalancePublishDetail(semesterCode, orgCode, balanceId);
        List<ZdBalanceRefundDetailVO> refundDetailVOS = zdBalanceRefundDao.queryBalanceRefundDetail(semesterCode, orgCode, balanceId);
        BigDecimal publishMayang = new BigDecimal("0.00");
        BigDecimal publishShiyang = new BigDecimal("0.00");
        BigDecimal refundMayang = new BigDecimal("0.00");
        BigDecimal refundshiyang = new BigDecimal("0.00");
        int publishSum = 0;
        int refundSum = 0;
        for (ZdBalancePublishDetailVO zdBalancePublishDetailVO : publishDetailVOS) {
            publishMayang = publishMayang.add(zdBalancePublishDetailVO.getMayng());
            publishShiyang = publishShiyang.add(zdBalancePublishDetailVO.getShiyang());
            publishSum += zdBalancePublishDetailVO.getPublishNum();
        }

        for (ZdBalanceRefundDetailVO zdBalanceRefundDetailVO : refundDetailVOS) {
            refundMayang = refundMayang.add(zdBalanceRefundDetailVO.getMayangTotal());
            refundshiyang = refundshiyang.add(zdBalanceRefundDetailVO.getShiyang());
            refundSum += zdBalanceRefundDetailVO.getRefundTotal();
        }

        reportBalanceVO.setPublishMayang(publishMayang);
        reportBalanceVO.setPublishShiyang(publishShiyang);
        reportBalanceVO.setPublishSum(publishSum);
        reportBalanceVO.setRefundMayang(refundMayang);
        reportBalanceVO.setRefundShiyang(refundshiyang);
        reportBalanceVO.setRefundSum(refundSum);
        BigDecimal shiyang = publishShiyang.subtract(refundshiyang);
        BigDecimal mayang = publishMayang.subtract(refundMayang);
        reportBalanceVO.setShiyang(shiyang);
        reportBalanceVO.setMayang(mayang);
        reportBalanceVO.setChineseTotal(ConvertMoney.toChinese(shiyang.doubleValue()));
        BigDecimal totalShiyang = publishShiyang.add(refundshiyang);
        reportBalanceVO.setAvgPrice(totalShiyang.divide(new BigDecimal(publishSum + refundSum), 2));
        reportBalanceVO.setPublishVOS(publishDetailVOS);
        reportBalanceVO.setRefundVOS(refundDetailVOS);
        return reportBalanceVO;
    }

    @Override
    @Transactional
    public void saveBalanceRemark(ZdBalanceEntity zdBalanceEntity) {
        ZdBalanceEntity updateEntity = baseMapper.selectById(zdBalanceEntity.getId());
        if (updateEntity == null) {
            throw new RRException("未查到此单据");
        }
        updateEntity.setRemark(zdBalanceEntity.getRemark());
        baseMapper.updateById(updateEntity);
    }

    @Override
    @Transactional
    public void refreshBalanceOrder(List<String> ids) {
        for (String id : ids) {
            ZdBalanceEntity zdBalanceEntity = baseMapper.selectById(id);
//			zdBalanceEntity.setSemesterCode(sysSemesterEntity.getSemesterCode());
//			zdBalanceEntity.setDiscountRate(req.getDiscountRate());
//			zdBalanceEntity.setBalanceNo("JS" +req.getLowLevelOrg()+ timeInSecond);
//			zdBalanceEntity.setLowLevelOrg(req.getLowLevelOrg());
//			zdBalanceEntity.setHighLevelOrg(sysUserEntity.getOrgCode());
//			zdBalanceEntity.setAuditStatus(0);
//			zdBalanceEntity.setBalanceStatus(0);
//			int result = adBalanceDao.insert(zdBalanceEntity);
            List<ZdRefundEntity> refundList = zdRefundDao.selectByBalanceId(id);
            for (ZdRefundEntity zdRefundEntity : refundList) {
                ZdRefundEntity update=new ZdRefundEntity();
                update.setId(zdRefundEntity.getId());
				/*ZdRefundEntity zdRefundEntity=zdRefundDao.selectById(ZdRefundEntity.getId());
				if(zdRefundEntity==null||Constant.REFUND_STATUS.FINISH.equals(zdRefundEntity.getStatus()))
				{
					throw new RRException("退货单据未查到或者退货单据状态不允许结算！");
				}*/
				/*ZdBalanceRefundEntity zdBalanceRefundEntity=new ZdBalanceRefundEntity();
				zdBalanceRefundEntity.setBalanceId(zdBalanceEntity.getId());
				zdBalanceRefundEntity.setRefundId(refundId);
				zdBalanceRefundDao.insert(zdBalanceRefundEntity);*/
                update.setStatus(Constant.REFUND_STATUS.FINISH);
                zdRefundDao.updateById(update);
//                refundList.add(zdRefundEntity);
            }
            List<ZdPublishEntity> publishEntityList = zdPublishDao.selectByBalanceId(id);
            for (ZdPublishEntity zdPublishEntity : publishEntityList) {
                ZdPublishEntity update=new ZdPublishEntity();
                update.setId(zdPublishEntity.getId());
//				ZdPublishEntity zdPublishEntity=zdPublishDao.selectById(publishId);
//				if(zdPublishEntity==null||Constant.PUBLISH_STATUS.FINISH.equals(zdPublishEntity.getStatus()))
//				{
//					throw new RRException("发行单据未查到或者发行单据状态不允许结算！");
//				}
//				ZdBalancePublishEntity zdBalancePublishEntity=new ZdBalancePublishEntity();
//				zdBalancePublishEntity.setBalanceId(zdBalanceEntity.getId());
//				zdBalancePublishEntity.setPublishId(publishId);
//				zdBalancePublishDao.insert(zdBalancePublishEntity);
                update.setStatus(Constant.PUBLISH_STATUS.FINISH);
                zdPublishDao.updateById(update);
//                publishEntityList.add(zdPublishEntity);
            }
            ZdBalancePriceVO refundVO = null;
            ZdBalancePriceVO publishVO = null;
            //计算入库单总价
            if (refundList.size() > 0) {
                refundVO = zdRefundDao.getRefundPrice(refundList);

            } else {
                refundVO = new ZdBalancePriceVO();
                refundVO.setMayang(new BigDecimal("0.00"));
                refundVO.setShiyang(new BigDecimal("0.00"));
            }
            //计算发行单总价
            if (publishEntityList.size() > 0) {
                //计算退货单总价
                publishVO = zdPublishDao.getPublishPrice(publishEntityList);

            }else {
                publishVO=new ZdBalancePriceVO();
                publishVO.setMayang(new BigDecimal("0.00"));
                publishVO.setShiyang(new BigDecimal("0.00"));
            }
            if (publishVO != null) {
                zdBalanceEntity.setPublishPriceTotal(publishVO.getMayang());
                zdBalanceEntity.setPublishPriceShiyang(publishVO.getShiyang());
            } else {
                zdBalanceEntity.setPublishPriceTotal(new BigDecimal("0.00"));
                zdBalanceEntity.setPublishPriceShiyang(new BigDecimal("0.00"));
            }
            if (refundVO != null) {
                zdBalanceEntity.setRefundPriceTotal(refundVO.getMayang());
                zdBalanceEntity.setRefundPriceShiyang(refundVO.getShiyang());
            } else {
                zdBalanceEntity.setRefundPriceTotal(new BigDecimal("0.00"));
                zdBalanceEntity.setRefundPriceShiyang(new BigDecimal("0.00"));
            }
            zdBalanceEntity.setPriceTotal(zdBalanceEntity.getPublishPriceTotal().subtract(zdBalanceEntity.getRefundPriceTotal()));
            zdBalanceEntity.setActualTotal(DecimalUtils.shiyang(zdBalanceEntity.getPublishPriceShiyang().subtract(zdBalanceEntity.getRefundPriceShiyang()), BigDecimal.valueOf(zdBalanceEntity.getDiscountRate())));
            zdBalanceEntity.setUpdateTime(new Date());
            adBalanceDao.updateById(zdBalanceEntity);
        }

    }

    @Override
    public List<BalanceExportVO> queryBalanceMergeList(Map<String, Object> params) {
        return adBalanceDao.selectBalanceMergeList(params);
    }

  /*  @Override
    public List<ZdBalancePublish> listResourceByBalanceId(String balanceNo) {
        return zdBalanceResourceDao.listResourceByBalanceNo(balanceNo);
    }
*/
}
