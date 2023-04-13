package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdRefundSupplierEntity;
import io.renren.modules.zd.entity.ZdSemesterRegEntity;
import io.renren.modules.zd.vo.ZdSemesterRegVO;

import java.util.List;
import java.util.Map;

public interface ZdSemesterRegService  extends IService<ZdSemesterRegEntity> {
    PageUtils selectListPage(Map<String, Object> params);

    ZdSemesterRegVO selectRate(Map<String, Object> params);

    List<ZdSemesterRegEntity> importReg(List<ZdSemesterRegEntity> list, SysUserEntity user, String semesterCode);

    List<ZdSemesterRegEntity> selectAll(Map<String, Object> params);

    Integer remove(String custCode, String semesterCode);

    ZdSemesterRegVO selectRateSum(Map<String, Object> params);
    ZdSemesterRegVO selectCurrentRate(Map<String, Object> params);

}
