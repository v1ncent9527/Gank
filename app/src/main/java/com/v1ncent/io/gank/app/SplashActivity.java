package com.v1ncent.io.gank.app;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.daily.pojo.HistoryDateResult;
import com.v1ncent.io.gank.daily.pojo.WeatherResult;
import com.v1ncent.io.gank.utils.HttpClientUtils;
import com.v1ncent.io.gank.utils.impl.VolleyErrorHelper;
import com.v1ncent.io.gank.utils.impl.VolleyListenerInterface;
import com.v1ncent.io.gank.widget.particle.ParticleView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by v1ncent on 2017/4/11.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash)
    ParticleView splash;
    @BindView(R.id.loadingView)
    ImageView loadingView;
    private SplashActivity context;
    private AnimationDrawable ad;

    @Override
    public void onClickListener(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        context = this;

        initView();
    }

    private void initView() {
        splash.startAnim();
        loadingView.setImageResource(R.drawable.amin_pgbar);
        ad = (AnimationDrawable) loadingView.getDrawable();

        splash.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                loadingView.setVisibility(View.VISIBLE);
                ad.start();
                initDate();
            }
        });

    }


    private void initDate() {
        /*获取当日服务器最新日期*/
        HttpClientUtils.StringRequestGet(context, InitUrl.HISTORY_DATE, "date", new VolleyListenerInterface() {
            @Override
            public void onMySuccess(String result) {
                if (null != result) {
//                    Logger.json(result);
                    HistoryDateResult historyDateResult = AppApplication.gson.fromJson(result, HistoryDateResult.class);

                    if (historyDateResult.getResults().size() > 7) {
                        AppApplication.setDailyDate(historyDateResult.getResults().get(0).replace("-", "/"));
                        loadWeather();
                    }
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                showError(VolleyErrorHelper.getMessage(error, context));
            }
        });
    }

    /*获取天气信息*/
    private void loadWeather() {
        HttpClientUtils.StringRequestGet(context, InitUrl.GET_WEATHER, "weather", new VolleyListenerInterface() {
            @Override
            public void onMySuccess(String result) {
                if (null != result) {
                    Logger.json(result);
                    WeatherResult weatherResult = AppApplication.gson.fromJson(result, WeatherResult.class);
                    AppApplication.setWeather(weatherResult.getResults().get(0).getNow().getText()
                            + "，" + weatherResult.getResults().get(0).getLocation().getName());
                    mSwipeBackHelper.forwardAndFinish(IndexActivity.class);
//                    mSwipeBackHelper.forwardAndFinish(TabMainActivity.class);

                }
            }

            @Override
            public void onMyError(VolleyError error) {
                Logger.i(VolleyErrorHelper.getMessage(error, context));
                mSwipeBackHelper.forwardAndFinish(IndexActivity.class);
//                mSwipeBackHelper.forwardAndFinish(TabMainActivity.class);

            }
        });
    }


    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        ad.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ad.stop();
    }


}
