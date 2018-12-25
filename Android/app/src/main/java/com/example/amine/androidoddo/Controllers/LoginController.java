package com.example.amine.androidoddo.Controllers;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.android.volley.VolleyError;
import com.example.amine.androidoddo.Interfaces.IResult;
import com.example.amine.androidoddo.Interfaces.IResultLogin;
import com.example.amine.androidoddo.MainActivity;
import com.example.amine.androidoddo.ProjectActivity;
import com.example.amine.androidoddo.RecyclerView.TaskAdapter;
import com.example.amine.androidoddo.VolleyServices.VolleyService;
import com.example.amine.androidoddo.config.Config;
import com.example.amine.androidoddo.loginActivity;
import com.example.amine.androidoddo.models.Activity;
import com.example.amine.androidoddo.models.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginController {

    public final String TAG = "Login COntroller";
    private IResultLogin iResultLoginCallback;
    private VolleyService mVolleyService;
    public Context context;
    public loginActivity loginActivity;

    private String password;
    private String email;

    public LoginController(Context context, String email, String password, loginActivity login) {
        this.context = context;
        this.loginActivity = login;
        this.initCallbackResults();
        this.mVolleyService = new VolleyService(null,context);
        this.mVolleyService.iResultLoginCallback = iResultLoginCallback;

        this.password = password;
        this.email = email;
        this.mVolleyService.login(Config.url8,email,password);

    }

    private void initCallbackResults() {

        iResultLoginCallback = new IResultLogin() {
            @Override

            // there is an error on context ==> it says it is null
            public void notifySuccess(JSONObject response) {

                Log.d(TAG, "result" + response);

                AlertDialog alert1Dialog = null;
                try {
                    Long uid = response.getLong("uid");
                    if(uid != 0) {

                        // save the user id in the shared preferences
                        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putLong("user_id", uid);
                        editor.putString("password", password);
                        editor.putString("email", email);
                        editor.apply();

                        Intent intent = new Intent(context, ProjectActivity.class);
                        context.startActivity(intent);

                    }else {

                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setMessage("Adresse email / mot de passe sont incorrects");
                        alertBuilder.setTitle("Erreur");

                        alertBuilder.setPositiveButton("Ok",null);

                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                Log.d(TAG, "Volley requester " + error.getMessage());

            }
        };
    }
}
