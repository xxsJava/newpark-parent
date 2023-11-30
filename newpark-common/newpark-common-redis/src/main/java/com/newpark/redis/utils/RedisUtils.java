package com.newpark.redis.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author jack
 * @date 2023/3/18
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    /**
     * 默认过期时长，单位：秒
     */
    private final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    private final static long NOT_EXPIRE = -1;

    /**
     * 设置键值对，并设置TTL
     */
    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value, Date date) {
        valueOperations.set(key, toJson(value));
        if (date != null) {
            redisTemplate.expireAt(key, date);
        }
    }

    /**
     * 设置键值对，默认TTL
     */
    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 获取对象，并设置TTL
     */
    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    /**
     * 获取对象
     */
    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    /**
     * 获取value字符串，并设置TTL
     */
    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 获取value字符串
     */
    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * 删除key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 清除以key为前缀的所有ke
     */
    public String cleanKeys(String key) {
        Set<String> keys = redisTemplate.keys(key);
        for (String k : keys) {
            redisTemplate.delete(k);
        }
        return keys.toString();
    }

    /**
     * 获取以key为前缀的所有key
     */
    public Set<String> getKeys(String key) {
        return redisTemplate.keys(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 清除key
     */
    public String cleanKey(String key) {
        Set<String> keys = redisTemplate.keys(key);
        for (String k : keys) {
            redisTemplate.delete(k);
        }
        return keys.toString();
    }

    public long increment(String key) {
        return valueOperations.increment(key, 1L);
    }

    /**
     * 根据key获取value
     */
    public String hget(String key, Long id) {
        return (String) hashOperations.entries(key).get(String.valueOf(id));
    }

    /**
     * 像hash中储存kv
     *
     * @param id
     * @param name
     */
    public void hset(String key, Long id, String name) {
        hashOperations.put(key, String.valueOf(id), name);
    }

    /**
     * 压栈
     *
     * @param key
     * @param value
     */
    public Long leftPush(String key, Object value) {
        return listOperations.leftPush(key, toJson(value));
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T leftPop(String key, Class<T> clazz) {
        Object value = listOperations.leftPop(key);
        return value == null ? null : fromJson(String.valueOf(value), clazz);
    }


    /**
     * 入队
     *
     * @param key
     * @param value
     * @return
     */
    public Long rightPush(String key, Object value) {
        return listOperations.rightPush(key, toJson(value));
    }

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T rightPop(String key, Class<T> clazz) {
        Object value = listOperations.rightPop(key);
        return value == null ? null : fromJson(String.valueOf(value), clazz);
    }

    public <T> T rightPopThronghJson(String key, Class<T> clazz) {
        Object value = listOperations.rightPop(key);
        return value == null ? null : JSON.parseObject(String.valueOf(value), clazz);
    }

    /**
     * 栈/队列长
     *
     * @param key
     * @return
     */
    public Long listSize(String key) {
        return listOperations.size(key);
    }

    public Set<Object> getSetFromRedis(String key) {
        return setOperations.members(key);
    }
}
