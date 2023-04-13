package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdZMCROrderLog;

import java.util.Map;


public interface ZdZMCROrderLogService extends IService<ZdZMCROrderLog> {

    PageUtils queryPage(Map<String, Object> params);
}

