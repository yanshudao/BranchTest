package io.renren.modules.sys.service.impl;

import io.renren.common.utils.Constant;
import io.renren.modules.sys.dao.SysMenuDao;
import io.renren.modules.sys.dao.SysOrgDao;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.dao.SysUserTokenDao;
import io.renren.modules.sys.entity.SysMenuEntity;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserTokenEntity;
import io.renren.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Autowired
    private SysOrgDao sysOrgDao;

    @Override
    @Cacheable(value = Constant.CACHE_NAMESPACE +"userPermission", key = "'MENU_PERMISSION:'+#userId")
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
           // permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    @Cacheable(value = Constant.CACHE_NAMESPACE + "currentToken")
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    @Cacheable(cacheNames="currentUser", key = "#p0")
    public SysUserEntity queryUser(String userId) {
        SysUserEntity sysUserEntity=sysUserDao.selectById(userId);
        SysOrgEntity sysOrgEntity=sysOrgDao.selectByOrgCode(sysUserEntity.getOrgCode());
        sysUserEntity.setOrgType(sysOrgEntity.getOrgType());
        sysUserEntity.setOrg(sysOrgEntity);
        return sysUserEntity;
    }

    @Override
    public SysOrgEntity queryParentByOrgCode(String orgCode) {
        return sysOrgDao.selectParentByOrgCode(orgCode);
    }
}
