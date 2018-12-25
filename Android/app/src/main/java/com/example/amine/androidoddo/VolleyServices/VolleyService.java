package com.example.amine.androidoddo.VolleyServices;


import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.amine.androidoddo.Interfaces.IResult;
import com.example.amine.androidoddo.Interfaces.IResultLogin;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyService  {
    IResult mResultCallback = null;
    public IResultLogin iResultLoginCallback = null;
    Context mContext;


    public VolleyService(IResult resultCallback, Context context){
        mResultCallback = resultCallback;
        mContext = context;
    }


    public void getListTaskByProject(String url) {
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonArrayRequest jsonObj = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(jsonArray);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(error);
                }
            });

            queue.add(jsonObj);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // the task id is included in the url
    public void getListActivitiesByTask(String url) {
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonArrayRequest jsonObj = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(jsonArray);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(error);
                }
            });

            queue.add(jsonObj);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    // get all project  by user id
    public void getListProjectsByUserId(String url) {

        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonRequest jsonObj = new JsonArrayRequest(Request.Method.POST, url,null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(jsonArray);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(error);
                }
            });

            queue.add(jsonObj);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    // get all stage for a specific project
    public void getStageForProject(String url) {
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonArrayRequest jsonObj = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(jsonArray);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(error);
                }
            });

            queue.add(jsonObj);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    // login
    public void login(String url, final String email, final String password) {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", email);
            jsonObject.put("pass", password);

            System.out.println("email : "+email);
            System.out.println("password: "+password);
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.POST, url,jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(iResultLoginCallback != null)
                        iResultLoginCallback.notifySuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(iResultLoginCallback != null)
                        iResultLoginCallback.notifyError(error);
                }
            });


            queue.add(jsonObj);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    //update stage for task
    public void updateStage(String url) {

        try {
            JSONObject jsonObject = new JSONObject();
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.PUT, url,jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(iResultLoginCallback != null)
                        iResultLoginCallback.notifySuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(iResultLoginCallback != null)
                        iResultLoginCallback.notifyError(error);
                }
            });


            queue.add(jsonObj);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
