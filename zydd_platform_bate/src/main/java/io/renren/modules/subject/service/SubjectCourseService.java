package io.renren.modules.subject.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.subject.entity.SubjectCourseEntity;

import java.util.List;
import java.util.Map;

/**
 * 课程
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
public interface SubjectCourseService extends IService<SubjectCourseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveCourseResource(String courseId, List<String> resourceIds);

    void deleteCourseResource(List<String> ids);

    PageUtils queryListPage(Map<String, Object> params);

    PageUtils queryListCoursePage(Map<String, Object> params);

    int countByIds(List<String> courseIds);

    void deleteCourse(List ids);

    SubjectCourseEntity selectByCode(String courseCode);
}

