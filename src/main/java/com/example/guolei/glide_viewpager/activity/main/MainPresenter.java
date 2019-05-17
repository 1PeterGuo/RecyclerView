package com.example.guolei.glide_viewpager.activity.main;

import android.util.Log;

import com.example.guolei.glide_viewpager.base.BaseCallBack;
import com.example.guolei.glide_viewpager.bean.weal.Result;

import java.util.List;

/**
 * Created on 2019/5/17 10:19
 *
 * @author Q.PeterGuo
 * @version 1.0.0
 * @Description
 */
public class MainPresenter implements MainConstract.ThisPresenter {


    private MainConstract.ThisView mView;
    private MainConstract.MainSource mSource;
    private static final String TAG = "MainPresenter";

    public MainPresenter() {
        mSource = new MainRepository();
    }

    @Override
    public void getSource() {

        if (mSource != null){

            mSource.getData(new BaseCallBack<List<Result>>() {
                @Override
                public void onSuccess(List<Result> data) {

                    if (mView != null){
                        mView.onSuccess(data);
                        Log.d(TAG, "onSuccess: "+data.size());
                    }

                }

                @Override
                public void onFail(String msg) {
                    if (mView != null){
                        mView.onFail(msg);
                    }
                }
            });

        }

    }

    @Override
    public void attachView(MainConstract.ThisView view) {
        this.mView = view;
    }

    @Override
    public void detachView(MainConstract.ThisView view) {
        this.mView = null;
    }
}
