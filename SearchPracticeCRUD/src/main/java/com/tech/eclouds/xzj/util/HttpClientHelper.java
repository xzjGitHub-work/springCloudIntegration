package com.tech.eclouds.xzj.util;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @author SunBo
 * @version v1.0
 * @since 2018/10/10
 */
public class HttpClientHelper {

    private static String CHARSET_UTF8 = "UTF-8";
    private static final int MAX_TOTAL_CONNECTIONS = 200;
    private static final int MAX_CONNECTIONS_PER_ROUTE = 200;
    private static final int SOCKET_TIMEOUT = 60000; //读超时时间
    private static final int CONNECT_TIMEOUT = 60000;   //连接超时时间
    private static final int CONNECTION_REQUEST_TIMEOUT = 500; //从池中获取连接超时时间

    private CloseableHttpClient httpClient;

    private HttpClientHelper() {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(SSLContexts.createSystemDefault()))
                .build();
        //初始化PoolingHttpClientConnectionManager
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);// 整个连接池最大连接数
        cm.setDefaultMaxPerRoute(MAX_CONNECTIONS_PER_ROUTE);// 每路由最大连接数，默认值是2

        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .setSoReuseAddress(true)
                .build();
        // Configure the connection manager to use socket configuration either
        // by default or for a specific host.
        cm.setDefaultSocketConfig(socketConfig);

        //RequestConfig
        RequestConfig config = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();

        httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).build();
    }

    /**
     * 获取实例
     */
    public static HttpClientHelper getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 通过连接池获取HttpClient
     */
    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * 通过静态内部类实现单例
     */
    private static class SingletonHolder {
        static final HttpClientHelper instance = new HttpClientHelper();
    }


}
