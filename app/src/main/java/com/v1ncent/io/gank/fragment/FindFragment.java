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

    }

    @Override
    public void onClickListener(View v) {

    }
}
