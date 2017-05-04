package com.v1ncent.io.gank.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.v1ncent.io.gank.app.AppApplication;
import com.v1ncent.io.gank.utils.impl.VolleyListenerInterface;

import java.util.Map;


/**
 * Created by v1ncent on 2017/2/25.
 * 统一网络访问接口
 */
public class HttpClientUtils {
    private static StringRequest stringRequest;
    private static JsonObjectRequest jsonObjectRequest;
    public static String client_id = "";
    public static String access_token = "";

    /**
     * 结束请求
     *
     * @param tag
     */
    public static void cancleRequest(String tag) {
        AppApplication.getRequestQueue().cancelAll(tag);
    }

    /**
     * 获取GET请求内容
     * 参数：
     * context：当前上下文；
     * url：请求的url地址；
     * tag：当前请求的标签；
     * volleyListenerInterface：VolleyListenerInterface接口；
     */
    public static void StringRequestGet(final Context context, final String url, String tag, VolleyListenerInterface volleyListenerInterface) {
        // 清除请求队列中的tag标记请求
        AppApplication.getRequestQueue().cancelAll(tag);
        // 创建当前的请求，获取字符串内容
        stringRequest = new StringRequest(Request.Method.GET, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener()) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<>();
//                if (TextUtils.isEmpty(client_id)) {
//                    client_id = SharePreferenceUtils.getPrefString(context, "client_id", "");
//                }
//                if (TextUtils.isEmpty(access_token)) {
//                    access_token = SharePreferenceUtils.getPrefString(context, "access_token", "");
//                }
//                L.i(url);
//                L.i("client_id：" + client_id);
//                L.i("access_token：" + access_token);
//                headers.put("clientid", client_id);
//                headers.put("accesstoken", access_token);
//                return headers;
//            }
        };
        //设置请求超时
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 0, 1.0f));
        // 为当前请求添加标记
        stringRequest.setTag(tag);
        // 将当前请求添加到请求队列中
        AppApplication.getRequestQueue().add(stringRequest);
        // 重启当前请求队列
        AppApplication.getRequestQueue().start();
    }

    /**
     * 获取POST请求内容（请求的代码为Map）
     * 参数：
     * context：当前上下文；
     * url：请求的url地址；
     * tag：当前请求的标签；
     * params：POST请求内容；
     * volleyListenerInterface：VolleyListenerInterface接口；
     */
    public static void StringRequestPost(final Context context, String url, String tag, final Map<String, String> params, final boolean needSetHeader, VolleyListenerInterface volleyListenerInterface) {
        // 清除请求队列中的tag标记请求
        AppApplication.getRequestQueue().cancelAll(tag);
        // 创建当前的POST请求，并将请求内容写入Map中
        stringRequest = new StringRequest(Request.Method.POST, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.i("请求参数：", params + "");
                return params;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<>();
//                if (needSetHeader) {
//                    if (TextUtils.isEmpty(client_id)) {
//                        client_id = SharePreferenceUtils.getPrefString(context, "client_id", "");
//                    }
//                    if (TextUtils.isEmpty(access_token)) {
//                        access_token = SharePreferenceUtils.getPrefString(context, "access_token", "");
//                    }
//                    L.i("client_id：" + client_id);
//                    L.i("access_token：" + access_token);
//                    headers.put("clientid", client_id);
//                    headers.put("accesstoken", access_token);
//                }
//                return headers;
//            }

//            /**
//             * 设置请求ContentType
//             * @return
//             */
//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded;charset=utf-8";
//            }

        };
        //设置请求超时
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        // 为当前请求添加标记
        stringRequest.setTag(tag);
        // 将当前请求添加到请求队列中
        AppApplication.getRequestQueue().add(stringRequest);
        // 重启当前请求队列
        AppApplication.getRequestQueue().start();
    }


