package com.ecloude.moniter;

public class test {
    public static void main(String[] args) throws InterruptedException {

        // 创建监听客户端
        new ZkMonitorClient();

        // 线程休眠
        Thread.sleep(Integer.MAX_VALUE);
    }


}
class test01{
    public static void main(String[] args) throws InterruptedException {

        // 创建注册客户端
        new ZkRegisteClient();

        // 线程休眠
        Thread.sleep(Integer.MAX_VALUE);
    }

}
