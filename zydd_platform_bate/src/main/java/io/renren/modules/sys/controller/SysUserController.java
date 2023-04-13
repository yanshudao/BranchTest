package io.renren.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.form.DeleteForm;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.PasswordForm;
import io.renren.modules.sys.form.SysUserForm;
import io.renren.modules.sys.service.SysOrgService;
import io.renren.modules.sys.service.SysOrgSettingService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
@Api("用户接口")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysOrgService sysOrgService;
	@Autowired
	private SysOrgSettingService sysOrgSettingService;



	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@ApiOperation("列表")
	//@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		/*if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}*/
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}
	@GetMapping("/listAll")
	@ApiOperation("列表所有")
	//@RequiresPermissions("sys:user:list")
	public R listAll(@RequestParam Map<String, Object> params){
		String orgCode= (String) params.get("orgCode");

		List list=sysUserService.selectList(new EntityWrapper<SysUserEntity>().eq(StringUtils.isNotBlank(orgCode),"org_code",orgCode));
	//	PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("data", list);
	}
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	@ApiOperation("获取当前登录人")
	public R info(){

		return R.ok().put("user", getUser()).put("org",sysOrgService.selectByOrgCode(getUser().getOrgCode())).put("orgSetting",sysOrgSettingService.selectByOrg(getUser().getOrgCode()));
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	@ApiOperation("修改密码")
	public R password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	//@RequiresPermissions("sys:user:info")
	@ApiOperation("获取用户详情")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.selectById(userId);
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(user.getOrgCode());
		if(sysOrgEntity!=null)
		{
			user.setOrg(sysOrgEntity);
			user.setOrgType(sysOrgEntity.getOrgType());
		}
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	//@RequiresPermissions("sys:user:save")
	@ApiOperation("保存用户")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	//@RequiresPermissions("sys:user:update")
	@ApiOperation("修改用户")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		//user.setCreateUserId(getUserId());
		sysUserService.update(user);

		return R.ok();
	}
	/**
	 * 修改用户
	 */
	@SysLog("重置密码")
	@PostMapping("/resetPassword")
	//@RequiresPermissions("sys:user:update")
	@ApiOperation("重置密码")
	public R resetPassword(@RequestBody PasswordForm form){
//		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		SysUserEntity sysUserEntity=sysUserService.selectById(form.getUserId());
		if(sysUserEntity==null)
		{
			return  R.error("未查询到此用户！");
		}
		if(StringUtils.isBlank(form.getPassword()))
		{
			form.setPassword("123456");
		}
		//sha256加密
		String newPassword = new Sha256Hash(form.getPassword(), sysUserEntity.getSalt()).toHex();
		sysUserEntity.setPassword(newPassword);
		sysUserService.resetPassword(sysUserEntity);

		return R.ok();
	}
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	//@RequiresPermissions("sys:user:delete")
	@ApiOperation("删除用户")
	public R delete(@RequestBody DeleteForm deleteForm){
		if(ArrayUtils.contains(deleteForm.getIds().toArray(), 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(deleteForm.getIds().toArray(), getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatchIds(deleteForm.getIds());
		
		return R.ok();
	}

	@SysLog("修改用户")
	@PostMapping("/updateUser")
	//@RequiresPermissions("sys:user:update")
	@ApiOperation("修改用户")
	public R updateUser(@RequestBody SysUserForm user){
		ValidatorUtils.validateEntity(user);
		//user.setCreateUserId(getUserId());
		SysUserEntity sysUserEntity=new SysUserEntity();
		sysUserEntity.setUserId(user.getUserId());
		if(StringUtils.isNotBlank(user.getPassword()))
		{
			String password = new Sha256Hash(user.getPassword(), getUser().getSalt()).toHex();
			sysUserEntity.setPassword(password);
		}
		sysUserEntity.setRoleIdList(user.getRoleIdList());
		sysUserEntity.setRealname(user.getRealname());
		sysUserEntity.setMobile(user.getMobile());
		sysUserEntity.setEmail(user.getEmail());
		sysUserEntity.setOrgType(user.getOrgType());
		sysUserService.updateUser(sysUserEntity);
		return R.ok();
	}
	@SysLog("修改资料")
	@PostMapping("/updateInfo")
	//@RequiresPermissions("sys:user:update")
	@ApiOperation("修改资料")
	public R updateInfo(@RequestBody SysUserForm user){
		ValidatorUtils.validateEntity(user);
		//user.setCreateUserId(getUserId());
		SysUserEntity sysUserEntity=new SysUserEntity();
		if(StringUtils.isNotBlank(user.getPassword()))
		{
			String password = new Sha256Hash(user.getPassword(), getUser().getSalt()).toHex();
			sysUserEntity.setPassword(password);
		}
		sysUserEntity.setUserId(getUser().getUserId());
		//sysUserEntity.setPassword(user.getPassword());
		sysUserEntity.setRealname(user.getRealname());
		sysUserEntity.setMobile(user.getMobile());
		sysUserEntity.setEmail(user.getEmail());
		sysUserService.updateUser(sysUserEntity);
		return R.ok();
	}
}
