package com.animee.forecast.juhe;

import java.util.List;
/*
* 聚合平台接口 提供最近5天的天气情况
* */
public class JHTempBean {
    /**
     * reason : 查询成功!
     * result : {"city":"绵阳","realtime":{"temperature":"26","humidity":"84","info":"阴","wid":"02","direct":"北风","power":"2级","aqi":"29"},"future":[{"date":"2025-07-02","temperature":"23/28℃","weather":"阵雨转暴雨","wid":{"day":"03","night":"10"},"direct":"持续无风向"},{"date":"2025-07-03","temperature":"24/28℃","weather":"阵雨","wid":{"day":"03","night":"03"},"direct":"持续无风向"},{"date":"2025-07-04","temperature":"24/29℃","weather":"阵雨","wid":{"day":"03","night":"03"},"direct":"持续无风向"},{"date":"2025-07-05","temperature":"22/28℃","weather":"小雨","wid":{"day":"07","night":"07"},"direct":"持续无风向"},{"date":"2025-07-06","temperature":"22/28℃","weather":"小雨","wid":{"day":"07","night":"07"},"direct":"持续无风向"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * city : 绵阳
         * realtime : {"temperature":"26","humidity":"84","info":"阴","wid":"02","direct":"北风","power":"2级","aqi":"29"}
         * future : [{"date":"2025-07-02","temperature":"23/28℃","weather":"阵雨转暴雨","wid":{"day":"03","night":"10"},"direct":"持续无风向"},{"date":"2025-07-03","temperature":"24/28℃","weather":"阵雨","wid":{"day":"03","night":"03"},"direct":"持续无风向"},{"date":"2025-07-04","temperature":"24/29℃","weather":"阵雨","wid":{"day":"03","night":"03"},"direct":"持续无风向"},{"date":"2025-07-05","temperature":"22/28℃","weather":"小雨","wid":{"day":"07","night":"07"},"direct":"持续无风向"},{"date":"2025-07-06","temperature":"22/28℃","weather":"小雨","wid":{"day":"07","night":"07"},"direct":"持续无风向"}]
         */

        private String city;
        private RealtimeBean realtime;
        private List<FutureBean> future;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public RealtimeBean getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeBean realtime) {
            this.realtime = realtime;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class RealtimeBean {
            /**
             * temperature : 26
             * humidity : 84
             * info : 阴
             * wid : 02
             * direct : 北风
             * power : 2级
             * aqi : 29
             */

            private String temperature;
            private String humidity;
            private String info;
            private String wid;
            private String direct;
            private String power;
            private String aqi;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getWid() {
                return wid;
            }

            public void setWid(String wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }
        }

        public static class FutureBean {
            /**
             * date : 2025-07-02
             * temperature : 23/28℃
             * weather : 阵雨转暴雨
             * wid : {"day":"03","night":"10"}
             * direct : 持续无风向
             */

            private String date;
            private String temperature;
            private String weather;
            private WidBean wid;
            private String direct;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WidBean getWid() {
                return wid;
            }

            public void setWid(WidBean wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public static class WidBean {
                /**
                 * day : 03
                 * night : 10
                 */

                private String day;
                private String night;

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getNight() {
                    return night;
                }

                public void setNight(String night) {
                    this.night = night;
                }
            }
        }
    }
}
