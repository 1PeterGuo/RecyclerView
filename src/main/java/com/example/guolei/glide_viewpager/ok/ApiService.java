package com.example.guolei.glide_viewpager.ok;

import com.example.guolei.glide_viewpager.bean.weal.HttpResult;
import com.example.guolei.glide_viewpager.bean.weal.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created on 2019/5/17 09:21
 *
 * @author Q.PeterGuo
 * @version 1.0.0
 * @Description
 */
public interface ApiService {

    @GET("20/1")
    Observable<HttpResult<List<Result>>>  getWeal();


}
