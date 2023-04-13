package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdSourceOrderResourceEntity;

import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2019-02-25 16:21:35
 */
public interface ZdSourceOrderResourceService extends IService<ZdSourceOrderResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

