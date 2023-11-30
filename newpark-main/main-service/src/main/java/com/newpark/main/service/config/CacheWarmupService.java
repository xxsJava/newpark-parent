package com.newpark.main.service.config;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.yulichang.query.MPJQueryWrapper;
import com.newpark.base.enums.PrefixMsgEnum;
import com.newpark.core.utils.StringUtils;
import com.newpark.main.service.entity.PostsLabel;
import com.newpark.main.service.posts.mapper.PostsLabelMapper;
import com.newpark.redis.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author xxs18
 * @Description 预热加载服务
 * @Date 2023/11/24 15:26
 **/
@Slf4j
@Service
public class CacheWarmupService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private PostsLabelMapper postsLabelMapper;

    public void warmup() {
        if(lookup()){
            log.info("从数据库预加载数据至redis");
            fixedData();
        }
        log.info("redis预热加载完毕");
    }

    /**
     * 添加分布式锁避免集群重复加载
     * @return
     */
    private boolean lookup(){
        //是否存在锁
        if(StringUtils.isNotEmpty(redisUtils.get("redis-preheat-look"))){
            //存在锁已加载过
            return false;
        }
        //不存在锁进行预加载 添加锁
        redisUtils.set("redis-preheat-look",new Date().getTime(), PrefixMsgEnum.REDIS_TIME_DAY_60.getTime() * 5L);
        return true;
    }

    /**
     * 固定数据预加载
     */
    private void fixedData(){
        //帖子标签预加载
        MPJQueryWrapper<PostsLabel> postsLabs = new MPJQueryWrapper<>();
        postsLabs.select("t1.posts_id,t.lab_id as lableId,t.lab_text as lableText")
                .rightJoin("posts_label_der t1 ON t.lab_id = t1.lab_id");
        redisUtils.set("postsComLab", postsLabelMapper.selectList(postsLabs), PrefixMsgEnum.REDIS_TIME_7_DAY.getTime());
        log.info("---------》1、标签已预热加载");

    }
}
