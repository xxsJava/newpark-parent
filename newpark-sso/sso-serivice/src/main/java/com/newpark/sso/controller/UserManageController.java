package com.newpark.sso.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

import cn.shuibo.annotation.Encrypt;
import com.newpark.base.enums.PrefixMsgEnum;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.sso.entity.vo.RegisteredVo;
import org.springframework.web.bind.annotation.*;

import com.newpark.base.model.vo.R;
import com.newpark.base.util.JwtUtil;
import com.newpark.redis.utils.RedisUtils;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.sys.service.ISysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author xxs18
 * @Description 用户管理控制器
 * @Date 2023/10/9 14:49
 **/
@Slf4j
@RestController
@RequestMapping("/usr")
@Api(value = "usr", tags = "用户管理", description = "用户操作")
public class UserManageController {

    @Resource
    private ISysUserService userService;

    @Resource
    private RedisUtils redisUtils;

    @Encrypt
    @PostMapping("/login")
    @ApiOperation(notes = "登录API", value = "登录API")
    public R loginToken(@RequestBody LoginVo loginVo, HttpServletRequest request) {

        String token = request.getHeader("Content-TOKEN");
        log.info("获取登录请求token,{}", token);

        try {
            @NotEmpty
            String tokenVal = redisUtils.get(token);

            log.info("redis有该用户数据,{}", tokenVal);
            // 验证是否被篡改过
            JwtUtil.getToken(tokenVal);
            // 验证通过
            return R.ok(token);
        } catch (Exception e) {
            log.error("数据验证未通过,{}",e);
            // 避免穿透
            redisUtils.set(token + "", 1, 60);
            return userService.loginFind(loginVo);
        }
    }

    @GetMapping("/loginOut")
    @ApiOperation(notes = "登出API", value = "登出API")
    public R loginOut() {
        return R.ok();
    }

    @GetMapping("/forget")
    @ApiOperation(notes = "忘记密码API", value = "忘记密码API")
    public R forgetUpdate() {
        return R.ok();
    }

    @GetMapping("/smsLogin")
    @ApiOperation(notes = "短信验证登录API", value = "短信验证登录API")
    public R smsLoginFind() {
        return R.ok();
    }

    @PostMapping("/registeredInfo")
    @ApiOperation(notes = "注册信息API", value = "注册API")
    public R registered(@RequestBody RegisteredVo registeredVo, String smsCode) {
        log.info("用户输入的验证码---》{}",smsCode);
        try {
            @NotEmpty
            String msgCode = redisUtils.get(PrefixMsgEnum.MSG_CODE.getMsg() + registeredVo.getUPhone());
            if(!smsCode.equals(msgCode)){
                return R.failed(ResponseCodeEnum.LOGIN_VERIFY_CODE_ERROR);
            }
            return userService.registeredUser(registeredVo);
        } catch (Exception e) {
            log.error("注册信息有误{}",e);
            return R.failed(ResponseCodeEnum.LOGIN_VERIFY_CODE_ERROR);
        }
    }
}
