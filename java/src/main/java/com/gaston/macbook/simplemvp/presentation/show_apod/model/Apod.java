package com.gaston.macbook.simplemvp.presentation.show_apod.model;

import com.google.gson.annotations.SerializedName;

public class Apod {

    private String explanation;
    private String hdurl;
    @SerializedName("url")
    private String lowresurl;
    private String title;
    private String date;
    private String copyright;

    public Apod(String explanation, String hdurl, String lowresurl, String title) {
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.lowresurl = lowresurl;
        this.title = title;
    }

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
