package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdBalanceCompanyEntity;
import io.renren.modules.zd.entity.ZdBalanceCompanyPayEntity;
import io.renren.modules.zd.form.ZdBalanceCompanyForm;
import io.renren.modules.zd.vo.ReportBalanceCompanyDetailVO;
import io.renren.modules.zd.vo.ReportBalanceCompanyVO;
import io.renren.modules.zd.vo.ZdBalanceCompanyIncomeVO;
import io.renren.modules.zd.vo.ZdBalanceCompanyRefundVO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-10 09:18:28
 */
public interface ZdBalanceCompanyService extends IService<ZdBalanceCompanyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int saveBalanceOrder(ZdBalanceCompanyForm req);

    PageUtils queryList(Map<String, Object> params);

    void savePayBalance(ZdBalanceCompanyPayEntity zdBalanceCompanyPayEntity);

    void finishBalance(String id);

    List<ZdBalanceCompanyRefundVO> listRefundListByBalanceId(String balanceId);

    List<ZdBalanceCompanyIncomeVO> listIncomeListByBalanceId(String balanceId);

    ReportBalanceCompanyVO generatorBalanceBill(String semesterCode,String orgCode);

    ReportBalanceCompanyDetailVO generatorBalanceBillDetail(String semesterCode,String orgCode,String companyId,String balanceId);

}

