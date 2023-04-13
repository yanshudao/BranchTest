package io.renren.modules.subject.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import io.renren.modules.subject.entity.SubjectCourseEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@Mapper
public interface SubjectCourseDao extends BaseMapper<SubjectCourseEntity> {

    List<SubjectCourseEntity> selectListPage(Pagination page, @Param("params") Map<String,Object> params);

    List<SubjectCourseEntity> selectListCoursePage(Page page,@Param("params") Map<String, Object> params);

    int countByIds(@Param("ids") List<String> courseIds);

    SubjectCourseEntity selectByCode(@Param("courseCode") String courseCode);

}
