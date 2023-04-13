package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysOrgSettingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 报订设置
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:15:51
 */
@Mapper
public interface SysOrgSettingDao extends BaseMapper<SysOrgSettingEntity> {

    SysOrgSettingEntity selectByOrg(@Param("orgCode") String orgCode);

    int updateRateConfig(@Param("status") String status,@Param("rateConfig")  String rateConfig);

}
