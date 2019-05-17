package com.example.guolei.glide_viewpager.base;

/**
 * Created on 2019/5/17 09:54
 *
 * @author Q.PeterGuo
 * @version 1.0.0
 * @Description
 */
public interface BaseCallBack<T> {


    void onSuccess(T data);

    void onFail(String msg);


}
