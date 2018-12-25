package com.example.amine.androidoddo.Controllers;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.example.amine.androidoddo.Interfaces.IResult;
import com.example.amine.androidoddo.RecyclerView.ProjectAdapter;
import com.example.amine.androidoddo.RecyclerView.TaskAdapter;
import com.example.amine.androidoddo.VolleyServices.VolleyService;
import com.example.amine.androidoddo.config.Config;
import com.example.amine.androidoddo.models.Project;
import com.example.amine.androidoddo.models.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProjectController {

    private final String TAG  = "Project Controller";
    private IResult mResultCallback;
    private VolleyService mVolleyService;
    public ProjectAdapter projectAdapter;
    public ArrayList<Project> projects;
    public RecyclerView recyclerView;
    public ProgressBar progressBar;
    public Context context;
    private Config config;

    public ProjectController(Context context,RecyclerView recyclerView,ProgressBar progressBar, ProjectAdapter projectAdapter) {
        this.projects = new ArrayList<>();
        this.context = context;
        this.initCallbackResults();
        this.mVolleyService = new VolleyService(mResultCallback, context);
        this.config = new Config(this.context);
        this.mVolleyService.getListProjectsByUserId(this.config.generateUrlGetProject());
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.projectAdapter = projectAdapter;
    }




    private void initCallbackResults() {
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(JSONArray response) {
                Log.d(TAG, "result" + response.length());

                fetchObject(response);


                projectAdapter = new ProjectAdapter(projects,context);
                recyclerView.setAdapter(projectAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                System.out.println(Config.url4);

                for (Project t: projects) {
                    System.out.println(t);
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                Log.d(TAG, "Volley requester " + error);

            }
        };
    }


    private void fetchObject(JSONArray jsonArray) {
        try {
            System.out.println(jsonArray.get(0).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<jsonArray.length();i++) {
            try {

                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                Project project = new Project();

                JSONArray jsonArray1 = (JSONArray) jsonObject.get("project_id");
                project.setId(jsonArray1.getLong(0));

                project.setName(jsonArray1.get(1).toString());

                this.projects.add(project);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
