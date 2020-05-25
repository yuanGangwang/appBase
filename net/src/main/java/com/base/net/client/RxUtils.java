package com.base.net.client;


import android.net.ParseException;

import com.base.net.http.BaseResponse;
import com.base.net.http.ExceptionHandle;
import com.base.net.http.ResponseThrowable;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RxUtils {
    public static <T> Single<T> wrapRestCall(final Observable<BaseResponse<T>> call) {
        return call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new ObservableTransformer<BaseResponse<T>, BaseResponse<T>>() {
                    @Override
                    public ObservableSource<BaseResponse<T>> apply(Observable<BaseResponse<T>> upstream) {
                        return upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends BaseResponse<T>>>() {
                            @Override
                            public ObservableSource<? extends BaseResponse<T>> apply(Throwable throwable) throws Exception {
                                return Observable.error(ExceptionHandle.handleException(throwable));
                            }
                        });
                    }
                })
                .flatMap(new Function<BaseResponse<T>, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                        if (tBaseResponse.isOk()) {
                            return Observable.just(tBaseResponse.getRetData());
                        } else {
                            return Observable.error(new Exception(tBaseResponse.getRetMsg()));
                        }
                    }
                }, new Function<Throwable, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                }, new Callable<ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> call() {
                        return Observable.empty();
                    }
                }).singleOrError();
    }


}
