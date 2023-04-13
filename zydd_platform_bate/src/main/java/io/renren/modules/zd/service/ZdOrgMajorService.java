package io.renren.modules.zd.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.zd.entity.ZdOrgMajorEntity;

import java.util.List;
import java.util.Map;

/**
 * 单位开设专业
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-04-01 15:58:41
 */
public interface ZdOrgMajorService extends IService<ZdOrgMajorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryByOrg(Map<String, Object> params);

    PageUtils queryBySheng(Map<String, Object> params);


    List queryPageGroup(Map<String, Object> params);

}

