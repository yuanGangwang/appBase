package dev.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class IntentUtils {
    /**
     * 打开系统相册
     */
    public static void startSysGallery(Activity activity, int requestCode) {
        if (activity == null) {
            throw new NullPointerException();
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode);
    }

    /**
     * 跳转系统拍照
     */
    public static void startSysTakePhoto(Activity activity, int requestCode, File file, OnTakePhotoIntent photoIntent) {
        if (activity == null || file == null) {
            throw new NullPointerException();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            photoUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", file);
        } else {
            photoUri = Uri.fromFile(file);
        }

        photoIntent.makeUrl(photoUri);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        activity.startActivityForResult(intent, requestCode);
    }

    public interface OnTakePhotoIntent {
        void makeUrl(Uri photoUrl);
    }

    /**
     * 前往指定设置界面 并提供定时器进行结果监听(调用时记得销毁定时器)
     *
     * @param activity
     * @param action
     * @param onJumpToSetting 接口自己填充判断条件 判断权限满足是否返回
     * @return
     */
    public static Timer jumpToOpenAccess(final Activity activity, String action, final OnJumpToSettingResult onJumpToSetting) {
        Intent intent = new Intent(action);
        activity.startActivity(intent);
        if (onJumpToSetting != null) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (onJumpToSetting != null) {
                                onJumpToSetting.onCheckResult();
                            } else {
                                cancel();
                            }
                        }
                    });
                }
            }, 0, 200);
            return timer;
        }
        return null;
    }

    public interface OnJumpToSettingResult {
        void onCheckResult();
    }

    /**
     * 打开指定设置界面
     *
     * @param action
     * @param activity
     */
    public static void jumpToOpenAccess(String action, Activity activity) {
        Intent intent = new Intent(action);
        activity.startActivity(intent);
    }

    /**
     * 发送邮件
     *
     * @param activity
     * @param receiver
     * @param subject
     * @param text
     * @return
     */
    public static boolean sendEmail(Activity activity, String receiver, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO).setData(Uri.parse("mailto:" + receiver));
        if (null != subject) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (null != text) {
            intent.putExtra(Intent.EXTRA_TEXT, text);
        }
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 安装apk
     * Android 7.0 或更高版本的应用私有目录被限制访问（错误检查注意install权限以及xml provide配置）
     *
     * @param context 上下文
     * @param file    APK文件
     * @paras authority provider->authority属性
     */
    public void installApk(Context context, File file, String authority) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ? FileProvider.getUriForFile(context, authority, file) :
                Uri.fromFile(file), "\"application/vnd.android.package-archive\"");
        context.startActivity(intent);
    }


    /**
     * 卸载apk
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void uninstallApk(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + packageName);
        intent.setData(packageURI);
        context.startActivity(intent);
    }
}
