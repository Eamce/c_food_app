package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Admin_Home extends AppCompatActivity {

    TextView view_customer,view_items,view_purchased,view_account;
    Typeface font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        init();

    }

    public void init(){
        view_customer   = (TextView) findViewById(R.id.view_customer);
        view_items      = (TextView) findViewById(R.id.view_items);
        view_purchased  = (TextView) findViewById(R.id.view_purchased);
        view_account    = (TextView) findViewById(R.id.view_account);
        setfontawesome(R.id.view_customer,"\uf0ca");
        setfontawesome(R.id.view_items,"\uf0ca");
        setfontawesome(R.id.view_purchased,"\uf0ca");
        setfontawesome(R.id.view_account,"\uf0ca");
    }

    public void setfontawesome(int id,String icon){
        TextView txtview = (TextView) findViewById(id);
        txtview.setTypeface(font);
        txtview.setText(icon);
    }
}