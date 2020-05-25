package com.example.appbase.setting;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.appbase.R;

public class SettingGroup extends LinearLayout {
    View topView;
    String title;
    int titleColor;
    float titleSize;
    private Context mcontext;
    private boolean collapseEnable;
    private boolean groupShowEnable;
    private int iconRes;
    private int upRes;
    private int downRes;
    private ImageView collaseImg;
    private ImageView groupImg;
    private TextView groupName;
    private CollapseState collapseState = CollapseState.down;
    private CollapseStateChange mChangeListener;

    public SettingGroup(Context context) {
        this(context, null);
    }

    public SettingGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SettingGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mcontext = context;
        initAttrs(attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        if (groupShowEnable) {
            addTopView();
        }
    }

    private void initAttrs(AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray typedArray = mcontext.obtainStyledAttributes(attrs, R.styleable.settingGroup, defStyleAttr, 0);
            collapseEnable = typedArray.getBoolean(R.styleable.settingGroup_collapseEnable, false);
            groupShowEnable = typedArray.getBoolean(R.styleable.settingGroup_groupShowEnable, false);
            iconRes = typedArray.getResourceId(R.styleable.settingGroup_iconResource, 0);
            upRes = typedArray.getResourceId(R.styleable.settingGroup_collapseUpRes, 0);
            downRes = typedArray.getResourceId(R.styleable.settingGroup_collapseDownRes, 0);

            title = typedArray.getString(R.styleable.settingGroup_groupText);
            titleColor = typedArray.getColor(R.styleable.settingGroup_groupTxtColor, 0);
            titleSize = typedArray.getDimension(R.styleable.settingGroup_groupTxtSize, 16);

        }
    }

    private void addTopView() {
        topView = LayoutInflater.from(mcontext).inflate(R.layout.setting_group_top, this, false);
        groupImg = topView.findViewById(R.id.groupImg);
        groupName = topView.findViewById(R.id.groupName);
        collaseImg = topView.findViewById(R.id.collaseImg);

        groupName.setText(title);
        groupName.setTextSize(titleSize);
        if (titleColor != 0) {
            groupName.setTextColor(titleColor);
        }

        if (iconRes != 0) {
            groupImg.setImageResource(iconRes);
        }

        applyCollapseState();

        topView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                collapse();
                applyCollapseState();
                if (mChangeListener != null)
                    mChangeListener.collapseStateChange(collapseState);
            }
        });

        addView(topView);
    }

    private void collapse() {
        int childCount = getChildCount();
        for (int i = 1; i < childCount; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                AutoTransition autoTransition = new AutoTransition();
                autoTransition.setDuration(150);
                TransitionManager.beginDelayedTransition((ViewGroup) getRootView(), autoTransition);
            }
            View view = getChildAt(i);
            if (view.getVisibility() == VISIBLE) {
                view.setVisibility(GONE);
                collapseState = CollapseState.up;
            } else {
                view.setVisibility(VISIBLE);
                collapseState = CollapseState.down;
            }
        }
    }


    private void applyCollapseState() {
        if (collapseState == CollapseState.down) {
            if (downRes != 0) {
                collaseImg.setImageResource(downRes);
            }
        }
        if (collapseState == CollapseState.up) {
            if (upRes != 0) {
                collaseImg.setImageResource(upRes);
            }
        }
    }

    public void setChangeListener(CollapseStateChange mChangeListener) {
        this.mChangeListener = mChangeListener;
    }

   public enum CollapseState {
        up, down
    }

   public interface CollapseStateChange {
        void collapseStateChange(CollapseState collapseState);
    }
}
