package com.animee.forecast.juhe;

public class URLUtils {
//864bdd5bfbaa5474078aa98ec94ed947 备用key 6bbd74466162e1c811e84fa5fd89ea40
    public static final  String KEY = "864bdd5bfbaa5474078aa98ec94ed947";
    public static String temp_url = "http://apis.juhe.cn/simpleWeather/query";

    public static String index_url = "http://apis.juhe.cn/simpleWeather/life";

    public static String getTemp_url(String city){
        String url = temp_url+"?city="+city+"&key="+KEY;
        return url;
    }

    public static String getIndex_url(String city){
        String url = index_url+"?city="+city+"&key="+KEY;
        return url;
    }
}
