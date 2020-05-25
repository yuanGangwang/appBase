package com.example.appbase.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.appbase.R;

/**
 * Created by niluogege on 2019/5/16.
 *
 * 可限制最大 最小 宽高的 FrameLayout
 */
public class LimitHeightLayout extends FrameLayout {

    public static final float DEF_VALUE = -1f;

    private Context mContext;

    private float mMaxHeight = -1f;
    private float mMaxWidth = -1f;

    public LimitHeightLayout(Context context) {
        this(context, null, 0);
    }

    public LimitHeightLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LimitHeightLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.LimitLayout);
        mMaxHeight = ta.getDimension(R.styleable.LimitLayout_ml_maxheight, DEF_VALUE);
        mMaxWidth = ta.getDimension(R.styleable.LimitLayout_ml_maxWidth, DEF_VALUE);

        ta.recycle();
    }

    public void setMaxHeight(float maxHeight) {
        mMaxHeight = maxHeight;
    }

    public void setMaxWidth(float maxWidth) {
        mMaxWidth = maxWidth;
    }


    public float getMaxHeight() {
        return mMaxHeight;
    }

    public float getMaxWidth() {
        return mMaxWidth;
    }

    public void clearMaxWidthFlag() {
        mMaxWidth = -1f;
    }

    public void clearMaxHeightFlag() {
        mMaxHeight = -1f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 拿到原来宽度，高度的  size
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);


        widthSize = getWidth(widthSize);
        heightSize = getHeight(heightSize);

        int maxHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode);
        int maxWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, widthMode);

        super.onMeasure(maxWidthMeasureSpec, maxHeightMeasureSpec);

    }


    private int getWidth(int widthSize) {
        int minimumWidth = getMinimumWidth();

        if (mMaxWidth == DEF_VALUE) {//没有设置最大高度

            if (minimumWidth >= widthSize) {
                return minimumWidth;
            } else {
                return widthSize;
            }
        } else {
            if (widthSize >= mMaxWidth) {
                return (int) mMaxWidth;
            } else {
                if (minimumWidth >= widthSize) {
                    return minimumWidth;
                } else {
                    return widthSize;
                }
            }
        }
    }


    private int getHeight(int heightSize) {
        int minimumHeight = getMinimumHeight();

        if (mMaxHeight == DEF_VALUE) {//没有设置最大高度

            if (minimumHeight >= heightSize) {
                return minimumHeight;
            } else {
                return heightSize;
            }
        } else {
            if (heightSize >= mMaxHeight) {
                return (int) mMaxHeight;
            } else {
                if (minimumHeight >= heightSize) {
                    return minimumHeight;
                } else {
                    return heightSize;
                }
            }
        }
    }
}