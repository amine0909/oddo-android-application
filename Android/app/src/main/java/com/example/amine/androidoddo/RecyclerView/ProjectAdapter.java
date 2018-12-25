package com.example.amine.androidoddo.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amine.androidoddo.R;
import com.example.amine.androidoddo.StageActivity;
import com.example.amine.androidoddo.models.Project;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectHolder>{

    public ArrayList<Project> projects;
    public Context context;

    public ProjectAdapter(ArrayList<Project> projects, Context context) {
        this.projects = projects;
        this.context = context;
    }
    @Override
    public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View projectInfo = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_layout, parent,false);
        return new ProjectHolder(projectInfo);
    }

    @Override
    public void onBindViewHolder(final ProjectHolder holder, int position) {
        holder.converImage.setImageResource(R.drawable.img_basketball);
        holder.projectTitle.setText(this.projects.get(position).getName());
        holder.id = this.projects.get(position).getId();
        // when a user click on the card, it will takes him to the stage list
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), StageActivity.class);
                intent.putExtra("project_id",holder.id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.projects.size();
    }
}
