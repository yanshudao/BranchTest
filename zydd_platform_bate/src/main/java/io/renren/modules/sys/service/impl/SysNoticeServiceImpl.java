package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysNoticeDao;
import io.renren.modules.sys.entity.SysNoticeEntity;
import io.renren.modules.sys.entity.SysNoticeOrgEntity;
import io.renren.modules.sys.service.SysNoticeOrgService;
import io.renren.modules.sys.service.SysNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysOrgNoticeService")
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeDao, SysNoticeEntity> implements SysNoticeService {

    @Autowired
    private SysNoticeOrgService sysNoticeOrgService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysNoticeEntity> page=new Query<SysNoticeEntity>(params).getPage();
        page.setRecords(baseMapper.selectListPage(page,params));
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveOrUpdate(SysNoticeEntity sysOrgNotice) {
        if(StringUtils.isNotBlank(sysOrgNotice.getId())){
            //更新
            baseMapper.updateById(sysOrgNotice);
            sysNoticeOrgService.delete(new EntityWrapper<SysNoticeOrgEntity>().eq("notice_id",sysOrgNotice.getId()));
            if(sysOrgNotice.getShowScope()==1){
            if(!CollectionUtils.isEmpty(sysOrgNotice.getOrgList())) {
                List<SysNoticeOrgEntity> sysNoticeOrgEntityList = new ArrayList<>();
                for (String orgId : sysOrgNotice.getOrgList()) {
                    SysNoticeOrgEntity sysNoticeOrgEntity = new SysNoticeOrgEntity();
                    sysNoticeOrgEntity.setNoticeId(sysOrgNotice.getId());
                    sysNoticeOrgEntity.setOrgId(orgId);
                    sysNoticeOrgEntityList.add(sysNoticeOrgEntity);
                }
                sysNoticeOrgService.insertBatch(sysNoticeOrgEntityList);
            }
            }
        }else{
            baseMapper.insert(sysOrgNotice);
            if(sysOrgNotice.getShowScope()==1){
                if(!CollectionUtils.isEmpty(sysOrgNotice.getOrgList())){
                    List<SysNoticeOrgEntity> sysNoticeOrgEntityList=new ArrayList<>();
                    for(String orgId:sysOrgNotice.getOrgList()){
                        SysNoticeOrgEntity sysNoticeOrgEntity=new SysNoticeOrgEntity();
                        sysNoticeOrgEntity.setNoticeId(sysOrgNotice.getId());
                        sysNoticeOrgEntity.setOrgId(orgId);
                        sysNoticeOrgEntityList.add(sysNoticeOrgEntity);
                    }
                    sysNoticeOrgService.insertBatch(sysNoticeOrgEntityList);
                }
            }

        }


    }

    @Override
    public List<SysNoticeEntity> listRead(Map<String, Object> params) {
        return null;
    }

    @Override
    public SysNoticeEntity getNoRead(Map<String, Object> params) {
        return baseMapper.selectNoRead(params);
    }

    @Override
    public int countNoRead(Map<String, Object> params) {
        return baseMapper.countNoRead(params);
    }

}
