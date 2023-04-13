package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdRefundEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 退货主单据
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
public interface ZdRefundService extends IService<ZdRefundEntity> {

    PageUtils queryPage(Map<String, Object> params);


}

