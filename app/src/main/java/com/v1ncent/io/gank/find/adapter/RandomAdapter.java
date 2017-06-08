package com.v1ncent.io.gank.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.find.pojo.RandomResult;
import com.v1ncent.io.gank.utils.OnClickUtils;
import com.v1ncent.io.gank.utils.impl.OnRecyclerViewListener;
import com.v1ncent.io.gank.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v1ncent on 2017/5/23.
 */

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.RandomViewHolder> {
    private Context context;
    private List<RandomResult.ResultsBean> resultsBeanList = new ArrayList<>();
    private int logoid[] = {
            R.mipmap.ic_person_logo1, R.mipmap.ic_person_logo2, R.mipmap.ic_person_logo3,
            R.mipmap.ic_person_logo4, R.mipmap.ic_person_logo5, R.mipmap.ic_person_logo6,
            R.mipmap.ic_person_logo7, R.mipmap.ic_person_logo8, R.mipmap.ic_person_logo9,
            R.mipmap.ic_person_logo10, R.mipmap.ic_person_logo11, R.mipmap.ic_person_logo3,
            R.mipmap.ic_person_logo13, R.mipmap.ic_person_logo14, R.mipmap.ic_person_logo15,
            R.mipmap.ic_person_logo16, R.mipmap.ic_person_logo17, R.mipmap.ic_person_logo18,
            R.mipmap.ic_person_logo19, R.mipmap.ic_person_logo20
    };

    public RandomAdapter(Context context, List<RandomResult.ResultsBean> resultsBeanList) {
        this.context = context;
        this.resultsBeanList = resultsBeanList;
    }

    @Override
    public RandomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_find_random, parent, false);
        return new RandomAdapter.RandomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RandomViewHolder holder, int position) {
        RandomResult.ResultsBean resultsBean = resultsBeanList.get(position);
        holder.item_author.setText(resultsBean.getWho());
        holder.item_category.setText(resultsBean.getType());
        holder.item_title.setText(resultsBean.getDesc());
        holder.item_date.setText(resultsBean.getPublishedAt().substring(0, 10));
        holder.item_head.setImageResource(logoid[(int) (Math.random() * 19 + 1)]);

        if (null != resultsBean.getImages()
                && resultsBean.getImages().size() > 0) {
            Glide
                    .with(context)
                    .load(resultsBean.getImages().get(0))
                    .asBitmap()
                    .placeholder(R.color.white)
                    .error(R.mipmap.img_load_error)
                    .into(holder.item_random_img);
        } else {
            holder.item_random_img.setVisibility(View.GONE);
        }

        if (position == resultsBeanList.size() - 1) {
            holder.item_dvider.setVisibility(View.INVISIBLE);
        } else {
            holder.item_dvider.setVisibility(View.VISIBLE);
        }

        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return resultsBeanList == null ? 0 : resultsBeanList.size();
    }

    public class RandomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int position;
        public CircleImageView item_head;
        public TextView item_author, item_title, item_category, item_date;
        public ImageView item_random_img;
        public LinearLayout root;
        public View item_dvider;

        public RandomViewHolder(View itemView) {
            super(itemView);
            item_head = (CircleImageView) itemView.findViewById(R.id.item_head);
            item_author = (TextView) itemView.findViewById(R.id.item_author);
            item_title = (TextView) itemView.findViewById(R.id.item_title);
            item_category = (TextView) itemView.findViewById(R.id.item_random_category);
            item_date = (TextView) itemView.findViewById(R.id.item_random_time);
            item_random_img = (ImageView) itemView.findViewById(R.id.item_random_img);
            item_dvider = itemView.findViewById(R.id.item_dvider);

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
