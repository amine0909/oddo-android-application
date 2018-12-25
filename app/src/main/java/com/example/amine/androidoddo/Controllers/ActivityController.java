package com.example.amine.androidoddo.Controllers;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.example.amine.androidoddo.Interfaces.IResult;
import com.example.amine.androidoddo.RecyclerView.ActivityAdapter;
import com.example.amine.androidoddo.RecyclerView.TaskAdapter;
import com.example.amine.androidoddo.VolleyServices.VolleyService;
import com.example.amine.androidoddo.config.Config;
import com.example.amine.androidoddo.models.Activity;
import com.example.amine.androidoddo.models.Stage;
import com.example.amine.androidoddo.models.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityController {

    private IResult mResultCallback;
    private VolleyService mVolleyService;
    public ArrayList<Activity> activities;
    public RecyclerView activityRecycler;
    public ProgressBar progressBar;
    public ActivityAdapter activityAdapter;
    public Context context;
    public Long task_id;
    public Config config;

    public ActivityController(Context context,RecyclerView activityRecycler, ActivityAdapter activityAdapter,ProgressBar progressBar, Long task_id){
        this.activities = new ArrayList<>();
        this.initCallbackResults();
        this.mVolleyService = new VolleyService(mResultCallback, context);
        this.context = context;
        this.config = new Config(context);
        this.mVolleyService.getListActivitiesByTask(config.generateUrlGetActivity(task_id));
        this.activityRecycler = activityRecycler;
        this.activityAdapter = activityAdapter;
        this.progressBar = progressBar;
        this.task_id = task_id;
    }

    private void initCallbackResults() {
        this.mResultCallback = new IResult() {
            @Override
            public void notifySuccess(JSONArray response) {
                fetchObject(response);
                activityAdapter = new ActivityAdapter(activities);
                activityRecycler.setAdapter(activityAdapter);

                progressBar.setVisibility(View.GONE);
                activityRecycler.setVisibility(View.VISIBLE);


            }

            @Override
            public void notifyError(VolleyError error) {
                throw new RuntimeException(error.getMessage());
            }
        };
    }



    private void fetchObject(JSONArray jsonArray) {

        for(int i=0;i<jsonArray.length();i++) {
            try {
                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                Activity activity = new Activity();
                activity.setId(jsonObject.getLong("id"));

                // get activity Name
                // its a json array
                JSONArray activity_type_id = jsonObject.getJSONArray("activity_type_id");
                activity.setTitle(activity_type_id.getString(1));

                activity.setDeadline(jsonObject.getString("date_deadline"));

                String activityDesc = Html.fromHtml(jsonObject.getString("note")).toString();
                activity.setDescription(activityDesc.trim());

                this.activities.add(activity);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
