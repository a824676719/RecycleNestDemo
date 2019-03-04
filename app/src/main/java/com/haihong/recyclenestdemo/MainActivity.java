package com.haihong.recyclenestdemo;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.center_reclycleview)
    RecyclerView c_RecyclerView;

    private RvAdapter mRvAdapter;
    private Bean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initdata();
    }

    //模拟数据
    private void initdata() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        c_RecyclerView.setLayoutManager(layoutManager);
        c_RecyclerView.setFocusableInTouchMode(false);

        mBean = new Bean();
        List<Bean.DatasBean> datas = new ArrayList<>();

        //模拟一些数据
        for (int i = 0; i < 20; i++) {
            Bean.DatasBean datasBean = new Bean.DatasBean();
            List<Bean.DatasBean.Option> option = new ArrayList<>();

            Bean.DatasBean.Option optionBean = new Bean.DatasBean.Option();
            optionBean.setDatas("这是选项" + i);
            option.add(optionBean);

            Bean.DatasBean.Option optionBean1 = new Bean.DatasBean.Option();
            optionBean1.setDatas("这是选项" + (i + 1));
            option.add(optionBean1);

            Bean.DatasBean.Option optionBean2 = new Bean.DatasBean.Option();
            optionBean2.setDatas("这是选项" + (i + 2));
            option.add(optionBean2);

            datasBean.setOptions(option);
            datasBean.setTitle("这是标题哦1");
            datas.add(datasBean);
        }

        mBean.setDatas(datas);

        mRvAdapter = new RvAdapter(this, mBean.getDatas());
        c_RecyclerView.setAdapter(mRvAdapter);
        mRvAdapter.notifyDataSetChanged();
    }

    /**
     * 设置一个public方法,供adapter点击事件调用
     *
     * @param position 为第一层recycleview位置
     * @param tag      为第二层recycleview位置
     */
    public void OnClickListener(int position, int tag) {
        final List<Bean.DatasBean> datasBeans = mBean.getDatas();
        for (int i = 0; i < datasBeans.size(); i++) {
            if (i == position) {
                List<Bean.DatasBean.Option> option = datasBeans.get(i).getOptions();
                for (int j = 0; j < option.size(); j++) {
                    if (j == tag) {
                        option.get(j).setSelect(true);
                    } else {
                        option.get(j).setSelect(false);
                    }
                }
                Toast.makeText(MainActivity.this,
                        datasBeans.get(position).getTitle() + "-" + option.get(tag).getDatas(),
                        Toast.LENGTH_SHORT).show();

            } else {
                //这里让之前选中的效果还原成未选中
                List<Bean.DatasBean.Option> option = datasBeans.get(i).getOptions();
                for (int j = 0; j < option.size(); j++) {
                    option.get(j).setSelect(false);
                }
            }
        }
        mRvAdapter.notifyDataSetChanged();
    }


}
