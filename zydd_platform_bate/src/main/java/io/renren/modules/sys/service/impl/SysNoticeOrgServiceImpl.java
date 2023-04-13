package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.SysNoticeOrgDao;
import io.renren.modules.sys.entity.SysNoticeOrgEntity;
import io.renren.modules.sys.service.SysNoticeOrgService;
import org.springframework.stereotype.Service;


@Service("sysNoticeOrgService")
public class SysNoticeOrgServiceImpl extends ServiceImpl<SysNoticeOrgDao, SysNoticeOrgEntity> implements SysNoticeOrgService {

}
