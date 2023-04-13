package io.renren.modules.subject.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.subject.entity.SubjectResourceTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 换版教材
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
@Mapper
public interface SubjectResourceTypeDao extends BaseMapper<SubjectResourceTypeEntity> {

    SubjectResourceTypeEntity selectByName(@Param("name") String invclassname);

}
