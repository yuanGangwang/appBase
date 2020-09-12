package com.base.net.client;


import com.base.net.exception.IExceptionHandle;
import com.base.net.http.BaseResponse;
import com.base.net.exception.ExceptionHandle;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {
    public static <T> Single<T> wrapRestCall(final Observable<BaseResponse<T>> call, final IExceptionHandle exceptionHandle) {
        return call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new ObservableTransformer<BaseResponse<T>, BaseResponse<T>>() {
                    @Override
                    public ObservableSource<BaseResponse<T>> apply(Observable<BaseResponse<T>> upstream) {
                        return upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends BaseResponse<T>>>() {
                            @Override
                            public ObservableSource<? extends BaseResponse<T>> apply(Throwable throwable) throws Exception {
                                IExceptionHandle handle;
                                if (exceptionHandle==null){
                                    handle = new ExceptionHandle();
                                }else {
                                    handle = exceptionHandle;
                                }
                                return Observable.error(handle.handleException(throwable));
                            }
                        });
                    }
                })
                .flatMap(new Function<BaseResponse<T>, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                        if (tBaseResponse.isOk()) {
                            if (tBaseResponse.getData() == null){
                                tBaseResponse.setData((T) new String("success"));
                            }
                            return Observable.just(tBaseResponse.getData());
                        } else {
                            return Observable.error(new Exception(tBaseResponse.getMsg()));
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
