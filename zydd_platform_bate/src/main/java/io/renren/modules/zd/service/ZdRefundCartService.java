package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.zd.entity.ZdRefundCartEntity;
import io.renren.modules.zd.form.RefundCartFrom;
import io.renren.modules.zd.form.SubmitZdRefundCartFrom;
import io.renren.modules.zd.form.ZdRefundCartFrom;
import io.renren.modules.zd.vo.ZdRefundCartVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-20 15:56:23
 */
public interface ZdRefundCartService extends IService<ZdRefundCartEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveCartForm(ZdRefundCartFrom cartFrom);

    PageUtils selectListPage(Map<String, Object> params);
    List<ZdRefundCartVO> selectList(Map<String, Object> params);

    void submitOrder(SubmitZdRefundCartFrom orderForm);
    void submitOrder2(SubmitZdRefundCartFrom orderForm);

    BigDecimal selectCartTotal(Map map,String semesterCode);

    void saveProvinceCartForm(ZdRefundCartFrom cartFrom);

    void provinceSubmitOrder(SubmitZdRefundCartFrom orderForm);
    void provinceSubmitOrder2(SubmitZdRefundCartFrom orderForm);

    void saveRefundForm(RefundCartFrom refundCartFrom);

    List<SysCompanyEntity> selectSuppliers(Long userId);


}

