package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysNoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lixiang
 * @email 395569664@qq.com
 * @date 2018-06-22 15:57:47
 */
public interface SysNoticeService extends IService<SysNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveOrUpdate(SysNoticeEntity sysOrgNotice);

    List<SysNoticeEntity> listRead(Map<String, Object> params);


    SysNoticeEntity getNoRead(Map<String, Object> params);

    int countNoRead(Map<String, Object> params);

}

