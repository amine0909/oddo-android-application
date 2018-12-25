package com.example.amine.androidoddo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.amine.androidoddo.Controllers.StageController;
import com.example.amine.androidoddo.RecyclerView.StageAdapter;

public class StageActivity extends AppCompatActivity {

    public Long projectId;
    public StageAdapter stageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);
        setTitle("Liste des stages");



        Intent intent = getIntent();
        this.projectId = intent.getLongExtra("project_id",0);

        RecyclerView recyclerView = findViewById(R.id.stageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.GONE);

        ProgressBar progressBar = findViewById(R.id.stageProgess);
        StageController stageController = new StageController(this,recyclerView,progressBar,stageAdapter, this.projectId);


        // zid intent fiha project id w aadiha lil controller
        // w aamel update lil config url bil project id heka
    }
}
