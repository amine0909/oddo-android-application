package com.example.amine.androidoddo.RecyclerView;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amine.androidoddo.R;
import com.example.amine.androidoddo.models.Activity;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder>{
    private ArrayList<Activity> activities;

    public ActivityAdapter(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View activityInfo = LayoutInflater.from(parent.getContext()).inflate(R.layout.activityinfo, parent,false);
        ActivityHolder activityHolder = new ActivityHolder(activityInfo);
        return activityHolder;
    }

    @Override
    public void onBindViewHolder(ActivityHolder holder, int position) {
        holder.activityId = this.activities.get(position).getId();
        holder.activityTitle.setText("Title : "+ this.activities.get(position).getTitle());
        if(!this.activities.get(position).getDescription().isEmpty()) {
            holder.activityDescription.setText("Description: "+this.activities.get(position).getDescription());
        }else {
            holder.activityDescription.setText("");
        }
        holder.activityDeadline.setText("Deadline date :"+ this.activities.get(position).getDeadline());
    }

    @Override
    public int getItemCount() {
        return this.activities.size();
    }
}
