package com.newpark.core.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程池工具类,简单单例模式修改为双检锁方式
 *
 * @author : jack
 * @date : 2023/3/14
 */
public class ThreadPoolUtil {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);
    private final static String PROFILE_COREPOOLSIZE = "threadPool.corePoolSize";
    private final static String PROFILE_MAXIMUMPOOLSIZE = "threadPool.maximumPoolSize";
    private final static String PROFILE_KEEPACTIVETIME = "threadPool.keepActiveTime";
    private final static String PROFILE_CAPACITY = "threadPool.capacity";
    /**
     * 默认线程池大小
     */
    private final static String COREPOOLSIZE_DEFAULT = "10";
    /**
     * 默认最大线程池
     */
    private final static String MAXIMUMPOOLSIZE_DEFAULT = "50";
    /**
     * 默认线程池空闲时间
     */
    private final static String KEEPACTIVETIME_DEFAULT = "200";
    /**
     * 默认线程池最大队列
     */
    private final static String CAPACITY_DEFAULT = "2048";
    /**
     * 核心池大小
     */
    private static int corePoolSize;
    /**
     * 线程池最大能接受多少线程
     */
    private static int maximumPoolSize;
    /**
     * 当前线程数大于corePoolSize、小于maximumPoolSize时，超出corePoolSize的线程数的生命周期
     */
    private static long keepActiveTime;
    /**
     * 线程池最大队列
     */
    private static int capacity;
    /**
     * 工具类实例，单例模式
     */
    private volatile static ThreadPoolUtil instance;
    /**
     * 线程池对象，单例
     */
    private static ExecutorService singleThreadPool;

    public static synchronized ThreadPoolUtil getInstance() {
        if (instance == null) {
            synchronized (ThreadPoolUtil.class) {
                if (instance == null) {
                    //创建工具类实例
                    instance = new ThreadPoolUtil();
                    corePoolSize = Integer.valueOf(COREPOOLSIZE_DEFAULT);
                    maximumPoolSize = Integer.valueOf(MAXIMUMPOOLSIZE_DEFAULT);
                    keepActiveTime = Long.valueOf(KEEPACTIVETIME_DEFAULT);
                    capacity = Integer.valueOf(CAPACITY_DEFAULT);
                }
            }
        }
        return instance;
    }

    public void execute(Runnable comm) {
        executorService().execute(comm);
    }

    public ExecutorService executorService() {
        //创建线程池对象
        if (singleThreadPool == null) {
            //设置时间单位，秒
            TimeUnit timeUnit = TimeUnit.SECONDS;
            singleThreadPool = getExecutorService(corePoolSize, maximumPoolSize, keepActiveTime, timeUnit, capacity);
            logger.debug("线程池创建成功[核心池大小:" + corePoolSize + ";最大线程：" + maximumPoolSize + ";生命周期:" + keepActiveTime + "]");
        }
        return singleThreadPool;
    }

    /**
     * 创建线程池对象PROFILE_NAME
     */
    private static ExecutorService getExecutorService(int corePoolSize, int maximumPoolSize, long keepActiveTime, TimeUnit timeUnit, int capacity) {
        //自定义线程名称
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("CRM-pool-%d").build();
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepActiveTime, timeUnit,
                new LinkedBlockingQueue<>(capacity), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }
}
