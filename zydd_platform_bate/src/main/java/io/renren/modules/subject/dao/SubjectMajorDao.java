package io.renren.modules.subject.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.subject.entity.SubjectMajorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 专业
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@Mapper
public interface SubjectMajorDao extends BaseMapper<SubjectMajorEntity> {

    List<SubjectMajorEntity> selectListPage(Page page,@Param("params") Map<String, Object> params);

    SubjectMajorEntity selectByCode(@Param("majorCode") String majorCode,@Param("studentType") String studentType,@Param("majorType") String majorType,
                                    @Param("orgCode") String orgCode,@Param("parentOrgCodes") String parentOrgCodes);
}
