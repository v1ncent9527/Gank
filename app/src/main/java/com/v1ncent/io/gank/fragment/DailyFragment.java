package com.v1ncent.io.gank.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.AppApplication;
import com.v1ncent.io.gank.app.BaseFragment;
import com.v1ncent.io.gank.app.InitUrl;
import com.v1ncent.io.gank.daily.DailyDetailsActivity;
import com.v1ncent.io.gank.daily.adapter.ModuleAndriodAdapter;
import com.v1ncent.io.gank.daily.adapter.ModuleExpansionAdapter;
import com.v1ncent.io.gank.daily.adapter.ModuleFrontEndAdapter;
import com.v1ncent.io.gank.daily.adapter.ModuleIOSAdapter;
import com.v1ncent.io.gank.daily.adapter.ModuleRecommendAdapter;
import com.v1ncent.io.gank.daily.adapter.ModuleVideoAdapter;
import com.v1ncent.io.gank.daily.pojo.DayDateResult;
import com.v1ncent.io.gank.daily.pojo.HistoryDateResult;
import com.v1ncent.io.gank.daily.pojo.WeatherResult;
import com.v1ncent.io.gank.utils.DateFormatUtils;
import com.v1ncent.io.gank.utils.HttpClientUtils;
import com.v1ncent.io.gank.utils.impl.OnRecyclerViewListener;
import com.v1ncent.io.gank.utils.impl.VolleyErrorHelper;
import com.v1ncent.io.gank.utils.impl.VolleyListenerInterface;
import com.v1ncent.io.gank.widget.loadingview.SlackLoadingView;
import com.v1ncent.io.gank.widget.refreshPlusLoadmore.SpringView;
import com.v1ncent.io.gank.widget.refreshPlusLoadmore.V1ncentHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.v1ncent.io.gank.R.id.recycler_android;

/**
 * Created by v1ncent on 2017/4/11.
 */

public class DailyFragment extends BaseFragment {


    @BindView(R.id.daily_date)
    TextView dailyDate;
    @BindView(R.id.daily_weather)
    TextView dailyWeather;
    @BindView(R.id.daily_meimei)
    ImageView dailyMeimei;
    @BindView(R.id.recycler_ios)
    RecyclerView recyclerIos;
    @BindView(R.id.module_ios)
    LinearLayout moduleIos;
    @BindView(recycler_android)
    RecyclerView recyclerAndroid;
    @BindView(R.id.module_android)
    LinearLayout moduleAndroid;
    @BindView(R.id.recycler_front_end)
    RecyclerView recyclerFrontEnd;
    @BindView(R.id.module_front_end)
    LinearLayout moduleFrontEnd;
    @BindView(R.id.recycle_blind_recommend)
    RecyclerView recycleBlindRecommend;
    @BindView(R.id.module_blind_recommend)
    LinearLayout moduleBlindRecommend;
    @BindView(R.id.recycle_expansion)
    RecyclerView recycleExpansion;
    @BindView(R.id.module_expansion)
    LinearLayout moduleExpansion;
    @BindView(R.id.recycle_video)
    RecyclerView recycleVideo;
    @BindView(R.id.module_video)
    LinearLayout moduleVideo;
    @BindView(R.id.daily_spring_view)
    SpringView dailySpringView;
    @BindView(R.id.loadingView)
    SlackLoadingView loadingView;
    @BindView(R.id.loadingRoot)
    RelativeLayout loadingRoot;
    private String TAG = "DailyFragment";
    private Context context;

