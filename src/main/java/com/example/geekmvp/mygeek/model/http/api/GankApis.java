package com.example.geekmvp.mygeek.model.http.api;

import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApis {

    //枚举 BaseData(getTechList,getGirlList,getRandomGirl)  SearchData(getSearchList)
    /*enum Gank{
        BaseData,SearchData
    }*/


    String HOST = "http://gank.io/api/";

    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Observable<GankItemBean> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

    /**
     * 妹纸列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankItemBean> getGirlList(@Path("num") int num, @Path("page") int page);

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Observable<GankItemBean> getRandomGirl(@Path("num") int num);

    /**
     * 搜索
     */
    //@GET("search/query/{query}/category/{type}/count/{count}/page/{page}")
    //Flowable<GankSearchItemBean> getSearchList(@Path("query") String query,@Path("type") String type,@Path("count") int num,@Path("page") int page);


    interface Callback<T>{
        void fail(String error);
        void success(T result);
    }

}
