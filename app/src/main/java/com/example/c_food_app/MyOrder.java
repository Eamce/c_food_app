package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyOrder extends AppCompatActivity {

    TextView userinfo,totalView;
    ListView order_list;
    SQLiteDatabase sqLiteDatabase;
    Globalvars globalvars;
    Msgbox msgbox;
    Context context= this;
    String id, description,price,quantity,total,username,str_address,cat_image,contact,user_id,status;
    String e_user;
    double final_total=0,double_total=0;
    ArrayList<My_Order_Class> my_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        init();
        Cursor row = sqLiteDatabase.rawQuery("Select * from tbl_order",null);
        while(row.moveToNext()){
            id          = row.getString(0);
            description = row.getString(1);
            price       = row.getString(2);
            quantity    = row.getString(3);
            total       = row.getString(4);
            user_id     = row.getString(5);
            username    = row.getString(6);
            str_address = row.getString(7);
            contact     = row.getString(8);
            status      = row.getString(9);
            cat_image   = row.getString(10);
            byte[] decodedString = Base64.decode(cat_image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            e_user = AES.decrypt(Server.key,username).toString();
            double_total=Double.parseDouble(total);
            String e_contact=AES.decrypt(Server.key,contact).toString();
            userinfo.setText(e_user+"\n"+e_contact+"\n"+str_address);
            final_total+=double_total;
            totalView.setText("Total: "+final_total);
            System.out.println("My Contact"+contact);
            my_order.add(new My_Order_Class(id, description,price,quantity,total,username,str_address,decodedByte,contact));
            My_Order_Adapter my_order_adapter = new My_Order_Adapter (getApplicationContext(),my_order,this);
            order_list.setAdapter(my_order_adapter);
        }
    }

    public void init(){
        String path     = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase  = openOrCreateDatabase(path, MODE_PRIVATE, null);
        globalvars      = new Globalvars(getApplicationContext(),this);
        userinfo        = (TextView) findViewById(R.id.userinfo);
        totalView       = (TextView) findViewById(R.id.total);
        msgbox          = new Msgbox(context);
        order_list      = (ListView) findViewById(R.id.order_list);
        my_order        = new ArrayList<My_Order_Class>();
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
                    Intent logout = new Intent(MyOrder.this, Login.class);
                    startActivity(logout);
                    globalvars.logout();
                }
                @Override
                public void onno() {
                }
            });
        }else if(item.getItemId()==R.id.account){
            Intent intent = new Intent(MyOrder.this, My_Account.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.home){
            Intent intent = new Intent(MyOrder.this, Home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}