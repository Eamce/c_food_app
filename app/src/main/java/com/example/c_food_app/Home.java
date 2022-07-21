package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.SyncFailedException;
import java.util.ArrayList;

public class Home extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView category_view;
        ArrayList<Category> categoryArrayList = new ArrayList<Category>();
        category_view       = (GridView) findViewById(R.id.grid_categories);
        Drawable shrimp     = this.getResources().getDrawable(R.drawable.shrimp);
        Drawable crabs      = this.getResources().getDrawable(R.drawable.crabs);
        Drawable fish       = this.getResources().getDrawable(R.drawable.fish);
        Drawable squid      = this.getResources().getDrawable(R.drawable.squid);
        Drawable lobster    = this.getResources().getDrawable(R.drawable.lobster);
        Drawable clamps     = this.getResources().getDrawable(R.drawable.clamps);
        Drawable guso       = this.getResources().getDrawable(R.drawable.guso);
        Drawable[] images = {shrimp,crabs,fish,squid,lobster,clamps,guso};
      //  int[] images={R.drawable.shrimp,R.drawable.crabs,R.drawable.fish,R.drawable.squid,R.drawable.lobster,R.drawable.lobster,R.drawable.clamps,R.drawable.clamps};
        String[] description = {"Shrimp","Crabs","Fish","Squid","Lobster","Clamps","Guso"};
        String[] price = {"400.00","400.00","350.00","380.00","430.00","400.00","120.00"};
        Category_Adapter adapter = new Category_Adapter(getApplicationContext(), images, description,price,this);
        category_view.setAdapter(adapter);

//           category_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//               @Override
//               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                   Toast.makeText(getApplicationContext(), "ADASDADADAD", Toast.LENGTH_SHORT).show();
//                   System.out.println("sdfsdfsdfsfsdfsdfsdfsdf");
//               }
//           });
//        category_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), "ADASDADADAD", Toast.LENGTH_SHORT).show();
//                System.out.println("sdfsdfsdfsfsdfsdfsdfsdf");
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main_menu, menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_main_setting){
            Toast.makeText(this, "ADASDADADAD", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.menu_main_cart){
            Intent cart = new Intent(this, Cart.class);
            startActivity(cart);
            Toast.makeText(this, "SSFJGFGFDG", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
}
/*
 categoryArrayList.add((new Category("Shrimp",shrimp,"400.00",R.drawable.addtocartred,"0")));
        categoryArrayList.add((new Category("Crabs",crabs,"450.00",R.drawable.addtocartred,"1")));
        categoryArrayList.add((new Category("Fish",fish,"350.00",R.drawable.addtocartred,"2")));
        categoryArrayList.add((new Category("Squid",squid,"400.00",R.drawable.addtocartred,"3")));
        categoryArrayList.add((new Category("Lobster",lobster,"450.00",R.drawable.addtocartred,"4")));
        categoryArrayList.add((new Category("Clamps",clamps,"350.00",R.drawable.addtocartred,"5")));
        categoryArrayList.add((new Category("Guso",guso,"250.00",R.drawable.addtocartred,"6")));
        Category_Array_Adapter category_adapter = new Category_Array_Adapter(getApplicationContext(),categoryArrayList);
        category_view.setAdapter(category_adapter);
 */