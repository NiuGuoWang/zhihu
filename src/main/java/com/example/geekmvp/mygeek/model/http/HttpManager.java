package com.example.geekmvp.mygeek.model.http;


import com.example.geekmvp.mygeek.model.http.api.GankApis;
import com.example.geekmvp.mygeek.model.http.api.IMyserver;
import com.example.geekmvp.mygeek.model.http.api.WxApis;
import com.example.geekmvp.mygeek.model.http.api.ZhihuApis;

/**
 * 管理所有的api接口
 */
public class HttpManager {

    private static ZhihuApis zhihuApis; //知乎接口
    private static GankApis gankApis; //干货
    private static IMyserver iMyserver; //用户信息
    private static WxApis wxApis; //微信精选


    /**
     * 获取知乎的api接口
     * @return
     */
    public static ZhihuApis getZhihuApis(){
        if(zhihuApis == null){
            synchronized (ZhihuApis.class){
                if(zhihuApis == null){
                    zhihuApis = HttpUtils.getInstance().getApiserver(ZhihuApis.HOST,ZhihuApis.class);
                }
            }
        }
        return zhihuApis;
    }
    /**
     * 获取微信的api接口
     * @return
     */
    public static WxApis getWxApis(){
        if(wxApis == null){
            synchronized (WxApis.class){
                if(wxApis == null){
                    wxApis = HttpUtils.getInstance().getApiserver(WxApis.HOST,WxApis.class);
                }
            }
        }
        return wxApis;
    }

    /**
     * 获取干货的api接口
     * @return
     */
    public static GankApis getGankApis(){
        if(gankApis == null){
            synchronized (GankApis.class){
                if(gankApis == null){
                    gankApis = HttpUtils.getInstance().getApiserver(GankApis.HOST,GankApis.class);
                }
            }
        }
        return gankApis;
    }
    /**
     * 获取用户信息的api接口
     * @return
     */
    public static IMyserver getIMyserver(){
        if(iMyserver == null){
            synchronized (IMyserver.class){
                if(iMyserver == null){
                    iMyserver = HttpUtils.getInstance().getApiserver(IMyserver.base_url, IMyserver.class);
                }
            }
        }
        return iMyserver;
    }

}
