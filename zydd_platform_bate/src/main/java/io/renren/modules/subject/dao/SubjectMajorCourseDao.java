package io.renren.modules.subject.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.subject.entity.SubjectCourseEntity;
import io.renren.modules.subject.entity.SubjectMajorCourseEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 专业课程
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@Mapper
public interface SubjectMajorCourseDao extends BaseMapper<SubjectMajorCourseEntity> {

    void deleteByCourseIds(@Param("ids") List ids);

    void deleteByMajorIds(@Param("ids") Collection<? extends Serializable> idList);
}
