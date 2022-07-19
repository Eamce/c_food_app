package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
public class
CreateAccount extends AppCompatActivity {
    TextInputEditText name;
    TextInputEditText emailadd;
    TextInputEditText password_r;
    TextInputEditText contact;
    Button registerbtn;
    ProgressDialog pd;
    Ajax ajax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        init();
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(checkFields()){
                        pd.setMessage("Please wait...");
                        pd.show();
                        pd.setCancelable(false);
                        pd.setCanceledOnTouchOutside(false);
                        ajax = new Ajax();
                        ajax.setCustomObjectListener(new Ajax.MyCustomObjectListener() {
                            @Override
                            public void onerror() {
                                pd.dismiss();
                                Toast toast = Toast.makeText(getApplicationContext(), "Unable to connect. Please check your connection.", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 80);
                                toast.show();
                            }
                            @Override
                            public void onsuccess(String data) {
                                //JSONArray data2;
                                pd.dismiss();
                                Toast toast = Toast.makeText(getApplicationContext(),"You have successfully registered.", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL,0,80);
                                toast.show();
                                Intent intent = new Intent(CreateAccount.this, Login.class);
                                startActivity(intent);
                                finishAffinity();
                                finish();
                            }
                        });
                        ajax.adddata("name",AES.encrypt(Server.key,name.getText().toString()).toString());
                        ajax.adddata("password",AES.encrypt(Server.key,password_r.getText().toString()).toString());
                        ajax.adddata("email",AES.encrypt(Server.key,emailadd.getText().toString()).toString());
                        ajax.adddata("phone",AES.encrypt(Server.key,contact.getText().toString()).toString());
                        ajax.execute(Server.address+ "saveUser");
                    }
                }catch (NetworkOnMainThreadException e){
                    e.getMessage();
                }
            }
        });
    }

    public void init(){
        name        = (TextInputEditText) findViewById(R.id.fname);
        password_r  = (TextInputEditText) findViewById(R.id.password_r);
        contact     = (TextInputEditText) findViewById(R.id.contact);
        emailadd    = (TextInputEditText) findViewById(R.id.emailadd);
        registerbtn = (Button) findViewById (R.id.registerbtn);
        pd=new ProgressDialog(this);
    }

    public boolean checkFields(){
        if(name.length()==0 && emailadd.length()==0 && contact.length()==0 && password_r.length()==0 ){
            Toast toast = Toast.makeText(getApplicationContext(),"Empty Fields Required!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL,0,80);
            toast.show();
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
    }
}