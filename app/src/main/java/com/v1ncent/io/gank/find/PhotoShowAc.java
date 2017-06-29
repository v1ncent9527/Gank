package com.v1ncent.io.gank.find;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.BaseActivity;
import com.v1ncent.io.gank.widget.LoadingDialog;
import com.v1ncent.io.gank.widget.popup.amin.AnimEnter;
import com.v1ncent.io.gank.widget.popup.amin.AnimExit;
import com.v1ncent.io.gank.widget.popup.v1ncentPupop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.path;

/**
 * Created by v1ncent on 2017/6/27.
 */

public class PhotoShowAc extends BaseActivity {
    @BindView(R.id.photo_show)
    ImageView photoShow;
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

    private PhotoShowAc context;
    private String photoUrl;
    private View menuPupop;
    private LinearLayout ll_photo_save;
    private v1ncentPupop pupop;
    private Bitmap bmp;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_show);
        ButterKnife.bind(this);
        context = this;
        photoUrl = getIntent().getStringExtra("photo_url");
        Logger.i(photoUrl);

        initView();
    }

    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.tab_2));
        title.setBackgroundColor(getResources().getColor(R.color.tab_2));
        titleText.setText("图片预览");

        rightImg.setImageResource(R.mipmap.title_more);
        rightImg.setVisibility(View.VISIBLE);

        //loadPhoto
        Glide
                .with(context)
                .load(photoUrl)
                .placeholder(R.color.white)
                .error(R.mipmap.img_load_error)
                .into(photoShow);

    }

    @OnClick({R.id.leftBtn, R.id.rightBtn})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                mSwipeBackHelper.executeBackwardAnim();
                break;
            case R.id.rightBtn:
                menuPupop = View.inflate(context, R.layout.photo_save, null);
                ll_photo_save = (LinearLayout) menuPupop.findViewById(R.id.ll_photo_save);

                pupop = new v1ncentPupop(context, menuPupop)
                        .gravity(Gravity.BOTTOM)
                        .anchorView(rightImg)
                        .bubbleColor(Color.parseColor("#646464"))
                        .triangleWidth(15)
                        .triangleHeight(10)
                        .showAnim(new AnimEnter())
                        .dismissAnim(new AnimExit());
                pupop.show();
                ll_photo_save.setOnClickListener(context);
                break;
            case R.id.ll_photo_save:
                pupop.dismiss();
                loadingDialog = new LoadingDialog(this, R.style.CustomDialog, "保存中..");
                loadingDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        savePhoto();
                    }
                }, 1200);
                break;

        }
    }

    /*保存图片*/
    private void savePhoto() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bmp = Glide.with(context)
                            .load(photoUrl)
                            .asBitmap()//转化为bitmap类型
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)//设置接收图片的像素，可以传入两个int类型，这里是保留原像素
                            .get();//得到图片
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                // 首先保存图片
                File appDir = new File(Environment.getExternalStorageDirectory(), "Gank");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = System.currentTimeMillis() + ".jpg";
                File file = new File(appDir, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 其次把文件插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(context.getContentResolver(),
                            file.getAbsolutePath(), fileName, null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 最后通知图库更新
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("fle://" + path)));
            }
        }).start();

        loadingDialog.success("保存成功！");
    }
}
