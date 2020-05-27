package dev.common.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class ToastUtil {

    private static final String TAG = "ToastUtil";

    private static ToastUtil mToastUtils;
    private static Toast mToast;

    private ToastUtil(Context context) {
        mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
    }

    public static synchronized ToastUtil getInstance(Context context) {
        if (null == mToastUtils) {
            mToastUtils = new ToastUtil(context);
        }
        return mToastUtils;
    }

    public void showCommon(String content) {
        mToast.setText(content);
        mToast.show();
    }

    public void showCommon(Context context, String content, int gravity, int offX, int offY) {
        mToast.setGravity(gravity, offX, offY);
        mToast.show();
    }

    /**
     * 自定义布局toast
     * @param context
     * @param layoutId
     * @param onCustomer
     */
    public void showCustomer(Context context, int layoutId, OnCustomer onCustomer) {
        View toastView = LayoutInflater.from(context).inflate(layoutId, null);
        if (onCustomer != null) {
            onCustomer.initCustomerView(toastView);
        }
        Toast toast = new Toast(context);
        if (onCustomer != null) {
            onCustomer.onToastGravity(toast);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }

    public interface OnCustomer {
        // 自定义Toast布局设置
        void initCustomerView(View toastView);
        //自定义Toast位置设置
        void onToastGravity(Toast toast);
    }

}
