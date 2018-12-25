package com.example.amine.androidoddo.Controllers;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.example.amine.androidoddo.Interfaces.IResult;
import com.example.amine.androidoddo.RecyclerView.TaskAdapter;
import com.example.amine.androidoddo.VolleyServices.VolleyService;
import com.example.amine.androidoddo.config.Config;
import com.example.amine.androidoddo.models.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskController {

    private final String TAG  = "TaskController";
    private IResult mResultCallback;
    private VolleyService mVolleyService;
    public ArrayList<Task> tasks;
    public TaskAdapter taskAdapter;
    public RecyclerView recyclerView;
    public ProgressBar progressBar;
    public Context context;

    public Long project_id, stage_id;
    public Config config;

    public TaskController(Context context,TaskAdapter taskAdapter,RecyclerView recyclerView,ProgressBar progressBar,Long stage_id,Long project_id){
        this.tasks = new ArrayList<>();
        this.initCallbackResults();
        this.mVolleyService = new VolleyService(mResultCallback, context);
        this.context = context;
        this.config = new Config(context);
        this.mVolleyService.getListTaskByProject(this.config.generateUrlGetTask(stage_id,project_id));
        this.recyclerView = recyclerView;
        this.taskAdapter = taskAdapter;
        this.progressBar = progressBar;
        this.stage_id = stage_id;
        this.project_id = project_id;
    }



    private void initCallbackResults() {
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(JSONArray jsonArray) {
                Log.d(TAG, "result" + jsonArray.length());

                ArrayList<Task> taskArrayList = fetchObject(jsonArray);

                System.out.println(Config.url6);
                // register the adapter
                taskAdapter = new TaskAdapter(taskArrayList,context);

                recyclerView.setAdapter(taskAdapter);

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void notifyError(VolleyError error) {
                Log.d(TAG, "Volley requester " + error.getMessage());

            }
        };
    }



    private ArrayList<Task> fetchObject(JSONArray jsonArray) {

        System.out.println(jsonArray.toString());
        ArrayList<Task> list = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++) {
            Task task = new Task();
            try {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                task.setId(jsonObject.getLong("id"));
                task.setName(jsonObject.getString("name"));

                // format date start


                String dateString = jsonObject.getString("date_start").substring(0,9);
                task.setStart_date(dateString);


                task.setDeadLine(jsonObject.getString("date_deadline"));

                JSONArray stage_id = (JSONArray) jsonObject.get("stage_id");
                task.setStage_name(stage_id.getString(1));

                JSONArray user_id = (JSONArray) jsonObject.get("user_id");
                task.setEmployee_name(user_id.getString(1));

                task.setProject_id(this.project_id);


                String cleanDescription = Html.fromHtml(jsonObject.getString("description")).toString();
                task.setDescription(cleanDescription.trim());
                list.add(task);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return list;
    }
}
