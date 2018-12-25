package com.example.amine.androidoddo.RecyclerView;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amine.androidoddo.R;

public class ProjectHolder extends RecyclerView.ViewHolder{

    public ImageView converImage;
    public TextView projectTitle;
    public View view;
    public Long id;

    public ProjectHolder(View view) {
        super(view);
        this.view = view;
        this.projectTitle = view.findViewById(R.id.projectTitle);
        this.converImage = view.findViewById(R.id.ProjectcoverImage);
    }
}
