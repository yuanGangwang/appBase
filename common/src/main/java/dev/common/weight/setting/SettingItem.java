package dev.common.weight.setting;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dev.common.R;
import dev.common.utils.AppUtils;
import dev.common.utils.SPUtil;

public class SettingItem extends FrameLayout {
    private Context mContext;
    private View view;
    private boolean hasDivider;
    private int dividerColor;
    private String attributeValue;
    private int iconRes;
    private int tipsRes;
    private int tipsResource;
    private View line;
    private ImageView icon;
    private ImageView settingNextImg;
    private ImageView settingTipsImg;
    private TextView settTitle;
    private TextView settDes;
    private TextView settingRightTxt;
    private Switch settingSwitch;
    private String swichKey;
    private int switchThume;
    private int switchTrack;
    private int itemType;
    private boolean switchDefault;


    public SettingItem(@NonNull Context context) {
        this(context, null);
    }

    public SettingItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SettingItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        applySettingView();
        initView(attrs, defStyleAttr);
    }

    private void applySettingView() {
        view = LayoutInflater.from(mContext).inflate(R.layout.setting_item_layout, null, false);
        line = view.findViewById(R.id.line);
        icon = view.findViewById(R.id.settingIcon);
        settTitle = view.findViewById(R.id.settTitle);
        settDes = view.findViewById(R.id.settDes);
        settingTipsImg = view.findViewById(R.id.settingTipsImg);
        settingRightTxt = view.findViewById(R.id.settingRightTxt);
        settingNextImg = view.findViewById(R.id.settingNextImg);
        settingSwitch = view.findViewById(R.id.settingSwitch);

        addView(view);
    }

    private void initView(AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.settingItem, defStyleAttr, 0);

            //是否有分割线
            hasDivider = a.getBoolean(R.styleable.settingItem_hasDivider, true);
            //分割线颜色
            dividerColor = a.getColor(R.styleable.settingItem_dividerColor, 0);
            //左侧图片
            iconRes = a.getResourceId(R.styleable.settingItem_iconRes, 0);
            //标题右侧图片
            tipsRes = a.getResourceId(R.styleable.settingItem_tipsImgRes, 0);
            //设置标题

            String settingName = a.getString(R.styleable.settingItem_titleText);
            int titleColor = a.getColor(R.styleable.settingItem_titleColor, 0);
            float titleSize = a.getDimension(R.styleable.settingItem_titleSize, 16);

            //描述标题
            String desText = a.getString(R.styleable.settingItem_desText);
            int desColor = a.getColor(R.styleable.settingItem_desColor, 0);
            float desSize = a.getDimension(R.styleable.settingItem_desSize, 12);

            String nextText = a.getString(R.styleable.settingItem_nextText);
            int nextRes = a.getResourceId(R.styleable.settingItem_nextImg, 0);

            swichKey = a.getString(R.styleable.settingItem_switchKey);
            switchThume = a.getResourceId(R.styleable.settingItem_switchThumb, 0);
            switchTrack = a.getResourceId(R.styleable.settingItem_switchTrack, 0);
            switchDefault = a.getBoolean(R.styleable.settingItem_switchDefault, false);

            itemType = a.getInt(R.styleable.settingItem_type, -1);

            if (switchThume != 0 || switchTrack != 0 && TextUtils.isEmpty(swichKey)) {
                Log.e("Setting", "switchKey is null,please check xml");
            }

            if (iconRes == 0) {
                icon.setVisibility(GONE);
            } else {
                icon.setVisibility(VISIBLE);
                icon.setImageResource(iconRes);
            }

            settTitle.setText(settingName);
            if (titleColor != 0)
                settTitle.setTextColor(titleColor);
            settTitle.setTextSize(titleSize);

            if (TextUtils.isEmpty(desText)) {
                settDes.setVisibility(GONE);
            } else {
                if (titleColor != 0)
                    settDes.setTextColor(desColor);
                settDes.setTextSize(desSize);
                settDes.setVisibility(VISIBLE);
                settDes.setText(desText);
            }

            if (TextUtils.isEmpty(nextText)) {
                settingRightTxt.setVisibility(GONE);
            } else {
                settingRightTxt.setText(nextText);
            }

            if (tipsRes == 0) {
                settingTipsImg.setVisibility(GONE);
            } else {
                settingTipsImg.setVisibility(VISIBLE);
                settingTipsImg.setImageResource(tipsRes);
            }

            if (hasDivider) {
                line.setVisibility(VISIBLE);
                if (dividerColor != 0) {
                    line.setBackgroundColor(dividerColor);
                }
            } else {
                line.setVisibility(INVISIBLE);

            }

            if (nextRes != 0) {
                settingNextImg.setImageResource(nextRes);
            } else {
                settingNextImg.setVisibility(GONE);
            }

            generateViewType(itemType);

            a.recycle();
        }
    }

    private void generateViewType(int itemType) {
        switch (itemType) {
            case 0:
                makeVersionView();
                break;
            case 1:
                makeSwitchView();
                break;
            default:
                settingSwitch.setVisibility(GONE);
        }
    }

    private void makeSwitchView() {

        settingSwitch.setVisibility(VISIBLE);
        settingNextImg.setVisibility(GONE);

        if (switchThume != 0)
            settingSwitch.setThumbResource(switchThume);
        if (switchTrack != 0)
            settingSwitch.setTrackResource(switchTrack);

        if (switchDefault) {
            if (SPUtil.getBoolean(swichKey) != switchDefault) {
                settingSwitch.setChecked(!switchDefault);
            } else {
                settingSwitch.setChecked(switchDefault);
            }
        } else {
            if (SPUtil.getBooleanDefaultFalse(swichKey) != switchDefault) {
                settingSwitch.setChecked(!switchDefault);
            } else {
                settingSwitch.setChecked(switchDefault);
            }
        }

        settingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPUtil.saveboolean(swichKey, isChecked);
            }
        });
    }

    public void setSettingRightTxt(String rightTxt) {
        if (settingRightTxt != null) {
            settingRightTxt.setText(rightTxt);
        }
    }

    private void makeVersionView() {

        settingSwitch.setVisibility(GONE);
        settingRightTxt.setVisibility(VISIBLE);

        String verName = AppUtils.getVerName(getContext());
        settingRightTxt.setText(verName);
    }

    public static class SettingOptions {
        public static boolean getSettingOptions(String key, boolean isDefaultFalse) {
            if (isDefaultFalse) {
                return SPUtil.getBoolean(key);
            } else {
                return SPUtil.getBooleanDefaultFalse(key);
            }
        }
    }


}
