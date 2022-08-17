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
    ListView list_item_view;
    ArrayList<AdminViewOrderedClass> viewOrderedClasses;
    String id,description,price,quantity,total,user_id,username,address,contact,status,cat_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_ordered);
        init();
        getOrdered();
    }

    public void init(){
        list_item_view = (ListView) findViewById(R.id.list_item_view);
        path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        row =sqLiteDatabase.rawQuery("Select * from tbl_order",null);
        viewOrderedClasses = new ArrayList<AdminViewOrderedClass>();
    }

    public void getOrdered(){
        while (row.moveToNext()){
            id          = row.getString(0);
            description = row.getString(1);
            price       = row.getString(2);
            quantity    = row.getString(3);
            total       = row.getString(4);
            user_id     = row.getString(5);
            username    = row.getString(6);
            address     = row.getString(7);
            contact     = row.getString(8);
            status      = row.getString(9);
            cat_image   = row.getString(10);
            byte[] decodedString = Base64.decode(cat_image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            viewOrderedClasses.add(new AdminViewOrderedClass(id,description,price,quantity,total,user_id,username,address,contact,status,cat_image));
            AdminViewOrderedAdapter adminViewOrderedAdapter = new AdminViewOrderedAdapter(getApplicationContext(),viewOrderedClasses,this);
            list_item_view.setAdapter(adminViewOrderedAdapter);
        }
    }
}