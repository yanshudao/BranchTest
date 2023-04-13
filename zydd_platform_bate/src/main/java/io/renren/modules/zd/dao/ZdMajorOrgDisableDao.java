package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdMajorOrg;
import io.renren.modules.zd.entity.ZdMajorOrgDisable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教材换版订单日志

 */
@Mapper
public interface ZdMajorOrgDisableDao extends BaseMapper<ZdMajorOrgDisable> {


    List selectPageList(Page page,@Param("params") Map<String, Object> params);
    List selectProvincePageList(Page page,@Param("params") Map<String, Object> params);

    int insertIgnoreByBatch(@Param("list") List<ZdMajorOrg> zdMajorOrgs,@Param("orgCode")  String orgCode,@Param("userId") Long userId);

    int selectInsert(@Param("insertMap") Map<String, Object> insertMap);

}
