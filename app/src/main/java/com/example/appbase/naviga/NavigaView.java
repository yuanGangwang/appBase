package com.example.appbase.naviga;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.dev.common.utils.QMUIStatusBarHelper;
import com.example.appbase.R;


public class NavigaView extends FrameLayout {

    OnApplyNaviListener listener;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private View view;
    private String titleTxt;
    private int titleColor;
    private int dividerColor;
    private int backImgResource;
    private boolean isShowLine;
    private int topStatusColor;
    private View divider;
    private TextView title;
    private TextView navBackText;
    private ImageView backImg;
    private boolean isCoverStatus;
    private View statusView;
    private String backTxt;
    private float titleSize;
    private int searchIcon;
    private int menuIcon;
    private ImageView navSearch;
    private ImageView navMenu;
    private int naviBackGroundColor;
    private int backTxtColor;

    public NavigaView(Context context) {
        this(context, null);
    }

    public NavigaView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }


    public NavigaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.naviga_layout, null, false);
        addView(view);
        initView(attrs, defStyleAttr);
    }

    private void initView(AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.naviga, defStyleAttr, 0);

            titleTxt = a.getString(R.styleable.naviga_naviTitle);
            titleColor = a.getColor(R.styleable.naviga_naviTitleColor, ContextCompat.getColor(mContext, R.color.colorBlack_17));
            titleSize = a.getDimension(R.styleable.naviga_naviTitleSize, 16);

            isShowLine = a.getBoolean(R.styleable.naviga_hasNaviDivider, false);
            dividerColor = a.getColor(R.styleable.naviga_naviDividerColor, 0);
            naviBackGroundColor = a.getColor(R.styleable.naviga_naviBackGround, ContextCompat.getColor(mContext, R.color.colorWhite));

            backImgResource = a.getResourceId(R.styleable.naviga_naviBackImg, R.mipmap.ic_back_gray);
            backTxt = a.getString(R.styleable.naviga_naviBackText);
            backTxtColor = a.getColor(R.styleable.naviga_naviBackColor, 0);

            isCoverStatus = a.getBoolean(R.styleable.naviga_isCoverStatus, false);

            topStatusColor = a.getColor(R.styleable.naviga_naviStatusColor, ContextCompat.getColor(mContext, R.color.colorWhite));

            searchIcon = a.getResourceId(R.styleable.naviga_searchIcon, 0);
            menuIcon = a.getResourceId(R.styleable.naviga_menuIcon, 0);

            divider = view.findViewById(R.id.navTitleBottomLine);
            title = (TextView) view.findViewById(R.id.navTitle);
            navBackText = (TextView) view.findViewById(R.id.backTxt);
            backImg = (ImageView) view.findViewById(R.id.backImg);
            navSearch = (ImageView) view.findViewById(R.id.navSearch);
            navMenu = (ImageView) view.findViewById(R.id.navMenu);


            view.findViewById(R.id.naviLayout).setBackgroundColor(naviBackGroundColor);

            if (searchIcon != 0) {
                navSearch.setImageResource(searchIcon);
            } else {
                navSearch.setVisibility(GONE);
            }

            if (menuIcon != 0) {
                navMenu.setImageResource(menuIcon);
            } else {
                navMenu.setVisibility(GONE);
            }

            if (isShowLine) {
                divider.setVisibility(View.VISIBLE);
                if (dividerColor != 0)
                    divider.setBackgroundColor(dividerColor);
            } else {
                divider.setVisibility(View.GONE);
            }

            title.setText(titleTxt);
            title.setTextColor(titleColor);
            title.setTextSize(titleSize);
            if (listener != null) {
                listener.onApplyTitle(title);
            }

            if (TextUtils.isEmpty(backTxt)) {
                navBackText.setVisibility(GONE);
                backImg.setVisibility(VISIBLE);
                backImg.setImageResource(backImgResource);
            } else {
                backImg.setVisibility(GONE);
                navBackText.setVisibility(VISIBLE);
                navBackText.setText(backTxt);
                if (backTxtColor != 0) {
                    navBackText.setTextColor(backTxtColor);
                }
            }

            (view.findViewById(R.id.backLayout)).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onBackImgClick();
                    }
                }
            });

            statusView = view.findViewById(R.id.statusBar);

            if (isCoverStatus) {
                ViewGroup.LayoutParams layoutParams = statusView.getLayoutParams();
                layoutParams.height = QMUIStatusBarHelper.getStatusbarHeight(mContext);
                statusView.setLayoutParams(layoutParams);
                view.findViewById(R.id.statusBar).setBackgroundColor(topStatusColor);
            } else {
                statusView.setVisibility(GONE);
            }

            a.recycle();
        }
    }

    public TextView getTitle() {
        return title;
    }

    public void addMenu() {

    }

    public void setOnBackClickListener(OnApplyNaviListener onApplyNaviListener) {
        this.listener = onApplyNaviListener;
    }


    public interface OnApplyNaviListener {
        void onBackImgClick();

        void onApplyTitle(TextView title);
    }

    OnSearchClickListener onSearchClickListener;
    OnMenuClickListener onMenuClickListener;

    public void setOnSearchClickListener(OnSearchClickListener onSearchClickListener) {
        this.onSearchClickListener = onSearchClickListener;
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    public interface OnSearchClickListener {
        void searchClick();
    }

    public interface OnMenuClickListener {
        void menuClick();
    }

}

