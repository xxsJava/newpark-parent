package com.newpark.redis.utils;

/**
 * Redis所有Keys
 *
 * @author jack
 * @date 2023/3/18
 */
public class RedisKeys {

    /**
     * 公司key
     */
    public static class Prefix {
        /**
         * 不同公司生成不同key前缀
         */
        private static String buildKey(String type, Object key) {
            return "newpark:" + type + ":" + key;
        }
    }

    /**
     * 配置key
     */
    public static class Config {
        public static String key(String key) {
            return Prefix.buildKey("sys:config", key);
        }
    }

    /**
     * 文件分片配置key
     */
    public static class ShardingConfig {
        public static String key(String key) {
            return Prefix.buildKey("file:sharding", key);
        }
        public static String lockKey(String key) {
            return Prefix.buildKey("file:shardingLock", key);
        }
    }

    /**
     * 数据字典key
     */
    public static class Dict {
        public static String key(String key) {
            return Prefix.buildKey("sys:dict", key);
        }
    }

    /**
     * 数据字典目录集合
     */
    public static class DictDir {
        public static String key() {
            return Prefix.buildKey("sys:dictDir", "list");
        }
    }


    /**
     * 用户key
     */
    public static class User {
        /**
         * 用户个人信息
         */
        public static String info(Long userId) {
            return "sys:user:info:" + userId;
        }

        /**
         * 用户认证前缀
         */
        public static String aut(String key) {
            return "sys:user:aut:" + key;
        }

        /**
         * 获取用户token
         */
        public static String token(Long userId) {
            return Prefix.buildKey(aut("token"), userId);
        }

        /**
         * 获取用户 登录信息
         *
         * @param token token名称
         * @return
         */
        public static String userLoginTokenKey(String token) {
            return "session:" + token;
        }
    }


}
