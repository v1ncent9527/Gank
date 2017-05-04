package com.v1ncent.io.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by v1ncent on 2017/4/11.
 */

public class FollowFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_follow, null);
        ButterKnife.bind(this, view);
        initView();
        showSuccess("FollowFragment");
        return view;
    }

    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.white), 32);
    }

    @Override
    public void onClickListener(View v) {

    }
}
