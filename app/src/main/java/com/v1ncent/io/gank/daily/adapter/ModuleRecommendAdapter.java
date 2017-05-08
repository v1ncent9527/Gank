package com.v1ncent.io.gank.daily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.daily.pojo.DayDateResult;
import com.v1ncent.io.gank.utils.OnClickUtils;
import com.v1ncent.io.gank.utils.impl.OnRecyclerViewListener;
import com.v1ncent.io.gank.widget.CircleImageView;

import java.util.List;

/**
 * Created by v1ncent on 2017/4/27.
 */

public class ModuleRecommendAdapter extends RecyclerView.Adapter<ModuleRecommendAdapter.ModuleViewHolder> {
    private Context context;
    private List<DayDateResult.ResultsBean.瞎推荐Bean> detailsList;
    private int logoid[] = {
            R.mipmap.ic_person_logo1, R.mipmap.ic_person_logo2, R.mipmap.ic_person_logo3,
            R.mipmap.ic_person_logo4, R.mipmap.ic_person_logo5, R.mipmap.ic_person_logo6,
            R.mipmap.ic_person_logo7, R.mipmap.ic_person_logo8, R.mipmap.ic_person_logo9,
            R.mipmap.ic_person_logo10, R.mipmap.ic_person_logo11, R.mipmap.ic_person_logo3,
            R.mipmap.ic_person_logo13, R.mipmap.ic_person_logo14, R.mipmap.ic_person_logo15,
            R.mipmap.ic_person_logo16, R.mipmap.ic_person_logo17, R.mipmap.ic_person_logo18,
            R.mipmap.ic_person_logo19, R.mipmap.ic_person_logo20
    };

    public ModuleRecommendAdapter(Context context, List<DayDateResult.ResultsBean.瞎推荐Bean> detailsList) {
        this.context = context;
        this.detailsList = detailsList;
    }

    @Override
    public ModuleRecommendAdapter.ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_module, parent, false);
        return new ModuleRecommendAdapter.ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ModuleRecommendAdapter.ModuleViewHolder holder, int position) {
        DayDateResult.ResultsBean.瞎推荐Bean details = detailsList.get(position);
        holder.item_module_author.setText(TextUtils.isEmpty(details.getWho()) ? "Gank.io" : details.getWho());
        holder.item_module_title.setText(details.getDesc());
        holder.item_module_time.setText(details.getPublishedAt().substring(0,10));
        holder.item_module_head.setImageResource(logoid[(int) (Math.random() * 19 + 1)]);

        if (position == detailsList.size() - 1) {
            holder.item_module_dvider.setVisibility(View.INVISIBLE);
        } else {
            holder.item_module_dvider.setVisibility(View.VISIBLE);
        }

        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return detailsList == null ? 0 : detailsList.size();
    }

    public class ModuleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int position;
        public CircleImageView item_module_head;
        public TextView item_module_author, item_module_title, item_module_love, item_module_time;
        public ImageView item_module_img;
        public LinearLayout item_module_parent;
        public View item_module_dvider;

        public ModuleViewHolder(View itemView) {
            super(itemView);
            item_module_head = (CircleImageView) itemView.findViewById(R.id.item_module_head);
            item_module_author = (TextView) itemView.findViewById(R.id.item_module_author);
            item_module_title = (TextView) itemView.findViewById(R.id.item_module_title);
            item_module_love = (TextView) itemView.findViewById(R.id.item_module_love);
            item_module_time = (TextView) itemView.findViewById(R.id.item_module_time);
            item_module_img = (ImageView) itemView.findViewById(R.id.item_module_img);
            item_module_dvider = itemView.findViewById(R.id.item_module_dvider);

            item_module_parent = (LinearLayout) itemView.findViewById(R.id.item_module_parent);

            item_module_parent.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.item_module_parent:
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
