package com.newpark.sso.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.newpark.common.enums.KeySm2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.newpark.base.enums.PrefixMsgEnum;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.base.util.JwtUtil;
import com.newpark.core.utils.StringUtils;
import com.newpark.redis.utils.RedisUtils;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.entity.vo.RegisteredVo;
import com.newpark.sso.entity.vo.SmsLoginVo;
import com.newpark.sso.sys.service.ISysUserService;

import cn.hutool.crypto.digest.MD5;
import cn.shuibo.annotation.Encrypt;
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
    public R loginUser(@RequestBody LoginVo loginVo) {
        try {
            // 账号登录
            return userService.loginFind(loginVo);
        } catch (Exception e) {
            log.info("数据验证未通过-->{}", loginVo.toString());
            return userService.loginFind(loginVo);
        }
    }

    @Encrypt
    @PostMapping("/loginToken")
    @ApiOperation(notes = "Token登录API", value = "Token登录API")
    public R loginToken(HttpServletRequest request) {

        String token = request.getHeader("Content-TOKEN");
        String newToken = MD5.create().digestHex16(token);
        try {
            // token 验证通过直接登录
            if (StringUtils.isNotEmpty(token)) {
                @NotEmpty
                String tokenVal = redisUtils.get(token);
                log.info("redis有该用户数据,{}", tokenVal);
                // 验证是否被篡改过
                JwtUtil.getToken(tokenVal);
                // redis中进行失效
                redisUtils.delete(token);
                // 验证通过
                // 生成新token 7天有效时间
                redisUtils.set(newToken, tokenVal, PrefixMsgEnum.REDIS_TIME_7_DAY.getTime());
            }
        } catch (Exception e) {
            log.info("数据验证未通过token--->{}", token);
            // 避免穿透
            redisUtils.set(token + "", 1, 60);
            return R.failed(ResponseCodeEnum.LOGIN_FAILURE);
        }
        return R.ok(newToken);
    }

    @PostMapping("/loginOut")
    @ApiOperation(notes = "登出API", value = "登出API")
    public R loginOut(HttpServletRequest request) throws UnsupportedEncodingException {

        // 删除redis里面的缓存数据
        String token = request.getHeader("Content-TOKEN");

        if(StringUtils.isEmpty(token)){
            return R.ok();
        }
        String usrToken = redisUtils.get(token);

        if (StringUtils.isNotEmpty(usrToken)) {

            String uId = JwtUtil.getToken(usrToken).getClaim("uId").asString();
            // 删除数据库明文数据
            redisUtils.delete(PrefixMsgEnum.MSG_USR.getMsg() + uId);
            // 删除usr token
            redisUtils.delete(token);
        }
        return R.ok();
    }

    @GetMapping("/forget")
    @ApiOperation(notes = "忘记密码API", value = "忘记密码API")
    public R forgetUpdate() {
        return R.ok();
    }

    @PostMapping("/smsLogin")
    @ApiOperation(notes = "短信验证登录API", value = "短信验证登录API")
    public R smsLoginFind(@RequestBody SmsLoginVo smsLoginVo) {
        log.info("接收验证码参数,{}", smsLoginVo);

        // 获取到验证码
        String codeRedis = redisUtils.get(PrefixMsgEnum.MSG_CODE.getMsg() + smsLoginVo.getPhone());

        if (smsLoginVo.getCode().equals("phone")) {
            if (StringUtils.isEmpty(codeRedis)) {
                // 发送验证码
                // 验证码存redis 5分钟过期
                redisUtils.set(PrefixMsgEnum.MSG_CODE.getMsg() + smsLoginVo.getPhone(), 1234,
                    PrefixMsgEnum.REDIS_TIME_DAY_5.getTime());
                return R.failed(ResponseCodeEnum.LOGIN_VERIFY_CODE_SUCC);
            }
            return R.failed(ResponseCodeEnum.LOGIN_VERIFY_CODE_SUCC1);
        }

        // 验证码比较
        if (smsLoginVo.getCode().equals(codeRedis)) {
            return userService.smsLoginFind(smsLoginVo);
        }

        return R.failed(ResponseCodeEnum.LOGIN_VERIFY_CODE_ERROR);
    }

    @PostMapping("/registeredInfo")
    @ApiOperation(notes = "注册信息API", value = "注册API")
    public R registered(@RequestBody @Validated RegisteredVo registeredVo) {

        try {
            return userService.registeredUser(registeredVo);
        } catch (Exception e) {
            log.error("注册信息有误{}", e);
            return R.failed(ResponseCodeEnum.LOGIN_VERIFY_CODE_ERROR);
        }
    }

    @PostMapping("/decrypt")
    @ApiOperation(notes = "解密API", value = "解密API")
    public R decrypt(@RequestBody String ciphertext) {
        SM2 sm2 = SmUtil.sm2(KeySm2.PRIVATE_KEY.getKey(), KeySm2.PUBLIC_KEY.getKey());
        try {
            return R.ok(StrUtil.utf8Str(sm2.decryptFromBcd(ciphertext, KeyType.PrivateKey)));
        } catch (Exception e) {
            return R.failed(ResponseCodeEnum.UNAUTHORIZED);
        }
    }
}
