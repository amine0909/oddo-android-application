package com.example.amine.androidoddo.Interfaces;


import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IResultLogin {

    void notifySuccess(JSONObject response);
    void notifyError(VolleyError error);
}
