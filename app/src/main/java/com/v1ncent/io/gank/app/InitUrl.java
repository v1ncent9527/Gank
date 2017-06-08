package com.v1ncent.io.gank.app;

/**
 * Created by v1ncent on 2017/4/20.
 */

public class InitUrl {
    /*gank*/
    public static final String BASE_URL = "http://gank.io/api/day/";

    /*获取干活发布的历史日期*/
    public static final String HISTORY_DATE = BASE_URL + "history";
    /*获取城市天气*/
    public static final String GET_WEATHER = "https://api.seniverse.com/v3/weather/now.json?key=4pivsyxgqljwecfg&location=ip&language=zh-Hans&unit=c";

    /*随机干货*/
    public static final String RANDOM = "http://gank.io/api/random/data/all/10";

    /*分类干货*/
    public static final String CATEGORY = "http://gank.io/api/data/";
}
