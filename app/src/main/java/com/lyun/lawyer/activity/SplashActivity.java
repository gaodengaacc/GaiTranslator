package com.lyun.lawyer.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.lyun.activity.BaseActivity;
import com.lyun.lawyer.Account;
import com.lyun.lawyer.R;
import com.lyun.utils.GlideUtils;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Created by 郑成裕 on 2017/1/20.
 */

public class SplashActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private static int sleepTime = 2000;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        GlideUtils.showImage((ImageView) findViewById(R.id.bg_splash), R.mipmap.bg_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        } else {
            sleepTime();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止handle的内存泄露问题
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }


    protected void processDone() {

        Intent intent = new Intent();
        if (Account.preference().isLogin()) {
            intent.setClass(SplashActivity.this, MainActivity.class);
        } else {
            intent.setClass(SplashActivity.this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }

    protected final int REQUEST_PERMISSION = 0x001;

    @AfterPermissionGranted(REQUEST_PERMISSION)
    public void checkPermission() {
        if (EasyPermissions.hasPermissions(this,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            sleepTime();
        } else {
            EasyPermissions.requestPermissions(this, "为保证app正常运行，需要这些权限",
                    REQUEST_PERMISSION,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        processDone();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        processDone();
    }

    public void sleepTime() {
        if (mHandler == null)
            mHandler = new Handler();
        mHandler.postDelayed(() -> processDone(), sleepTime);
    }
}
