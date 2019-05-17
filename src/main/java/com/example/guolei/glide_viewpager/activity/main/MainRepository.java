package com.example.guolei.glide_viewpager.activity.main;

import android.util.Log;

import com.example.guolei.glide_viewpager.base.BaseCallBack;
import com.example.guolei.glide_viewpager.bean.weal.HttpResult;
import com.example.guolei.glide_viewpager.bean.weal.Result;
import com.example.guolei.glide_viewpager.ok.AppConstant;
import com.example.guolei.glide_viewpager.ok.DataService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2019/5/17 10:10
 *
 * @author Q.PeterGuo
 * @version 1.0.0
 * @Description
 */
public class MainRepository implements MainConstract.MainSource {


    private static final String TAG = "MainRepository";

    @Override
    public void getData(final BaseCallBack<List<Result>> callBack) {

        DataService.getApiService(AppConstant.WEAL_URL).getWeal().flatMap(new Function<HttpResult<List<Result>>, ObservableSource<List<Result>>>() {
            @Override
            public ObservableSource<List<Result>> apply(HttpResult<List<Result>> listHttpResult) throws Exception {

                // 转换的作用，把HttpResult<List<Banner> 的对象转换成 <List<Banner> 的对象
                if (listHttpResult != null && listHttpResult.error == false && listHttpResult.results != null && listHttpResult.results.size() > 0) {

                    // 如过解析的json的列表不为null ，返回列表状态中的data中数据
                    return Observable.just(listHttpResult.results);
                }

                return null;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Result>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Result> value) {
                        callBack.onSuccess(value);
                        Log.d(TAG, "onNext: " + value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
