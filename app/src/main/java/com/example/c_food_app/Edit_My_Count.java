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
import android.widget.EditText;

public class Edit_My_Count extends AppCompatActivity {
    Globalvars globalvars;
    EditText fname,contact,email,pass;
    String str_name,str_contact,str_email,str_pass;
    Msgbox msgbox;
    Button editbtb;
    Context context =this;
//    AES aes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_account);
        init();
        getGlobalVars();
        field_disable();

        editbtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                field_enable();
            }
        });
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
                    Intent logout = new Intent(Edit_My_Count.this, Login.class);
                    startActivity(logout);
                    globalvars.logout();
                }
                @Override
                public void onno() {
                }
            });
        }else if(item.getItemId()==R.id.account){
            Intent intent = new Intent(Edit_My_Count.this, Edit_My_Count.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void init(){
        globalvars = new Globalvars(getApplicationContext(),this);
        fname = (EditText) findViewById(R.id.fname);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.emailadd);
        pass = (EditText) findViewById(R.id.password_r);
        editbtb = (Button) findViewById(R.id.editbtb);
        msgbox= new Msgbox(context);
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