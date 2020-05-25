package com.recycler.adapter.base.listener;

import androidx.annotation.Nullable;

/**
 LoadMore需要设置的接口。使用java定义，以兼容java写法
 */
public interface LoadMoreListenerImp {

    void setOnLoadMoreListener(@Nullable OnLoadMoreListener listener);
}
