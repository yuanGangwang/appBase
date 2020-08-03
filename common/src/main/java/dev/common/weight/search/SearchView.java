package dev.common.weight.search;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.common.R;
import dev.common.utils.KeyboardUtil;

public class SearchView extends FrameLayout {

    private static final String TAG = "SearchView";
    OnSearChKeyClickListener searChKeyClickListener;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private View view;
    private int searchCursor;
    private int searchBackground;
    private String hint;
    private int backImg;
    private int searchDrawableLeft;
    private float searchSize;
    private String searchTxt;
    private int searchColor;
    private EditText searchEt;
    private TextView searchTx;
    private ImageView searchBackImg;
    private RecyclerView searchList;
    private View searchBgView;
    private FrameLayout contentView;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        addView();
        initView(attrs, defStyleAttr);
    }

    private void addView() {
        layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.search_layout, null, false);

        searchBgView = (view.findViewById(R.id.searchBgView));
        searchEt = ((EditText) view.findViewById(R.id.searchEt));
        searchTx = ((TextView) view.findViewById(R.id.searchTv));
        searchBackImg = ((ImageView) view.findViewById(R.id.searchBackImg));
        searchList = view.findViewById(R.id.searchList);
        contentView = view.findViewById(R.id.contentView);
        searchList.setLayoutManager(new LinearLayoutManager(mContext));

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (searChKeyClickListener != null) {
                    searChKeyClickListener.onTextChange(searchEt.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    if (searChKeyClickListener != null) {
                        hideKeyBoard();
                        searChKeyClickListener.onSearch(searchEt.getText().toString());
                    }
                }
                return false;
            }
        });

        searchBackImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searChKeyClickListener!=null){
                    hideKeyBoard();
                    searChKeyClickListener.onLeftClick();
                }
            }
        });

        searchTx.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searChKeyClickListener!=null){
                    hideKeyBoard();
                    searChKeyClickListener.onRightClick(searchEt.getText().toString());
                }

            }
        });
        addView(view);
    }

    private void hideKeyBoard() {
        if (mContext instanceof Activity) {
            KeyboardUtil.mayHideKeyboard((Activity) mContext);
        }
    }

    private void initView(AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.searchView, defStyleAttr, 0);

            searchCursor = a.getResourceId(R.styleable.searchView_searchCursorDrawable, R.drawable.color_cursor);
            searchBackground = a.getResourceId(R.styleable.searchView_searchBackground, R.drawable.bg_search);
            searchDrawableLeft = a.getResourceId(R.styleable.searchView_searchDrawableLeft, R.mipmap.ic_search);
            hint = a.getString(R.styleable.searchView_searchHint);

            backImg = a.getResourceId(R.styleable.searchView_searchBackImg, R.mipmap.back);


            searchTxt = a.getString(R.styleable.searchView_searchString);
            searchColor = a.getColor(R.styleable.searchView_searchColor, ContextCompat.getColor(mContext, R.color.colorBlack_17));
//            searchSize = a.getDimension(R.styleable.naviga_naviTitleSize, 16);


            searchBackImg.setImageResource(backImg);

            searchEt.setHint(hint);
            searchEt.setTextCursorDrawable(searchCursor);
            searchEt.setCompoundDrawables(getDrawable(searchDrawableLeft), null, null, null);

            a.recycle();
        }
    }


    public void setAdapter(RecyclerView.Adapter adapter) {
        searchList.setAdapter(adapter);
    }

    public void setCustomerView(View view) {
        searchList.setVisibility(GONE);
        contentView.addView(view);
        requestFocus();
    }

    public RecyclerView getSearchList() {
        return searchList;
    }


    private Drawable getDrawable(int id) {
        Drawable dra = getResources().getDrawable(id);
        dra.setBounds(0, 0, 42, 42);
        return dra;
    }

    public void setSearChKeyClickListener(OnSearChKeyClickListener searChKeyClickListener) {
        this.searChKeyClickListener = searChKeyClickListener;
    }

    public interface OnSearChKeyClickListener {
        void onSearch(String search);

        void onTextChange(String search);

        void onLeftClick();

        void onRightClick(String search);
    }
}