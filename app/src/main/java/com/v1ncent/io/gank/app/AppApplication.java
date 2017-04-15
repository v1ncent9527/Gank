package com.v1ncent.io.gank.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by v1ncent on 2017/4/10.
 */

public class AppApplication extends Application {
    private static final String TAG = "AppApplication";
    public static AppApplication mInstance;
    public static Gson gson;
    private static RequestQueue volleyQueue;
    public static int savaY;

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
        //第一：默认初始化
//        Bmob.initialize(this, "Your Application ID");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId("43db8691af9425838daff11528ced8c1")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
    }

    public static RequestQueue getRequestQueue() {
        return volleyQueue;
    }

    public static int getSavaY() {
        return savaY;
    }

    public static void setSavaY(int savaY) {
        AppApplication.savaY = savaY;
    }

}
