package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class My_Account extends AppCompatActivity {
    Globalvars globalvars;
    EditText fname,contact,email,pass;
    String str_name,str_contact,str_email,str_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        init();
        getGlobalVars();
        field_disable();
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
        }else if (item.getItemId() == R.id.menu_main_cart) {
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

    public void init(){
        globalvars = new Globalvars(getApplicationContext(),this);
        fname = (EditText) findViewById(R.id.fname);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.emailadd);
        pass = (EditText) findViewById(R.id.password_r);
    }

    public void getGlobalVars(){
        str_name = globalvars.get("name");
        str_contact=globalvars.get("phone");
        str_email=globalvars.get("email");
        str_pass=globalvars.get("password");
        fname.setText(str_name);
        contact.setText(str_contact);
        email.setText(str_email);
        pass.setText(str_pass);
    }

    public void field_disable(){
        fname.setEnabled(false);
        contact.setEnabled(false);
        email.setEnabled(false);
        pass.setEnabled(false);
    }

    public void field_enable(){
        fname.setEnabled(true);
        contact.setEnabled(true);
        email.setEnabled(true);
        pass.setEnabled(true);
    }
}