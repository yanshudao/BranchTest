package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.subject.vo.SubjectMajorVO;
import io.renren.modules.zd.entity.ZdOrgMajorCourseEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.vo.MajorCourseOrgVO;
import io.renren.modules.zd.vo.OrderResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 单位开设专业课程
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
@Mapper
public interface ZdOrgMajorCourseDao extends BaseMapper<ZdOrgMajorCourseEntity> {

    List<MajorCourseOrgVO> selectListPage(Page page, @Param("params") Map<String, Object> params);

    List<MajorCourseOrgVO> selectByList(Page page, @Param("params") Map<String, Object> params);


    List selectListAll(@Param("params") Map<String, Object> params);

    List<MajorCourseOrgVO> selectNotHaveByOrg(Page page,@Param("params")  Map<String, Object> params);

    List<MajorCourseOrgVO> selectHaveByOrg(Page page,@Param("params") Map<String, Object> params);

    List<MajorCourseOrgVO> selectDisableCourseByOrg(Page page,@Param("params")  Map<String, Object> params);

    void deleteByCourseIds(@Param("ids") List ids);

    void deleteByMajorIds(@Param("ids") Collection<? extends Serializable> idList);

    List<OrderResourceVO> selectResourceByCourseId(Page page, @Param("params")  Map<String, Object> params);
}
