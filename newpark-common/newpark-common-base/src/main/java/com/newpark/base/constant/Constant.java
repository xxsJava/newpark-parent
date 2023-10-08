package com.newpark.base.constant;


/**
 * 常量
 */
public class Constant {

    /**
     * 常量
     */
    public final static Integer MIUNS_INTEGER_TWO = -2;
    public final static Integer INTEGER_NEGATIVE = -1;
    public final static Integer INTEGER_ZERO = 0;
    public final static Integer INTEGER_ONE = 1;
    public final static Integer INTEGER_TWO = 2;
    public final static Integer INTEGER_THREE = 3;
    public final static Integer INTEGER_FOUR = 4;
    public final static Integer INTEGER_FIVE = 5;
    public final static Integer INTEGER_SIX = 6;
    public final static Integer INTEGER_SEVEN = 7;
    public final static Integer INTEGER_EIGHT = 8;
    public final static Integer INTEGER_NINE = 9;
    public final static Integer INTEGER_TEN = 10;
    public final static Integer INTEGER_ELEVEN = 11;


    public final static String STRING_NEGATIVE = "-1";
    public final static String STRING_ZERO = "0";
    public final static String STRING_ONE = "1";
    public final static String STRING_TWO = "2";
    public final static String STRING_THREE = "3";
    public final static String STRING_FOUR = "4";
    public final static String STRING_FIVE = "5";
    public final static String STRING_SIX = "6";
    public final static String STRING_SEVEN = "7";
    public final static String STRING_EIGHT = "8";
    public final static String STRING_NINE = "9";
    public final static String STRING_TEN = "10";
    public final static String STRING_ELEVEN = "11";
    public final static String STRING_TWELVE = "12";

    /**
     * token相关
     */
    public class Token {
        //请求头
        public final static String REQUEST_HEADER = "reds-access-token";
        //生成token密钥
        public final static String SECRET_KEY = "!@#$%^&*()/";
        //签发者
        public final static String ISSUER = "mustang";
        //过期时间
        public final static String EXPIRE_TIME = "43200";
        //token内容参数
        public final static String CLAIM_USER = "userId";
        public final static String CLAIM_PARENT = "parentId";
        //redis缓存前缀
        public static final String CACHE_PREFIX = "app-token:";
    }


    /**
     * 微信相关
     */
    public class WeChat {
        //公众号唯一id
        public final static String APP_ID = "wx3eee36fde6e41e26";
        //公众号密钥
        public final static String APP_SECRET = "1d7fa1c85a1dc3ed30af1d11def15c3f";
        //公众号访问令牌Key
        public final static String ACCESS_TOKEN_KEY = "access_token";
        //公众号获取登入二维码票据Key
        public final static String QRCODE_TICKET_KEY = "qrcode_ticket";

    }

    //用户默认密码
    public final static String INITIAL_PASSWORD = "Aa123456";

    /**
     * 角色
     */
    public enum Role {

        /**
         * 超级管理员
         */
        SUPER_ADMIN("SUPER_ADMIN"),
        /**
         * 管理员
         */
        ADMIN("ADMIN");

        private String code;

        Role(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

    }

}
