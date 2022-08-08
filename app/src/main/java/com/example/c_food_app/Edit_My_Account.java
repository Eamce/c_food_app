package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_My_Account extends AppCompatActivity {
    Globalvars globalvars;
    EditText fname,contact,email,pass;
    String str_name,str_contact,str_email,str_pass;
    Msgbox msgbox;
    Button editbtb;
    SQLiteDatabase sqLiteDatabase;
    Context context =this;
//    AES aes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_account);
        init();
        getGlobalVars();
        field_enable();
        String id = globalvars.get("id");
//        field_disable();
        editbtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fieldIsEmpty()){
                    Toast.makeText(context, "Fill Up Empty Fields!", Toast.LENGTH_SHORT).show();
                }else{
                    String edited_name =  AES.encrypt(Server.key,fname.getText().toString()).toString();
                    String edited_email =  AES.encrypt(Server.key,email.getText().toString()).toString();
                    String edited_phone =  AES.encrypt(Server.key,contact.getText().toString()).toString();
                    String edited_password =  AES.encrypt(Server.key,pass.getText().toString()).toString();
                    ContentValues values = new ContentValues();
                    values.put("email", edited_email);
                    values.put("name", edited_name);
                    values.put("phone", edited_phone);
                    values.put("password", edited_password);

                    sqLiteDatabase.update("user",values,"user_id"+"= ?",new String[]{id});
//                    sqLiteDatabase.update("user",values,"user_id"+ "=?",new String[]{id});
//                    sqLiteDatabase.update("user",values,"user_id"+ "=1",null);
//                    sqLiteDatabase.execSQL("UPDATE user SET email ="+edited_email+", name = "+edited_name+", phone = "+edited_phone+", password = "+edited_password+", WHERE user_id="+id+"");
                    System.out.println("USER ID::"+id);
                    Toast.makeText(context, "Account Updated Successfully", Toast.LENGTH_SHORT).show();
                    globalvars.set("name",edited_name);
                    globalvars.set("phone",edited_phone);
                    globalvars.set("password",edited_password);
                    globalvars.set("email",edited_email);
                    Intent intent = new Intent(Edit_My_Account.this, My_Account.class );
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    public void init(){
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
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

    public boolean fieldIsEmpty(){
        if(fname.getText().toString().isEmpty() ||contact.getText().toString().isEmpty()
                || email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
            return true;
        }else{
            return false;
        }
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
                    Intent logout = new Intent(Edit_My_Account.this, Login.class);
                    startActivity(logout);
                    globalvars.logout();
                }
                @Override
                public void onno() {
                }
            });
        }else if(item.getItemId()==R.id.account){
            Intent intent = new Intent(Edit_My_Account.this, My_Account.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.home){
            Intent intent = new Intent(Edit_My_Account.this, Home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}