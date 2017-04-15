package com.v1ncent.io.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by v1ncent on 2017/4/11.
 */

public class MeFragment extends BaseFragment {


    @BindView(R.id.leftImg)
    ImageView leftImg;
    @BindView(R.id.leftBtn)
    View leftBtn;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.rightText)
    TextView rightText;
    @BindView(R.id.rightImg)
    ImageView rightImg;
    @BindView(R.id.rightBtn)
    View rightBtn;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.me_root)
    ScrollView meRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_me, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        titleText.setText("我的");
        leftImg.setVisibility(View.GONE);
        rightImg.setImageResource(R.mipmap.icon_settings);
        rightImg.setVisibility(View.VISIBLE);

        OverScrollDecoratorHelper.setUpOverScroll(meRoot);//设置滑动

    }

    @Override
    public void onClickListener(View v) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