    /*android*/
    private List<DayDateResult.ResultsBean.AndroidBean> androidList = new ArrayList<>();
    private ModuleAndriodAdapter andriodAdapter;
    /*ios*/
    private List<DayDateResult.ResultsBean.IOSBean> iosList = new ArrayList<>();
    private ModuleIOSAdapter iosAdapter;
    /*FrontEnd*/
    private List<DayDateResult.ResultsBean.前端Bean> frontEndList = new ArrayList<>();
    private ModuleFrontEndAdapter frontEndAdapter;
    /*Recommend*/
    private List<DayDateResult.ResultsBean.瞎推荐Bean> recommendList = new ArrayList<>();
    private ModuleRecommendAdapter recommendAdapter;
    /*Expansion*/
    private List<DayDateResult.ResultsBean.拓展资源Bean> expansionList = new ArrayList<>();
    private ModuleExpansionAdapter expansionAdapter;
    /*Video*/
    private List<DayDateResult.ResultsBean.休息视频Bean> videoList = new ArrayList<>();
    private ModuleVideoAdapter videoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_daily, null);
        ButterKnife.bind(this, view);
        context = getActivity();
        initView();
        initData(true);
        return view;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setStatusBarColor(getResources().getColor(R.color.white));

            //TODO now visible to user
        } else {
            //TODO now invisible to user
        }
    }

    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.white));
        loadingView.start();
        dailySpringView.setEnable(false);

        dailySpringView.setType(SpringView.Type.FOLLOW);
        dailySpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                /*下拉刷新*/
                HttpClientUtils.StringRequestGet(context, InitUrl.HISTORY_DATE, "date", new VolleyListenerInterface() {
                    @Override
                    public void onMySuccess(String result) {
                        if (null != result) {
                            Logger.json(result);
                            HistoryDateResult historyDateResult = AppApplication.gson.fromJson(result, HistoryDateResult.class);

                            if (historyDateResult.getResults().size() > 7) {
                                AppApplication.setDailyDate(historyDateResult.getResults().get(0).replace("-", "/"));
                                loadWeather();
                                initData(false);
                            }
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        showError(VolleyErrorHelper.getMessage(error, context));
                    }
                });
            }

            @Override
            public void onLoadmore() {
            }
        });
        dailySpringView.setHeader(new V1ncentHeader(getActivity(), R.mipmap.ali));   //参数为：logo图片资源，是否显示文字

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "thinfont-thin.ttf"); // create a typeface from the raw ttf
        dailyDate.setTypeface(typeface); // apply the typeface to the textview
        dailyDate.setText(DateFormatUtils.getTodayDate().replace("/", "  /  "));
        dailyWeather.setText(AppApplication.getWeather());
    }

    /*获取天气信息*/
    private void loadWeather() {
        HttpClientUtils.StringRequestGet(context, InitUrl.GET_WEATHER, "weather", new VolleyListenerInterface() {
            @Override
            public void onMySuccess(String result) {
                if (null != result) {
                    Logger.json(result);
                    WeatherResult weatherResult = AppApplication.gson.fromJson(result, WeatherResult.class);
                    dailyWeather.setText(weatherResult.getResults().get(0).getNow().getText()
                            + "，" + weatherResult.getResults().get(0).getLocation().getName());
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                Logger.i(VolleyErrorHelper.getMessage(error, context));
            }
        });
    }


    /*加载干货详情*/
    private void initData(boolean isshowInfo) {
        if (!DateFormatUtils.getTodayDate().equals(AppApplication.getDailyDate())
                && isshowInfo) {
            showInfo("今天的干货还没更新吆^_^");
        }
        String url = InitUrl.BASE_URL + AppApplication.getDailyDate();
        HttpClientUtils.StringRequestGet(context, url, TAG, new VolleyListenerInterface() {
            @Override
            public void onMySuccess(String result) {
                dailySpringView.onFinishFreshAndLoad();
                loadingRoot.setVisibility(View.GONE);
                loadingView.reset();
                dailySpringView.setEnable(true);
                if (null != result) {
//                    Logger.json(result);
                    DayDateResult dayDateResult = AppApplication.gson.fromJson(result, DayDateResult.class);

                    Glide
                            .with(context)
                            .load(dayDateResult.getResults().get福利().get(0).getUrl())
                            .placeholder(R.color.white)
                            .error(R.mipmap.img_load_error)
                            .into(dailyMeimei);
                    /*android*/
                    if (null != dayDateResult.getResults().getAndroid()
                            && dayDateResult.getResults().getAndroid().size() > 0) {
                        androidList.clear();
                        androidList.addAll(dayDateResult.getResults().getAndroid());
                        recyclerAndroid.setLayoutManager(new LinearLayoutManager(context));
                        andriodAdapter = new ModuleAndriodAdapter(context, androidList);
                        recyclerAndroid.setAdapter(andriodAdapter);
                        andriodAdapter.addItemClickListener(new OnRecyclerViewListener() {
                            @Override
                            public void onItemClickListener(int position) {
                                intoDetails(androidList.get(position).getUrl());
                            }

                            @Override
                            public void onItemViewClickListener(int position) {

                            }
                        });
                        moduleAndroid.setVisibility(View.VISIBLE);
                    }

                     /*ios*/
                    if (null != dayDateResult.getResults().getIOS()
                            && dayDateResult.getResults().getIOS().size() > 0) {
                        iosList.clear();
                        iosList.addAll(dayDateResult.getResults().getIOS());
                        recyclerIos.setLayoutManager(new LinearLayoutManager(context));
                        iosAdapter = new ModuleIOSAdapter(context, iosList);
                        recyclerIos.setAdapter(iosAdapter);
                        iosAdapter.addItemClickListener(new OnRecyclerViewListener() {
                            @Override
                            public void onItemClickListener(int position) {
                                intoDetails(iosList.get(position).getUrl());
                            }

                            @Override
                            public void onItemViewClickListener(int position) {

                            }
                        });
                        moduleIos.setVisibility(View.VISIBLE);
                    }

                     /*FrontEnd*/
                    if (null != dayDateResult.getResults().get前端()
                            && dayDateResult.getResults().get前端().size() > 0) {
                        frontEndList.clear();
                        frontEndList.addAll(dayDateResult.getResults().get前端());
                        recyclerFrontEnd.setLayoutManager(new LinearLayoutManager(context));
                        frontEndAdapter = new ModuleFrontEndAdapter(context, frontEndList);
                        recyclerFrontEnd.setAdapter(frontEndAdapter);
                        frontEndAdapter.addItemClickListener(new OnRecyclerViewListener() {
                            @Override
                            public void onItemClickListener(int position) {
                                intoDetails(frontEndList.get(position).getUrl());
                            }

                            @Override
                            public void onItemViewClickListener(int position) {

                            }
                        });
                        moduleFrontEnd.setVisibility(View.VISIBLE);
                    }

                     /*Recommend*/
                    if (null != dayDateResult.getResults().get瞎推荐()
                            && dayDateResult.getResults().get瞎推荐().size() > 0) {
                        recommendList.clear();
                        recommendList.addAll(dayDateResult.getResults().get瞎推荐());
                        recycleBlindRecommend.setLayoutManager(new LinearLayoutManager(context));
                        recommendAdapter = new ModuleRecommendAdapter(context, recommendList);
                        recycleBlindRecommend.setAdapter(recommendAdapter);
                        recommendAdapter.addItemClickListener(new OnRecyclerViewListener() {
                            @Override
                            public void onItemClickListener(int position) {
                                intoDetails(recommendList.get(position).getUrl());
                            }

                            @Override
                            public void onItemViewClickListener(int position) {

                            }
                        });
                        moduleBlindRecommend.setVisibility(View.VISIBLE);
                    }

                     /*Expansion*/
                    if (null != dayDateResult.getResults().get拓展资源()
                            && dayDateResult.getResults().get拓展资源().size() > 0) {
                        expansionList.clear();
                        expansionList.addAll(dayDateResult.getResults().get拓展资源());
                        recycleExpansion.setLayoutManager(new LinearLayoutManager(context));
                        expansionAdapter = new ModuleExpansionAdapter(context, expansionList);
                        recycleExpansion.setAdapter(expansionAdapter);
                        expansionAdapter.addItemClickListener(new OnRecyclerViewListener() {
                            @Override
                            public void onItemClickListener(int position) {
                                intoDetails(expansionList.get(position).getUrl());
                            }

                            @Override
                            public void onItemViewClickListener(int position) {

                            }
                        });
                        moduleExpansion.setVisibility(View.VISIBLE);
                    }

                     /*Video*/
                    if (null != dayDateResult.getResults().get休息视频()
                            && dayDateResult.getResults().get休息视频().size() > 0) {
                        videoList.clear();
                        videoList.addAll(dayDateResult.getResults().get休息视频());
                        recycleVideo.setLayoutManager(new LinearLayoutManager(context));
                        videoAdapter = new ModuleVideoAdapter(context, videoList);
                        recycleVideo.setAdapter(videoAdapter);
                        videoAdapter.addItemClickListener(new OnRecyclerViewListener() {
                            @Override
                            public void onItemClickListener(int position) {
                                intoDetails(videoList.get(position).getUrl());
                            }

                            @Override
                            public void onItemViewClickListener(int position) {

                            }
                        });
                        moduleVideo.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                loadingRoot.setVisibility(View.GONE);
                dailySpringView.onFinishFreshAndLoad();
                showError(VolleyErrorHelper.getMessage(error, context));
            }
        });
    }

    private void intoDetails(String url) {
        Intent intent = new Intent(getActivity(), DailyDetailsActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }

    @Override
    public void onClickListener(View v) {

    }
}
