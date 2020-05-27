package com.banner.indicator;

import android.view.View;

import androidx.annotation.NonNull;

import com.banner.config.IndicatorConfig;
import com.banner.listener.OnPageChangeListener;

public interface Indicator extends OnPageChangeListener {
    @NonNull
    View getIndicatorView();

    IndicatorConfig getIndicatorConfig();

    void onPageChanged(int count, int currentPosition);

}
