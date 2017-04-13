package com.v1ncent.io.gank.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by v1ncent on 2017/4/10.
 */

public class AppApplication extends Application {
    private static final String TAG = "AppApplication";
    public static AppApplication mInstance;
    public static Gson gson;
    private static RequestQueue volleyQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Logger.d(TAG, "AppApplication onCreate");
        // 必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回
        BGASwipeBackManager.getInstance().init(this);
        initInfo();
    }

    private void initInfo() {
        //Gson初始化
        gson = new Gson();
        //网络请求队列都是整个APP内使用的全局性对象
        volleyQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getRequestQueue() {
        return volleyQueue;
    }
}
