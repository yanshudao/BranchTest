package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdPublishResourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:33
 */
@Mapper
public interface ZdPublishResourceDao extends BaseMapper<ZdPublishResourceEntity> {

	List queryPublishListDetail(@Param("params") Map<String, Object> params);
	
	/**
	  * 获取orgCode对应的发行过的发行详情
	  * @param fromCode
	  * @param toCode
	  * @return
	  */
	 List<ZdPublishResourceEntity> getPublishResourceByOrgCode(@Param("fromOrgCode") String fromCode, @Param("toOrgCode") String toCode);

    int countByResourceIds(@Param("list") List ids);

	List<ZdPublishResourceEntity> selectByPublishId(@Param("publishId") String id);

    void deleteByPublishId(@Param("publishId") String id);
}
