package com.animee.forecast.zhihuicity;

public class Station {
    private String name;
    private String distance;
    private String time;

    public Station(String name, String distance, String time) {
        this.name = name;
        this.distance = distance;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
