package com.example.appbase.demo;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbase.R;
import com.banner.weight.Banner;
import com.banner.adapter.BannerAdapter;
import com.banner.config.IndicatorConfig;
import com.banner.indicator.CircleIndicator;
import com.banner.listener.OnBannerListener;
import com.banner.util.BannerUtils;

import java.util.List;

public class BannerActivity extends AppCompatActivity {

    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        banner = ((Banner) findViewById(R.id.banner));
        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData());
        banner.setAdapter(adapter)
                .isAutoLoop(true)
                .setDelayTime(3000)
                .setIndicator(new CircleIndicator(this))
                .setIndicatorGravity(IndicatorConfig.Direction.LEFT)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        Toast.makeText(BannerActivity.this, position + "", Toast.LENGTH_SHORT).show();
                    }
                })
        ;
        banner.setBannerRound(BannerUtils.dp2px(5));
    }


    public class ImageAdapter extends BannerAdapter<DataBean, ImageHolder> {

        public ImageAdapter(List<DataBean> mDatas) {
            //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
            super(mDatas);
        }

        //更新数据
        public void updateData(List<DataBean> data) {
            //这里的代码自己发挥，比如如下的写法等等
            mDatas.clear();
            mDatas.addAll(data);
            notifyDataSetChanged();
        }


        //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        @Override
        public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new ImageHolder(imageView);
        }

        @Override
        public void onBindView(ImageHolder holder, DataBean data, int position, int size) {
            holder.imageView.setImageResource(data.imageRes);
        }
    }

}
