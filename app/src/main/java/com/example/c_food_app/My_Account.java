package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class My_Account extends AppCompatActivity {

    TextView fname,contact,email,pass;
    Globalvars globalvars;
    Button editbtb;
    String str_name,str_contact,str_email,str_pass;
    Msgbox msgbox;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        init();
        getGlobalVars();
        editbtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_Account.this, Edit_My_Account.class);
                startActivity(intent);
            }
        });
    }
    public void init(){
        globalvars = new Globalvars(getApplicationContext(),this);
        fname = (TextView) findViewById(R.id.fname);
        contact = (TextView) findViewById(R.id.contact);
        email = (TextView) findViewById(R.id.emailadd);
        pass = (TextView) findViewById(R.id.password_r);
        editbtb = (Button) findViewById(R.id.editbtb);
//        msgbox= new Msgbox(context);
    }
    public void getGlobalVars(){
        str_name =AES.decrypt(Server.key,globalvars.get("name")).toString();
        str_contact=AES.decrypt(Server.key,globalvars.get("phone")).toString();
        str_email=AES.decrypt(Server.key,globalvars.get("email")).toString();
        str_pass=AES.decrypt(Server.key,globalvars.get("password")).toString();
        msgbox         = new Msgbox(context);
        fname.setText(str_name);
        contact.setText(str_contact);
        email.setText(str_email);
        pass.setText(str_pass);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_main_setting) {
        } else if(item.getItemId()==R.id.logout){
            msgbox.showyesno( "Hello","Are you sure you want to log out?");
            msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
                @Override
                public void onyes() {
                    Intent logout = new Intent(My_Account.this, Login.class);
                    startActivity(logout);
                    globalvars.logout();
                }
                @Override
                public void onno() {
                }
            });
        }else if(item.getItemId()==R.id.account){
            Intent intent = new Intent(My_Account.this, My_Account.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.home){
            Intent intent = new Intent(My_Account.this, Home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}