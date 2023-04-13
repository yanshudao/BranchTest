package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.zd.entity.ZdMajorOrg;

import java.util.List;
import java.util.Map;


public interface ZdMajorOrgService extends IService<ZdMajorOrg> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryZMCRPage(Map<String, Object> params);

    PageUtils queryOpenCountryPage(Map<String, Object> params);

    R saveBatch(List<ZdMajorOrg> zdMajorOrgs);

    int selectInsert(Map<String, Object> insertMap);

}

