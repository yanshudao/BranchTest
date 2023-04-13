package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdStockEntity;

import java.util.Map;

/**
 * 库存
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
public interface ZdStockService extends IService<ZdStockEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 库存操作 orgCode 操作单位
     * @param zdStockEntity
     * @param orgCode
     */
    void saveStock(ZdStockEntity zdStockEntity,String orgCode);

    PageUtils queryResourcePage(Map<String, Object> params);
}

