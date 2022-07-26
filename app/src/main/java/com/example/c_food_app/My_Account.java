package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class My_Account extends AppCompatActivity {
    Globalvars globalvars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        globalvars = new Globalvars(getApplicationContext(),this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_main_setting) {
            Toast.makeText(this, "ADASDADADAD", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_main_cart) {
            Intent cart = new Intent(this, My_Account.class);
            startActivity(cart);
            Toast.makeText(this, "SSFJGFGFDG", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.logout){
            Intent logout = new Intent(this, Login.class);
            startActivity(logout);
            globalvars.logout();
        }
        return super.onOptionsItemSelected(item);
    }
}