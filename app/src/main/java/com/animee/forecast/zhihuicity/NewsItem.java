package com.animee.forecast.zhihuicity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsItem {
    @SerializedName("title")
    private String title;

    @SerializedName("imgList")
    private List<String> imgList;

    @SerializedName("source")
    private String source;

    @SerializedName("newsId")
    private String newsId;

    @SerializedName("digest")
    private String digest;

    @SerializedName("postTime")
    private String postTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }
}
