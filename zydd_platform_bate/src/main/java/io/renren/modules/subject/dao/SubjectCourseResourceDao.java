package io.renren.modules.subject.dao;

import io.renren.modules.subject.entity.SubjectCourseResourceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程教材
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@Mapper
public interface SubjectCourseResourceDao extends BaseMapper<SubjectCourseResourceEntity> {

    int updateVersion(@Param("oldResourceId") String id,@Param("newResourceId") String id1);

    void deleteByCourseIds(@Param("ids")List ids);

    void deleteByResourceIds(@Param("ids")List ids);
}
