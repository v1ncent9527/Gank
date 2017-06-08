package com.v1ncent.io.gank.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.nineoldandroids.animation.ObjectAnimator;
import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.AppApplication;
import com.v1ncent.io.gank.app.BaseFragment;
import com.v1ncent.io.gank.app.InitUrl;
import com.v1ncent.io.gank.daily.DailyDetailsActivity;
import com.v1ncent.io.gank.find.CategoryDetailsAc;
import com.v1ncent.io.gank.find.MeiMeiAc;
import com.v1ncent.io.gank.find.adapter.CategoryAdapter;
import com.v1ncent.io.gank.find.adapter.RandomAdapter;
import com.v1ncent.io.gank.find.pojo.CategoryVO;
import com.v1ncent.io.gank.find.pojo.RandomResult;
import com.v1ncent.io.gank.ui.AutoLinearLayoutManager;
import com.v1ncent.io.gank.utils.HttpClientUtils;
import com.v1ncent.io.gank.utils.impl.OnRecyclerViewListener;
import com.v1ncent.io.gank.utils.impl.VolleyErrorHelper;
import com.v1ncent.io.gank.utils.impl.VolleyListenerInterface;
import com.v1ncent.io.gank.widget.refreshPlusLoadmore.SpringView;
import com.v1ncent.io.gank.widget.refreshPlusLoadmore.V1ncentFooter;
import com.v1ncent.io.gank.widget.refreshPlusLoadmore.V1ncentHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v1ncent on 2017/4/11.
 */

public class FindFragment extends BaseFragment {

    @BindView(R.id.recycler_find)
    RecyclerView recyclerFind;
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
    @BindView(R.id.refresh_find)
    ImageView refreshFind;
    @BindView(R.id.recycle_random)
    RecyclerView recycleRandom;
    @BindView(R.id.find_spring_view)
    SpringView findSpringView;

    private CategoryAdapter categoryAdapter;

    private List<CategoryVO> categoryVOList;
    private ObjectAnimator amin;
    private String TAG = "DailyFragment";
    private Context context;
    private List<RandomResult.ResultsBean> resultsBeanList = new ArrayList<>();
    private RandomAdapter randomAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_find, null);
        ButterKnife.bind(this, view);
        context = getActivity();
        initDate();
        initView();
        loadRandom();
        return view;
    }


    private void initDate() {
        categoryVOList = new ArrayList<>();
        categoryVOList.add(new CategoryVO(R.mipmap.ic_android, ContextCompat.getColor(getActivity(), R.color.category_0), "Android"));
        categoryVOList.add(new CategoryVO(R.mipmap.ic_ios, ContextCompat.getColor(getActivity(), R.color.category_1), "iOS"));
        categoryVOList.add(new CategoryVO(R.mipmap.ic_html, ContextCompat.getColor(getActivity(), R.color.category_2), "前端"));
        categoryVOList.add(new CategoryVO(R.mipmap.ic_app, ContextCompat.getColor(getActivity(), R.color.category_3), "App"));
        categoryVOList.add(new CategoryVO(R.mipmap.ic_vedio, ContextCompat.getColor(getActivity(), R.color.category_4), "休息视频"));
        categoryVOList.add(new CategoryVO(R.mipmap.ic_expansion, ContextCompat.getColor(getActivity(), R.color.category_5), "拓展资源"));
        categoryVOList.add(new CategoryVO(R.mipmap.ic_recommend, ContextCompat.getColor(getActivity(), R.color.category_6), "瞎推荐"));
        categoryVOList.add(new CategoryVO(R.mipmap.ic_welfare, ContextCompat.getColor(getActivity(), R.color.category_7), "福利"));
    }

    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.tab_2));
        title.setBackgroundColor(getResources().getColor(R.color.tab_2));
        titleText.setText("发现");
        leftImg.setVisibility(View.INVISIBLE);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerFind.setLayoutManager(gridLayoutManager);
        categoryAdapter = new CategoryAdapter(getActivity(), categoryVOList);
        recyclerFind.setAdapter(categoryAdapter);

        categoryAdapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                if (position != categoryVOList.size() - 1) {
                    Intent intent = new Intent(context, CategoryDetailsAc.class);
                    intent.putExtra("category", categoryVOList.get(position).getName());
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                } else {
                    Intent intent = new Intent(context, MeiMeiAc.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }

            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });

        recycleRandom.setLayoutManager(new AutoLinearLayoutManager(context));
        randomAdapter = new RandomAdapter(context, resultsBeanList);
        recycleRandom.setItemAnimator(new DefaultItemAnimator());
        recycleRandom.setAdapter(randomAdapter);
        randomAdapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent = new Intent(context, DailyDetailsActivity.class);
                intent.putExtra("url", resultsBeanList.get(position).getUrl());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });

        amin = ObjectAnimator.ofFloat(refreshFind, "rotation", 0f, 359f);
        amin.setDuration(1000);
        amin.setInterpolator(new LinearInterpolator());
        amin.setRepeatCount(-1);
        amin.setTarget(refreshFind);
        amin.start();

        findSpringView.setType(SpringView.Type.FOLLOW);
        findSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                loadRandom();
            }

            @Override
            public void onLoadmore() {
                loadMore();
            }

        });

        findSpringView.setHeader(new V1ncentHeader(context, 0));
        findSpringView.setFooter(new V1ncentFooter(context));
    }

    /**
     * 下拉刷新
     */
    private void loadRandom() {
        HttpClientUtils.StringRequestGet(context, InitUrl.RANDOM, TAG, new VolleyListenerInterface() {
            @Override
            public void onMySuccess(String result) {
                findSpringView.onFinishFreshAndLoad();

                if (null != amin) {
                    amin.cancel();
                }
                if (null != result) {
                    Logger.json(result);
                    RandomResult randomResult = AppApplication.gson.fromJson(result, RandomResult.class);
                    resultsBeanList.clear();
                    resultsBeanList.addAll(randomResult.getResults());
                    randomAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                findSpringView.onFinishFreshAndLoad();
                if (null != amin) {
                    amin.cancel();
                }
                showError(VolleyErrorHelper.getMessage(error, context));
            }
        });
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        HttpClientUtils.StringRequestGet(context, InitUrl.RANDOM, TAG, new VolleyListenerInterface() {
            @Override
            public void onMySuccess(String result) {
                findSpringView.onFinishFreshAndLoad();
                if (null != result) {
                    Logger.json(result);
                    RandomResult randomResult = AppApplication.gson.fromJson(result, RandomResult.class);
                    resultsBeanList.addAll(randomResult.getResults());
                    randomAdapter.notifyItemInserted(resultsBeanList.size() - 10);
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                findSpringView.onFinishFreshAndLoad();
                showError(VolleyErrorHelper.getMessage(error, context));
            }
        });
    }


    @OnClick({R.id.refresh_find})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.refresh_find:
                amin.start();
                loadRandom();
                break;
        }
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
