package io.renren.modules.user.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.PasswordForm;
import io.renren.modules.sys.form.SysUserForm;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api("前台用户")
public class UserController extends AbstractController {

}
