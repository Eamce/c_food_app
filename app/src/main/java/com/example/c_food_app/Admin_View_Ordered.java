package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Admin_View_Ordered extends AppCompatActivity {
    Cursor row;
    SQLiteDatabase sqLiteDatabase;
    String path;
    ListView list_item_view;
    Msgbox msgbox;
    Context context = this;
    Globalvars globalvars;
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
        globalvars = new Globalvars(getApplicationContext(),this);
        msgbox = new Msgbox(context);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent logout = new Intent(Admin_View_Ordered.this, Login.class);
            startActivity(logout);
            finish();
        } else if(item.getItemId()==R.id.logout){
            msgbox.showyesno( "Hello","Are you sure you want to log out?");
            msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
                @Override
                public void onyes() {
                    Intent logout = new Intent(Admin_View_Ordered.this, Login.class);
                    startActivity(logout);
                    finish();
                    globalvars.logout();
                }
                @Override
                public void onno() {
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}