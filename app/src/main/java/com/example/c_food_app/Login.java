package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    TextView tv_createaccount;
    Button loginbtn;
    Button registerbtn;
    Ajax ajax;
    SQLiteDatabase sqLiteDatabase;
    String encrypted_user,encrypted_pass;
    ProgressDialog pd;
    String id="",name="",email="",e_password="",contact="";
    Globalvars globalvars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
//        username.setText("sample@gmail.com");
//        password.setText("123456");
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString()=="admin" && password.getText().toString()=="admin"){
                    Intent intent = new Intent(Login.this, Admin_Home.class);
                    startActivity(intent);
                    finish();
                }
                encryptData();
                checkUser(encrypted_user,encrypted_pass);
            }
        });
        tv_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(Login.this, CreateAccount.class);
                startActivity(register);
            }
        });
    }
    //=====================================S T A R T  O F  F U N C T I O N S=========================================================
    public void init(){
        username         = (EditText) findViewById(R.id.username);
        password         = (EditText) findViewById(R.id.password);
        tv_createaccount = (TextView) findViewById(R.id.tv_createaccount);
        loginbtn         = (Button)   findViewById(R.id.loginbtn);
        globalvars  = new Globalvars(getApplicationContext(),this);
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        pd = new ProgressDialog(this);
    }
    public void encryptData(){
        encrypted_user= AES.encrypt(Server.key,username.getText().toString()).toString();
        encrypted_pass= AES.encrypt(Server.key,password.getText().toString()).toString();
//        encrypted_user=username.getText().toString();
//        encrypted_pass=password.getText().toString();
    }

    public void checkUser(String user, String pass)
    {
        pd.setMessage("Validating user...Please wait");
        pd.show();
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        Cursor row = sqLiteDatabase.rawQuery("select * from user where email like '%"+user+"%' and password like '%"+pass+"%'", null);
        if(row.getCount()!=0){
            while (row.moveToNext()){
               id = row.getString(0);
               email = row.getString(1);
               name = row.getString(2);
               contact = row.getString(3);
               e_password = row.getString(4);
            }
            globalvars.set("id",id);
            globalvars.set("email",email);
            globalvars.set("name",name);
            globalvars.set("phone",contact);
            globalvars.set("password",e_password);
            Intent login = new Intent(Login.this,Home.class);
            startActivity(login);
            finish();
        }
    }
}

/*
    ajax = new Ajax();
        ajax.setCustomObjectListener(new Ajax.MyCustomObjectListener()
        {
            @Override
            public void onerror()
            {
                pd.dismiss();
                Toast toast = Toast.makeText(getApplicationContext(),"Unable to connect. Please check your connection.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL,0,80);
                toast.show();
            }
            @Override
            public void onsuccess(String data)
            {
                JSONArray thedata;
                System.out.println("ASDSFDFDGFDG DATA: " + data);
                try
                {
                    thedata = new JSONArray(data);
                    //  String id, username, password, fname;
                    if(thedata.length() > 0)
                    {
                        for (int a = 0; a < thedata.length(); a++)
                        {
                            JSONArray row = thedata.getJSONArray(a);
                            //id           = AES.decrypt(Server.key,row.getString(0) ).toString();
                            id           = row.getString(0).trim();
                            name         = AES.decrypt(Server.key,row.getString(1) ).toString();
                            email        = AES.decrypt(Server.key,row.getString(2) ).toString();
                            e_password   = AES.decrypt(Server.key,row.getString(3) ).toString();
                            contact      = AES.decrypt(Server.key,row.getString(4) ).toString();
                        }
                        pd.dismiss();
                        System.out.println("IDDDD"+id);
                        globalvars.set("id",id);
                        globalvars.set("name",name);
                        globalvars.set("email",email);
                        globalvars.set("password",e_password);
                        globalvars.set("phone",contact);
                        Toast toast = Toast.makeText(getApplicationContext(),"Welcome, "+name+"!",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,80);
                        toast.show();
                        Intent goToHome = new Intent(Login.this, Home.class);
                        startActivity(goToHome);
//                        finishAffinity();
                        finish();
                    }
                    else
                    {
                        pd.dismiss();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        ajax.adddata("email",user);
        ajax.adddata("password",pass);
        ajax.execute(Server.address + "validateUser");
 */