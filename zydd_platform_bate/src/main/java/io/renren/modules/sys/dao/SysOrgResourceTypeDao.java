package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysOrgResourceTypeEntity;
import io.renren.modules.sys.vo.SysOrgResourceTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-22 15:57:47
 */
@Mapper
public interface SysOrgResourceTypeDao extends BaseMapper<SysOrgResourceTypeEntity> {


    List<SysOrgResourceTypeVO> selectAll(@Param("params") Map<String, Object> queryParams);

}
