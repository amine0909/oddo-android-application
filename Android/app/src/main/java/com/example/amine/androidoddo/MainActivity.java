package com.example.amine.androidoddo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.amine.androidoddo.Controllers.ActivityController;
import com.example.amine.androidoddo.Controllers.TaskController;
import com.example.amine.androidoddo.Interfaces.IResult;
import com.example.amine.androidoddo.RecyclerView.ActivityAdapter;
import com.example.amine.androidoddo.RecyclerView.TaskAdapter;
import com.example.amine.androidoddo.VolleyServices.VolleyService;
import com.example.amine.androidoddo.config.Config;
import com.example.amine.androidoddo.models.Stage;
import com.example.amine.androidoddo.models.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public TaskAdapter taskAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Liste des taches");

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.GONE);

        ProgressBar progressBar = findViewById(R.id.progressBar);


        Intent intent = getIntent();
        Long project_id = intent.getLongExtra("project_id",0);
        Long stage_id = intent.getLongExtra("stage_id", 0);


        // store the project_id on shared preferences
        // need it to use it in intent from alertDialog
        SharedPreferences sharedPreferences = getSharedPreferences("current_project_id", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("project_id",project_id);
        editor.apply();




        TaskController taskController = new  TaskController(this,taskAdapter,recyclerView,progressBar,stage_id,project_id);


    }


    public void showStageDialog(View view) {
        TextView Text_task_id = findViewById(R.id.task_id);
        Long task_id = Long.parseLong(Text_task_id.getText().toString());

        //changeTaskStage.project_id = project_id;


        // store the task_d on shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("task_id_selected", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("task_id",task_id);
        editor.apply();




        changeTaskStage dialog = new changeTaskStage();
        dialog.show(getSupportFragmentManager(),"dialog stage");



    }








}
