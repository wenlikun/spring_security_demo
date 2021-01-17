package com.benbird.bencenter.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 获取本地IP
 */
@Slf4j
public class IPUtil {

    private static volatile String IP_ADDRESS = "";

    /**
     * 获取本地IP
     *
     * @return IP地址
     */
    public static String getLocalIP() {

        if (StringUtils.isNotBlank(IP_ADDRESS)) {
            return IP_ADDRESS;
        }
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip instanceof Inet4Address) {
                        IP_ADDRESS = ip.getHostAddress();
                        return IP_ADDRESS;
                    }
                }
            }
        } catch (SocketException e) {
            log.error("获取本机IP Socket异常:"+ e);
        } catch (Exception e) {
            log.error("获取本机IP异常:"+e);
        }
        return "127.0.0.1";
    }
}
