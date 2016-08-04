package com.myblue.bean;

/**
 * Desc:
 * Created by wangdexin on 2016/8/3.
 */
public class CityBean {
    private static final String TAG = "CityBean";
    private String imageUrl;
    private String cityName;

    public CityBean(String imageUrl, String cityName) {
        this.imageUrl = imageUrl;
        this.cityName = cityName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
