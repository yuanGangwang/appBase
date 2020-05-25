package com.example.appbase.demo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.common.utils.StatusBarUtils;
import com.dev.common.utils.ToastUtil;
import com.example.appbase.R;
import com.example.appbase.naviga.NavigaView;

public class NaviActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setTranslucentBar(getWindow());
        setContentView(R.layout.activity_navi);

        ((NavigaView) findViewById(R.id.backNavi)).setOnBackClickListener(new NavigaView.OnApplyNaviListener() {
            @Override
            public void onBackImgClick() {
                finish();
            }

            @Override
            public void onApplyTitle(TextView title) {

            }
        });


        ((NavigaView) findViewById(R.id.iconBar)).setOnSearchClickListener(new NavigaView.OnSearchClickListener() {
            @Override
            public void searchClick() {
                ToastUtil.getInstance(NaviActivity.this).showCommon("1111");
            }
        });
        ((NavigaView) findViewById(R.id.iconBar)).setOnMenuClickListener(new NavigaView.OnMenuClickListener() {
            @Override
            public void menuClick() {
                ToastUtil.getInstance(NaviActivity.this).showCommon("2222");
            }
        });


    }
}
