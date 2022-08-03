package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class My_Account extends AppCompatActivity {

    TextView fname,contact,email,pass;
    Globalvars globalvars;
    Button editbtb;
    String str_name,str_contact,str_email,str_pass;

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
        fname.setText(str_name);
        contact.setText(str_contact);
        email.setText(str_email);
        pass.setText(str_pass);
    }
}