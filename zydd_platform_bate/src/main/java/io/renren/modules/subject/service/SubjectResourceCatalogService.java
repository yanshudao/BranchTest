package io.renren.modules.subject.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.subject.entity.SubjectResourceCatalogEntity;

import java.util.Map;

/**
 * 教材目录
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-03-29 23:29:28
 */
public interface SubjectResourceCatalogService extends IService<SubjectResourceCatalogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

