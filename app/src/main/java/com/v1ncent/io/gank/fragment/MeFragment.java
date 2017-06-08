package com.v1ncent.io.gank.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.BaseFragment;
import com.v1ncent.io.gank.me.SettingActivity;
import com.v1ncent.io.gank.me.fragment.MeInfoEidtFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.info_edit)
    ImageView infoEdit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_me, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.tab_4));
        titleText.setText("我的");
        leftImg.setVisibility(View.GONE);
        rightImg.setImageResource(R.mipmap.title_setting);
        rightImg.setVisibility(View.VISIBLE);

        OverScrollDecoratorHelper.setUpOverScroll(meRoot);//设置滑动

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setStatusBarColor(getResources().getColor(R.color.tab_4));
            //TODO now visible to user
        } else {
            //TODO now invisible to user
        }
    }

    @OnClick({R.id.info_edit, R.id.rightBtn})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.info_edit:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                MeInfoEidtFragment editDialog = new MeInfoEidtFragment();
                editDialog.show(fm, "MeInfoEidtFragment");
                break;
            case R.id.rightBtn:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
