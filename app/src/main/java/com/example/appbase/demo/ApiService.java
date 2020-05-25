package com.example.appbase.demo;

import com.base.net.http.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/web/systemconf/recycle/address")
    Observable<BaseResponse<String>> getSendOlderPhoneAddress();

}
