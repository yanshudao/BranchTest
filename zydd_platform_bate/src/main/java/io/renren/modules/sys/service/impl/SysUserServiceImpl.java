package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {

		String username = (String)params.get("username");
		String orgCode = (String)params.get("orgCode");
		String orgId = (String)params.get("orgId");
		Long createUserId = (Long)params.get("createUserId");

		Page<SysUserEntity> page = this.selectPage(
			new Query<SysUserEntity>(params).getPage(),
			new EntityWrapper<SysUserEntity>()
				.like(StringUtils.isNotBlank(username),"username", username)
				.eq(createUserId != null,"create_user_id", createUserId)
				.eq(StringUtils.isNotBlank(orgCode),"org_code",orgCode)
				.eq(StringUtils.isNotBlank(orgId),"org_id",orgId)
		);

		return new PageUtils(page);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		this.insert(user);
		
		//检查角色是否越权
		//checkRole(user);
//		SysRoleEntity role=sysRoleService.selectById(user.getOrgType());
//		List roleIDList=new ArrayList<>();
//		roleIDList.add(role.getRoleId());
//		user.setRoleIdList(roleIDList);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	@CacheEvict(value="currentUser",allEntries = true)
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		this.updateById(user);
		
		//检查角色是否越权
		//checkRole(user);
//		SysRoleEntity role=sysRoleService.selectById(user.getOrgType());
//		List roleIDList=new ArrayList<>();
//		roleIDList.add(role.getRoleId());
//		user.setRoleIdList(roleIDList);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@CacheEvict(value="currentUser",allEntries = true)
	public void deleteBatch(Long[] userId) {
		this.deleteBatchIds(Arrays.asList(userId));
	}

	@Override
	@CacheEvict(value="currentUser",allEntries = true)
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new EntityWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
	}

	@Override
	@CacheEvict(value={"currentUser",Constant.CACHE_NAMESPACE + "MENU_USER"},allEntries = true)
	public void updateUser(SysUserEntity sysUserEntity) {
		if(sysUserEntity.getRoleIdList().size()<=0)
		{
			throw new RRException("请选择角色并保存");
		}
		sysUserRoleService.saveOrUpdate(sysUserEntity.getUserId(), sysUserEntity.getRoleIdList());
		this.updateById(sysUserEntity);
	}

	@Override
	@CacheEvict(value ={
			Constant.CACHE_NAMESPACE + "GET_ORG",
			Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG",
			Constant.CACHE_NAMESPACE + "LIST_CHILDREN_ORG",
			Constant.CACHE_NAMESPACE + "GET_PARENT_ORG",
			Constant.CACHE_NAMESPACE + "LIST_PARENT_ORG",
			Constant.CACHE_NAMESPACE + "AllCompany",
			Constant.CACHE_NAMESPACE + "MENU_USER",
			},allEntries = true)

	public void clearCache(SysUserEntity user) {

	}

	@Override
	@Transactional
	public void resetPassword(SysUserEntity sysUserEntity) {
		baseMapper.updateById(sysUserEntity);
	 	}

	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user){
		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
			return;
		}
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new RRException("新增用户所选角色，不是本人创建");
		}
	}
}
