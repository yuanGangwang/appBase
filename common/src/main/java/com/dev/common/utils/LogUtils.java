package com.dev.common.utils;

import android.util.Log;

import com.dev.common.BuildConfig;


/**
 * 日志封装
 */

public class LogUtils {

    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数

    public static boolean show = true;


    private static boolean isDebuggable() {
        return (BuildConfig.DEBUG || show);
    }

    private static boolean isShowMethodNames = false;


    public LogUtils(boolean show, boolean isShowMethodNames) {
        LogUtils.show = show;
        LogUtils.isShowMethodNames = isShowMethodNames;
    }

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        if (isShowMethodNames) {
            buffer.append(className).append(":").append(lineNumber).append(")->").append(methodName).append("-->");
        }
        buffer.append(log);
        return buffer.toString();
    }

    /**
     * 获取文件名、方法名、所在行数
     *
     * @param sElements
     */
    private static void getMethodNames(StackTraceElement[] sElements) {
        if (isShowMethodNames) {
            className = sElements[1].getFileName();
            methodName = sElements[1].getMethodName();
            lineNumber = sElements[1].getLineNumber();
        } else {
            className = "";
            methodName = "";
            lineNumber = 0;
        }

    }

    public static void e(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }

    public static void i(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }
}