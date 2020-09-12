package com.base.net.client;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    static RetrofitClient retrofitClients = null;
    Retrofit retrofit;

    public static RetrofitClient getInstance() {
        if (retrofitClients == null) {
            retrofitClients = new RetrofitClient();
        }

        return retrofitClients;
    }

    public RetrofitClient init(String baseUrl, OkHttpClient okHttpClient) throws Exception {
        if (TextUtils.isEmpty(baseUrl) || okHttpClient == null) {
            throw new Exception("Check your params");
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit = builder.build();
        return this;
    }


    public <T> T createApiServer(Class<T> apiServer) {
        if (retrofit != null) {
            return retrofit.create(apiServer);
        }
        return null;
    }

}
