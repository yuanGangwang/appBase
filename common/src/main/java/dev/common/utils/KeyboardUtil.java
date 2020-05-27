package dev.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘管理
 */
public class KeyboardUtil {

    /**
     * 隐藏软键盘
     * @param activity
     */
    public static void mayHideKeyboard(Activity activity) {
        if (null == activity || null == activity.getCurrentFocus()) {
            return;
        }
        IBinder token = activity.getCurrentFocus().getWindowToken();
        if (null != token) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示软键盘
     * @param activity
     */
    public static void showKeyboard(Activity activity) {
        if (null == activity || activity.isFinishing()) {
            return;
        }
        if (null != activity.getCurrentFocus()) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(activity.getCurrentFocus(), InputMethodManager.SHOW_FORCED);
        }
    }
}
