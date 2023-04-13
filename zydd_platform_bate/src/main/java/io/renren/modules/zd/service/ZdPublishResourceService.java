package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdPublishResourceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
public interface ZdPublishResourceService extends IService<ZdPublishResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    /**
     * 批量插入发行单据， 更新库存
     * @param list
     * @return  没有插入成功的单据教材代码信息
     */
   // boolean insertList(List<ZdPublishResourceEntity> list) ;
}

