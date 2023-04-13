package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdRefundSupplierResourceEntity;

import java.util.Map;

/**
 * 退货详情
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-11-26 18:46:17
 */
public interface ZdRefundSupplierResourceService extends IService<ZdRefundSupplierResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

