package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdMajorCourseOrg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教材换版订单日志

 */
@Mapper
public interface ZdMajorCourseOrgDao extends BaseMapper<ZdMajorCourseOrg> {

    List selectPageList(Page page,@Param("params") Map<String, Object> params);

    List selectDisableList(Page page,@Param("params") Map<String, Object> params);

    List selectZMCRList(Page page,@Param("params")  Map<String, Object> params);

    void deleteChildren(@Param("ids") List ids,@Param("orgCodes")  List<String> orgCodes);

    List selectOpenCountryList(Page page,@Param("params") Map<String, Object> params);

    int selectInsert(@Param("insertMap") Map<String, Object> insertMap);

}
