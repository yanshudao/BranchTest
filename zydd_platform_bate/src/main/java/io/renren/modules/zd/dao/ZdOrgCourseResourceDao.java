package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdOrgCourseResourceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.vo.CourseResourceOrgVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 单位开设课程教材
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@Mapper
public interface ZdOrgCourseResourceDao extends BaseMapper<ZdOrgCourseResourceEntity> {

    List<CourseResourceOrgVO> selectByList(Page page,@Param("params") Map<String, Object> params);

    List<CourseResourceOrgVO> selectResourceByZdOrg(Page page, @Param("params")Map<String, Object> params);

    List<CourseResourceOrgVO> selectResourceByZdOrg(@Param("params")Map<String, Object> params);

    List<CourseResourceOrgVO> selectResourceByZd(@Param("params")Map<String, Object> params);

    List<CourseResourceOrgVO> selectNotHaveByOrg(Page page, @Param("params")Map<String, Object> params);

    List<CourseResourceOrgVO> selectHaveByOrg(Page page,@Param("params") Map<String, Object> params);

    List<CourseResourceOrgVO> selectDisableResourceByOrg(Page page, @Param("params")Map<String, Object> params);

    void deleteByCourseIds(@Param("ids") List ids);

    void deleteByResourceIds(@Param("ids")List ids);

    void deleteByMajorIds(@Param("ids")Collection<? extends Serializable> idList);
}


