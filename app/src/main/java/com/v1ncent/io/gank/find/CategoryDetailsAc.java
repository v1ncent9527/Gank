package com.v1ncent.io.gank.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.AppApplication;
import com.v1ncent.io.gank.app.BaseActivity;
import com.v1ncent.io.gank.app.InitUrl;
import com.v1ncent.io.gank.daily.DailyDetailsActivity;
import com.v1ncent.io.gank.find.adapter.RandomAdapter;
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
 * Created by v1ncent on 2017/5/26.
 */

public class CategoryDetailsAc extends BaseActivity {

    private static final String TAG = "CategoryDetailsAc";
    @BindView(R.id.recycle_details)
    RecyclerView recycleDetails;
    @BindView(R.id.find_spring_view)
    SpringView findSpringView;
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

    private CategoryDetailsAc context;
    private String category;
    private RandomAdapter randomAdapter;
    private List<RandomResult.ResultsBean> resultsBeanList = new ArrayList<>();
    private int page = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);
        ButterKnife.bind(this);
        context = this;
        category = getIntent().getStringExtra("category");
        initView();
        initDate();
    }


    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.tab_2));
        titleText.setText(category);
        title.setBackgroundColor(getResources().getColor(R.color.tab_2));
        recycleDetails.setLayoutManager(new AutoLinearLayoutManager(context));
        randomAdapter = new RandomAdapter(context, resultsBeanList);
        recycleDetails.setItemAnimator(new DefaultItemAnimator());
        recycleDetails.setAdapter(randomAdapter);
        randomAdapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent = new Intent(context, DailyDetailsActivity.class);
                intent.putExtra("url", resultsBeanList.get(position).getUrl());
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });

        findSpringView.setType(SpringView.Type.FOLLOW);
        findSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                initDate();
            }

            @Override
            public void onLoadmore() {
                loadMore();
            }

        });

        findSpringView.setHeader(new V1ncentHeader(context, 0));
        findSpringView.setFooter(new V1ncentFooter(context));

    }

    private void initDate() {
        HttpClientUtils.StringRequestGet(context, InitUrl.CATEGORY + category + "/10/1", TAG, new VolleyListenerInterface() {
            @Override
            public void onMySuccess(String result) {
                findSpringView.onFinishFreshAndLoad();
                if (null != result) {
                    page = 2;
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
                showError(VolleyErrorHelper.getMessage(error, context));
            }
        });
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        HttpClientUtils.StringRequestGet(context, InitUrl.CATEGORY + category + "/10/" + page, TAG, new VolleyListenerInterface() {
            @Override
            public void onMySuccess(String result) {
                findSpringView.onFinishFreshAndLoad();
                if (null != result) {
                    page++;
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


    @OnClick({R.id.leftBtn})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
        }
    }
}
