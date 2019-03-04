package com.haihong.recyclenestdemo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author qdafengzi
 */
public class RvvAdapter extends RecyclerView.Adapter<RvvAdapter.ViewHolder> {
    private Context mContext;
    private List<Bean.DatasBean.Option> mList;

    private int mPosition;

    public RvvAdapter(Context context, List<Bean.DatasBean.Option> list, int position) {
        mContext = context;
        mList = list;
        mPosition = position;
    }

    /**
     * 新增方法
     *
     * @param position 位置
     */
    public void setPosition(int position) {
        this.mPosition = position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lay_option.setTag(position);

        holder.tvOption.setText(mList.get(position).getDatas());
        if (mList.get(position).isSelect()) {
            holder.lay_option.setBackgroundResource(R.drawable.background_grid_unselect);
        } else {
            holder.lay_option.setBackgroundResource(R.drawable.background_grid_select);
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lay_option)
        LinearLayout lay_option;
        @BindView(R.id.tv_option)
        TextView tvOption;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            //这里设置一个tag,作为被嵌套的recycleview item点击事件的 position
            lay_option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.get((int) v.getTag()).setSelect(true);
                    ((MainActivity) mContext).OnClickListener(mPosition, (int) v.getTag());
                }
            });


        }
    }
}