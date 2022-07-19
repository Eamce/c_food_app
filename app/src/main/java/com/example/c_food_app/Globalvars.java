package com.example.c_food_app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Globalvars {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static Context context;
    public static Activity activity;

    public Globalvars(Context context, Activity act){
        prefs = act.getSharedPreferences("Global_vars",context.MODE_PRIVATE);
        editor = prefs.edit();
    }
    public void logout()
    {
        editor.clear();
        editor.commit();
    }
    public void set(String varname, String varval)
    {
     //   editor = prefs.edit();
        editor.putString(varname,varval);
        editor.commit();
    }
    public String get(String varname)
    {
            return prefs.getString(varname,"");
    }
}