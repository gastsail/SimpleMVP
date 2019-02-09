package com.gaston.macbook.simplemvp.model;

import com.google.gson.annotations.SerializedName;

public class Apod {

    private String explanation;
    private String hdurl;
    @SerializedName("url")
    private String lowresurl;
    private String title;
    private String date;
    private String copyright;

    public String getDate() {
        return date;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getLowresurl() {
        return lowresurl;
    }

    public String getTitle() {
        return title;
    }

}
