package com.base.net.client;

import android.content.Context;
import android.text.TextUtils;

import com.base.net.http.cookie.CookieJarImpl;
import com.base.net.http.cookie.store.PersistentCookieStore;
import com.base.net.http.interceptor.logging.Level;
import com.base.net.http.interceptor.logging.LoggingInterceptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;

public class OkHttpFactory {

    boolean isOpenHttps;
    boolean isDebug;
    Context mContext;
    List<Interceptor> interceptors;
    OkHttpClient okHttpClient;
    long timeOut = 20;
    String cacheFilePath;


    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (isOpenHttps) {
//            builder.sslSocketFactory()
        }

        if (interceptors != null && interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        try {
            addCache(builder);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        builder.cookieJar(new CookieJarImpl(new PersistentCookieStore(mContext)))
                .addInterceptor(new LoggingInterceptor
                        .Builder()
                        .loggable(isDebug) //是否开启日志打印
                        .setLevel(Level.BASIC) //打印的等级
                        .log(Platform.INFO) // 打印类型
                        .request("Request") // request的Tag
                        .response("Response")// Response的Tag
                        .build()
                )
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS));

        okHttpClient = builder.build();

    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private void addCache(OkHttpClient.Builder builder) throws Throwable {

        if (mContext == null) {
            throw new Throwable("context is empty");
        }

        File httpCacheDirectory;
        if (!TextUtils.isEmpty(cacheFilePath)) {
            httpCacheDirectory = new File(cacheFilePath);
        } else {
            httpCacheDirectory = new File(mContext.getExternalCacheDir(), "okhttpcache");
        }
        Cache cache;
        try {
            cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            builder.cache(cache);

        } catch (Exception e) {
            throw new RuntimeException("无法创建缓存文件");
        }
    }

    public static final class OkHttpFactoryBuilder {
        boolean isOpenHttps;
        List<Interceptor> interceptors = new ArrayList<>();
        long timeOut = 20;
        String cacheFilePath;
        Context mContent;
        boolean isDebug;


        public OkHttpFactoryBuilder() {
        }


        public OkHttpFactoryBuilder withIsOpenHttps(boolean isOpenHttps) {
            this.isOpenHttps = isOpenHttps;
            return this;
        }

        public OkHttpFactoryBuilder withInterceptors(List<Interceptor> interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public OkHttpFactoryBuilder withInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        public OkHttpFactoryBuilder withContent(Context content) {
            this.mContent = content;
            return this;
        }

        public OkHttpFactoryBuilder withTimeOut(long timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public OkHttpFactoryBuilder withDebug(boolean isDebug) {
            this.isDebug = isDebug;
            return this;
        }

        public OkHttpFactoryBuilder withCacheFilePath(String cacheFilePath) {
            this.cacheFilePath = cacheFilePath;
            return this;
        }

        public OkHttpFactory build() {
            OkHttpFactory okHttpFactory = new OkHttpFactory();
            okHttpFactory.timeOut = this.timeOut;
            okHttpFactory.isOpenHttps = this.isOpenHttps;
            okHttpFactory.interceptors = this.interceptors;
            okHttpFactory.cacheFilePath = this.cacheFilePath;
            okHttpFactory.mContext = this.mContent;
            okHttpFactory.isDebug = this.isDebug;
            okHttpFactory.init();
            return okHttpFactory;
        }
    }
}
