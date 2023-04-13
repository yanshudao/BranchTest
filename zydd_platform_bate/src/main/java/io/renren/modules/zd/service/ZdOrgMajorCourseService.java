package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdOrgMajorCourseEntity;

import java.util.List;
import java.util.Map;

/**
 * 单位开设专业课程
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
public interface ZdOrgMajorCourseService extends IService<ZdOrgMajorCourseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 县级查询可报订的课程
     * @param params
     * @return
     */
    PageUtils queryByOrg(Map<String, Object> params);

    void deleteByIds(List<String> ids,String orgCode,String semesterCode);

    List queryAllByOrg(Map<String, Object> params);

    /**
     * 获得未开设的课程列表
     * @param params
     * @return
     */
    PageUtils queryNotHaveByOrg(Map<String, Object> params);

    /**
     * 获得已开设的课程列表
     * @param params
     * @return
     */
    PageUtils queryHaveByOrg(Map<String, Object> params);

    /**
     * 获取本单位已经屏蔽的课程
     * @param params
     * @return
     */
    PageUtils queryDisableCourseByOrg(Map<String, Object> params);

    PageUtils queryByOrgCourseResource(Map<String, Object> params);
}

