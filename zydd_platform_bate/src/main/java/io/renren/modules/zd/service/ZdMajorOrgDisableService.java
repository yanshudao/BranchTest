package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.zd.entity.ZdMajorOrg;
import io.renren.modules.zd.entity.ZdMajorOrgDisable;

import java.util.List;
import java.util.Map;


public interface ZdMajorOrgDisableService extends IService<ZdMajorOrgDisable> {

    R insertIgnoreByBatch(List<ZdMajorOrg> zdMajorOrgs, String orgCode);

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryProvincePage(Map<String, Object> params);

    int selectInsert(Map<String, Object> insertMap);

}

