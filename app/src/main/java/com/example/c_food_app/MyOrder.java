package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class MyOrder extends AppCompatActivity {

    TextView address;
    ListView order_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
    }

    public void init(){
        address    = (TextView) findViewById(R.id.address);
        order_list = (ListView) findViewById(R.id.order_list);
    }
}