package com.example.appbase.demo;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbase.R;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import java.util.List;

public class BannerActivity extends AppCompatActivity {

    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        banner = ((Banner) findViewById(R.id.banner));
        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData());
        banner.setAdapter(adapter);
        banner.isAutoLoop(true);

        banner.setIndicator(new CircleIndicator(this));
        banner.setIndicatorGravity(IndicatorConfig.Direction.LEFT);
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
