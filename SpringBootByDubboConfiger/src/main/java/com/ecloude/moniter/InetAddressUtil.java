package com.ecloude.moniter;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class InetAddressUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(InetAddressUtil.class);


    /**
     * @Description: 获取主机地址
     * @return: String
     * @author: zongf
     * @time:  2019-03-11 14:47:53
     */
    public static String getHostIp(){

        String realIp = null;

        try {
            InetAddress address = InetAddress.getLocalHost();

            // 如果是回环网卡地址, 则获取ipv4 地址
            if (address.isLoopbackAddress()) {
                address = getInet4Address();
            }

            realIp = address.getHostAddress();

            //LOGGER.info("获取主机ip地址成功, 主机ip地址:{}", address);
            return address.getHostAddress();
        } catch (Exception e) {
            LOGGER.error("获取主机ip地址异常", e);
        }

        return realIp;
    }

    /**
     * @Description: 获取IPV4网络配置
     * @return:  InetAddress
     * @author: zongf
     * @time:  2019-03-11 14:09:17
     */
    private static InetAddress getInet4Address() throws SocketException {
        // 获取所有网卡信息
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) networkInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = (InetAddress) addresses.nextElement();
                if (ip instanceof Inet4Address) {
                    return ip;
                }
            }
        }
        return null;
    }
}
