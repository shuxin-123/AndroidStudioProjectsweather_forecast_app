package com.animee.forecast.zhihuicity.dang;

public class News {
    private String title;
    private String date;

    public News(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}