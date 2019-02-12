package com.example.geekmvp.mygeek.model.http.api;

import com.example.geekmvp.mygeek.model.bean.myserver.LoginBean;
import com.example.geekmvp.mygeek.model.bean.myserver.RegisterBean;
import com.example.geekmvp.mygeek.model.bean.myserver.UploadHeaderBean;
import com.example.geekmvp.mygeek.model.bean.myserver.VerifyBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface IMyserver {

    int TYPE = 0;

    String base_url = "http://yun918.cn/study/public/index.php/";

    @GET("verify")
    Observable<VerifyBean> getVerify();

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@FieldMap Map<String, Object> map);

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(@FieldMap Map<String, Object> map);

    @POST("uploadheader")
    @FormUrlEncoded
    Observable<UploadHeaderBean> getUploadheader(@FieldMap Map<String, Object> map);

}
