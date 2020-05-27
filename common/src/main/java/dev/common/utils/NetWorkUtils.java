package dev.common.utils;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

import androidx.annotation.RequiresPermission;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetWorkUtils {
    /**
     * 网络类型 - 无连接
     */
    public static final int NETWORK_TYPE_NO_CONNECTION = -1231545315;

    public static final String NETWORK_TYPE_WIFI = "wifi";
    public static final String NETWORK_TYPE_3G = "eg";
    public static final String NETWORK_TYPE_2G = "2g";
    public static final String NETWORK_TYPE_WAP = "wap";
    public static final String NETWORK_TYPE_UNKNOWN = "unknown";
    public static final String NETWORK_TYPE_DISCONNECT = "disconnect";


    /**
     *
     */
    public static void getNetWordState() {
        //todo 获取普通网络的链接状态
    }


    /**
     * 获取Wifi的状态，需要ACCESS_WIFI_STATE权限
     *
     * @param context 上下文
     * @return 取值为WifiManager中的WIFI_STATE_ENABLED、WIFI_STATE_ENABLING、WIFI_STATE_DISABLED、WIFI_STATE_DISABLING、WIFI_STATE_UNKNOWN之一
     * @throws Exception 没有找到wifi设备
     */
    @RequiresPermission(value = Manifest.permission.ACCESS_WIFI_STATE)
    public static int getWifiState(Context context) throws Exception {
        WifiManager wifiManager = ((WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE));
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        } else {
            throw new Exception("wifi device not found!");
        }
    }

    /**
     * 判断Wifi是否打开，需要ACCESS_WIFI_STATE权限
     */
    @RequiresPermission(value = Manifest.permission.ACCESS_WIFI_STATE)
    public static boolean isWifiOpen(Context context) throws Exception {
        int wifiState = getWifiState(context);
        return wifiState == WifiManager.WIFI_STATE_ENABLED || wifiState == WifiManager.WIFI_STATE_ENABLING;
    }

    /**
     * 判断移动网络是否打开，需要ACCESS_NETWORK_STATE权限
     */
    @RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isMobileNetworkOpen(Context context) {
        return (((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).
                getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).isConnected();
    }


    /**
     * 获取本机IP地址
     */
    public static String getIpAddress() {
        try {
            NetworkInterface nerworkInterface;
            InetAddress inetAddress;
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                nerworkInterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = nerworkInterface.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return null;
        } catch (SocketException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}