package io.renren.modules.subject.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.subject.entity.SubjectMajorEntity;

import java.util.List;
import java.util.Map;

/**
 * 专业
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
public interface SubjectMajorService extends IService<SubjectMajorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveMajorCourse(String majorId, List<String> courseIds,String semesterCode);

    void deleteMajorCourse(List<String> ids);

    SubjectMajorEntity selectByCode(String majorCode,String studentType,String majorType,String orgCode,String parentOrgCodes);

    //PageUtils queryMajorByPage(Map<String, Object> params);
}

