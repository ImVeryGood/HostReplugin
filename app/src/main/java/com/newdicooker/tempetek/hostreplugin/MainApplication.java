package com.newdicooker.tempetek.hostreplugin;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.qihoo360.replugin.RePlugin;

/**
 * Created by SPC
 * on 2018/7/25
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        RePlugin.App.attachBaseContext(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RePlugin.App.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        /* Not need to be called if your application's minSdkVersion > = 14 */
        RePlugin.App.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        /* Not need to be called if your application's minSdkVersion > = 14 */
        RePlugin.App.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        /* Not need to be called if your application's minSdkVersion > = 14 */
        RePlugin.App.onConfigurationChanged(config);
    }
}