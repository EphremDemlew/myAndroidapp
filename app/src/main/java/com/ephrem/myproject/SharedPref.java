package com.ephrem.myproject;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences sharedprefe;
    private Context context;

    public SharedPref(Context context){
        this.context = context;
        sharedprefe= context.getSharedPreferences(context.getResources().getString(R.string.login_pref),Context.MODE_PRIVATE);
    }
    public void writeStatus(boolean stat){
        SharedPreferences.Editor editor = sharedprefe.edit();
        editor.putBoolean(context.getResources().getString(R.string.pref_stat),stat);
        editor.commit();
    }
    public boolean readStatus(){
        boolean val=false;
        val=sharedprefe.getBoolean(context.getResources().getString(R.string.pref_stat),false);
        return val;

    }
}

/*
public class sharedPreferenceConfig {


    public sharedPreferenceConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit(); // check 1
        editor.putBoolean(context.getResources().getString(R.string.login_status_preference), status);
        editor.commit();
    }

    public boolean readLoginStatus() {
        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preference),false);
        return status;

    }
}*/
