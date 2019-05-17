package com.example.guolei.glide_viewpager.activity.main;

import com.example.guolei.glide_viewpager.base.BaseCallBack;
import com.example.guolei.glide_viewpager.base.BasePresenter;
import com.example.guolei.glide_viewpager.base.BaseView;
import com.example.guolei.glide_viewpager.bean.weal.HttpResult;
import com.example.guolei.glide_viewpager.bean.weal.Result;

import java.util.List;

/**
 * Created on 2019/5/17 10:01
 *
 * @author Q.PeterGuo
 * @version 1.0.0
 * @Description
 */
public interface MainConstract {


    interface MainSource {
        void getData(BaseCallBack<List<Result>> callBack);
    }


    interface ThisPresenter extends BasePresenter<ThisView> {
        void getSource();
    }


    interface ThisView extends BaseView<ThisPresenter> {
        void onSuccess(List<Result> datas);

        void onFail(String msg);
    }


}
