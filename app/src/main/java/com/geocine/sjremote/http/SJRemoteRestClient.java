package com.geocine.sjremote.http;

/**
 * Created by Aivan on 4/9/2015.
 */
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.apache.http.Header;

public class SJRemoteRestClient {
    private static final String BASE_URL = "http://192.168.1.254/?custom=1";
    public static Integer PHOTO_MODE = 0;
    public static Integer VIDEO_MODE = 1;
    private static String[] mode = { "photo" , "video" };
    private static String[] record = { "stopped" , "recording" };

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d("URL", getAbsoluteUrl(url));
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void takePhoto(final Context context){

        //HttpGetter get = new HttpGetter();
        //get.execute(getAbsoluteUrl("&cmd=1001"));
        SJRemoteRestClient.get("&cmd=1001", null, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //Toast.makeText(context, "Photo success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(context, "Cannot take photo!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void switchMode(final Context context, final Integer mode){

        SJRemoteRestClient.get("&cmd=3001&par="+mode, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //Toast.makeText(context, "Now in " + SJRemoteRestClient.mode[mode] + " mode !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "Cannot change modes!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void toggleRecord(final Context context, final Boolean start){
        final Integer mode = start ? 1 : 0;
        SJRemoteRestClient.get("&cmd=2001&par="+mode, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //Toast.makeText(context, "Video is now "+ SJRemoteRestClient.record[mode] + " !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "Cannot toggle record!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
