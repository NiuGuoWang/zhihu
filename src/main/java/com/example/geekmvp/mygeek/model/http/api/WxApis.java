package com.example.geekmvp.mygeek.model.http.api;

import com.example.geekmvp.mygeek.model.bean.wx.WXItemBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WxApis {
    String HOST = "http://api.tianapi.com/";

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXItemBean> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXItemBean> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

}
