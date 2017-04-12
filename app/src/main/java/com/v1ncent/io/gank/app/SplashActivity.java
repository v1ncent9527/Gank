package com.v1ncent.io.gank.app;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.widget.particle.ParticleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v1ncent on 2017/4/11.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash)
    ParticleView splash;

    @Override
    public void onClickListener(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        splash.startAnim();
        splash.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                mSwipeBackHelper.forwardAndFinish(IndexActivity.class);
            }
        });
    }


    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
}
