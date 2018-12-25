package com.example.amine.androidoddo.Controllers;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.example.amine.androidoddo.Interfaces.IResult;
import com.example.amine.androidoddo.RecyclerView.ProjectAdapter;
import com.example.amine.androidoddo.RecyclerView.StageAdapter;
import com.example.amine.androidoddo.VolleyServices.VolleyService;
import com.example.amine.androidoddo.config.Config;
import com.example.amine.androidoddo.models.Project;
import com.example.amine.androidoddo.models.Stage;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StageController {

    private final String TAG  = "Stage Controller";
    private IResult mResultCallback;
    private VolleyService mVolleyService;
    public StageAdapter stageAdapter;
    public ArrayList<Stage> stages;
    public RecyclerView recyclerView;
    public ProgressBar progressBar;
    public Context context;
    private Config config;
    public Long projectID;

    public StageController(Context context,RecyclerView recyclerView,ProgressBar progressBar, StageAdapter stageAdapter, Long projectID) {
        this.stages = new ArrayList<>();
        this.context = context;
        this.projectID = projectID;
        this.initCallbackResults();
        this.mVolleyService = new VolleyService(mResultCallback, context);
        this.config = new Config(this.context);
        this.mVolleyService.getStageForProject(this.config.generateUrlGetStage(projectID));
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.stageAdapter = stageAdapter;

    }



    private void initCallbackResults() {
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(JSONArray response) {
                Log.d(TAG, "result" + response.length());

                fetchObject(response);


                stageAdapter = new StageAdapter(stages,context,projectID);
                recyclerView.setAdapter(stageAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                // store stage on SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences("stages", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Gson gson = new Gson();
                String stage_stringify = gson.toJson(stages);
                editor.putString("stages", stage_stringify);
                editor.apply();


            }

            @Override
            public void notifyError(VolleyError error) {
                Log.d(TAG, "Volley requester " + error);

            }
        };
    }


    private void fetchObject(JSONArray response) {
        for(int i=0;i<response.length();i++) {
            try {
                JSONObject jsonObject = (JSONObject)response.get(i);
                Stage stage = new Stage();
                stage.setId(jsonObject.getLong("id"));
                stage.setName(jsonObject.getString("name"));
                stages.add(stage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



}
