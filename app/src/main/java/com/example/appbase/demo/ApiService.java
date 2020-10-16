package com.example.appbase.demo;

import com.base.net.http.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/web/systemconf/recycle/address")
    Observable<BaseResponse<String>> getSendOlderPhoneAddress();

    /**
     *
     */
    @POST("/web/product/list")
    Observable<BaseResponse<HomeItemBean>> getHomeMoreItem();

}
