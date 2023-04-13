package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.sys.entity.SysOrgAreaEntity;
import io.renren.modules.sys.entity.SysOrgRateEntity;
import io.renren.modules.zd.vo.ZdSemesterRegVO;

import java.util.List;
import java.util.Map;

public interface SysOrgRateService extends IService<SysOrgRateEntity> {
    List<SysOrgRateEntity> queryPage(Map<String, Object> params);

    int replaceRate(SysOrgRateEntity sysOrgRateEntity);

    ZdSemesterRegVO querySumPage(String orgCode, String semesterCode);



}
