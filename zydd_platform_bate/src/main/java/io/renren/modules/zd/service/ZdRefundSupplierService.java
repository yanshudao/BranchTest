package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdRefundSupplierEntity;
import io.renren.modules.zd.entity.ZdRefundSupplierResourceEntity;
import io.renren.modules.zd.form.ZdRefundForm;
import io.renren.modules.zd.vo.RefundSupplierResourceVO;

import java.util.List;
import java.util.Map;

/**
 * 退货主单据
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-26 18:46:17
 */
public interface ZdRefundSupplierService extends IService<ZdRefundSupplierEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ZdRefundSupplierResourceEntity> selectByRefundId(String id);

    PageUtils queryRefundOrderList(Map<String, Object> params);

    PageUtils getRefundResourceinfo(Map<String, Object> params);
    List<RefundSupplierResourceVO> getRefundResourceinfoAll(Map<String, Object> params);

    int reportRefundOrder(String refundId);

    int deleteRefundResource(List<String> id);

    PageUtils queryStatisticsByMap(Map<String, Object> params);

    boolean deleteRefundSuppiler(List ids);

    int auditRefund(ZdRefundForm zdRefundForm, String status);
}

