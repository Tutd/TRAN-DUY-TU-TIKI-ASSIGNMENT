package com.example.tiki_assignment.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class FetchingKeyWordData {

    private LoadKeyWordListener mListener;
    private Context mContext;

    public FetchingKeyWordData(LoadKeyWordListener listener, Context context) {
        this.mListener = listener;
        this.mContext = context;
    }

    /**
     * Fetching data from server
     */
    public void fetchingKeyWordData() {
        String url ="https://raw.githubusercontent.com/tikivn/android-home-test/v2/keywords.json";
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest strRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(getClass().getSimpleName(), "onResponse " + response);
                mListener.onLoadKeyWordSuccess(handleData(response));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getClass().getSimpleName(), "error " + error);
                mListener.onLoadKeyWordFailure();
            }
        });
        queue.add(strRequest);
    }

    private ArrayList<String> handleData(String response) {
        Gson gson = new Gson();
        String[] arr = gson.fromJson(response, String[].class);
        return new ArrayList<>(Arrays.asList(arr));
    }
}
