package com.example.amine.androidoddo.config;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Config {

    //public static String url1 = "http://192.168.1.5/odoo_json_api/getTasksByProjectIdAndUserId.php";
    //public static String url2 = "http://192.168.1.5/odoo_json_api/login.php";
    //public static String url3 = "http://192.168.1.5/odoo_json_api/getActivitiesFromTask.php";
    public static String url4 = "http://192.168.137.79:3000/api/projectsByUserID";
    public static String url5 = "http://192.168.137.79:3000/api/stages";
    public static String url6 = "http://192.168.137.79:3000/api/getTasksByProjectIdAndUserIdAndStageID";
    public static String url7 = "http://192.168.137.79:3000/api/getActivitiesFromTask";
    public static String url8 = "http://192.168.137.79:3000/api/login";
    public static String url9 = "http://192.168.137.79:3000/api/task";


    public Context context;

    public Config(Context context) {
        this.context = context;
    }



    public String generateUrlGetProject() {
        // get the user is from shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo",0);
        Long user_id = sharedPreferences.getLong("user_id",0);
        String password = sharedPreferences.getString("password","");
        String email = sharedPreferences.getString("email","");


        return url4+"?user_id="+user_id+"&password="+password+"&email="+email;
    }


    public String generateUrlGetStage(Long id) {


        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo",0);
        String password = sharedPreferences.getString("password","");
        String email = sharedPreferences.getString("email","");


        return url5+"?project_id="+id+"&email="+email+"&password="+password;
    }

    public String generateUrlGetTask(Long stage_id,Long project_id) {
        // get the user is from shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo",0);
        Long user_id = sharedPreferences.getLong("user_id",0);
        String password = sharedPreferences.getString("password","");
        String email = sharedPreferences.getString("email","");

        return url6+"?project_id="+project_id+"&user_id="+user_id+"&stage_id="+stage_id+"&email="+email+"&password="+password;
    }


    public String generateUrlGetActivity(Long task_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo",0);
        String password = sharedPreferences.getString("password","");
        String email = sharedPreferences.getString("email","");


        return url7+"?task_id="+task_id+"&email="+email+"&password="+password;
    }


    public String generateUrlUpdateTaskStage(Long task_id, Long stage_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo",0);
        String password = sharedPreferences.getString("password","");
        String email = sharedPreferences.getString("email","");

        return url9+"?task_id="+task_id+"&stage_id="+stage_id+"&email="+email+"&password="+password;
    }

}
