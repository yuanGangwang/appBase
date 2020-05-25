package com.base.net.client;

import android.content.Context;
import android.text.TextUtils;


import com.base.net.BuildConfig;
import com.base.net.http.cookie.CookieJarImpl;
import com.base.net.http.cookie.store.PersistentCookieStore;
import com.base.net.http.interceptor.BaseInterceptor;
import com.base.net.http.interceptor.CacheInterceptor;
import com.base.net.http.interceptor.logging.Level;
import com.base.net.http.interceptor.logging.LoggingInterceptor;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    //超时时间
    private static final int DEFAULT_TIMEOUT = 20;
    //缓存时间

    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024;

    //服务端根路径
    public static String baseUrl = "https://www.oschina.net/";

    private static Context mContext = null;

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;
    private static RetrofitClient retrofitClient;

    private Cache cache = null;
    private File httpCacheDirectory;


    public static RetrofitClient getInstance(String baseUrl, Context context) {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient(baseUrl, context);
        }

        return retrofitClient;
    }

    private RetrofitClient(String baseUrl, Context context) {
        RetrofitClient.baseUrl = baseUrl;
        mContext = context;
    }

    public Retrofit initRetrofit() {

        if (TextUtils.isEmpty(baseUrl)) {
            throw new RuntimeException("服务器地址为空");
        }

        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "g_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, CACHE_TIMEOUT);
            }
        } catch (Exception e) {
            throw new RuntimeException("无法创建缓存文件");
        }

        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory();

        okHttpClient = new OkHttpClient.Builder()
//                .cookieJar(new CookieJarImpl(new PersistentCookieStore(mContext)))
//                .cache(cache)
//                .addInterceptor(new BaseInterceptor(headers))
//                .addInterceptor(new CacheInterceptor(mContext))
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new LoggingInterceptor
                        .Builder()//构建者模式
                        .loggable(BuildConfig.DEBUG) //是否开启日志打印
                        .setLevel(Level.BASIC) //打印的等级
                        .log(Platform.INFO) // 打印类型
                        .request("Request") // request的Tag
                        .response("Response")// Response的Tag
                        .build()
                )
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .build();
        return  new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();


    }

    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }


    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        return null;
    }
}
