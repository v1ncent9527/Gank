package com.v1ncent.io.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by v1ncent on 2017/4/11.
 */

public class FindFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_find, null);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.tab_2));
    }


    @Override
    public void onClickListener(View v) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setStatusBarColor(getResources().getColor(R.color.tab_2));
            //TODO now visible to user
        } else {
            //TODO now invisible to user
        }
    }
//
    @Override
    public void onResume() {
        super.onResume();
        Logger.i("onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.i("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.i("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy");
    }


}
