package com.example.amine.androidoddo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.amine.androidoddo.Interfaces.IResult;
import com.example.amine.androidoddo.VolleyServices.VolleyService;
import com.example.amine.androidoddo.config.Config;
import com.example.amine.androidoddo.models.Stage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class changeTaskStage extends AppCompatDialogFragment {

    private ArrayList<Stage> stages;
    private View view;
    public  Long stage_id;

    public VolleyService volleyService;
    public IResult resultCallbacks;
    public Config config;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.init();


       // get stages from SharedPreferences
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("stages", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String stage_stringify = sharedPreferences.getString("stages", "");
        Type type = new TypeToken<ArrayList<Stage>>() {}.getType();
        this.stages  = gson.fromJson(stage_stringify,type);





        // get task id
        SharedPreferences sharedPreferences2 = this.getActivity().getSharedPreferences("task_id_selected", Context.MODE_PRIVATE);
        final Long task_id = sharedPreferences2.getLong("task_id",0);

        // get project id
        SharedPreferences sharedPreferences3 = this.getActivity().getSharedPreferences("current_project_id", Context.MODE_PRIVATE);
        final Long project_id = sharedPreferences2.getLong("project_id",0);


        // build dialog
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_change_state, null);

        this.createRadioButton();

        alertBuilder.setView(view)
                            .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    update_task_stage(task_id,stage_id);

                                    // refresh the page
                                    Intent intent = new Intent(getContext(),MainActivity.class);
                                    intent.putExtra("project_id", project_id);
                                    intent.putExtra("stage_id", stage_id);


                                    startActivity(intent);
                                    getActivity().finish();

                                }
                            })
                            .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

        return alertBuilder.create();
    }



    private void createRadioButton() {

        RadioGroup radioGroup = this.view.findViewById(R.id.radioGroup);
        for(Stage stage: stages) {

            System.out.println(stage);


            final RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(stage.getName());
            radioButton.setId(stage.getId().intValue());

            radioGroup.addView(radioButton);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton rb  = view.findViewById(i);
                    stage_id = (long)rb.getId();
                }
            });
        }
    }

    private void init() {
        this.config = new Config(getContext());
        this.initCallbacks();
        this.volleyService = new VolleyService(resultCallbacks,getContext());
    }


    private void update_task_stage(Long task_id, Long stage_id) {
        this.volleyService.updateStage(config.generateUrlUpdateTaskStage(task_id,stage_id));
    }



    private void initCallbacks() {

        resultCallbacks = new IResult() {
            @Override
            public void notifySuccess(JSONArray response) {
                try {
                    Log.d("Alert dialog :", response.get(0).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                System.out.println(error.getMessage());
            }
        };
    }
}
