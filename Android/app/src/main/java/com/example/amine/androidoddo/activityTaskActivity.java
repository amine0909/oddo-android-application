package com.example.amine.androidoddo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.amine.androidoddo.Controllers.ActivityController;
import com.example.amine.androidoddo.RecyclerView.ActivityAdapter;

public class activityTaskActivity extends AppCompatActivity {
    ActivityAdapter activityAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        setTitle("Liste des activitées");


        RecyclerView activityRecycler = findViewById(R.id.activityRecycler);
        activityRecycler.setLayoutManager(new LinearLayoutManager(this));
        activityRecycler.setVisibility(View.GONE);

        Intent intent = getIntent();
        Long task_id = intent.getLongExtra("task_id", 0);

        progressBar = findViewById(R.id.activityProgress);

        ActivityController activityController = new ActivityController(this,activityRecycler, activityAdapter,progressBar, task_id);
    }



}
