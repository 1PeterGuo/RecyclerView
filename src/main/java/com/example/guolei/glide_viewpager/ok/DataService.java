package com.example.guolei.glide_viewpager.ok;

import com.example.guolei.glide_viewpager.BuildConfig;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 2019/5/17 09:26
 *
 * @author Q.PeterGuo
 * @version 1.0.0
 * @Description
 */
public class DataService {


    private static final long DEFAULT_TIMEOUT = 20000;

    private static volatile ApiService apiService;

    public static ApiService getApiService(String url) {

        if (apiService == null) {

            synchronized (DataService.class) {

                if (apiService == null) {

                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

                    if (BuildConfig.DEBUG) {
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    } else {
                        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }

                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .build();


                    Retrofit retrofit = new Retrofit.Builder()
                            .client(client)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(url)
                            .build();

                    apiService = retrofit.create(ApiService.class);

                }

            }

        }

        return apiService;
    }


}
