package com.ecloude.echo.xzj.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        // 服务器监听端口号
        int port = 8080;
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        // NioEventLoopGroup是处理I/O操作的多线程事件循环
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // ServerBootstrap是一个用于设置服务器的引导类。
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class) // 使用NioServerSocketChannel类，用于实例化新的通道以接受传入连接
                    .localAddress(new InetSocketAddress(port)) // 设置服务器监听端口号
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler()); // 添加请求处理
                        }
                    });
            // 绑定到端口和启动服务器
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() +
                    " started and listening for connections on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
