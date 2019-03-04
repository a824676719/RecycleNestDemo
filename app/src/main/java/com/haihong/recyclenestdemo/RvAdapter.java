package com.haihong.recyclenestdemo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    //新增itemType
    public static final int ITEM_TYPE = 100;

    private Context mContext;
    private List<Bean.DatasBean> mList;

    public RvAdapter(Context context, List<Bean.DatasBean> list) {
        mContext = context;
        mList = list;
    }

    //重写改方法，设置ItemViewType
    @Override
    public int getItemViewType(int position) {
        //返回值与使用时设置的值需保持一致
        return ITEM_TYPE;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detaillist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(mList.get(position).getTitle());

        /*
         1.把内部RecyclerView的adapter和集合数据通过holder缓存
         2.使内部RecyclerView的adapter提供设置position的方法
         */
        holder.list.clear();
        holder.list.addAll(mList.get(position).getOptions());
        if (holder.mRvAdapter == null) {
            holder.mRvAdapter = new RvvAdapter(mContext, holder.list, position);
            GridLayoutManager layoutManage = new GridLayoutManager(mContext, 2);
            holder.rvItemItem.setLayoutManager(layoutManage);
            holder.rvItemItem.addItemDecoration(new GridSpacingItemDecoration(2, 20, false));
            holder.rvItemItem.setAdapter(holder.mRvAdapter);
        } else {
            holder.mRvAdapter.setPosition(position);
            holder.mRvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.rv_item)
        RecyclerView rvItemItem;

        private RvvAdapter mRvAdapter;
        private List<Bean.DatasBean.Option> list = new ArrayList<>();

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}