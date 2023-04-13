package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysOrgAddressEntity;

import java.util.Map;

/**
 * 报订设置
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:15:51
 */
public interface SysOrgAddressService extends IService<SysOrgAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

