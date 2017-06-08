package com.v1ncent.io.gank.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.utils.OnClickUtils;
import com.v1ncent.io.gank.utils.impl.OnRecyclerViewListener;

import java.util.List;

/**
 * Created by v1ncent on 2017/6/6.
 */

public class MeimeiAdapter extends RecyclerView.Adapter<MeimeiAdapter.MeimeiViewHolder> {
    private Context context;
    private List<String> imgUrls;
    private ViewGroup.LayoutParams para;

    public MeimeiAdapter(Context context, List<String> imgUrls) {
        this.context = context;
        this.imgUrls = imgUrls;
    }

    @Override
    public MeimeiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_meimei, null);
        return new MeimeiAdapter.MeimeiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeimeiViewHolder holder, int position) {
//        para = holder.item_meimei.getLayoutParams();
//        if (position % 3 == 0) {
//            para.height = 550;
//        } else if (position % 3 == 1) {
//            para.height = 700;
//        } else {
//            para.height = 450;
//        }
//
//        holder.item_meimei.setLayoutParams(para);

        Glide
                .with(context)
                .load(imgUrls.get(position))
                .asBitmap()
                .placeholder(R.color.white)
                .error(R.mipmap.img_load_error)
                .into(holder.item_meimei);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return imgUrls == null ? 0 : imgUrls.size();
    }

    public class MeimeiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int position;
        public ImageView item_meimei;
        public LinearLayout root;

        public MeimeiViewHolder(View itemView) {
            super(itemView);
            item_meimei = (ImageView) itemView.findViewById(R.id.item_meimei);
            root = (LinearLayout) itemView.findViewById(R.id.root);
            root.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.root:
                    if (OnClickUtils.isFastDoubleClick()) {
                        return;
                    }
                    listener.onItemClickListener(position);
                    break;
            }
        }

    }

    public static OnRecyclerViewListener listener;

    public void addItemClickListener(OnRecyclerViewListener listener) {
        this.listener = listener;
    }

}
