package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;

public class Admin_Home extends AppCompatActivity {

    TextView view_customer,view_items,view_purchased,view_account;
    Typeface font;
    Msgbox msgbox;
    Context context=this;
    Globalvars globalvars;
    Button view_purchased_btn,view_items_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        init();

        view_purchased_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Home.this, Admin_View_Ordered.class);
                startActivity(intent );
            }
        });

        view_items_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void init(){
        view_customer   = (TextView) findViewById(R.id.view_customer);
        view_items      = (TextView) findViewById(R.id.view_items);
        view_purchased  = (TextView) findViewById(R.id.view_purchased);
        view_account    = (TextView) findViewById(R.id.view_account);
        view_purchased_btn = (Button) findViewById(R.id.view_purchased_btn) ;
        view_items_btn = (Button) findViewById(R.id.view_items_btn) ;
        globalvars = new Globalvars(getApplicationContext(),this);
        msgbox = new Msgbox(context);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent logout = new Intent(Admin_Home.this, Login.class);
            startActivity(logout);
            finish();
        } else if(item.getItemId()==R.id.logout){
            msgbox.showyesno( "Hello","Are you sure you want to log out?");
            msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
                @Override
                public void onyes() {
                    Intent logout = new Intent(Admin_Home.this, Login.class);
                    startActivity(logout);
                    finish();
                    globalvars.logout();
                }
                @Override
                public void onno() {
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}