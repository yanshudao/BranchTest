package io.renren.modules.zd.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.zd.entity.ZdMajorCourseResourceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.zd.vo.ZdMajorCourseResourceVO;
import io.renren.modules.zd.vo.MajorCourseOrgVO;
import io.renren.modules.zd.vo.OrderResourceVO;
import io.renren.modules.zd.vo.OrgZdMajorVO;
import io.renren.modules.zd.vo.OrgZdVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 征订关系
 * 
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-10-22 11:01:03
 */
@Mapper
public interface ZdMajorCourseResourceDao extends BaseMapper<ZdMajorCourseResourceEntity> {

    List<OrgZdVO> selectOrgZdGroup(@Param("params") Map<String, Object> params);

    List<OrgZdMajorVO> selectOrgZdMajorMap(@Param("params")Map<String, Object> params);

    List<MajorCourseOrgVO> selectCourseByOrg(Page page,@Param("params") Map<String, Object> params);

    List<OrderResourceVO> selectResourceByOrg(Page<OrderResourceVO> page, @Param("params")Map<String, Object> params);

    List<ZdMajorCourseResourceVO> selectListPage(Page page,@Param("params") Map<String, Object> params);
    List<ZdMajorCourseResourceVO> selectListPage(@Param("params") Map<String, Object> params);

    int countByMap(@Param("params") Map<String, Object> map);

    ZdMajorCourseResourceEntity selectByUk(@Param("majorCode")String majorCode, @Param("majorType")String majorType,
                                           @Param("studentType")String studentType, @Param("subjectType")String subjectType,
                                           @Param("courseCode")String courseCode, @Param("resourceCode")String resourceCode,@Param("orgCode") String orgCode,@Param("semesterCode") String semesterCode);

    int selectInsert(@Param("orgCode") String orgCode,@Param("list")List ids);
}