//    /**
//     * 获取GET请求内容（请求的代码为Map）
//     * 参数：
//     * context：当前上下文；
//     * url：请求的url地址；
//     * tag：当前请求的标签；
//     * params：GET请求内容；
//     * volleyListenerInterface：VolleyListenerInterface接口；
//     */
//    public static void StringGetPost(final Context context, String url, String tag, final Map<String, String> params, final boolean needSetHeader, VolleyListenerInterface volleyListenerInterface) {
//        // 清除请求队列中的tag标记请求
//        BaseApplication.getRequestQueue().cancelAll(tag);
//        // 创建当前的POST请求，并将请求内容写入Map中
//        stringRequest = new StringRequest(Request.Method.GET, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener()) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<>();
//                if (needSetHeader) {
//                    if (TextUtils.isEmpty(client_id)) {
//                        client_id = SharePreferenceUtils.getPrefString(context, "client_id", "");
//                    }
//                    if (TextUtils.isEmpty(access_token)) {
//                        access_token = SharePreferenceUtils.getPrefString(context, "access_token", "");
//                    }
//                    headers.put("clientid", client_id);
//                    headers.put("accesstoken", access_token);
//                }
//                return headers;
//            }
//
//            /**
//             * 设置请求ContentType
//             * @return
//             */
//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded;charset=utf-8";
//            }
//
//        };
//        // 为当前请求添加标记
//        stringRequest.setTag(tag);
//        // 将当前请求添加到请求队列中
//        BaseApplication.getRequestQueue().add(stringRequest);
//        // 重启当前请求队列
//        BaseApplication.getRequestQueue().start();
//    }
//
//
//    /**
//     * volley json post请求封装
//     *
//     * @param context
//     * @param url
//     * @param tag
//     * @param jsonObject
//     * @param volletJsonObjListener
//     */
//    public static void JsonObjectRequest(Context context, String url, String tag, JSONObject jsonObject, VolletJsonObjListener volletJsonObjListener) {
//        // 清除请求队列中的tag标记请求
//        BaseApplication.getRequestQueue().cancelAll(tag);
//        // 创建当前的POST请求，并将请求内容写入Map中
//
//        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, volletJsonObjListener.responseListener(), volletJsonObjListener.errorListener()) {
//            @Override
//            public Map<String, String> getHeaders() {
//                HashMap<String, String> headers = new HashMap<>();
//                headers.put("Accept", "application/json");
//                headers.put("Content-Type", "application/json; charset=UTF-8");
//                return headers;
//            }
//        };
//        // 为当前请求添加标记
//        jsonObjectRequest.setTag(tag);
//        // 将当前请求添加到请求队列中
//        BaseApplication.getRequestQueue().add(jsonObjectRequest);
//        // 重启当前请求队列
//        BaseApplication.getRequestQueue().start();
//    }
//
//
//    /**
//     * 网络请求加载图片
//     *
//     * @param networkImageView
//     * @param imageUrl
//     */
//    public static void ImageRequest(RoundHead_NetWorkView networkImageView, String imageUrl) {
//        BitmapCache bitmapCache = BitmapCache.instance();
//        ImageLoader imageLoader = new ImageLoader(BaseApplication.getRequestQueue(), bitmapCache);
//        networkImageView.setImageUrl(imageUrl, imageLoader);
//    }
//
//    public static void downLoadFiles(String url) {
//        FileDownloader.detect(url, new OnDetectBigUrlFileListener() {
//            @Override
//            public void onDetectNewDownloadFile(String url, String fileName, String saveDir, long fileSize) {
//                // 如果有必要，可以改变文件名称fileName和下载保存的目录saveDir
//                FileDownloader.createAndStart(url, saveDir, fileName);
//                L.i("下载成功", "true");
//            }
//
//            @Override
//            public void onDetectUrlFileExist(String url) {
//                // 继续下载，自动会断点续传（如果服务器无法支持断点续传将从头开始下载）
//                FileDownloader.start(url);
//            }
//
//            @Override
//            public void onDetectUrlFileFailed(String url, DetectBigUrlFileFailReason failReason) {
//                // 探测一个网络文件失败了，具体查看failReason
//            }
//
//
//        });
//    }
}
