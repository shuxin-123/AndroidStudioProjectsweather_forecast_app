package com.animee.forecast.zhihuicity;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    private static final String TAG = "HttpUtil";
    private static final OkHttpClient client = new OkHttpClient();

    public static void getNewsList(String typeId, int page, String appId, String appSecret, final NewsCallback callback) {
        String url = "https://www.mxnzp.com/api/news/list?typeId=" + typeId +
                "&page=" + page +
                "&app_id=" + appId +
                "&app_secret=" + appSecret;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "请求失败: " + e.getMessage());
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String result = response.body().string();
                    callback.onSuccess(result);
                } else {
                    callback.onFailure("响应失败: " + response.code());
                }
            }
        });
    }

    public interface NewsCallback {
        void onSuccess(String result);
        void onFailure(String error);
    }
}

