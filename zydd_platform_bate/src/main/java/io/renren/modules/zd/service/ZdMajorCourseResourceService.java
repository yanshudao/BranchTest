package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.zd.entity.ZdMajorCourseResourceEntity;
import io.renren.modules.zd.form.UpdateResourceVersionForm;
import io.renren.modules.zd.vo.ZdMajorCourseResourceVO;

import java.util.List;
import java.util.Map;

/**
 * 征订关系
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-10-22 11:01:03
 */
public interface ZdMajorCourseResourceService extends IService<ZdMajorCourseResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List queryOrgZdGroup(Map<String, Object> params);

    PageUtils queryCourseByOrg(Map<String, Object> params);

    PageUtils queryResourceByOrg(Map<String, Object> params);

    int countByMap(Map<String, Object> map);

    List<ZdMajorCourseResourceVO> importZMCR(List<ZdMajorCourseResourceVO> list, SysUserEntity user,String semesterCode);

    List<ZdMajorCourseResourceVO> queryAll(Map<String, Object> params);

    List selectOrgZdMajorMap(Map<String, Object> params);

    boolean updateVersion(UpdateResourceVersionForm updateResourceVersionForm);
    ZdMajorCourseResourceEntity selectByUk(String majorCode, String majorType,
                                          String studentType,String subjectType,
                                           String courseCode, String resourceCode, String orgCode,String semesterCode);

    int syncCourse(List ids);

}

