package com.tech.eclouds.xzj.util;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author SunBo
 * @version v1.0
 * @since 2018/11/14
 */
public class ThreadPoolUtil {

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static final ThreadPoolUtil instance = new ThreadPoolUtil();

    public static ThreadPoolUtil getInstance() {
        return instance;
    }

    private ThreadPoolUtil() {
        this.threadPoolTaskExecutor = getExecutor();
    }

    private synchronized ThreadPoolTaskExecutor getExecutor() {

        if (threadPoolTaskExecutor != null) {
            return threadPoolTaskExecutor;
        }
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(8);
        taskExecutor.setMaxPoolSize(16);
        taskExecutor.setQueueCapacity(2000);
        taskExecutor.setKeepAliveSeconds(20);
//        taskExecutor.setAllowCoreThreadTimeOut();
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        return threadPoolTaskExecutor;
    }

}
