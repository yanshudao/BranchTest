package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.search.vo.StatisticsRefundResourceVO;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdRefundCheckEntity;
import io.renren.modules.zd.entity.ZdRefundEntity;
import io.renren.modules.zd.entity.ZdRefundResourceEntity;
import io.renren.modules.zd.form.CompleteOrgRefundForm;
import io.renren.modules.zd.form.ZdIncomeForm;
import io.renren.modules.zd.form.ZdRefundAuditForm;
import io.renren.modules.zd.form.ZdRefundForm;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.RefundOrgCountVO;
import io.renren.modules.zd.vo.RefundResourceVo;

import java.util.List;
import java.util.Map;

/**
 * 退货主单据
 *
 * */


public interface ZdRefundProvinceService extends IService<ZdRefundEntity> {

    List<Map<String, String>> getRefundOrgInfotByUserId(String userid);


    int updateCheckStatus(List<ZdRefundCheckEntity> refundlist);

    PageUtils getRefundResourceinfo(Map<String, Object> params);

    PageUtils getRefundResourceOrderinfo(Map<String, Object> mparams);


    void saveRefund(ZdRefundForm zdRefundForm);

    String syncOrder(String id);

    List<ZdRefundResourceEntity> selectByRefundId(String id);

    ZdRefundEntity selectByCode(String id);

    PageUtils queryStatisticsByMap(Map<String, Object> params);

    PageUtils queryRefundOrderList(Map<String, Object> params);

    int auditRefund(ZdRefundForm zdRefundForm,String status);

    List<RefundOrgCountVO> queryRefundCount(Map<String, Object> params);

    Integer selectCountByMap(Map<String, Object> params);

    Integer selectZyddCountByMap(Map<String, Object> params);

    List<RefundResourceVo> listRefundDetail(Map<String, Object> params);

    void modifyRefundResource(ZdRefundForm zdRefundForm);


    void modifyIncomeResource(ZdIncomeForm zdIncomeForm);

    List<StatisticsRefundResourceVO> queryStatisticsAllByMap(Map<String, Object> params);

    List<RefundResourceVo> getRefundResourceinfoAll(Map<String, Object> params);

    RefundLimitInfoVO getRefundLimitInfo(String orgCode);

    void auditPass(List<String> ids,ZdRefundAuditForm zdRefundEntity);

    void auditFail(List<String> ids);

    void completeRefund(CompleteOrgRefundForm completeOrgRefundForm, SysUserEntity userEntity);

    void syncRefund(String refundId, SysUserEntity userEntity);

    void returnRefund(List<String> ids);

    void syncRefundSupplier(String refundId, SysUserEntity userEntity);

    int saveRefundResource(ZdRefundForm zdRefundForm);

    Integer getRefundResourceLimit(String orgCode, List<SysSemesterEntity> semesterEntityList, String resourceId);
    Integer getRefundResourceLimit2(String orgCode, String  semesterCode, String resourceId);

    RefundLimitInfoVO getRefundLimitInfo2(String semesterCode, String orgCode, String supplierId);
}

