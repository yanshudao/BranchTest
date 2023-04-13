package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysSemesterEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdRefundEntity;
import io.renren.modules.zd.entity.ZdRefundResourceDetailEntity;
import io.renren.modules.zd.entity.ZdRefundResourceEntity;
import io.renren.modules.zd.form.CountyRefundSubmitFrom;
import io.renren.modules.zd.form.ZdRefundCreateOrderFrom;
import io.renren.modules.zd.form.ZdRefundForm;
import io.renren.modules.zd.vo.RefundLimitInfoVO;
import io.renren.modules.zd.vo.ZdCountyRefundResourceVO;

import java.util.List;
import java.util.Map;

public interface ZdRefundCountryService extends IService<ZdRefundEntity> {

    PageUtils listResourcesRefundable(Map<String, Object> params);

    int insertRefundOrder(ZdRefundCreateOrderFrom req);
    int submitRefundOrder(ZdRefundCreateOrderFrom req);


    PageUtils queryRefundOrderList(Map<String,Object> params);

    PageUtils queryRefundResourcesList(Map<String,Object> params);

    List<ZdRefundResourceDetailEntity> queryAllRefundResourcesList(Map<String,Object> params);

    String getUserIdByOrgCode(String orgCode);

    List<ZdRefundResourceEntity> listRefundResourcesByRefundId(String refundId);
    List<ZdCountyRefundResourceVO> listResourceByRefundId(String refundId);

    int reportRefundOrder(String refundId);

    int saveRefundResource(ZdRefundForm zdRefundForm);

    int deleteRefundResource(String id);

    int updateAddress(ZdRefundForm zdRefundForm);

    RefundLimitInfoVO getRefundLimitInfo(String orgCode,List<SysSemesterEntity> sysSemesterEntityList);

    /**
     * 按报订季筛选(可退为报订季的5%)
     * @param orgCode
     * @param semesterCode
     * @return
     */
    RefundLimitInfoVO getRefundLimitInfo2(String orgCode,String semesterCode);

    void auditPass(List<String> ids);


    void confirmRefund(CountyRefundSubmitFrom countyRefundSubmitFrom, SysUserEntity sysUserEntity);

    void confirmAddress(ZdRefundEntity zdRefundEntity);


    int getRefundResourceLimit(String orgCode, List<SysSemesterEntity> semesterEntityList,String resourceId,String semesterCode,String toOrgCode);

    int getRefundResourceLimit2(String refundSemesterCode,String orgCode,String toOrgCode, String resourceId);

    int deleteRefund(List ids);

    PageUtils listRefundResource(Map<String, Object> params);



}
