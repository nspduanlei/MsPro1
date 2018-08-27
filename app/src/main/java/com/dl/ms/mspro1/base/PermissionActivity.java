package com.dl.ms.mspro1.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

/**
 * Created by duanlei on 2018/3/23.
 */
public abstract class PermissionActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_START = 100;
    private static final int REQUEST_CODE_SETTING = 101;

    String[] mPermissions;

    ClickListener mClickListener;
    public interface ClickListener {
        void onSuccess();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Permission.STORAGE, Permission.LOCATION, Permission.CAMERA
        requestPermissions(Permission.STORAGE);
        //reqNotification();
    }

    public void requestPermission(String[] permissions, ClickListener clickListener) {
        mClickListener = clickListener;
        mPermissions = permissions;
        requestPermissions(mPermissions);
    }

    private void requestPermissions(final String[] permissions) {
        myRequestPermissions(this,
                new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        if (mClickListener != null) {
                            mClickListener.onSuccess();
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        AndPermission.defaultSettingDialog(PermissionActivity.this,
                                REQUEST_CODE_SETTING)
                                .setTitle("权限申请失败")
                                .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                                .setPositiveButton("好，去设置")
                                .show();
                    }
                }, REQUEST_CODE_START, permissions);
    }


    public void myRequestPermissions(final Activity activity, PermissionListener permissionListener,
                                     int requestId, String[]... permissions) {
        AndPermission.with(activity)
                .requestCode(requestId)
                .permission(permissions)
                .callback(permissionListener)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(activity, rationale).show();
                    }
                })
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SETTING) {
            requestPermissions(mPermissions);
        }
    }

    /**
     * 请求通知权限
     */
    private void reqNotification() {
        NotificationManagerCompat manager =
                NotificationManagerCompat.from(this);
        boolean isOpened = manager.areNotificationsEnabled();

        if (!isOpened) {
            AndPermission.defaultSettingDialog(PermissionActivity.this,
                    REQUEST_CODE_SETTING)
                    .setTitle("通知权限申请失败")
                    .setMessage("我们需要的通知权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                    .setPositiveButton("好，去设置")
                    .show();
        }
    }
}
