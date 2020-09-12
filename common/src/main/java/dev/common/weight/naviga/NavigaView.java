package dev.common.weight.naviga;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import dev.common.R;
import dev.common.utils.QMUIStatusBarHelper;


public class NavigaView extends FrameLayout {

    OnApplyNaviListener listener;
    OnSearchClickListener onSearchClickListener;
    OnMenuClickListener onMenuClickListener;
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
    private TextView navRightTitle;
    private String rightTitle;


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
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.NavigaView, defStyleAttr, 0);

            titleTxt = a.getString(R.styleable.NavigaView_naviTitle);
            titleColor = a.getColor(R.styleable.NavigaView_naviTitleColor, ContextCompat.getColor(mContext, R.color.colorBlack_17));
            titleSize = a.getDimension(R.styleable.NavigaView_naviTitleSize, 18);

            isShowLine = a.getBoolean(R.styleable.NavigaView_hasNaviDivider, false);
            dividerColor = a.getColor(R.styleable.NavigaView_naviDividerColor, 0);
            naviBackGroundColor = a.getColor(R.styleable.NavigaView_naviBackGround, ContextCompat.getColor(mContext, R.color.colorWhite));

            backImgResource = a.getResourceId(R.styleable.NavigaView_naviBackImg, R.mipmap.ic_back_gray);
            backTxt = a.getString(R.styleable.NavigaView_naviBackText);
            backTxtColor = a.getColor(R.styleable.NavigaView_naviBackColor, 0);

            isCoverStatus = a.getBoolean(R.styleable.NavigaView_isCoverStatus, false);

            topStatusColor = a.getColor(R.styleable.NavigaView_naviStatusColor, ContextCompat.getColor(mContext, R.color.colorWhite));

            searchIcon = a.getResourceId(R.styleable.NavigaView_searchIcon, 0);
            menuIcon = a.getResourceId(R.styleable.NavigaView_menuIcon, 0);

            divider = view.findViewById(R.id.navTitleBottomLine);
            title = (TextView) view.findViewById(R.id.navTitle);
            navBackText = (TextView) view.findViewById(R.id.backTxt);
            navRightTitle = (TextView) view.findViewById(R.id.navRightTitle);
            backImg = (ImageView) view.findViewById(R.id.backImg);
            navSearch = (ImageView) view.findViewById(R.id.navSearch);
            navMenu = (ImageView) view.findViewById(R.id.navMenu);


            view.findViewById(R.id.naviLayout).setBackgroundColor(naviBackGroundColor);

            if (!TextUtils.isEmpty(a.getString(R.styleable.NavigaView_naviFontFamily))) {
                title.setTypeface(Typeface.create(a.getString(R.styleable.NavigaView_naviFontFamily), Typeface.NORMAL));
            }

            rightTitle = a.getString(R.styleable.NavigaView_naviRightTitle);

            if (!TextUtils.isEmpty(rightTitle)) {
                navRightTitle.setText(rightTitle);
                navRightTitle.setTextColor(a.getColor(R.styleable.NavigaView_naviRightTitleColor, ContextCompat.getColor(mContext, R.color.colorBlack_17)));
                if (!TextUtils.isEmpty(a.getString(R.styleable.NavigaView_naviRightFontFamily))) {
                    navRightTitle.setTypeface(Typeface.create(a.getString(R.styleable.NavigaView_naviRightFontFamily), Typeface.NORMAL));
                }
                navRightTitle.setTextSize(a.getDimension(R.styleable.NavigaView_naviTitleSize, 16));
                navRightTitle.setVisibility(VISIBLE);
                navSearch.setVisibility(GONE);
                navMenu.setVisibility(GONE);
            } else {
                navRightTitle.setVisibility(INVISIBLE);
            }

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
                        listener.onBackLayoutClick();
                    } else {
                        if ((getContext() instanceof Activity)) {
                            ((Activity) getContext()).finish();
                        }
                    }
                }
            });

            navRightTitle.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onRightLayoutClick();
                    }
                }
            });
            statusView = view.findViewById(R.id.statusBar);

            a.recycle();
        }
    }

    boolean isMeasure = false;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isMeasure){
            if (isCoverStatus) {
                ViewGroup.LayoutParams layoutParams = statusView.getLayoutParams();
                int statusbarHeight = QMUIStatusBarHelper.getStatusbarHeight(mContext);
                layoutParams.height = statusbarHeight;
                statusView.setLayoutParams(layoutParams);
                view.findViewById(R.id.statusBar).setBackgroundColor(topStatusColor);
                getLayoutParams().height = getMeasuredHeight() + statusbarHeight;
                requestFocus();
            } else {
                statusView.setVisibility(GONE);
            }
            isMeasure = true;
        }

    }

    public TextView getTitle() {
        return title;
    }

    public void setTitleTxt(String titleTxt) {
        this.titleTxt = titleTxt;
        title.setText(titleTxt);
    }

    public void addMenu() {

    }

    public void setOnBackClickListener(OnApplyNaviListener onApplyNaviListener) {
        this.listener = onApplyNaviListener;
    }

    public void setOnSearchClickListener(OnSearchClickListener onSearchClickListener) {
        this.onSearchClickListener = onSearchClickListener;
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    public interface OnApplyNaviListener {
        void onBackLayoutClick();

        void onRightLayoutClick();
    }



    public interface OnSearchClickListener {
        void searchClick();
    }

    public interface OnMenuClickListener {
        void menuClick();
    }

}

