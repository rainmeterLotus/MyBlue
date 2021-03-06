package com.myblue.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "BLUE_FEY".
 */
public class BlueFey {

    private Long id;
    private String blueUrl;
    private String blueName;

    public BlueFey() {
    }

    public BlueFey(Long id) {
        this.id = id;
    }

    public BlueFey(Long id, String blueUrl, String blueName) {
        this.id = id;
        this.blueUrl = blueUrl;
        this.blueName = blueName;
    }

    public BlueFey(String blueUrl, String blueName) {
        this.blueUrl = blueUrl;
        this.blueName = blueName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlueUrl() {
        return blueUrl;
    }

    public void setBlueUrl(String blueUrl) {
        this.blueUrl = blueUrl;
    }

    public String getBlueName() {
        return blueName;
    }

    public void setBlueName(String blueName) {
        this.blueName = blueName;
    }

}
