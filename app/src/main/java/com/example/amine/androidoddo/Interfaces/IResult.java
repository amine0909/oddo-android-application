package com.example.amine.androidoddo.Interfaces;


import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IResult {
    void notifySuccess(JSONArray response);
    void notifyError(VolleyError error);
}
