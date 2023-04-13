package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.*;
import io.renren.modules.zd.form.ZdBalanceForm;
import io.renren.modules.zd.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 结算单据
 *
 * */


public interface ZdBalanceService extends IService<ZdBalanceEntity> {


    PageUtils queryBalancePage(Map<String, Object> params);

	int saveBalanceOrder(ZdBalanceForm req);

//	int inserBalancetList(List<ZdBalancePublish> zdbrList);

    int insertPayForAccount(ZdBalancePayEntity payDebt);

    List<ZdBalancePayEntity> selectPayDetailForAccount(Map<String, Object> params);

    int auditBalance(Map<String, Object> params);

    List<ZdBalancePublishVO> listPublishByBalanceId(String balanceId);

    List<ZdBalanceRefundVO> listRefundByBalanceId(String balanceId);

    int finisBalance(String id);

    List<BalanceOrgVO> listBalanceOrg(Map map);

    ReportBalanceVO generatorBalanceBill(String semesterCode,String orgCode);

    ReportBalanceDetailVO generatorBalanceBillDetail(String semesterCode,String orgCode,String balanceId);

    void saveBalanceRemark(ZdBalanceEntity zdBalanceEntity);

    void refreshBalanceOrder(List<String> ids);

    List<BalanceExportVO> queryBalanceMergeList(Map<String, Object> params);



//    List<ZdBalancePublish> listResourceByBalanceId(String balanceId);

}

