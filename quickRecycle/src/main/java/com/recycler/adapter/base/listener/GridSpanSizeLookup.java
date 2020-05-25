package com.recycler.adapter.base.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;


public interface GridSpanSizeLookup {

    int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int viewType, int position);
}
