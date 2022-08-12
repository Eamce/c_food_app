package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ListView;

import java.util.ArrayList;

public class Admin_View_Ordered extends AppCompatActivity {
    Cursor row;
    SQLiteDatabase sqLiteDatabase;
    String path;
    ListView list_item;
    ArrayList<AdminViewOrderedClass> viewOrderedClasses;
    String id,description,price,quantity,total,user_id,username,address,contact,status,cat_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_ordered);
        init();

    }

    public void init(){
        list_item = (ListView) findViewById(R.id.list_item);
        path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
    }
    public void getOrdered(){
        while (row.moveToNext()){
            id = row.getString(0);
            description = row.getString(0);
            price = row.getString(0);
            quantity = row.getString(0);
            total = row.getString(0);
            user_id = row.getString(0);
            username = row.getString(0);
            address = row.getString(0);
            contact = row.getString(0);
            cat_image = row.getString(0);
            byte[] decodedString = Base64.decode(cat_image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            viewOrderedClasses.add(new AdminViewOrderedClass(id,description,price,quantity,total,user_id,username,address,contact,status,cat_image));
        }
    }
}