package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdOrgCourseResourceEntity;

import java.util.Map;

/**
 * 单位开设课程教材
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-31 00:14:32
 */
public interface ZdOrgCourseResourceService extends IService<ZdOrgCourseResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryByOrg(Map<String, Object> params);


    /**
     * 获得未开设教材
     * @param params
     * @return
     */
    PageUtils queryNotHaveByOrg(Map<String, Object> params);

    /**
     * 获得已开设教材
     * @param params
     * @return
     */
    PageUtils queryHaveByOrg(Map<String, Object> params);

    /**
     * 获取单位屏蔽的教材
     * @param params
     * @return
     */
    PageUtils queryDisableResourceByOrg(Map<String, Object> params);
}

