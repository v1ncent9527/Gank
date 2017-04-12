package com.v1ncent.io.gank.app;

import android.app.Application;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by v1ncent on 2017/4/10.
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回
        BGASwipeBackManager.getInstance().init(this);
    }
}
