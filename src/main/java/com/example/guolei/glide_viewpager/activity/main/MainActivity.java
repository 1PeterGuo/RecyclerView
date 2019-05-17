package com.example.guolei.glide_viewpager.activity.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.guolei.glide_viewpager.R;
import com.example.guolei.glide_viewpager.adapter.main.MainAdapter;
import com.example.guolei.glide_viewpager.bean.weal.Result;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainConstract.ThisView {

    private Toolbar toolBar;
    private RecyclerView rlv;
    private MainConstract.ThisPresenter mPresenter;
    private List<Result> mList;
    private MainAdapter mAdapter;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolBar();
        initData();

    }

    private void initView() {
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        rlv = (RecyclerView) findViewById(R.id.rlv);

        mList = new ArrayList<>();

        // 瀑布流布局管理器
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        // 设置布局管理器
        rlv.setLayoutManager(manager);

        // 设置适配器
        mAdapter = new MainAdapter(this, mList);
        rlv.setAdapter(mAdapter);

    }

    public void initToolBar() {
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void initData() {

        setPresenter(new MainPresenter());

        if (mPresenter != null) {
            mPresenter.getSource();
            Log.d(TAG, "initData: " + "调用P层的方法");
        } else {
            Log.d(TAG, "initData:" + "没有P层对象");
        }
    }


    @Override
    public void onSuccess(List<Result> datas) {
        mList.addAll(datas);
        mAdapter.setData(mList);
    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void setPresenter(MainConstract.ThisPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }
}
