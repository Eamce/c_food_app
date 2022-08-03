package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyOrder extends AppCompatActivity {

    TextView address;
    ListView order_list;
    SQLiteDatabase sqLiteDatabase;
    Globalvars globalvars;
    Msgbox msgbox;
    Context context= this;
    String id, description,price,quantity,total,username,str_address,cat_image,contact;
    ArrayList<My_Order_Class> my_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        init();
        Cursor row = sqLiteDatabase.rawQuery("Select * from tbl_order",null);
        while(row.moveToNext()){
            id          = row.getString(0);
            description = row.getString(1);
            price       = row.getString(2);
            quantity    = row.getString(3);
            total       = row.getString(4);
            username    = row.getString(5);
            str_address = row.getString(6);
            contact     = row.getString(7);
            cat_image   = row.getString(8);
            byte[] decodedString = Base64.decode(cat_image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            my_order.add(new My_Order_Class(id, description,price,quantity,total,username,str_address,decodedByte,contact));
            My_Order_Adapter my_order_adapter = new My_Order_Adapter (getApplicationContext(),my_order,this);
            order_list.setAdapter(my_order_adapter);
        }
    }

    public void init(){
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        globalvars = new Globalvars(getApplicationContext(),this);
        address    = (TextView) findViewById(R.id.address);
        msgbox = new Msgbox(context);
        order_list = (ListView) findViewById(R.id.order_list);
        my_order = new ArrayList<My_Order_Class>();
    }
}