package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysOrgAreaEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-22 15:57:47
 */
public interface SysOrgAreaService extends IService<SysOrgAreaEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysOrgAreaEntity> queryAll(Map<String, Object> params);

    SysOrgAreaEntity selectByCode(String areaCode);

}

