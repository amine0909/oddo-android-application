package com.example.amine.androidoddo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.amine.androidoddo.Controllers.ProjectController;
import com.example.amine.androidoddo.RecyclerView.ProjectAdapter;

public class ProjectActivity extends AppCompatActivity {
    public ProjectAdapter projectAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        setTitle("Liste des projets");

        RecyclerView recyclerView = findViewById(R.id.projectRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.GONE);

        ProgressBar progressBar = findViewById(R.id.projectProgess);


        ProjectController projectController = new ProjectController(this,recyclerView,progressBar,projectAdapter);
    }
}
