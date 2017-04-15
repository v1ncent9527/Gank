package com.v1ncent.io.gank.utils;

import android.content.Context;
import android.widget.Toast;

import com.v1ncent.io.gank.utils.toast.Toasty;

import cn.bmob.v3.exception.BmobException;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by v1ncent on 16/12/14.
 * 服务器返回异常码提示类
 */

public class ServeBlockUtils {
    private ServeBlockUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void showBlockTip(Context context, BmobException e) {
        switch (e.getErrorCode()) {
            case 101:
                Toasty.error(getApplicationContext(), "用户名或密码不正确!", Toast.LENGTH_SHORT, true).show();
                break;
            case 202:
                Toasty.error(getApplicationContext(), "该用户名已被注册!", Toast.LENGTH_SHORT, true).show();
                break;
            case 9016:
                Toasty.error(getApplicationContext(), "无网络连接，请检查您的手机网络!", Toast.LENGTH_SHORT, true).show();
                break;
            default:
                Toasty.error(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT, true).show();
                break;

        }
    }
}
