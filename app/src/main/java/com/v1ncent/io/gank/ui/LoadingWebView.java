package com.v1ncent.io.gank.ui;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;

/**
 * Created by v1ncent on 2017/5/4.
 *
 * @description: 带加载条的webview
 */

public class LoadingWebView extends WebView {
    private ProgressBar mProgressBar;
    /**
     * 网页缓存目录
     */
    private static final String cacheDirPath = Environment
            .getExternalStorageDirectory() + "/LoadingWebViewDome/webCache/";

    public LoadingWebView(Context context) {
        super(context, null);
        initContext(context);
    }

    public LoadingWebView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initContext(context);
    }

    public LoadingWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initContext(context);
    }

    private void initContext(Context context) {
        requestFocus();
        setInitialScale(39);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//支持通过Javascript打开新窗口
        getSettings().setJavaScriptEnabled(true);//设置WebView属性，能够执行Javascript脚本
        getSettings().setUseWideViewPort(true);//将图片调整到适合webview的大小
        getSettings().setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        getSettings().setDomStorageEnabled(true);//设置是否启用了DOM Storage API
        getSettings().setDatabaseEnabled(true);//开启database storage API功能
        getSettings().setDatabasePath(cacheDirPath); //设置数据库缓存路径
        getSettings().setAppCachePath(cacheDirPath);//设置Application Caches缓存目录
        getSettings().setAppCacheEnabled(true);//开启Application Caches功能
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    /**
     * 加载网页url
     *
     * @param url
     */
    public void loadMessageUrl(String url) {
        super.loadUrl(url);
        setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadUrl(url);//加载需要显示的网页
                return true;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Logger.i(error.getErrorCode() + "\n" + error.getDescription());
                Toast.makeText(getContext(), "页面加载好像出现了问题￣へ￣", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接受所有网站的证书
            }
        });
    }

    /**
     * 添加进度条
     */
    public void addProgressBar() {
        mProgressBar = new ProgressBar(getContext(), null,
                android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, 5, 0, 0));
        mProgressBar.setProgressDrawable(getContext().getResources()
                .getDrawable(R.drawable.bg_pb_web_loading));
        addView(mProgressBar);//添加进度条至LoadingWebView中

        setWebChromeClient(new WebChromeClient());//设置setWebChromeClient对象
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                if (mProgressBar.getVisibility() == GONE)
                    mProgressBar.setVisibility(VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    /**
     * 回收webview
     */
    public void destroyWebView() {
        clearCache(true);
        clearHistory();
    }
}
