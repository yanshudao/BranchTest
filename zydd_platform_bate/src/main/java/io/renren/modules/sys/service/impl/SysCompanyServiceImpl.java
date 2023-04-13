package io.renren.modules.sys.service.impl;

import io.renren.common.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysCompanyDao;
import io.renren.modules.sys.entity.SysCompanyEntity;
import io.renren.modules.sys.service.SysCompanyService;


@Service("sysCompanyService")
public class SysCompanyServiceImpl extends ServiceImpl<SysCompanyDao, SysCompanyEntity> implements SysCompanyService {

    @Autowired
    private SysCompanyDao sysCompanyDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysCompanyEntity> page=new Query<SysCompanyEntity>(params).getPage();
        page.setRecords(sysCompanyDao.selectListPage(page,params));
        return new PageUtils(page);

    }

    @Override
    @Cacheable(value = Constant.CACHE_NAMESPACE + "AllCompany", key = "'ALLCompany:'+#params[orgCode]")
    public List<SysCompanyEntity> queryAllByMap(Map<String, Object> params) {
        return sysCompanyDao.selectAllByMap(params);
    }

    @Override
    @CacheEvict(value = Constant.CACHE_NAMESPACE + "AllCompany",allEntries = true)
    public boolean insert(SysCompanyEntity entity) {
        return super.insert(entity);
    }

    @Override
    @CacheEvict(value = Constant.CACHE_NAMESPACE + "AllCompany",allEntries = true)
    public boolean deleteById(Serializable id) {
        return super.deleteById(id);
    }

    @Override
    @CacheEvict(value = Constant.CACHE_NAMESPACE + "AllCompany",allEntries = true)
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        return super.deleteBatchIds(idList);
    }

    @Override
    @CacheEvict(value = Constant.CACHE_NAMESPACE + "AllCompany",allEntries = true)
    public boolean updateById(SysCompanyEntity entity) {
        return super.updateById(entity);
    }
}
