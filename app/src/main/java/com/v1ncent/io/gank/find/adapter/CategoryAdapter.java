package com.v1ncent.io.gank.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.find.pojo.CategoryVO;
import com.v1ncent.io.gank.utils.OnClickUtils;
import com.v1ncent.io.gank.utils.impl.OnRecyclerViewListener;

import java.util.List;

/**
 * Created by v1ncent on 2017/5/23.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<CategoryVO> categoryVOList;

    public CategoryAdapter(Context context, List<CategoryVO> categoryVOList) {
        this.context = context;
        this.categoryVOList = categoryVOList;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, null);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        CategoryVO categoryVO = categoryVOList.get(position);
        holder.item_icon.setImageResource(categoryVO.getIcon());
        holder.root.setBackgroundColor(categoryVO.getBg());
        holder.item_name.setText(categoryVO.getName());
        holder.position = position;
        if (position == categoryVOList.size() - 1) {
            LayoutParams para;
            para = holder.item_icon.getLayoutParams();
            para.width = 108;
            para.height = 108;
            holder.item_icon.setLayoutParams(para);
        }
    }

    @Override
    public int getItemCount() {
        return categoryVOList == null ? 0 : categoryVOList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public int position;
        private LinearLayout root;
        private ImageView item_icon;
        private TextView item_name;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            root = (LinearLayout) itemView.findViewById(R.id.root);
            item_icon = (ImageView) itemView.findViewById(R.id.item_icon);
            item_name = (TextView) itemView.findViewById(R.id.item_name);

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
