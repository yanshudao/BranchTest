package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysSemesterOrgEntity;

import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-04-26 23:28:22
 */
public interface SysSemesterOrgService extends IService<SysSemesterOrgEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

