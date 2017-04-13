package com.v1ncent.io.gank.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.utils.toast.Toasty;
import com.v1ncent.io.gank.widget.LoadingDialog;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * Created by v1ncent on 2017/4/10.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener, BGASwipeBackHelper.Delegate {

    @Override
    public void onClick(View v) {
        onClickListener(v);
    }

    public abstract void onClickListener(View v);

    protected BGASwipeBackHelper mSwipeBackHelper;
    private Toast toast;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        initConfigs();//配置其他相关
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
    }

    private void initConfigs() {
        /**
         * 取消含有EditText的界面一进来就弹出软键盘
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * toast INFO
     *
     * @param info
     */
    public void showInfo(String info) {
        Toasty.info(getApplicationContext(), info, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * toast SUCCESS
     *
     * @param success
     */
    public void showSuccess(String success) {
        Toasty.success(getApplicationContext(), success, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * toast ERROR
     *
     * @param error
     */
    public void showError(String error) {
        Toasty.error(getApplicationContext(), error, Toast.LENGTH_SHORT, true).show();

    }


    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColorForSwipeBack(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColor(this, color, statusBarAlpha);
    }

    /**
     * 加载dialog
     */
    public void showProgressDialog(String loadMsg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this, R.style.CustomDialog, loadMsg);
        }
        loadingDialog.show();
    }

    /**
     * 隐藏dialog
     */
    public void hideProgressDialog() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
    }

    /**
     * 成功dialog
     */
    public void dialogSuccess(String success) {
        if (loadingDialog != null) {
            loadingDialog.success(success);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.cancel();
                }
            }, 1000);
        }
    }

    /**
     * 失败dialog
     */
    public void dialogFail(String failed) {
        if (loadingDialog != null) {
            loadingDialog.failed(failed);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.cancel();
                }
            }, 1000);
        }
    }
}
