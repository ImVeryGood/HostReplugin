package com.newdicooker.tempetek.hostreplugin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qihoo360.replugin.RePlugin;

/*
RePlugin.getPluginVersion("androidgo")获取插件的versionCode
* */
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
    }

    public void jumpTo(View view) {
        RePlugin.startActivity(MainActivity.this,
                RePlugin.createIntent("androidgo", "com.newdicooker.tempetek.androidgo.MainActivity"));
    }

    public void jumpToOut(View view) {
        if (RePlugin.isPluginInstalled("image")) {
            RePlugin.startActivity(MainActivity.this,
                    RePlugin.createIntent("image", "com.xq.imageplugindemo.MainActivity"));
            return;
        }

        // 插件下载地址
        String urlPath = "https://raw.githubusercontent.com/ZhangZeQiao/ImagePluginDemo/7c5866db83b57c455302fac12ea72af30d9a5364/app/src/main/assets/image.apk";
        // 插件下载后的存放路径
        String downloadDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        Intent intent = new Intent(this, DownloadAndUpdateService.class);
        intent.putExtra("urlPath", urlPath);
        intent.putExtra("downloadDir", downloadDir);
        startService(intent);
    }

    /**
     * 动态申请读写权限
     **/
    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
