package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdStockRecordEntity;

import java.util.Map;

/**
 * 库存变化明细
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
public interface ZdStockRecordService extends IService<ZdStockRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

