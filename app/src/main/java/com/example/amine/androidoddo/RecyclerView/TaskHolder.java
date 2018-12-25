package com.example.amine.androidoddo.RecyclerView;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.amine.androidoddo.activityTaskActivity;

import com.example.amine.androidoddo.R;

public class TaskHolder extends RecyclerView.ViewHolder{
    public Long id;
    public TextView nameTask,TaskDescription,taskStatus,TaskStartDate,TaskDeadline,task_id;

    public ImageView coverImg;
    public View view;
    TaskHolder(View view){

        super(view);
        this.view = view;
        this.nameTask = view.findViewById(R.id.nameTask);
        this.TaskDescription = view.findViewById(R.id.TaskDescription);
        this.taskStatus = view.findViewById(R.id.TaskStatus);
        this.TaskStartDate = view.findViewById(R.id.TaskStartDate);
        this.TaskDeadline = view.findViewById(R.id.TaskDeadline);
        this.coverImg = view.findViewById(R.id.coverImage);
        this.task_id = view.findViewById(R.id.task_id);

    }

}
