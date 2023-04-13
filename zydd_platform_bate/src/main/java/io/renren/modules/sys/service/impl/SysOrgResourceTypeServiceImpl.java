package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.SysOrgResourceTypeDao;
import io.renren.modules.sys.entity.SysOrgResourceTypeEntity;
import io.renren.modules.sys.form.SysOrgResourceTypeForm;
import io.renren.modules.sys.service.SysOrgResourceTypeService;
import io.renren.modules.sys.vo.SysOrgResourceTypeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysOrgResourceTypeService")
public class SysOrgResourceTypeServiceImpl extends ServiceImpl<SysOrgResourceTypeDao, SysOrgResourceTypeEntity> implements SysOrgResourceTypeService {
    @Override
    public List<SysOrgResourceTypeVO> selectAll(Map<String, Object> queryParams) {
        return baseMapper.selectAll(queryParams);
    }

    @Override
    @Transactional
    public void save(SysOrgResourceTypeForm resourceTypeForm) {
        List<SysOrgResourceTypeEntity> list=new ArrayList<>();
        resourceTypeForm.getIdList().forEach(item->{
            SysOrgResourceTypeEntity entity=new SysOrgResourceTypeEntity();
            entity.setTypeId(item);
            entity.setOrgCode(resourceTypeForm.getOrgCode());
            list.add(entity);
        });
        baseMapper.delete(new EntityWrapper<SysOrgResourceTypeEntity>().eq("org_code",resourceTypeForm.getOrgCode()));
        insertBatch(list);
    }
}
