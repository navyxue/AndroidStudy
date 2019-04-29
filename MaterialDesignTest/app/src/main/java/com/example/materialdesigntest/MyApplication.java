package com.example.materialdesigntest;

import android.app.Application;
import android.content.Context;

/**
 * 自定义全局的Application
 * 应用程序启动的时候，系统会自动将这个类进行初始化
 * 通过自定义Application，可以方便的管理程序内一些全局的状态信息，比如Context
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // 这里可以初始化第三方框架的全局Context，如LitePal中的LitePalApplication
        // LitePalApplication.initialize(context);
    }

    public static Context getContext() {
        return context;
    }
}
