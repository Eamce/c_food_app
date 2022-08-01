package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Checkout_Details extends AppCompatActivity {
        EditText fullname,address;
        Button checkout_btn;
        SQLiteDatabase sqLiteDatabase;

        List<String> towns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_details);
        init();
//            getTown();
//
        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullname.getText().toString().isEmpty() || address.getText().toString().isEmpty() ){
                    Toast.makeText(Checkout_Details.this, "Please fill up information!", Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });
    }

    public void init(){
//        fullname = (EditText) findViewById(R.id.fullname);
//        address  = (EditText) findViewById(R.id.address);
        checkout_btn = (Button) findViewById(R.id.checkout_btn);
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
//        towns=getTown();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
    }

    public List<String> getTown(){
        List<String> town = new ArrayList<String>();
        Cursor row=null;
        row = sqLiteDatabase.rawQuery("Select town_name from towns where status = '1'",null);
        System.out.println("sdfsdfsdfsdf"+row);
            while (row.moveToNext()){
                town.add(row.getString(1));
        }
        return town;
    }


}




