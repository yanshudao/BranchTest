package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysOrgRateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysOrgRateDao extends BaseMapper<SysOrgRateEntity> {
    List<SysOrgRateEntity> selectListPage(@Param("params") Map<String, Object> params);

    int replaceRate(SysOrgRateEntity sysOrgRateEntity);
}
