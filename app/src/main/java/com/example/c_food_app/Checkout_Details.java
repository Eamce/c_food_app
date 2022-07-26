package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Checkout_Details extends AppCompatActivity {

        Button checkout_btn;
        SQLiteDatabase sqLiteDatabase;
        TextView address;
        List<String> towns;
        Cursor row=null;
        ListView checkout_list;
        String cartId = "";
        String descrption = "";
        String price = "";
        String image = "";
        String quantity = "";
        String total = "";
        String id="";
        Msgbox msgbox;
        float f_price=0;
        float f_quan=0;
        TextView text_total;
        float total_payable = 0;
        float finalTotal_payable=0;
        float float_total = 0;
        Context context = this;
        Checkout checkout;
        Globalvars globalvars;
        ArrayList<Checkout> checkout_Array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_details);
        init();
//            getTown();

//        address.setText("Mandaug \n" +
//                        "Calape \n" +
//                        "Bohol, 6328");

        address.setText(globalvars.get("delivery_address"));
        while (row.moveToNext()) {
            id = row.getString(0);
            cartId = row.getString(1);
            descrption = row.getString(2);
            price = row.getString(3);
            quantity = row.getString(4);
            total = row.getString(5);
            image = row.getString(6);
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            float_total = Float.parseFloat(total);
//            total_payable += float_total;
            f_price=Float.parseFloat(price);
            f_quan=Float.parseFloat(quantity);
            System.out.println("Rowww" + row.getCount());
            System.out.println("total_: " + total);
            System.out.println("total_payable: " + total_payable);
            finalTotal_payable=f_price*f_quan;
            total_payable += finalTotal_payable;
            text_total.setText("Total: " + total_payable);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            checkout_Array.add(new Checkout(id, decodedByte, descrption, price, String.valueOf(finalTotal_payable), quantity));
            Checkout_Adapter checkout_adapter = new Checkout_Adapter(getApplicationContext(), checkout_Array, this);
            checkout_list.setAdapter(checkout_adapter);
            ContentValues cv = new ContentValues();
            cv.put("description",descrption);
            cv.put("price",price);
            cv.put("quantity",quantity);
            cv.put("address",address.getText().toString());
            cv.put("username",globalvars.get("name"));
            cv.put("cat_image",image);
            cv.put("total",total_payable);
            cv.put("user_id",globalvars.get("id"));
            cv.put("status","pending");
            cv.put("contact",globalvars.get("phone"));
            sqLiteDatabase.insert("tbl_order",null,cv);
        }
        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgbox.showyesno( "Hello","Are you sure you want to proceed?");
                msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
                    @Override
                    public void onyes() {
   //                        while (row.moveToNext()) {
//                            id = row.getString(0);
//                            cartId = row.getString(1);
//                            descrption = row.getString(2);
//                            price = row.getString(3);
//                            quantity = row.getString(4);
//                            total = row.getString(5);
//                            image = row.getString(6);
//                            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
//                            float_total = Float.parseFloat(total);
//                            total_payable += float_total;
//                            System.out.println("Rowww" + row.getCount());
//                            System.out.println("total_: " + total);
//                            System.out.println("total_payable: " + total_payable);
//                            text_total.setText("Total: " + total_payable);
//
                            sqLiteDatabase.execSQL("Delete from cart");
                        Intent intent = new Intent(Checkout_Details.this, MyOrder.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onno() {
                    }
                });
            }
        });
    }

    public void init(){
//        fullname = (EditText) findViewById(R.id.fullname);
        address  = (TextView) findViewById(R.id.address);
        checkout_btn = (Button) findViewById(R.id.checkout_btn);
        checkout_list = (ListView) findViewById(R.id.checkout_list);
        text_total = (TextView) findViewById(R.id.text_total);
        checkout_Array=new ArrayList<Checkout>();
        msgbox = new Msgbox(context);
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        row = sqLiteDatabase.rawQuery("select * from cart", null);
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
        } else if(item.getItemId()==R.id.logout){
            msgbox.showyesno( "Hello","Are you sure you want to log out?");
            msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
                @Override
                public void onyes() {
                    Intent logout = new Intent(  Checkout_Details.this, Login.class);
                    startActivity(logout);
                    globalvars.logout();
                }
                @Override
                public void onno() {    
                }
            });
        }else if(item.getItemId()==R.id.account){
            Intent intent = new Intent(Checkout_Details.this, Edit_My_Account.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.home){
            Intent intent = new Intent(Checkout_Details.this, Home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}




