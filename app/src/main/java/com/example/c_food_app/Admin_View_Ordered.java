package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Admin_View_Ordered extends AppCompatActivity {
    Cursor row;
    SQLiteDatabase sqLiteDatabase;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_ordered);
        init();
        while (row.moveToNext()){

        }
    }

    public void init(){

    }
}