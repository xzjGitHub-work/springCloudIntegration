package com.ecloude.filter;

import com.ecloude.intercepter.InterceptorByDubbo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.UUID;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/4/27 16:33
 */
@Slf4j
//@Activate(group = {CommonConstants.CONSUMER,Constants.TOKEN_KEY},order = 10000)
@Activate(group = {CommonConstants.CONSUMER,CommonConstants.PROVIDER},order = 10000)
public class TestDubboFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result ;
        long start = System.currentTimeMillis();
        if (RpcContext.getContext().isConsumerSide()){
            result = invoker.invoke(invocation);
            log.info("dubbo Filter Log consumer log 打印 耗时[ {} ] LogId [ {} ]",System.currentTimeMillis() -start, InterceptorByDubbo.threadLocal.get());
        }else if (RpcContext.getContext().isProviderSide()){
            result = invoker.invoke(invocation);
            log.info("dubbo Filter Log provider log 打印 耗时[ {} ] LogId [ {} ]",System.currentTimeMillis() -start, InterceptorByDubbo.threadLocal.get());
        }else {
            log.info("日志打印了");
            result = invoker.invoke(invocation);
        }
        return result;
    }
}
