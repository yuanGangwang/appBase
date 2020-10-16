package com.example.appbase.demo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbase.R;

import dev.common.utils.StatusBarUtils;
import dev.common.utils.ToastUtil;
import dev.common.weight.naviga.NavigaView;

public class NaviActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setTranslucentBar(getWindow());
        setContentView(R.layout.activity_navi);

        ((NavigaView) findViewById(R.id.backNavi)).setOnBackClickListener(new NavigaView.OnApplyNaviListener() {
            @Override
            public void onBackLayoutClick() {
                finish();
            }


            @Override
            public void onRightLayoutClick() {

            }
        });

        ((NavigaView) findViewById(R.id.iconBar)).setOnSearchClickListener(() -> ToastUtil.getInstance(NaviActivity.this).showCommon("1111"+(i++)));
        ((NavigaView) findViewById(R.id.iconBar)).setOnMenuClickListener(() -> ToastUtil.getInstance(NaviActivity.this).showCommon("2222"));


    }
    int i = 0;
}
