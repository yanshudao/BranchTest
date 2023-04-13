package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysOrgSettingEntity;

import java.util.Map;

/**
 * 报订设置
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:15:51
 */
public interface SysOrgSettingService extends IService<SysOrgSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SysOrgSettingEntity selectByOrg(String orgCode);

    int updateRateConfig(String status, String rateConfig);
}

