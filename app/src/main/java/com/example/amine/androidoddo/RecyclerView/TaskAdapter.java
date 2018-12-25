package com.example.amine.androidoddo.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amine.androidoddo.activityTaskActivity;
import com.example.amine.androidoddo.R;
import com.example.amine.androidoddo.config.Config;
import com.example.amine.androidoddo.models.Task;

import java.util.ArrayList;


public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
    ArrayList<Task> tasks;
    public Context context;

   public TaskAdapter(ArrayList<Task> tasks,Context context){
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View taskLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
        TaskHolder itemHolder = new TaskHolder(taskLayout);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(final TaskHolder holder, int position) {
        holder.id = tasks.get(position).getId();
        holder.nameTask.setText(tasks.get(position).getName());
        holder.TaskDescription.append(tasks.get(position).getDescription());
        holder.taskStatus.append(tasks.get(position).getStage_name());
        holder.TaskStartDate.append(tasks.get(position).getStart_date());
        holder.TaskDeadline.append(tasks.get(position).getDeadLine());
        holder.coverImg.setImageResource(R.drawable.img_basketball);
        holder.task_id.setText(tasks.get(position).getId().toString());
        holder.view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,activityTaskActivity.class);
                intent.putExtra("task_id", holder.id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
