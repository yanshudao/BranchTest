package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.form.ZdMajorCourseOrgForm;
import io.renren.modules.zd.entity.ZdMajorCourseOrg;

import java.util.List;
import java.util.Map;


public interface ZdMajorCourseOrgService extends IService<ZdMajorCourseOrg> {

    PageUtils queryPage(Map<String, Object> params);


    R remove(List<ZdMajorCourseOrg> zdMajorCourseOrgList);

    R saveBatch(List<ZdMajorCourseOrg> zdMajorCourseOrgList);
    R saveBatchZMO(List<ZdMajorCourseOrg> zdMajorCourseOrgList, SysOrgEntity sysOrgEntity);

    PageUtils queryOpenCountryPage(Map<String, Object> params);

    int selectInsert(Map<String, Object> insertMap);

    R removeZMO(ZdMajorCourseOrgForm zdMajorCourseOrgForm);

    PageUtils queryDisablePage(Map<String, Object> params);
}

