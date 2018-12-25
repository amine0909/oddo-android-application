package com.example.amine.androidoddo.RecyclerView;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amine.androidoddo.R;

public class StageHolder extends RecyclerView.ViewHolder{
    public View view;
    public TextView stageTitle;
    public ImageView imageView;
    public Long id;


    public StageHolder(View view) {
        super(view);
        this.view = view;
        this.stageTitle = view.findViewById(R.id.titleStage);
        this.imageView = view.findViewById(R.id.stageImage);
    }

}
