package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdSourceResourceEntity;

import java.util.Map;

/**
 * 采购详情
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
public interface ZdSourceResourceService extends IService<ZdSourceResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);


    int selectLeftCount(String sourceId);
}

