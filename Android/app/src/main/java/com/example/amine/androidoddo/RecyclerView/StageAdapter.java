package com.example.amine.androidoddo.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amine.androidoddo.MainActivity;
import com.example.amine.androidoddo.R;
import com.example.amine.androidoddo.models.Stage;

import java.util.ArrayList;

public class StageAdapter extends RecyclerView.Adapter<StageHolder>{


    public Context context;
    public ArrayList<Stage> stages;
    private Long projectId;

    public StageAdapter(ArrayList<Stage> stages, Context context,Long projectId) {
        this.stages = stages;
        this.context = context;
        this.projectId = projectId;
    }

    @Override
    public StageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View stageLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.stage_layout,parent,false);
        return new StageHolder(stageLayout);

    }

    @Override
    public void onBindViewHolder(final StageHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.img_stage);
        holder.stageTitle.setText("Stage : "+this.stages.get(position).getName());
        holder.id = this.stages.get(position).getId();

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mainactivity == task activity
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("project_id",projectId);
                intent.putExtra("stage_id",holder.id);



                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.stages.size();
    }
}
