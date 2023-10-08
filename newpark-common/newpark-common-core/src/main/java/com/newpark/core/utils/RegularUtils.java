package com.newpark.core.utils;


import com.newpark.base.constant.Constant;
import com.newpark.base.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证
 *
 * @author jack
 * @date 2023/4/13
 */
@Slf4j
public class RegularUtils {

    public static final String PHONE_REGULAR = "^$|^((19[0-9])|(16[0-9])|(17[0-9])|(14[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
    public static final String PASSWORD_REGULAR = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";
    public static final String QQ_REGULAR = "^$|^[1-9][0-9]{4,12}$";
    public static final String WE_CHAT_REGULAR_PHONE = "^[1-9][0-9]{4,19}$";
    public static final String WE_CHAT_REGULAR = "^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}+$";
    public static final String INTEGER_REGULAR = "\\-{0,1}\\d+";
    /**
     * 浮点数的正则表达式-小数点在中间与前面
     */
    public static final String FLOAT_PREFIX_REGULAR = "(\\-|\\+){0,1}\\d*\\.\\d+";
    /**
     * 浮点数的正则表达式-小数点在后面
     */
    public static final String FLOAT_SUFFIX_REGULAR = "(\\-|\\+){0,1}\\d+\\.";

    public static final String EMAIL_ONE = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    public static final String EMAIL_TWO = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    public static final String LANDLINE_LONG_ONE = "^0\\d{2}-\\d{8}$";
    public static final String LANDLINE_LONG_TWO = "^0\\d{3}-\\d{7}$";
    public static final String LANDLINE_SHORT = "^[1-9]{1}[0-9]{6,7}$";

    /**
     * 检验QQ号码格式是否正确
     *
     * @param str 输入QQ号码
     */
    public static void checkQq(String str) {

        if (StringUtils.isNotBlank(str)) {
            Pattern pattern = Pattern.compile(QQ_REGULAR);
            Matcher matcher = pattern.matcher(str);
            if (!matcher.find()) {
                throw new BizException("Q格式错误！并且最长只能12位！");
            }
        }

    }

    /**
     * 检验微信格式是否正确
     */
    public static void checkWechat(String str) {
        if (StringUtils.isNotBlank(str)) {
            //
            Matcher matcher = Pattern.compile(WE_CHAT_REGULAR).matcher(str);
            Matcher matcherp = Pattern.compile(WE_CHAT_REGULAR_PHONE).matcher(str);
            if (!matcher.find() && !matcherp.find()) {
                throw new BizException("微信格式错误");
            }
        }
    }

    /**
     * 检验电话号码格式是否正确
     *
     * @param str 输入座机号码
     */
    public static void checkPhone(String str) {

        if (StringUtils.isNotBlank(str)) {
            Pattern pattern = Pattern.compile(PHONE_REGULAR);
            Matcher matcher = pattern.matcher(str);
            if (!matcher.find()) {
                throw new BizException("电话号码格式错误！");
            }
        }
    }

    /**
     * 检验电话号码格式是否正确
     *
     * @param phone 输入座机号码
     */
    public static boolean isPhone(String phone) {
        if (StringUtils.isNotBlank(phone)) {
            if (phone.length() != Constant.INTEGER_ELEVEN) {
                log.error("手机号应为11位数:校验的手机号码" + phone);
                return false;
            } else {
                Pattern p = Pattern.compile(PHONE_REGULAR);
                Matcher m = p.matcher(phone);
                boolean isMatch = m.matches();
                if (!isMatch) {
                    log.error("请填入正确的手机号:校验的手机号码" + phone);
                }
                return isMatch;
            }
        }
        return true;
    }

    /**
     * 检验密码安全强度
     */
    public static void checkPassWord(String str) {
        if (StringUtils.isNotBlank(str)) {
            Pattern pattern = Pattern.compile(PASSWORD_REGULAR);
            Matcher matcher = pattern.matcher(str);
            if (!matcher.find()) {
                throw new BizException("密码8-20位，必须包含数字和字母");
            }
        }
    }

    /**
     * 判断number参数是否是浮点数表示方式
     *
     * @param number 数值字符串
     * @return 返回结果
     */
    public static boolean isFloat(String number) {
        number = number.trim();
        //浮点数的正则表达式-小数点在中间与前面
        //浮点数的正则表达式-小数点在后面
        return number.matches(FLOAT_PREFIX_REGULAR) || number.matches(FLOAT_SUFFIX_REGULAR);
    }


    /**
     * 判断number参数是否是整型数表示方式
     *
     * @param number 数值字符串
     * @return 返回结果
     */
    public static boolean isInteger(String number) {
        number = number.trim();
        //整数的正则表达式
        return number.matches(INTEGER_REGULAR);
    }


    public static boolean isQq(String qq) {
        if (StringUtils.isNotBlank(qq)) {
            qq = qq.replaceAll("\r\n|\r|\n", "").trim();
            Pattern pattern = Pattern.compile(QQ_REGULAR);
            Matcher matcher = pattern.matcher(qq);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmail(String email) {
        if (StringUtils.isNotBlank(email)) {
            Pattern p1, p2;
            Matcher m1, m2;
            p1 = Pattern.compile(EMAIL_ONE);
            p2 = Pattern.compile(EMAIL_TWO);
            m1 = p1.matcher(email);
            m2 = p2.matcher(email);
            if (!m1.matches() && !m2.matches()) {
                return false;
            }
        }
        return true;
    }


    public static boolean isLandLine(String landLine) {
        if (StringUtils.isNotBlank(landLine)) {
            landLine = landLine.replaceAll("\r\n|\r|\n", "").trim();
            Pattern p1, p2, p3;
            boolean isPhone1, isPhone2, isPhone3;
            // 验证带区号的
            p1 = Pattern.compile(LANDLINE_LONG_ONE);
            p2 = Pattern.compile(LANDLINE_LONG_TWO);
            // 验证没有区号的
            p3 = Pattern.compile(LANDLINE_SHORT);
            isPhone1 = p1.matcher(landLine).matches();
            isPhone2 = p2.matcher(landLine).matches();
            isPhone3 = p3.matcher(landLine).matches();
            if (!isPhone1 && !isPhone2 && !isPhone3) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        checkPassWord("1238111114a");
    }

}
