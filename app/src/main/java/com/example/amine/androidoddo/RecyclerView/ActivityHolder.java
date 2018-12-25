package com.example.amine.androidoddo.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.amine.androidoddo.R;


public class ActivityHolder extends RecyclerView.ViewHolder {
    public TextView activityTitle,activityDeadline,activityDescription;
    public Long activityId;


    public ActivityHolder(View itemView) {
        super(itemView);

        this.activityTitle = itemView.findViewById(R.id.activityTitle);
        this.activityDeadline = itemView.findViewById(R.id.activityDeadline);
        this.activityDescription = itemView.findViewById(R.id.activityDescription);

    }
}
