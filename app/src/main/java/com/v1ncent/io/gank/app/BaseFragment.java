package com.v1ncent.io.gank.app;

import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.utils.toast.Toasty;
import com.v1ncent.io.gank.widget.LoadingDialog;

/**
 * Created by v1ncent on 2017/4/11.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private LoadingDialog loadingDialog;

    @Override
    public void onClick(View view) {
        onClickListener(view);
    }

    public abstract void onClickListener(View v);

    /**
     * toast INFO
     *
     * @param info
     */
    public void showInfo(String info) {
        Toasty.info(getActivity().getApplicationContext(), info, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * toast SUCCESS
     *
     * @param success
     */
    public void showSuccess(String success) {
        Toasty.success(getActivity().getApplicationContext(), success, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * toast ERROR
     *
     * @param error
     */
    public void showError(String error) {
        Toasty.error(getActivity().getApplicationContext(), error, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColor(getActivity(), color, statusBarAlpha);
    }

    /**
     * 加载dialog
     */
    public void showProgressDialog(String loadMsg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getActivity(), R.style.CustomDialog, loadMsg);
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
