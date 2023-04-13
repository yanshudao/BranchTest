package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.SysNoticeRecordDao;
import io.renren.modules.sys.entity.SysNoticeRecordEntity;
import io.renren.modules.sys.service.SysNoticeRecordService;
import org.springframework.stereotype.Service;


@Service("sysNoticeRecordService")
public class SysNoticeRecordServiceImpl extends ServiceImpl<SysNoticeRecordDao, SysNoticeRecordEntity> implements SysNoticeRecordService {


}
