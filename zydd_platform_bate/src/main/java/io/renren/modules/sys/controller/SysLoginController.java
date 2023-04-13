package io.renren.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import io.renren.common.utils.R;
import io.renren.common.utils.RSAUtils;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.sys.entity.SysOrgEntity;
import io.renren.modules.sys.entity.SysOrgSettingEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.SysLoginForm;
import io.renren.modules.sys.service.*;
import io.renren.modules.sys.vo.SysUserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
@Api("登录接口")
public class SysLoginController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysCaptchaService sysCaptchaService;
	@Autowired
	private SysOrgService sysOrgService;
	@Autowired
	private SysOrgSettingService sysOrgSettingService;
	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private SysConfigService sysConfigService;

	/**
	 * 验证码
	 */
	@GetMapping("captcha")
	@ApiOperation("验证码")
	public R captcha(HttpServletResponse response, String uuid)throws ServletException, IOException {
		try {
			String base64 = sysCaptchaService.getBase64Captcha(uuid);
			return R.ok().put("data",base64);
		} catch (Exception e) {
			return R.error("获取验证码出错!");
		}

	}

	private static final String PRIVATE_KEY= "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMNpq6tHHxCh7PfR" +
			"PQWhcXrLeqCaqdqWQV8G2GX+mMQ8Ecxm+qB50uq49uUFzjm2Fgg0CjTYu7n6L5iD" +
			"nXe3oUQvn+WA7LK3s/mZskCAB2ZHBKe/IHVrvVSPI93X1TJ+Qfu0yt03hbWwoPYl" +
			"st3pCr/O8TkXQmAgEnGeuYbHwl85AgMBAAECgYEAgcUxDeODS8Zc2g4IGi8mb0el" +
			"vfY4CkNyUir3lnRG+zpxD27rzZpZaStAOsNqOoUjiHsWtBWiRVVSOlRdRF2cJ8Tm" +
			"VeTYVUdiSP+sEcIlt8VnaJoQ5BJ8SRqs/bR4hG9c5gZDZKEAktfmCO0gzk8uCAUP" +
			"usENxcLogEekwVHAfckCQQDjKBwvApLeput/e5zeN6Ltiab0AfM7RUTxxEzrNb2d" +
			"sktv29eOgS/WvAgDT5+Ge1lMAFaFTkJ7QyP5iCLj2csLAkEA3DmzxdlcGAPvhAyh" +
			"+NiQKzeDFavyw6j9lh0/0EUaOSY8+T8YdQBP6+NlYX4CrpQERjc/YoFvFUYOOUw0" +
			"8sGJSwJAelwSwmfagUDcvfDyEOlbRCTP38RlJtorRyf8Xv61wwpVhE2hkUuZX0wt" +
			"7MqpHaG3+i58bJY5TXhfGnzwflfE/QJBAMecHZA1Jb42wwBDmwQ89t/VHyGjixVB" +
			"tSg9NrwGBnDKcfXQ9NAICmja4eduGew3CXDDXtZgT1lO+FGC+3MVbOUCQC9BzAaY" +
			"VvMK+JMA8hbW2ipXf/7DQpGFqSVGgpMAky3oDkTNveYfY5fxTHMNOtryl+TSnYWe" +
			"lC0PCH+p7mCD5Z4=";

	/**
	 * 登录
	 */
	@PostMapping("/sys/login")
	@ApiOperation("登录")
	public Map<String, Object> login(@RequestBody String loginCrypt)throws IOException {
		SysLoginForm form=null;
		try {
			String descrypt= RSAUtils.privateKeyDecrypt(loginCrypt,PRIVATE_KEY);
			form= JSONObject.parseObject(descrypt,SysLoginForm.class);

		} catch (Exception e) {
			return R.error("参数非法");
		}
		if(form==null){
			return R.error("参数非法");
		}
		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
		if(!captcha){
			return R.error("验证码不正确");
		}

		String loginFlag=sysConfigService.getValueByQuery("LOGIN_FLAG");
		String loginMessage=sysConfigService.getValueByQuery("LOGIN_MESSAGE");
		if("0".equals(loginFlag)){
			return R.error(loginMessage);
		}
//		if(1==1)
//		return R.error("  由于本系统数据进行更新升级，需要暂停报订，更新时间为2018年4月19日18：00至2018年4月23日00：00。给您带来的不便，我们深表歉意，希望您谅解，我们将继续竭诚为您服务。\n");
//		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
		/*	if(user!=null){
				Integer errorCount=redisUtils.get("user_login_error:"+user.getUserId(),Integer.class);
				if(errorCount==null){
					errorCount=1;
				}else{
					errorCount++;
				}
				if(errorCount>=5){
					return R.error("账号或密码不正确");
				}
				*//*
				String date=DateUtils.format(new Date());
				date+=" 23:59:59";
				long expire=DateUtils.stringToDate(date,DateUtils.DATE_TIME_PATTERN)*//*;
				redisUtils.set("user_login_error:"+user.getUserId(),errorCount, 60*60*2);

			}*/
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}
		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());
		SysOrgEntity sysOrgEntity=sysOrgService.selectByOrgCode(user.getOrgCode());
		SysOrgSettingEntity sysOrgSettingEntity=sysOrgSettingService.selectByOrg(user.getOrgCode());
		SysUserLoginVO sysUserLoginVO=new SysUserLoginVO();
		BeanUtils.copyProperties(user,sysUserLoginVO);
		sysUserLoginVO.setOrg(sysOrgEntity);
		sysUserLoginVO.setOrgConf(sysOrgSettingEntity);
		r.put("user",sysUserLoginVO);
		return r;
	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	@ApiOperation("退出登录")
	public R logout() {
		sysUserService.clearCache(getUser());
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	@PostMapping("/sys/clearCache")
	@ApiOperation("退出登录")
	public R clearCache() {
		sysUserService.clearCache(getUser());
		return R.ok();
	}
}
